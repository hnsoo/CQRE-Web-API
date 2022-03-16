package sch.cqre.api.controller;

import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sch.cqre.api.dto.CommentDto;
import sch.cqre.api.service.BoardService;

@RestController
@RequestMapping("/api/v1/comment/*")
@RequiredArgsConstructor
@Slf4j
public class CommentV1Controller {

    private final BoardService boardService;

    //view
    @GetMapping("/{commentUID}")
    public ResponseEntity viewPostMap(@PathVariable @Validated @NotNull int commentUID) {
        return ResponseEntity.ok("ASD");
    }

    @PostMapping("/write")
    public ResponseEntity commentWriteMap(@RequestBody @Validated CommentDto.CommentWriteRequest commentWriteRequest) {
        log.info("CommentController - write called");
        return ResponseEntity.ok(boardService.commentWriteProc(commentWriteRequest));
    }

    @PutMapping("/modify")
    public ResponseEntity commentModifyMap(@RequestBody @Validated CommentDto.CommentModifyRequest commentModifyRequest) {
        log.info("CommentController - modify called");
        return ResponseEntity.ok(boardService.commentModifyProc(commentModifyRequest));
    }



}