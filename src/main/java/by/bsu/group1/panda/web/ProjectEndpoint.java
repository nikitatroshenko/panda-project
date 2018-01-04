package by.bsu.group1.panda.web;

import by.bsu.group1.panda.dao.ProjectRepository;
import by.bsu.group1.panda.model.Project;
import by.bsu.group1.panda.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.Collection;

@RestController
//@RequestMapping("/projects")
public class ProjectEndpoint {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    public Collection<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @GetMapping("/projects/{id}")
    public Project getProjectById(@PathVariable long id) {
        return projectService.getProject(id);
    }

    @PostMapping("/{username}/projects")
    public ResponseEntity<?> createProject(@PathVariable String username,
                                           @RequestBody Project project) {

        Project created = projectService.createProject(username, project);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/projects/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/projects/{id}")
    public ResponseEntity<?> updateProject(@PathVariable long id, @RequestBody Project project) {
        project = projectService.updateProject(id, project);
        return ResponseEntity.ok(project);
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable long id) {
        projectService.deleteProject(id);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound() {
        // nothing to do
    }

    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
}
