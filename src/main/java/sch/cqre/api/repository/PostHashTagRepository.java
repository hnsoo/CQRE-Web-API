package sch.cqre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.cqre.api.domain.PostHashTagEntity;

@Repository
public interface PostHashTagRepository extends JpaRepository<PostHashTagEntity,Long> {
      PostHashTagEntity findBypostId(int post_id);
      PostHashTagEntity findByPostIdAndHashtagId(int post_id, int hashtag_id);
      int countBypostId(int post_id);

      void deleteBypostId(int post_id);
}
