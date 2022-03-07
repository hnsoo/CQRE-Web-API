package sch.cqre.api.repository;

import org.springframework.stereotype.Repository;
import sch.cqre.api.domain.HashTagEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class HashTagRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(HashTagEntity hashTagEntity){
        em.persist(hashTagEntity);
    }

    public HashTagEntity findid(String name){
        return em.find(HashTagEntity.class, name);
    }

    public HashTagEntity findName(String name){
        try {
            return em.createQuery("select m from HashTagEntity m where m.name = :name", HashTagEntity.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e){
            return null;
        }
    }


}
