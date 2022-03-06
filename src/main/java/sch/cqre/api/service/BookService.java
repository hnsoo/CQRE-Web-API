package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sch.cqre.api.DTO.BookDTO;
import sch.cqre.api.domain.BookEntity;
import sch.cqre.api.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    @Autowired
    private final BookRepository Bookdao;
    @Autowired
    private ModelMapper modelMapper;

    public List<BookDTO> findAll(){
        return Bookdao.findAll().stream().map(p -> modelMapper.map(p, BookDTO.class)).collect(Collectors.toList());
    }

    public Optional<BookEntity> findById(Integer bookid){
        return Bookdao.findById(bookid);
    }

    public BookEntity addBook(BookEntity book) {
        return Bookdao.save(book);
    }

    public BookEntity updateBook(BookEntity book) {
        return Bookdao.save(book);
    }

    public void deleteById(Integer bookid) {
        Bookdao.deleteById(bookid);
    }

    public List<BookDTO> findByName(String keyword) {
        List<BookEntity> book = Bookdao.findByNameContainingIgnoreCase(keyword);
        if (book.isEmpty()) {
            return null;
        }
        return book.stream().map(p -> modelMapper.map(p, BookDTO.class)).collect(Collectors.toList());
    }

}
