package sch.cqre.api.domain;

import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "ProjectLink", schema = "main")
public class ProjectLinkEntity {
	@Id
	@Column(name = "project_id")
	private Integer projectId;
	@Basic(optional = false)
	@Column(name = "link")
	private String link;

	@Builder
	public ProjectLinkEntity(Integer projectId, String link) {
		this.projectId = projectId;
		this.link = link;
	}

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
