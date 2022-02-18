 package sch.cqre.api.controller;

import java.util.List;

import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping("{userId}")
	public ResponseEntity<MyInfoDTO> getMyInfo(@PathVariable("userId") Integer userId) {
		UserEntity result = this.accountService.searchById(userId);
		return ResponseEntity.ok().body(new MyInfoDTO(result));
	}

	@PatchMapping("/profile/{userId}/{path}")
	public ResponseEntity<MyInfoDTO> updateProfile(@PathVariable("userId") Integer userId,
		@PathVariable("path") String path) {
		UserEntity result = this.accountService.setProfile(userId, path);
		return ResponseEntity.ok().body(new MyInfoDTO(result));
	}

	@PatchMapping("/email/{userId}/{email}")
	public ResponseEntity<MyInfoDTO> updateEmail(@PathVariable("userId") Integer userId,
		@PathVariable("email") String path) {
		UserEntity result = this.accountService.setEmail(userId, email);
		return ResponseEntity.ok().body(new MyInfoDTO(result));
	}

	@DeleteMapping("{userId}")
	public void withdrawUser(@PathVariable("userId") Integer userId) {
		this.accountService.withdrawal(userId);
	}

	@GetMapping("/post/{userId}")
	public List<PostEntity> getMyPost(@PathVariable("userId") Integer userId) {
		List<PostEntity> result = this.postService.searchAllByAuthorId(userId);
		return result;
	}

	// @GetMapping("/scrap/{userId}")
	// public List<ScrapEntity> getMyScrap(@PathVariable("userId") Integer userId) {
	// 	List<ScrapEntity> result = this.postService
	// }

	@GetMapping("/notice/{userId}")
	public List<NotificationEntity> getMyNotice(@PathVariable("userId") Integer userId) {
		List<NotificationEntity> result = this.noticeService.searchAllByUserId(userId);
		return result;
	}
}
