package sch.cqre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.cqre.api.domain.FileEntity;

@Repository
public interface FileRepository extends JpaRepository<FileEntity,Long> {
    FileEntity findOneByfilepathIsContaining(String filepath);

}
