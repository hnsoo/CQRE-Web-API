package sch.cqre.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import sch.cqre.api.service.AccountService;
import sch.cqre.api.service.NotificationService;
import sch.cqre.api.service.PostService;
import sch.cqre.api.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class MyPageControllerTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private AccountService accountService;
	@Autowired
	private PostService postService;
	@Autowired
	private NotificationService noticeService;
	@Autowired
	private UserService userService;

	private static final String BASE_URL = "/api/mypage";

	@Test
	void getMyInfo() throws Exception {
		mvc.perform(get(BASE_URL))
			.andExpect(status().isOk())
			.andDo(print());
	}

	@Test
	void withdrawMe() {
	}

	@Test
	void checkPassword() {
	}

	@Test
	void changePassword() {
	}

	@Test
	void getMyPost() {
	}

	@Test
	void getMyScrap() {
	}

	@Test
	void getMyNotice() {
	}

	@Test
	void readOneNotice() {
	}

	@Test
	void deleteOneNotice() {
	}

	@Test
	void readAllNotice() {
	}

	@Test
	void deleteReadNotice() {
	}

	@Test
	void deleteAllNotice() {
	}
}