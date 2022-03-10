package sch.cqre.api.repository;

import org.springframework.stereotype.Repository;
import sch.cqre.api.domain.ReactionEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ReactionRepository {
    
    @PersistenceContext
    private EntityManager em;

    public void save(ReactionEntity reactionEntity){
        em.persist(reactionEntity);
    }

    public ReactionEntity findOne(Long postId){
        return em.find(ReactionEntity.class, postId);
    }

    public void deleteOne(Long postId) { em.remove(findOne(postId)); }


    public ReactionEntity findReaction(long userId, long postId){
        try {
            return em.createQuery("select m from ReactionEntity m where m.userId = :userId And m.postId = :postId", ReactionEntity.class)
                    .setParameter("userId", userId)
                    .setParameter("postId", postId)
                    .getSingleResult();
        } catch (Exception e){
            return null;
        }
    }

    public int countReaction(long postId, String reaction){
        try {
            return em.createQuery("select m from ReactionEntity m where m.postId = :postId And m.reaction = :reaction", ReactionEntity.class)
                    .setParameter("postId", postId)
                    .setParameter("reaction", reaction)
                    .getResultList().size();
        } catch (Exception e){
            return 0;
        }
    }


    public void delete(ReactionEntity reactionEntity) { em.remove(reactionEntity); }

}
