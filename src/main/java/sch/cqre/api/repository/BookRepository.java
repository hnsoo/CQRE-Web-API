package sch.cqre.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sch.cqre.api.domain.BookEntity;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    List<BookEntity> findByNameContainingIgnoreCase(String keyword);

}
