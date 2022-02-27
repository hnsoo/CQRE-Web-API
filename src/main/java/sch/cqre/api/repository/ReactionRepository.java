package sch.cqre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.cqre.api.domain.ReactionEntity;

@Repository
public interface ReactionRepository extends JpaRepository<ReactionEntity,Long> {

    ReactionEntity findOneByUserIdAndPostIdAndReaction(int user_id, int post_id, String reaction);

}
