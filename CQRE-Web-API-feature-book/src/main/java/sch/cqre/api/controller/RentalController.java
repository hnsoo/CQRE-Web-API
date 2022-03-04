package sch.cqre.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sch.cqre.api.domain.BookEntity;
import sch.cqre.api.domain.RentalEntity;
import sch.cqre.api.service.BookService;
import sch.cqre.api.service.RentalService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
@Slf4j
@RequiredArgsConstructor
public class RentalController {
    private final RentalService RentalService;
    private final BookService BookService;

    //도서 대여
    @PostMapping("/rental")
    public ResponseEntity toRent(@RequestParam(value = "bookId",required = false, defaultValue = "") int bookId,
                                 @RequestParam(value = "userId",required = false, defaultValue = "") int userId){
        //빌릴 도서가 존재하는지, 남아있는 수량이 1개 이상인지 확인
        Optional<BookEntity> book = BookService.findById(bookId);
        if (!book.isPresent() || book.get().getRemainAmount() <= 0) {
            log.error("error");
            return ResponseEntity.badRequest().build();
        }
        //도서 bookId, userId 값 설정
        RentalEntity rental = new RentalEntity();
        rental.setBookId(bookId);
        rental.setUserId(userId);

        RentalService.insertRent(rental);

        //reaminAmount -1
        book.get().setRemainAmount((byte) (book.get().getRemainAmount()-1));
        return ResponseEntity.ok(BookService.updateBook(book.get()));
    }


    //도서 대여 bookid, userid, timestamp 확인
    @PostMapping("/rental/log")
    public ResponseEntity<List<RentalEntity>> findAll(){
        return ResponseEntity.ok(RentalService.findAll());
    }
}
