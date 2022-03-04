package sch.cqre.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sch.cqre.api.domain.BookEntity;
import sch.cqre.api.service.BookService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
@Slf4j
@RequiredArgsConstructor
public class BookController {
    private final BookService BookService;

    //도서 모두 출력
    @GetMapping
    public ResponseEntity<List<BookEntity>> findAll() {
        return ResponseEntity.ok(BookService.findAll());
    }
//    @GetMapping("/search/")
//    public ResponseEntity<List<BookEntity>> findAllIfNull() {
//        return ResponseEntity.ok(BookService.findAll());
//    }

    //도서 이름 검색
    @PostMapping("/search")
    public ResponseEntity<List<BookEntity>> findByNameContainingIgnoreCase(@RequestParam(value = "keyword",required = false, defaultValue = "") String name) {
        List<BookEntity> book = BookService.findByName(name);
        if (book.isEmpty()) {
            return null;
        }
        return ResponseEntity.ok(book);
    }

    //도서 생성
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody BookEntity book) {
        return ResponseEntity.ok(BookService.addBook(book));
    }

    //도서 아이디 검색
    @GetMapping("/{id}")
    public ResponseEntity<BookEntity> findById(@PathVariable(value = "id") int id) {
        Optional<BookEntity> book = BookService.findById(id);
        if (!book.isPresent()) {
            log.error("Id : " + id + "  not found");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(book.get());
    }

    //test
    @GetMapping("test/{id}")
    public ResponseEntity<Byte> test(@PathVariable(value = "id") int id) {
        Optional<BookEntity> book = BookService.findById(id);
        if (!book.isPresent()) {
            log.error("Id : " + id + "  not found");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(book.get().getAmount());
    }
    //test

    //도서 수정
    @PutMapping("/{id}")
    public ResponseEntity<BookEntity> update(@PathVariable(value = "id") int id, @Valid @RequestBody BookEntity book) {
        if (!BookService.findById(id).isPresent()) {
            log.error("Id : " + id + "  not found");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(BookService.updateBook(book));
    }

    //도서 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") int id) {
        if (!BookService.findById(id).isPresent()) {
            log.error("Id : " + id + "  not found");
            return ResponseEntity.badRequest().build();
        }
        BookService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}





