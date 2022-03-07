package sch.cqre.api.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sch.cqre.api.domain.PostHashTagEntity;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@AllArgsConstructor
@NoArgsConstructor
public class PostHashTagRepository {

    @Autowired
    private EntityManager em;

    public void save(PostHashTagEntity postHashTagEntity){
        em.persist(postHashTagEntity);
    }



    public PostHashTagEntity findByPostAndHashTag(long postId, long hashtagId){
        return em.createQuery("select m from PostHashTagEntity m where m.postId = :postId and m.hashtagId = :hashtagId", PostHashTagEntity.class)
                .setParameter("postId", postId)
                .setParameter("hashtagId", hashtagId)
                .getSingleResult();
    }

    public void deletePosthashTag(long postId){
       /* Query query = em.createQuery("DELETE FROM PostHashTagEntity AS m WHERE m.postId = :postId", PostHashTagEntity.class);
        query.setParameter("postId", postId);
        query.executeUpdate();*/
        //위에 코드 not work

        List<PostHashTagEntity> lists = em.createQuery("select m from PostHashTagEntity m where m.postId = :postId", PostHashTagEntity.class)
                        .setParameter("postId", postId).getResultList();
        if (!lists.isEmpty())
            for (PostHashTagEntity list : lists) {
                em.remove(list);
            }

    }



}
