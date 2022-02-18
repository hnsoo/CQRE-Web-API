package sch.cqre.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.cqre.api.domain.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{


}
