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
@Table(name = "File", schema = "main")
public class FileEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "file_id")
	private Long fileId;
	@Basic(optional = false)
	@Column(name = "originalname")
	private String originalname;
	@Basic(optional = false)
	@Column(name = "filename")
	private String filename;
	@Basic(optional = false)
	@Column(name = "filepath")
	private String filepath;
	@Basic(optional = false)
	@Column(name = "size")
	private Integer size;
	@Basic(optional = false)
	@Column(name = "filetype")
	private String filetype;

	@Builder
	public FileEntity(String originalname, String filename, String filepath, Integer size,
		String filetype) {
		this.originalname = originalname;
		this.filename = filename;
		this.filepath = filepath;
		this.size = size;
		this.filetype = filetype;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FileEntity that = (FileEntity)o;
		return fileId == that.fileId && Objects.equals(originalname, that.originalname)
			&& Objects.equals(filename, that.filename) && Objects.equals(filepath, that.filepath)
			&& Objects.equals(size, that.size) && Objects.equals(filetype, that.filetype);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fileId, originalname, filename, filepath, size, filetype);
	}
}
