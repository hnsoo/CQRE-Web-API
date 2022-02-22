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
	 private final UserRepository userRepo;

	 // 회원 정보 불러오기
	 @GetMapping
	 public ResponseEntity<MyInfoDTO> getMyInfo(@RequestParam(value = "userId", required = false, defaultValue = "") Integer userId) {
		 UserEntity result = this.accountService.searchById(userId);
		 return ResponseEntity.ok().body(new MyInfoDTO(result));
	 }

	 // 회원 탈퇴
	 @DeleteMapping
	 public void withdrawMe(@RequestParam(value = "userId", required = false, defaultValue = "") Integer userId) {
		 this.accountService.withdrawal(userId);
	 }

	 // 내가 쓴 게시글 불러오기
	 @GetMapping("/post")
	 public List<PostEntity> getMyPost(@RequestParam(value = "userId", required = false, defaultValue = "") Integer userId) {
		 List<PostEntity> result = this.postService.searchAllByAuthorId(userId);
		 return result;
	 }

	 // 스크랩한 포스트 로드
	 @GetMapping("/scrap")
	 public List<Optional<PostEntity>> getMyScrap(@RequestParam(value = "userId", required = false, defaultValue = "") Integer userId) {
		 List<Optional<PostEntity>> result = this.postService.searchScrapByUserId(userId);
		 return result;
	 }

	 // 내 알림 불러오기
	 @GetMapping("/notice")
	 public List<NotificationEntity> getMyNotice(@RequestParam(value = "userId", required = false, defaultValue = "") Integer userId) {
		 List<NotificationEntity> result = this.noticeService.searchAllByUserId(userId);
		 return result;
	 }

	 // 알림 하나 읽기
	 @PatchMapping("/notice")
	 public void readOneNotice(@RequestParam(value = "notiId", required = false, defaultValue = "") Integer notiId) {
	 	this.noticeService.checkNotice(notiId);
	 }

	 // 알림 하나 삭제
	 @DeleteMapping("/notice")
	 public void deleteOneNotice(@RequestParam(value = "notiId", required = false, defaultValue = "") Integer notiId) {
		this.noticeService.deleteOneNotice(notiId);
	 }

	 // 알림 전체 읽기
	 @PatchMapping("/notice/all")
	 public List<NotificationEntity> readAllNotice(@RequestParam(value = "userId", required = false, defaultValue = "") Integer userId) {
		 List<NotificationEntity> result = this.noticeService.readAllNotice(userId);
		 return result;
	 }

	 // 읽은 알림 전체 삭제
	 @DeleteMapping("/notice/read")
	 public void deleteReadNotice(@RequestParam(value = "userId", required = false, defaultValue = "") Integer userId) {
		 this.noticeService.deleteReadNotice(userId);
	 }

	 // 알림 전체 삭제
	 @DeleteMapping("/notice/all")
	 public void deleteAllNotice(@RequestParam(value = "userId", required = false, defaultValue = "") Integer userId) {
		 this.noticeService.deleteAllNotice(userId);
	 }
 }
