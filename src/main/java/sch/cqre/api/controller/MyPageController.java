 package sch.cqre.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import sch.cqre.api.domain.NotificationEntity;
import sch.cqre.api.domain.PostEntity;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.mypage.MyInfoDTO;
import sch.cqre.api.repository.UserRepository;
import sch.cqre.api.service.my.AccountService;
import sch.cqre.api.service.my.NoticeService;
import sch.cqre.api.service.my.PostService;

 /*
  * 마이페이지 컨트롤러
  * 제작자: 김현수
  * 비밀번호 변경 기능 추가 필요
  * */

 @AllArgsConstructor
 @RestController
 @RequestMapping("/api/mypage")
 public class MyPageController {
	 private final AccountService accountService;
	 private final PostService postService;
	 private final NoticeService noticeService;
	 private final UserService userService;

	 // 회원 정보 불러오기
	 @GetMapping
	 public ResponseEntity<UserEntity> getMyInfo() {
		 UserEntity result = this.accountService.searchByEmail(userService.getEmail());
		 return ResponseEntity.ok().body(result);
	 }

	 // 회원 탈퇴
	 // @DeleteMapping
	 // public void withdrawMe(@RequestParam() {
		//  UserEntity result = this.accountService.searchByEmail(userService.getEmail());
	 // }

	 // 내가 쓴 게시글 불러오기
	 @GetMapping("/post")
	 public ResponseEntity getMyPost() {
		 UserEntity userEntity = this.accountService.searchByEmail(userService.getEmail());
		 Integer userId = userEntity.getUserId();
		 List<PostEntity> result = this.postService.searchAllByAuthorId(userId);
		 return ResponseEntity.ok().body(result);
	 }

	 // 스크랩한 포스트 로드
	 @GetMapping("/scrap")
	 public ResponseEntity getMyScrap() {
		 UserEntity userEntity = this.accountService.searchByEmail(userService.getEmail());
		 Integer userId = userEntity.getUserId();
		 List<PostEntity> result = this.postService.searchScrapByUserId(userId);
		 return ResponseEntity.ok().body(result);
	 }

	 // 내 알림 불러오기
	 @GetMapping("/notice")
	 public ResponseEntity getMyNotice() {
		 UserEntity userEntity = this.accountService.searchByEmail(userService.getEmail());
		 Integer userId = userEntity.getUserId();
		 List<NotificationEntity> result = this.noticeService.searchAllByUserId(userId);
		 return ResponseEntity.ok().body(result);
	 }

	 // 알림 하나 읽기
	 @PatchMapping("/notice")
	 public ResponseEntity readOneNotice(@RequestParam(value = "notiId", required = false, defaultValue = "") Integer notiId) {
	 	NotificationEntity result = this.noticeService.checkNotice(notiId);
		 return ResponseEntity.ok().body(result);
	 }

	 // 알림 하나 삭제
	 @DeleteMapping("/notice")
	 public void deleteOneNotice(@RequestParam(value = "notiId", required = false, defaultValue = "") Integer notiId) {
		this.noticeService.deleteOneNotice(notiId);
	 }

	 // 알림 전체 읽기
	 @PatchMapping("/notice/all")
	 public ResponseEntity readAllNotice() {
		 UserEntity userEntity = this.accountService.searchByEmail(userService.getEmail());
		 Integer userId = userEntity.getUserId();
		 List<NotificationEntity> result = this.noticeService.readAllNotice(userId);
		 return ResponseEntity.ok().body(result);
	 }

	 // 읽은 알림 전체 삭제
	 @DeleteMapping("/notice/read")
	 public void deleteReadNotice() {
		 UserEntity userEntity = this.accountService.searchByEmail(userService.getEmail());
		 Integer userId = userEntity.getUserId();
		 this.noticeService.deleteReadNotice(userId);
	 }

	 // 알림 전체 삭제
	 @DeleteMapping("/notice/all")
	 public void deleteAllNotice() {
		 UserEntity userEntity = this.accountService.searchByEmail(userService.getEmail());
		 Integer userId = userEntity.getUserId();
		 this.noticeService.deleteAllNotice(userId);
	 }
 }
