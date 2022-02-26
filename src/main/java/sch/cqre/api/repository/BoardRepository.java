package sch.cqre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.cqre.api.domain.PostEntity;
import sch.cqre.api.domain.UserEntity;

@Repository
public interface BoardRepository extends JpaRepository<PostEntity,Long> {
    PostEntity findOnceByPostId(int postId);
    int countByPostId(int postId);
    void deleteBypostId(int post_id);

}
