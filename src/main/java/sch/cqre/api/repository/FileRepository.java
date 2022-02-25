package sch.cqre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sch.cqre.api.domain.FileEntity;
import sch.cqre.api.domain.PostEntity;

public interface FileRepository extends JpaRepository<FileEntity,Long> {

}
