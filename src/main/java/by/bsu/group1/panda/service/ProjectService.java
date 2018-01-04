package by.bsu.group1.panda.service;

import by.bsu.group1.panda.model.Project;

import java.util.Collection;

public interface ProjectService {

    Collection<Project> getAllProjects();

    Project getProjectByKey(String key);

    Project getProject(long id);

    Project updateProject(long id, Project project);

    void deleteProject(long id);

    Project createProject(String managerUsername, Project project);
}
