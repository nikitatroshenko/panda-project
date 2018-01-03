package by.bsu.group1.panda.dao;

import by.bsu.group1.panda.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
