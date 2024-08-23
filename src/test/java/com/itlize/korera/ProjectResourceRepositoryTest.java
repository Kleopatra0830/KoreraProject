// package com.itlize.korera;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.junit.jupiter.api.Assertions.*;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.itlize.korera.model.Project;
// import com.itlize.korera.model.ProjectResource;
// import com.itlize.korera.model.Resource;
// import com.itlize.korera.model.ResourceDetail;
// import com.itlize.korera.model.Template;
// import com.itlize.korera.model.User;
// import com.itlize.korera.repository.ProjectRepository;
// import com.itlize.korera.repository.ProjectResourceRepository;
// import com.itlize.korera.repository.ResourceDetailRepository;
// import com.itlize.korera.repository.ResourceRepository;
// import com.itlize.korera.repository.TemplateRepository;
// import com.itlize.korera.repository.UserRepository;

// import java.time.LocalDateTime;
// import java.util.List;
// import java.util.Optional;

// @SpringBootTest
// public class ProjectResourceRepositoryTest {
//     @Autowired
//     private ProjectResourceRepository projectResourceRepository;

//     @Autowired
//     private ProjectRepository projectRepository;

//     @Autowired
//     private ResourceRepository resourceRepository;

//     @Autowired
//     private UserRepository userRepository;

//     @Test
//     public void testSaveAndFindProjectAndResource() {
//         // Create and save a new user
//         User user = new User();
//         user.setUsername("testuser");
//         user.setPassword("1234");
//         user.setRole("admin");
//         user.setRegistrationTime(LocalDateTime.now());
//         user.setPictureUrl("http://example.com/picture.jpg");
//         userRepository.save(user);

//         User user3 = new User();
//         user3.setUsername("user3");
//         user3.setPassword("3456");
//         user3.setRole("user");
//         user3.setRegistrationTime(LocalDateTime.now());
//         userRepository.save(user3);

//         // Find a user by username
//         Optional<User> user2 = userRepository.findByUsername("testuser");
//         user2.ifPresent(u -> System.out.println("Found user: " + u.getUsername()));

        

//         // Create and save a new project
//         Project project = new Project();
//         project.setProjectName("Test Project");
//         project.setProjectCode(1001);
//         project.setDescription("A test project description.");
//         project.setUser(user);
//         projectRepository.save(project);

//         Project projectt = new Project();
//         projectt.setProjectName("Test Project");
//         projectt.setProjectCode(1002);
//         projectt.setDescription("A test project description No 2.");
//         projectt.setUser(user3);
//         projectRepository.save(projectt);

//         Project projectNew = new Project();
//         projectNew.setProjectName("Test Project 3");
//         projectNew.setProjectCode(333);
//         projectNew.setDescription("A test project description for user 3.");
//         projectNew.setUser(user3);
//         projectRepository.save(projectNew);

//         System.out.println("user3.getProjects()"+user3.getProjects());

//         // Find all projects by project name
//         List<Project> projects = projectRepository.findByProjectName("Test Project");
//         projects.forEach(project2 -> System.out.println("Found project: " + project2.getDescription()));

//         // Create and save a new resource
//         Resource resource = new Resource();
//         resource.setResourceName("Test Resource");
//         resource.setResourceCode(1001);
//         resource.setType("Material");
//         resource.setDescription("Test Description");
//         resource.setCategory("Category A");
//         Resource savedResource = resourceRepository.save(resource);

//         assertThat(savedResource).isNotNull();
//         assertThat(savedResource.getResourceId()).isNotNull();
//         assertThat(savedResource.getResourceName()).isEqualTo("Test Resource");
//         assertThat(savedResource.getResourceCode()).isEqualTo(1001);

//         // // Create and save a new ProjectResource
//         // ProjectResource projectResource = new ProjectResource();
//         // projectResource.setProject(project);
//         // projectResource.setResource(resource);
//         // projectResourceRepository.save(projectResource);

//         // // Retrieve the ProjectResource and assert
//         // Optional<ProjectResource> foundProjectResource = projectResourceRepository.findById(projectResource.getPrId());
//         // assertTrue(foundProjectResource.isPresent());
//         // assertEquals("Test Project", foundProjectResource.get().getProject().getProjectName());
//         // assertEquals("Test Resource", foundProjectResource.get().getResource().getResourceName());
//     }

//     @Test
//     void testSaveAndFindProjectResource() {
//         // Create and save a new user
//         User user = new User();
//         user.setUsername("testuser2");
//         user.setPassword("1234");
//         user.setRole("user");
//         user.setRegistrationTime(LocalDateTime.now());
//         user.setPictureUrl("http://example.com/picture.jpg");
//         userRepository.save(user);

//         // Create Project
//         Project project1 = new Project();
//         project1.setProjectName("Project 1");
//         project1.setProjectCode(101);
//         project1.setDescription("Description for Project 1");
//         project1.setUser(user);

//         Project project2 = new Project();
//         project2.setProjectName("Project 2");
//         project2.setProjectCode(102);
//         project2.setDescription("Description for Project 2");
//         project2.setUser(user);

//         projectRepository.save(project1);
//         projectRepository.save(project2);

//         // Create Resources
//         Resource resourceA = new Resource();
//         resourceA.setResourceName("Resource A");
//         resourceA.setResourceCode(201);
//         resourceA.setDescription("Description for Resource A");

//         Resource resourceB = new Resource();
//         resourceB.setResourceName("Resource B");
//         resourceB.setResourceCode(202);
//         resourceB.setDescription("Description for Resource B");

//         Resource resourceC = new Resource();
//         resourceC.setResourceName("Resource C");
//         resourceC.setResourceCode(203);
//         resourceC.setDescription("Description for Resource C");

//         resourceRepository.save(resourceA);
//         resourceRepository.save(resourceB);
//         resourceRepository.save(resourceC);

//         // Create ProjectResource (linking Project 1 with Resources A and B)
//         ProjectResource pr1 = new ProjectResource();
//         pr1.setProject(project1);
//         pr1.setResource(resourceA);

//         ProjectResource pr2 = new ProjectResource();
//         pr2.setProject(project1);
//         pr2.setResource(resourceB);

//         projectResourceRepository.save(pr1);
//         projectResourceRepository.save(pr2);

//         // Create ProjectResource (linking Project 2 with Resources A and C)
//         ProjectResource pr3 = new ProjectResource();
//         pr3.setProject(project2);
//         pr3.setResource(resourceA);

//         ProjectResource pr4 = new ProjectResource();
//         pr4.setProject(project2);
//         pr4.setResource(resourceC);

//         projectResourceRepository.save(pr3);
//         projectResourceRepository.save(pr4);

//         // Fetch and Assert
//         List<ProjectResource> project1Resources = projectResourceRepository.findByProject(project1);
//         assert project1Resources.size() == 2;

//         List<ProjectResource> project2Resources = projectResourceRepository.findByProject(project2);
//         assert project2Resources.size() == 2;
//     }
// }
