package sch.cqre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.cqre.api.domain.FileEntity;
import sch.cqre.api.domain.PostHashTagEntity;
import sch.cqre.api.domain.PostHashTagEntityPK;

@Repository
public interface PostHashTagRepository extends JpaRepository<PostHashTagEntity,Long> {
  //  int countByPost(String email);
}
