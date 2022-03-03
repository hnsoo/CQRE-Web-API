package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sch.cqre.api.domain.BookEntity;
import sch.cqre.api.repository.BookRepository;

import javax.transaction.Transactional;
import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    @Autowired
    private final BookRepository dao;

    public List<BookEntity> findAll(){
        return dao.findAll();
    }

    public Optional<BookEntity> findById(Long bookid){
        return dao.findById(bookid);
    }

    public BookEntity addBook(BookEntity book) {
        return dao.save(book);
    }

    public BookEntity updateBook(BookEntity book) {
        return dao.save(book);
    }

    public void deleteById(Long bookid) {
        dao.deleteById(bookid);
    }

    public List<BookEntity> findByName(String keyword) {
        return dao.findByNameContainingIgnoreCase(keyword);
    }

}
