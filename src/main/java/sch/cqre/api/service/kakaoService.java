package sch.cqre.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import lombok.RequiredArgsConstructor;
import sch.cqre.api.dto.RetKakaoOAuth;
import sch.cqre.api.exception.CCommunicationException;

@Service
@RequiredArgsConstructor
public class kakaoService {

	private final Environment env;
	private final RestTemplate restTemplate;
	private final Gson gson;

	@Value("${url.local}")
	private String baseUrl;

	@Value("${security.oauth2.client.registration.kakao.client-id}")
	private String kakaoClientId;

	@Value("${security.oauth2.client.registration.kakao.redirectUri}")
	private String kakaoRedirectUri;

	@Value("${security.oauth2.client.registration.provider.kakao.token}")
	private String requestUri;

	public RetKakaoOAuth getKakaoTokenInfo(String code) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", kakaoClientId);
		params.add("redirect_uri", baseUrl + kakaoRedirectUri);
		params.add("code", code);

		if (requestUri == null) throw new CCommunicationException();

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(requestUri, request, String.class);

		if (response.getStatusCode() == HttpStatus.OK)
			return gson.fromJson(response.getBody(), RetKakaoOAuth.class);
		throw new CCommunicationException();
	}
}
