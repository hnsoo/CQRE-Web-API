package sch.cqre.api.dto.project;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sch.cqre.api.domain.ProjectMemberEntity;

@Getter
@NoArgsConstructor
public class ProjectCreateRequestDto {
    private String memberType;

    @Builder
    public ProjectCreateRequestDto(String memberType) {
        this.memberType = memberType;
    }

    public ProjectMemberEntity toEntity(){
        return ProjectMemberEntity.builder()
            .memberType(memberType)
            .build();
    }
}
