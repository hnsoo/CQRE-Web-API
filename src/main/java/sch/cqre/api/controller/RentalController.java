package sch.cqre.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sch.cqre.api.DTO.RentalDTO;
import sch.cqre.api.domain.BookEntity;
import sch.cqre.api.domain.RentalEntity;
import sch.cqre.api.dto.MyInfoDto;
import sch.cqre.api.service.BookService;
import sch.cqre.api.service.RentalService;
import sch.cqre.api.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/rental")
@Slf4j
@RequiredArgsConstructor
public class RentalController {
    private final RentalService RentalService;
    private final UserService userService;
    private final BookService BookService;

    //도서 대여
    @PostMapping()
    public ResponseEntity toRent(@RequestParam(value = "bookId",required = false, defaultValue = "") Long bookId){
//                                 @RequestParam(value = "userId",required = false, defaultValue = "") Long userId){
        MyInfoDto result = new MyInfoDto(userService.getMyInfo());
        Long userId = result.getUserId();
        RentalService.insertRent(bookId, userId);
        return new ResponseEntity(HttpStatus.OK);
    }

    //도서 대여 bookid, userid, timestamp 확인 / 안쓸듯
//    @PostMapping("/log")
//    public ResponseEntity<List<RentalDTO>> findAll(){
//        return ResponseEntity.ok(RentalService.findAll());
//    }
}
