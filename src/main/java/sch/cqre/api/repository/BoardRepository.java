package sch.cqre.api.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import sch.cqre.api.domain.PostEntity;
import sch.cqre.api.domain.UserEntity;

@Repository
public class BoardRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(PostEntity postEntity){
        em.persist(postEntity);
    }

    public PostEntity findOne(Long postId){
        return em.find(PostEntity.class, postId);
    }


   // public List<PostEntity> findList(UserEntity post){
    //    return (List<PostEntity>)em.find(PostEntity.class, post);
   // }

    public void deleteOne(Long postId) { em.remove(findOne(postId)); }





   public List<PostEntity> findPostListByUserId(UserEntity user){
        try {
            return em.createQuery("select m from PostEntity m where m.user = :user", PostEntity.class)
                .setParameter("user", user).getResultList();
        } catch (Exception e){
            return null;
        }
    }






}
