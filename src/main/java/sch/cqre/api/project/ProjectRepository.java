package sch.cqre.api.project;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sch.cqre.api.dto.ProjectRequestDto;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    String UPDATE_PROJECT = "UPDATE Project " +
        "SET ProjectTitle = :#{#projectRequestDto.projectTitle}, " +
        "ProjectContent = :#{#projectRequestDto.projectContent}, " +
        "UPDATE_TIME = NOW() " +
        "WHERE ID = :#{#projectRequestDto.projectId}";

    @Transactional
    @Modifying
    @Query(value = UPDATE_PROJECT, nativeQuery = true)
    public int updateProject(@Param("projectRequestDto")ProjectRequestDto projectRequestDto);

    static final String DELETE_PROJECT = "DELETE FROM Project " + "WHERE ID IN (:deleteList)";

    @Transactional
    @Modifying
    @Query(value = UPDATE_PROJECT, nativeQuery = true)
    public int deleteProject(@Param("deleteList") Long[] deleteList);
}
