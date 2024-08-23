// package com.itlize.korera;

// import com.itlize.korera.model.*;
// import com.itlize.korera.repository.*;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import java.time.LocalDateTime;
// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// @SpringBootTest
// class ResourceCascadeTest {

//     @Autowired
//     ProjectRepository projectRepository;

//     @Autowired
//     ResourceRepository resourceRepository;

//     @Autowired
//     ProjectResourceRepository projectResourceRepository;

//     @Autowired
//     ResourceDetailRepository resourceDetailRepository;

//     @Autowired
//     UserRepository userRepository;

//     @BeforeEach
//     void setUp() {
//         // Clean up the repositories before each test
//         projectResourceRepository.deleteAll();
//         resourceDetailRepository.deleteAll();
//         resourceRepository.deleteAll();
//         projectRepository.deleteAll();
//         userRepository.deleteAll();
//     }

//     @Test
//     void testCascadePersist() {
//         // Create User
//         User user = new User();
//         user.setUsername("testuser3");
//         user.setPassword("23455");
//         user.setRole("admin");
//         user.setRegistrationTime(LocalDateTime.now());
//         user.setPictureUrl("http://example.com/picture.jpg");
//         userRepository.save(user);

//         // Create Project
//         Project project = new Project();
//         project.setProjectName("Project 1");
//         project.setProjectCode(101);
//         project.setDescription("Description for Project 1");
//         project.setUser(user);
//         projectRepository.save(project);

//         // Create Resource with ResourceDetail and ProjectResource
//         Resource resource = new Resource();
//         resource.setResourceName("Resource A");
//         resource.setResourceCode(201);
//         resource.setDescription("Description for Resource A");
//         // resource.setCategory("Category A");

//         ResourceDetail resourceDetail = new ResourceDetail();
//         resourceDetail.setDetailName("Detail A");
//         resourceDetail.setDetailValue("Value A");
//         resourceDetail.setResource(resource);
//         resource.setResourceDetail(resourceDetail);

//         ProjectResource projectResource = new ProjectResource();
//         projectResource.setProject(project);
//         projectResource.setResource(resource);
//         resource.setProjectResources(Arrays.asList(projectResource));

//         resourceRepository.save(resource);

//         // Fetch and assert
//         Optional<Resource> fetchedResource = resourceRepository.findById(resource.getResourceId());
//         assert fetchedResource.isPresent();
//         assert fetchedResource.get().getResourceDetail().getDetailName().equals("Detail A");
//         assert fetchedResource.get().getProjectResources().size() == 1;
//     }

//     @Test
//     void testCascadeRemove() {
//         // Create User
//         User user = new User();
//         user.setUsername("testuser");
//         user.setPassword("password");
//         user.setRole("admin");
//         user.setRegistrationTime(LocalDateTime.now());
//         userRepository.save(user);

//         // Create Project
//         Project project = new Project();
//         project.setProjectName("Project 1");
//         project.setProjectCode(101);
//         project.setDescription("Description for Project 1");
//         project.setUser(user);
//         projectRepository.save(project);

//         // Create Resource with ResourceDetail and ProjectResource
//         Resource resource = new Resource();
//         resource.setResourceName("Resource A");
//         resource.setResourceCode(201);
//         resource.setDescription("Description for Resource A");
//         resource.setCategory("Category A");

//         ResourceDetail resourceDetail = new ResourceDetail();
//         resourceDetail.setDetailName("Detail A");
//         resourceDetail.setDetailValue("Value A");
//         resourceDetail.setResource(resource);
//         resource.setResourceDetail(resourceDetail);

//         ProjectResource projectResource = new ProjectResource();
//         projectResource.setProject(project);
//         projectResource.setResource(resource);
//         resource.setProjectResources(Arrays.asList(projectResource));

//         resourceRepository.save(resource);

//         // Delete the resource
//         resourceRepository.delete(resource);

//         // Fetch and assert
//         Optional<Resource> fetchedResource = resourceRepository.findById(resource.getResourceId());
//         assert !fetchedResource.isPresent();

//         Optional<ResourceDetail> fetchedResourceDetail = resourceDetailRepository.findById(resourceDetail.getDetailId());
//         assert !fetchedResourceDetail.isPresent();

//         List<ProjectResource> fetchedProjectResources = projectResourceRepository.findByProject(project);
//         assert fetchedProjectResources.isEmpty();
//     }
// }
