package sch.cqre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.cqre.api.domain.FileEntity;
import sch.cqre.api.domain.HashTagEntity;

@Repository
public interface HashTagRepository extends JpaRepository<HashTagEntity,Long> {
    int countByName(String name);
    HashTagEntity findOnceByName(String name);
}
