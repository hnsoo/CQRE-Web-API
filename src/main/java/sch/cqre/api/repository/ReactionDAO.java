package sch.cqre.api.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.PostHashTagEntity;
import sch.cqre.api.domain.ReactionEntity;

@Repository
@RequiredArgsConstructor
public class ReactionDAO {
    private final ReactionRepository reactionRepository;


    @Transactional
    public int reaction(int userId, int postId, String reaction){

        ReactionEntity reactionForm = new ReactionEntity();
        reactionForm.setUserId(userId);
        reactionForm.setPostId(postId);
        reactionForm.setReaction(reaction);

        return reactionRepository.save(reactionForm).getPostId();


    }

    @Transactional
    public int unReaction(int userId, int postId, String reaction) {

        ReactionEntity reactionForm = reactionRepository.findOneByUserIdAndPostIdAndReaction(userId, postId, reaction);

        reactionRepository.delete(reactionForm);

        return 0;

    }

    @Transactional
    public boolean isVaildReaction(int userId, int postId, String reaction){
    //해당 리액션이 존재하는지 확인하는 메서드
        ReactionEntity reactionForm = reactionRepository.findOneByUserIdAndPostIdAndReaction(userId, postId, reaction);

        return reactionForm != null;

    }



}
