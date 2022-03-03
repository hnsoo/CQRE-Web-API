package sch.cqre.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sch.cqre.api.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	Integer countByUserId(Integer userId);
	Optional<UserEntity> findByEmail(String email);
}
