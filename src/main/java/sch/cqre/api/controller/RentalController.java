package sch.cqre.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sch.cqre.api.DTO.RentalDTO;
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
    public ResponseEntity toRent(@RequestParam(value = "bookId",required = false, defaultValue = "") Integer bookId,
                                 @RequestParam(value = "userId",required = false, defaultValue = "") Integer userId){
        RentalService.insertRent(bookId, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    //도서 대여 bookid, userid, timestamp 확인
    @PostMapping("/rental/log")
    public ResponseEntity<List<RentalDTO>> findAll(){
        return ResponseEntity.ok(RentalService.findAll());
    }
}
