package sch.cqre.api.repository;

import org.springframework.stereotype.Repository;
import sch.cqre.api.domain.PostEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    public void deleteOne(Long postId) { em.remove(findOne(postId)); }






}
