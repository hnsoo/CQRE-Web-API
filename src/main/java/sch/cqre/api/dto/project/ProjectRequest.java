package sch.cqre.api.dto.project;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class ProjectRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private Long memberId;
    private String memberType;
    private String projectTitle;
    private String projectContent;
    private LocalDateTime startedAt;

    public ProjectRequest(Long projectId, Long memberId, String memberType, String projectTitle, String projectContent, LocalDateTime startedAt){
        this.projectId = getProjectId();
        this.memberId = getMemberId();
        this.memberType = getMemberType();
        this.projectTitle = getProjectTitle();
        this.projectContent = getProjectContent();
        this.startedAt = getStartedAt();
    }

}