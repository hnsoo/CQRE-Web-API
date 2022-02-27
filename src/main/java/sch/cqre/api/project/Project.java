package sch.cqre.api.project;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sch.cqre.api.entity.BaseTimeEntity;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
@Entity
public class Project extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    private String projectTitle;
    private String projectContent;

    @Builder
    public Project(Long projectId, String projectTitle, String projectContent, String registerId){
        this.projectId = projectId;
        this.projectTitle = projectTitle;
        this.projectContent = projectContent;
    }
}
