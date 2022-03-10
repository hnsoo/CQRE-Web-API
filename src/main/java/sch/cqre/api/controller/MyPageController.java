package sch.cqre.api.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sch.cqre.api.dto.BoardDto;
import sch.cqre.api.dto.MyInfoDto;
import sch.cqre.api.dto.NotiDto;
import sch.cqre.api.dto.PasswordResponseDto;
import sch.cqre.api.service.AccountService;
import sch.cqre.api.service.NotificationService;
import sch.cqre.api.service.PostService;
import sch.cqre.api.service.UserService;

import java.util.List;

/*
  * 마이페이지 컨트롤러
  * 제작자: 김현수
  * 비밀번호 변경 기능 추가 필요
  * */

 @AllArgsConstructor
 @RestController
 @RequestMapping("/api/v1/mypage")
 public class MyPageController {
	 private final AccountService accountService;
	 private final PostService postService;
	 private final NotificationService noticeService;
	 private final UserService userService;

	/*
	 기능: 회원 정보 불러오기
	 요청: 없음 (토큰에서 유저 Email 추출 후 사용)
	 반환: 200, body{
	 			userId : 유저 UID
	 			studentID : 학번
	 			email : 이메일
	 			nickname : 닉네임
	 		}
	 */
	 @GetMapping
	 public ResponseEntity<MyInfoDto> getMyInfo() {
		 MyInfoDto result = new MyInfoDto(userService.getMyInfo());

		 // 테스트
		 // MyInfoDto result = this.accountService.searchById(1);
		 return ResponseEntity.ok(result);
	 }

	 /*
	 기능: 회원 탈퇴
	 요청: 없음 (토큰에서 유저 Email 추출 후 사용)
	 반환: 200, body{
	 			userId : 유저 UID
	 		}
	 */
	 @DeleteMapping
	 public ResponseEntity<NotiDto.DeleteNotiResponseDto> withdrawMe() {
		 MyInfoDto myInfo = new MyInfoDto(userService.getMyInfo());
		 NotiDto.DeleteNotiResponseDto result = this.accountService.withdrawal(myInfo.getUserId());

		 // 테스트
		 // DeleteUserResponseDto result = this.accountService.withdrawal(1);

		 return ResponseEntity.ok(result);
	 }

	 /*
	 기능: 비밀번호 확인
	 요청: 없음 (토큰에서 유저 Email 추출 후 사용)
	 반환: 200, body {
	 			userId: 유져 UID
	 			}
	 */
	 @GetMapping("/pw")
	 public ResponseEntity<PasswordResponseDto> CheckPassword(@RequestParam (value = "pw", required = false, defaultValue = "") String pw) {
		 MyInfoDto myInfo = new MyInfoDto(userService.getMyInfo());
		PasswordResponseDto result = this.accountService.checkPassword(myInfo, pw);
		 return ResponseEntity.ok(result);
	 }

	 /*
	 기능: 비밀번호 변경
	 요청: 없음 (토큰에서 유저 Email 추출 후 사용)
	 반환: 200, body{
	 			userId: 유저 UID
	 			}
	  */
	@PatchMapping("/pw")
	public ResponseEntity<PasswordResponseDto> changePassword(@RequestParam (value = "pwOne", required = false, defaultValue = "") String pwOne,
		@RequestParam (value = "pwTwo", required = false, defaultValue = "") String pwTwo) {
		MyInfoDto myInfo = new MyInfoDto(userService.getMyInfo());
		PasswordResponseDto result = this.accountService.changePassword(myInfo, pwOne, pwTwo);
		return ResponseEntity.ok(result);
	}

	 /*
	 기능: 내가 쓴 게시글 불러오기
	 요청: 없음 (토큰에서 유저 Email 추출 후 사용)
	 반환: 200, body{
	 			(리스트 형식으로 여러 포스트 반환)
	 			postId : 포스트 UID
				authorId : 작성자 UID
				postTitle : 포스트 제목
				postContent : 포스트 내용
				views : 조회수
				thumbnail : 썸네일 그림
				createdAt : 작성 시간
				updatedAt : 최종 수정 시간
			}
	 */
	 @GetMapping("/post")
	 public ResponseEntity<List<BoardDto.PostResponseDto>> getMyPost() {
		 MyInfoDto myInfo = new MyInfoDto(userService.getMyInfo());
		 List<BoardDto.PostResponseDto> result = this.postService.searchAllByAuthorId(myInfo.getUserId());

		 // 테스트
		 // List<PostResponseDto> result = this.postService.searchAllByAuthorId(1);
		 return ResponseEntity.ok(result);
	 }

	 /*
	 기능: 스크랩한 포스트 로드
	 요청: 없음 (토큰에서 유저 Email 추출 후 사용)
	 반환: 200, body{
	 			(리스트 형식으로 여러 포스트 반환)
	 			postId : 포스트 UID
				authorId : 작성자 UID
				postTitle : 포스트 제목
				postContent : 포스트 내용
				views : 조회수
				thumbnail : 썸네일 그림
				createdAt : 작성 시간
				updatedAt : 최종 수정 시간
			}
	 */
	 @GetMapping("/scrap")
	 public ResponseEntity<List<BoardDto.PostResponseDto>> getMyScrap() {
		 MyInfoDto myInfo = new MyInfoDto(userService.getMyInfo());
		 List<BoardDto.PostResponseDto> result = this.postService.searchScrapByUserId(myInfo.getUserId());


		 // 테스트
		 // List<PostResponseDto> result = this.postService.searchScrapByUserId(32);
		 return ResponseEntity.ok(result);
	 }

	 /*
	 기능: 내 알림 불러오기
	 요청: 없음 (토큰에서 유저 Email 추출 후 사용)
	 반환: 200, body{
	 			(리스트 형식으로 여러 알림 반환)
	 			notiId : 알림 UID
				receiverId : 알림 받는 유저 UID
				senderId : 알림 보내는 유저 UID
				notiType : 알림 타입
				notiPost : 알림이 발생한 포스트 UID
				notiContent : 알림 내용
				notiDatetime : 알림 발생 시간
				whether : 알림 확인 여부
			}
	 */
	 @GetMapping("/notice")
	 public ResponseEntity<List<NotiDto.NotiResponse>> getMyNotice() {
		 MyInfoDto myInfo = new MyInfoDto(userService.getMyInfo());
		 List<NotiDto.NotiResponse> result = this.noticeService.searchByUserId(myInfo.getUserId());

		 // 테스트
		 // List<NotificationResponseDto> result = this.noticeService.searchByUserId(1);
		 return ResponseEntity.ok(result);
	 }

	 /*
	 기능: 알림 하나 읽기
	 요청: 알림 UID
	 반환: 200, body{
	 			notiId : 알림 UID
				whether : 알림 확인 여부
			}
	 */
	 @PatchMapping("/notice")
	 public ResponseEntity<NotiDto.CheckNotiResponse> readOneNotice(@RequestParam(value = "notiId", required = false, defaultValue = "0") Long notiId) {
		 NotiDto.CheckNotiResponse result = this.noticeService.checkNotification(notiId);
		 return ResponseEntity.ok().body(result);
	 }

	 /*
	 기능: 알림 하나 삭제
	 요청: 알림 UID
	 반환: 200, body{
	 			notiId : 삭제한 알림 UID
			}
	 */
	 @DeleteMapping("/notice")
	 public ResponseEntity<NotiDto.DeleteNotiResponseDto> deleteOneNotice(@RequestParam(value = "notiId", required = false, defaultValue = "0") Long notiId){
		 // 알림 삭제 실행 후 결과 객체를 불러옴
		 NotiDto.DeleteNotiResponseDto result = this.noticeService.deleteOneNotice(notiId);
		 // 성공적으로 알림 삭제
		 return ResponseEntity.ok().body(result);
	 }

	 /*
	 기능: 알림 전체 읽음 처리
	 요청: 없음 (토큰에서 유저 Email 추출 후 사용)
	 반환: 200, body{}
	 */
	 @PatchMapping("/notice/all")
	 public ResponseEntity<HttpStatus> readAllNotice() {
		 MyInfoDto myInfo = new MyInfoDto(userService.getMyInfo());
		 this.noticeService.readAllNotice(myInfo.getUserId());

		 // 테스트
		 // this.noticeService.readAllNotice(1);
		 return new ResponseEntity<>(HttpStatus.OK);
	 }

	 /*
	 기능: 읽은 알림들 삭제
	 요청: 없음 (토큰에서 유저 Email 추출 후 사용)
	 반환: 200, body{}
	 */
	 @DeleteMapping("/notice/read")
	 public ResponseEntity<HttpStatus> deleteReadNotice() {
		 MyInfoDto myInfo = new MyInfoDto(userService.getMyInfo());
		 this.noticeService.deleteReadNotification(myInfo.getUserId());

		 // 테스트
		 // this.noticeService.deleteReadNotification(1);
		 return new ResponseEntity<>(HttpStatus.OK);
	 }

	 /*
	 기능: 알림 전체 삭제
	 요청: 없음 (토큰에서 유저 Email 추출 후 사용)
	 반환: 200, body{}
	 */
	 @DeleteMapping("/notice/all")
	 public ResponseEntity<HttpStatus> deleteAllNotice() {
		 MyInfoDto myInfo = new MyInfoDto(userService.getMyInfo());
		 this.noticeService.deleteAllNotice(myInfo.getUserId());

		 // 테스트
		 // this.noticeService.deleteAllNotice(1);
		 return new ResponseEntity<>(HttpStatus.OK);
	 }
 }
