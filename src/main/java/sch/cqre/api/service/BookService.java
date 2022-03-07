package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sch.cqre.api.DTO.BookDTO;
import sch.cqre.api.domain.BookEntity;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static sch.cqre.api.exception.ErrorCode.BOOK_NOT_EXIST;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {
    @Autowired
    private final BookRepository Bookdao;
    @Autowired
    private ModelMapper modelMapper;

    //도서 전체 조회 서비스
    public List<BookDTO> findAll(){
        return Bookdao.findAll().stream().map(p -> modelMapper.map(p, BookDTO.class)).collect(Collectors.toList());
    }

    //도서 이름으로 검색 서비스
    public List<BookDTO> findBookByName(String keyword) {
        List<BookEntity> book = Bookdao.findByNameContainingIgnoreCase(keyword);
        if (book.isEmpty()) {
            throw new CustomException(BOOK_NOT_EXIST);
        }
        return book.stream().map(p -> modelMapper.map(p, BookDTO.class)).collect(Collectors.toList());
    }
}
//CUD 주석처리
//    public BookEntity addBook(BookEntity book) {
//        return Bookdao.save(book);
//    }
//    public BookEntity updateBook(BookEntity book) {
//        return Bookdao.save(book);
//    }
//    public void deleteById(Integer bookid) {
//        Bookdao.deleteById(bookid);
//    }
//    public Optional<BookEntity> findById(Integer bookid){
////        return Bookdao.findById(bookid);
////    }