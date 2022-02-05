package sch.cqre.api.model.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ProjectMember", schema = "main")
public class ProjectMemberEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "project_id")
	private int projectId;
	@Basic
	@Column(name = "member_id")
	private int memberId;
	@Basic
	@Column(name = "member_type")
	private String memberType;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
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
