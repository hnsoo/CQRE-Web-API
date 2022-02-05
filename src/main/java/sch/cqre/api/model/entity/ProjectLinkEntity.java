package sch.cqre.api.model.entity;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "ProjectLink", schema = "main")
public class ProjectLinkEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "project_id")
	private int projectId;
	@Basic
	@Column(name = "link")
	private String link;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ProjectLinkEntity that = (ProjectLinkEntity)o;
		return projectId == that.projectId && Objects.equals(link, that.link);
	}

	@Override
	public int hashCode() {
		return Objects.hash(projectId, link);
	}
}
