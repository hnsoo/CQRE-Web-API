package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sch.cqre.api.domain.BookEntity;
import sch.cqre.api.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    @Autowired
    private final BookRepository Bookdao;

    public List<BookEntity> findAll(){
        return Bookdao.findAll();
    }

    public Optional<BookEntity> findById(int bookid){
        return Bookdao.findById(bookid);
    }

    public BookEntity addBook(BookEntity book) {
        return Bookdao.save(book);
    }

    public BookEntity updateBook(BookEntity book) {
        return Bookdao.save(book);
    }

    public void deleteById(int bookid) {
        Bookdao.deleteById(bookid);
    }

    public List<BookEntity> findByName(String keyword) {
        return Bookdao.findByNameContainingIgnoreCase(keyword);
    }

}
