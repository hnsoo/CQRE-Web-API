package sch.cqre.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import sch.cqre.api.dto.RetKakaoOAuth;
import sch.cqre.api.service.kakaoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login/oauth2/code")
public class OAuthController {

	private final RestTemplate restTemplate;
	private final Environment env;
	private final kakaoService kakaoService;

	@Value("${url.local}")
	private String baseUrl;

	@Value("${security.oauth2.client.registration.kakao.client-id}")
	private String kakaoClientId;

	@Value("${security.oauth2.client.registration.kakao.redirectUri}")
	private String kakaoRedirectUri;

	@GetMapping("/kakao")
	public RetKakaoOAuth getOwnTokenFromKakao(@RequestParam String code) {
		return kakaoService.getKakaoTokenInfo(code);
	}
}
