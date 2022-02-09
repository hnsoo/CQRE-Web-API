package sch.cqre.api.model.entity;

import java.sql.Timestamp;
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
@Table(name = "Project", schema = "main")
public class ProjectEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "project_id")
	private Long projectId;
	@Basic
	@Column(name = "project_title")
	private String projectTitle;
	@Basic
	@Column(name = "project_content")
	private String projectContent;
	@Basic
	@Column(name = "started_at")
	private Timestamp startedAt;
	@Basic
	@Column(name = "finished_at")
	private Timestamp finishedAt;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ProjectEntity that = (ProjectEntity)o;
		return projectId == that.projectId && Objects.equals(projectTitle, that.projectTitle)
			&& Objects.equals(projectContent, that.projectContent) && Objects.equals(startedAt,
			that.startedAt) && Objects.equals(finishedAt, that.finishedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(projectId, projectTitle, projectContent, startedAt, finishedAt);
	}
}
