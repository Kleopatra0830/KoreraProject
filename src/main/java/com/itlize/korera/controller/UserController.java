package com.itlize.korera.controller;

import com.itlize.korera.model.Project;
import com.itlize.korera.model.User;
import com.itlize.korera.repository.ProjectRepository;
import com.itlize.korera.service.JwtTokenService;
import com.itlize.korera.service.UserService;
import com.itlize.korera.service.impl.ProjectServiceImpl;

// import javafx.beans.binding.When;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private JwtTokenService jwtTokenService;

    // Create a new User -  this handles user registration
    // POST /api/users
    // This endpoint allows you to create a new user by sending a JSON object representing the user in the request body.

    // Before creating or updating a user, we should validate the email format and ensure it isn't already in use by another user.
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        // Validate email format
        if (!user.getUsername().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return new ResponseEntity<>("Invalid email format", HttpStatus.BAD_REQUEST);
        }

        // Check if email is already in use
        Optional<User> existingUser = userService.getUserByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            return new ResponseEntity<>("Email is already in use", HttpStatus.CONFLICT);
        }

        // Password Strength Validation
        ResponseEntity<String> passwordValidationResponse = validatePassword(user.getPassword());
        if (passwordValidationResponse != null) {
            return passwordValidationResponse;
        }

        try {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // User Authentication (Login)
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, Object> loginRequest) {
        String username = (String) loginRequest.get("username");
        String password = (String) loginRequest.get("password");
        boolean rememberMe = (boolean) loginRequest.getOrDefault("rememberMe", false);

        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent() && userService.checkPassword(user.get(), password)) {
            // Generate JWT token or session and return
            // returning a session token (or JWT) if valid
            String token = jwtTokenService.generateToken(user.get(), rememberMe);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or password", HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * In a stateless REST API, logout typically involves the client deleting the JWT (or session token) it has stored, 
     * which effectively logs the user out. 
     * However, if we want to invalidate the token server-side, we can implement a token blacklist or 
     * simply instruct the client to discard the token.
     * @param request
     * NOT TESTED
     */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        // Invalidate the session if it exists (this is more applicable to session-based auth)
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // we can remove the JWT from a token blacklist.
        return new ResponseEntity<>("Logged out successfully", HttpStatus.OK);
    }

    // Get a User by username
    @GetMapping("/read/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // test to see if endpoint is being reached
    // GET /api/users/{username}
    @GetMapping("/hello")
    public ResponseEntity<?> helloWorld() {
        return new ResponseEntity<>("hello,world", HttpStatus.OK);
    }

    // generate a reset token and store it in the user's record in the database. we can later implement sending the token via email
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        Optional<User> user = userService.getUserByUsername(email);

        if (user.isPresent()) {
            // Generate a reset token (e.g., a UUID)
            String resetToken = UUID.randomUUID().toString();

            // Save the token in the user's record
            User existingUser = user.get();
            existingUser.setResetToken(resetToken);
            existingUser.setResetTokenExpiry(LocalDateTime.now().plusHours(1)); // Token valid for 1 hour
            userService.updateUser(existingUser.getUserId(), existingUser);

            // Return the reset token as a response (in a real-world application, you wouldn't do this)
            return new ResponseEntity<>("Reset token: " + resetToken, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Email not found", HttpStatus.NOT_FOUND);
        }
    }
    // Updating the User in the Database
    // When the reset token and expiry are set, we can update the user in the database as usual:
    // User updatedUser = userService.updateUser(existingUser.getUserId(), existingUser);


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        String newPassword = request.get("newPassword");

        Optional<User> user = userService.getUserByResetToken(token);

        if (user.isPresent()) {
            User existingUser = user.get();
            if (existingUser.getResetTokenExpiry().isAfter(LocalDateTime.now())) {
                // Validate new password
                ResponseEntity<String> passwordValidationResponse = validatePassword(newPassword);
                if (passwordValidationResponse != null) {
                    return passwordValidationResponse;
                }
                
                // Token is valid, update the password
                existingUser.setPassword(newPassword);
                existingUser.setResetToken(null); // Invalidate the token
                existingUser.setResetTokenExpiry(null);
                userService.updateUser(existingUser.getUserId(), existingUser);
                return new ResponseEntity<>("Password has been reset", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Reset token has expired", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Invalid reset token", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody Map<String, String> passwordRequest) {
        String username = passwordRequest.get("username");
        String oldPassword = passwordRequest.get("oldPassword");
        String newPassword = passwordRequest.get("newPassword");
    
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent() && userService.checkPassword(user.get(), oldPassword)) {
            // Validate new password
            ResponseEntity<String> passwordValidationResponse = validatePassword(newPassword);
            if (passwordValidationResponse != null) {
                return passwordValidationResponse;
            }
            user.get().setPassword(newPassword);
            userService.updateUser(user.get().getUserId(), user.get());
            return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid username or old password", HttpStatus.UNAUTHORIZED);
        }
    }

    // Update an existing User 
    // This is a more general method
    // update by password only, should we update its corresponding projects?
    // we know one user can have multiple projects, but should we only update and controll its relationship from project side?
    @PutMapping("/update/{username}")
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody Map<String, Object> updates) {
        Optional<User> existingUser = userService.getUserByUsername(username);
        if (existingUser.isPresent()) {
            User currentUser = existingUser.get();

            // Check each field and update if present in the request body
            if (updates.containsKey("password")) {
                String newPassword = (String) updates.get("password");
                ResponseEntity<String> passwordValidationResponse = validatePassword(newPassword);
                if (passwordValidationResponse != null) {
                    return passwordValidationResponse; // Return error if the password is invalid
                }
                currentUser.setPassword(newPassword);
            }
            if (updates.containsKey("role")) {
                currentUser.setRole((String) updates.get("role"));
            }
            if (updates.containsKey("pictureUrl")) {
                currentUser.setPictureUrl((String) updates.get("pictureUrl"));
            }

            // Handle the projects update if present in the request body
            if (updates.containsKey("projects")) {
                List<Map<String, Object>> updatedProjects = (List<Map<String, Object>>) updates.get("projects");

                for (Map<String, Object> updatedProject : updatedProjects) {
                    Long projectId = updatedProject.containsKey("projectId")
                            ? ((Number) updatedProject.get("projectId")).longValue()
                            : null;

                    Project project;

                    // If projectId is provided, check if the project exists in the system
                    if (projectId != null) {
                        project = projectRepository.findById(projectId)
                                .orElse(new Project()); // Find the project by ID or create a new one if not found
                        // Check if the project is associated with another user

                        // HERE even if we provide a nonexistent projectID, the system will automatically generate a new ID
                        currentUser.getProjects().add(project);
                        project.setUser(currentUser);
                    } else {
                        // If no projectId is provided, create a new project
                        project = new Project();
                        project.setUser(currentUser);
                        currentUser.getProjects().add(project);
                    }

                    // Update project details
                    if (updatedProject.containsKey("projectName")) {
                        project.setProjectName((String) updatedProject.get("projectName"));
                    }
                    if (updatedProject.containsKey("projectCode")) {
                        project.setProjectCode((Integer) updatedProject.get("projectCode"));
                    }
                    if (updatedProject.containsKey("description")) {
                        project.setDescription((String) updatedProject.get("description"));
                    }

                    // Add the project to the user's project list if it's new
                    if (project.getProjectId() == null) {
                        currentUser.getProjects().add(project);
                    }
                }
            }

            // Save the updated user and projects
            User updatedUser = userService.updateUser(currentUser.getUserId(), currentUser);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    // Delete a User
    // If the user is successfully deleted, the status should be 204 NO CONTENT.
    // If the user is not found, the status should be 404 NOT FOUND.
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        Optional<User> existingUser = userService.getUserByUsername(username);
        if (existingUser.isPresent()) {
            userService.deleteUser(existingUser.get().getUserId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    // Retrieve all users
    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    // each should have distinct api url

    private ResponseEntity<String> validatePassword(String password) {
        if (password.length() < 8) {
            return new ResponseEntity<>("Password must be at least 8 characters long.", HttpStatus.BAD_REQUEST);
        }
        if (!password.matches(".*[A-Z].*")) {
            return new ResponseEntity<>("Password must contain at least one uppercase letter.", HttpStatus.BAD_REQUEST);
        }
        if (!password.matches(".*\\d.*")) {
            return new ResponseEntity<>("Password must contain at least one number.", HttpStatus.BAD_REQUEST);
        }
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            return new ResponseEntity<>("Password must contain at least one special character.", HttpStatus.BAD_REQUEST);
        }
        return null; // Indicate that the password is valid
    }
    
    // LOGOUT?
}
