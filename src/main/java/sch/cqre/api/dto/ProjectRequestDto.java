package sch.cqre.api.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sch.cqre.api.project.Project;

@Getter
@Setter
@NoArgsConstructor
public class ProjectRequestDto {
    private Long projectId;
    private String projectTitle;
    private String projectContent;

    public Project toEntity(){
        return Project.builder()
            .projectTitle(projectTitle)
            .projectContent(projectContent)
            .build();
    }
}
