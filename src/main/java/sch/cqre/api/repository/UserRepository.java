package sch.cqre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sch.cqre.api.domain.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{

    //boolean existsByStudentId(long studentId);
    int countByEmail(String email);
    int countByStudentId(int studentId);
    int countByNickname(String nickname);
    UserEntity findOnceByEmail(String email);
    //boolean existsByNickname(String nickname);

}
