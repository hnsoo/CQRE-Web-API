package sch.cqre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sch.cqre.api.domain.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	UserEntity findByEmail(String email);
}
