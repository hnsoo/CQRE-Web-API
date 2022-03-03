package sch.cqre.api.dto.project;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProjectEditRequestDto {
    private String projectTitle;
    private String projectContent;

    @Builder
    public ProjectEditRequestDto(String projectTitle, String projectContent){
        this.projectTitle = projectTitle;
        this.projectContent = projectContent;
    }
}
