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
@Table(name = "File", schema = "main")
public class FileEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "file_id")
	private int fileId;
	@Basic
	@Column(name = "originalname")
	private String originalname;
	@Basic
	@Column(name = "filename")
	private String filename;
	@Basic
	@Column(name = "filepath")
	private String filepath;
	@Basic
	@Column(name = "size")
	private Integer size;
	@Basic
	@Column(name = "filetype")
	private String filetype;

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
