package sch.cqre.api.domain;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ProjectMember", schema = "main")
public class ProjectMemberEntity {
	@Id
	@Column(name = "project_id")
	private Integer projectId;
	@Basic(optional = false)
	@Column(name = "member_id")
	private Integer memberId;
	@Basic(optional = false)
	@Column(name = "member_type")
	private String memberType;

	@Builder
	public ProjectMemberEntity(Integer projectId, Integer memberId, String memberType) {
		this.projectId = projectId;
		this.memberId = memberId;
		this.memberType = memberType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ProjectMemberEntity that = (ProjectMemberEntity)o;
		return projectId == that.projectId && memberId == that.memberId && Objects.equals(memberType,
			that.memberType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(projectId, memberId, memberType);
	}
}
