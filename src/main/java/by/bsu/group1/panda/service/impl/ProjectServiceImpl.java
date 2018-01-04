package by.bsu.group1.panda.service.impl;

import by.bsu.group1.panda.dao.ProjectRepository;
import by.bsu.group1.panda.model.Project;
import by.bsu.group1.panda.model.User;
import by.bsu.group1.panda.service.ProjectService;
import by.bsu.group1.panda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserService userService;

    @Override
    public Collection<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectByKey(String key) {
        return projectRepository.findProjectByProjectKey(key);
    }

    @Override
    public Project getProject(long id) {
        return projectRepository.getOne(id);
    }

    @Override
    public Project updateProject(long id, Project project) {
        Project persistent = projectRepository.getOne(id);

        if (project.getDescription() != null) {
            persistent.setDescription(project.getDescription());
        }
        return projectRepository.save(project);
    }

    @Override
    public void deleteProject(long id) {
        projectRepository.delete(id);
    }

    @Override
    public Project createProject(String managerUsername, Project project) {
        Project created = new Project();
        User manager = userService.getUserByUsername(managerUsername);

        created.setProjectKey(project.getProjectKey());
        created.setDescription(project.getDescription());
        created.setManager(manager);
        return projectRepository.save(created);
    }

    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
}
