package by.bsu.group1.panda.dao;

import by.bsu.group1.panda.model.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {
}
