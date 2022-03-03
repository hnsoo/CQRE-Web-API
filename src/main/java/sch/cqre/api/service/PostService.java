package sch.cqre.api.service;

import static sch.cqre.api.exception.ErrorCode.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
// import sch.cqre.api.domain.NotificationEntity;
import sch.cqre.api.domain.PostEntity;
import sch.cqre.api.domain.ScrapEntity;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.repository.PostRepository;
import sch.cqre.api.repository.ScrapRepository;

@Service
@AllArgsConstructor
public class PostService {
	private final ScrapRepository scrapRepo;
	private final PostRepository postRepo;

	// 작성자 정보를 가지고 모든 포스트 검색
	public List<PostEntity> searchAllByAuthorId(Integer authorId) {
		List<PostEntity> result = this.postRepo.findByAuthorId(authorId)
			// 찾지 못할 경우 "포스트 없음" 예외 처리
			.orElseThrow(()-> new CustomException(POST_NOT_FOUND));
		return result;
	}

	// 유저가 스크랩한 모든 포스트 검색
	public List<PostEntity> searchScrapByUserId(Integer userId) {
		List<ScrapEntity> scraps = this.scrapRepo.findByUserId(userId)
			// 찾지 못할 경우 "스크랩 없음" 예외 처리
			.orElseThrow(()-> new CustomException(SCRAP_NOT_FOUND));

		List<PostEntity> result = new ArrayList<>();

		// 스크랩 엔티티에서 포스트 ID를 추출하고 리스트에 담아서 출력
		for (ScrapEntity scrapEntity : scraps) {
			result.add(postRepo.findById(scrapEntity.getPostId())
				// 스크랩한 포스트가 존재하지 않을 경우 "포스트 없음" 예외 처리
				.orElseThrow(() -> new CustomException(POST_NOT_FOUND)));
		}
		return result;
	}
}
