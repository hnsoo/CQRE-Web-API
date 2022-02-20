 package sch.cqre.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;

import lombok.AllArgsConstructor;
import sch.cqre.api.domain.NotificationEntity;
import sch.cqre.api.domain.PostEntity;
import sch.cqre.api.domain.ScrapEntity;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.mypage.MyInfoDTO;
import sch.cqre.api.repository.UserRepository;
import sch.cqre.api.service.my.AccountService;
import sch.cqre.api.service.my.NoticeService;
import sch.cqre.api.service.my.PostService;
import sch.cqre.api.service.my.ProfileService;

 /*
  * 마이페이지 컨트롤러
  * 제작자: 김현수
  * 비밀번호 변경 기능 추가 필요
  * */
 @AllArgsConstructor
 @RestController
 @RequestMapping("/mypage")
 public class MyPageController {
	 private final ProfileService profileService;
	 private final AccountService accountService;
	 private final PostService postService;
	 private final NoticeService noticeService;
	 private final UserRepository userRepo;

	 // 회원 정보 불러오기
	 @GetMapping("{userId}")
	 public ResponseEntity<MyInfoDTO> getMyInfo(@PathVariable("userId") Integer userId) {
		 UserEntity result = this.accountService.searchById(userId);
		 return ResponseEntity.ok().body(new MyInfoDTO(result));
	 }

	 // // 프로필 변경
	 // @PutMapping("/profile/{userId}")
	 // public void updateProfile(@PathVariable("userId") Integer userId) {
		//
	 // }

	 // 이메일 변경
	 @PatchMapping("/email/{userId}/{email}")
	 public ResponseEntity<MyInfoDTO> updateEmail(@PathVariable("userId") Integer userId,
		 @PathVariable("email") String email) {
		 UserEntity result = this.accountService.setEmail(userId, email);
		 return ResponseEntity.ok().body(new MyInfoDTO(result));
	 }

	 // 회원 탈퇴
	 @DeleteMapping("{userId}")
	 public void withdrawUser(@PathVariable("userId") Integer userId) {
		 this.accountService.withdrawal(userId);
	 }

	 // 내가 쓴 게시글 불러오기
	 @GetMapping("/post/{userId}")
	 public List<PostEntity> getMyPost(@PathVariable("userId") Integer userId) {
		 List<PostEntity> result = this.postService.searchAllByAuthorId(userId);
		 return result;
	 }

	 // 스크랩한 포스트 로드
	 @GetMapping("/scrap/{userId}")
	 public List<PostEntity> getMyScrap(@PathVariable("userId") Integer userId) {
		 List<PostEntity> result = this.postService.searchScrapByUserId(userId);
		 return result;
	 }

	 // 내 알림 불러오기
	 @GetMapping("/notice/{userId}")
	 public List<NotificationEntity> getMyNotice(@PathVariable("userId") Integer userId) {
		 List<NotificationEntity> result = this.noticeService.searchAllByUserId(userId);
		 return result;
	 }

	 // 알림 하나 읽기
	 @PatchMapping("/notice/{notiId}")
	 public String readOneNotice(@PathVariable("notiId") Integer notiId) {
	 	return this.noticeService.checkNotice(notiId);
	 }

	 // 알림 하나 삭제
	 @DeleteMapping("/notice/{notiId}")
	 public void deleteOneNotice(@PathVariable("userId") Integer userId, @PathVariable("notiId") Integer notiId) {
		this.noticeService.deleteOneNotice(notiId);
	 }

	 // 알림 전체 읽기
	 @PatchMapping("/notice/{userId}")
	 public List<NotificationEntity> readAllNotice(@PathVariable("userId") Integer userId) {
		 List<NotificationEntity> result = this.noticeService.readAllNotice(userId);
		 return result;
	 }

	 // 읽은 알림 전체 삭제
	 @DeleteMapping("/notice/read/{userId}")
	 public void deleteReadNotice(@PathVariable("userId") Integer userId) {
		 this.noticeService.deleteReadNotice(userId);
	 }

	 // 알림 전체 삭제
	 @DeleteMapping("/notice/{userId}")
	 public void deleteAllNotice(@PathVariable("userId") Integer userId) {
		 this.noticeService.deleteAllNotice(userId);
	 }
 }
