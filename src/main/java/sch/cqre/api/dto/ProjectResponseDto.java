package sch.cqre.api.dto;

import java.sql.Timestamp;

import lombok.Getter;
import sch.cqre.api.project.Project;

@Getter
public class ProjectResponseDto {
    private Long projectId;
    private String projectTitle;
    private String projectContent;
    private Timestamp startedAt;
    private Timestamp finishedAt;

    public ProjectResponseDto(Project entity){
        this.projectId = entity.getProjectId();
        this.projectTitle = entity.getProjectTitle();
        this.projectContent = entity.getProjectContent();
        this.startedAt = Timestamp.valueOf(entity.getStartedAt());
        this.finishedAt = Timestamp.valueOf(entity.getFinishedAt());
    }

    @Override
    public String toString(){
        return "ProjectListDto [projectId=" + projectId + ", projectTitle=" + projectTitle + ",projectContent=" + projectContent + ",startedAt=" + startedAt + ",finishedAt=" + finishedAt + "]";
    }
}
