package sch.cqre.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sch.cqre.api.dto.BoardDto;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.exception.ErrorCode;
import sch.cqre.api.service.BoardService;

@RestController
@RequestMapping("/api/v1/post/*")
@RequiredArgsConstructor
@Slf4j
public class PostV1Controller {

    private final BoardService boardService;

    //view
    @GetMapping("/{boardUID}")
    public ResponseEntity viewPostMap(@PathVariable @Validated @NotNull long boardUID) {
        // Load file as Resource
        return ResponseEntity.ok(boardService.viewProc(boardUID));

    }

    @PostMapping("/write")
    public ResponseEntity writeMap(@RequestBody @Validated BoardDto.WritePostRequest writePostRequest) {
        return ResponseEntity.ok(boardService.writeProc(writePostRequest));
    }

    @DeleteMapping("/delete/{boardUID}")
    public ResponseEntity deleteMap(@PathVariable @Validated @NotNull long boardUID){
        if (boardUID <= 0)
            throw new CustomException(ErrorCode.INVALID_INPUT);

        boardService.deleteProc(boardUID);
        return ResponseEntity.ok("");
    }


    @PutMapping("/modify") // 수정
    public ResponseEntity modifyMap(@RequestBody @Validated BoardDto.ModifyPostRequest modifyPostRequest, HttpServletRequest request){
        return ResponseEntity.ok(boardService.modifyProc(modifyPostRequest));
    }



    @PostMapping("/reaction")
    public ResponseEntity reactionMap(@RequestBody @Validated BoardDto.ReactionRequest reactionRequest) {

        boardService.reactionProc(reactionRequest.getPostId(), reactionRequest.getReaction());
        return ResponseEntity.ok("");
    }

    @DeleteMapping("/unreaction/{boardUID}")
    public ResponseEntity unReactionMap(@PathVariable @Validated @NotNull Long boardUID) {
        if (boardUID <= 0)
            throw new CustomException(ErrorCode.INVALID_INPUT);

        boardService.unReactionProc(boardUID);
        return ResponseEntity.ok("");
    }



}