package sch.cqre.api.service;

import static sch.cqre.api.exception.ErrorCode.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sch.cqre.api.domain.PostEntity;
import sch.cqre.api.domain.ScrapEntity;
import sch.cqre.api.dto.PostResponseDto;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.repository.PostRepository;
import sch.cqre.api.repository.ScrapRepository;

@Service
@AllArgsConstructor
public class PostService {
	private final ScrapRepository scrapRepo;
	private final PostRepository postRepo;

	private ModelMapper modelMapper;

	// 작성자 정보를 가지고 모든 포스트 검색
	public List<PostResponseDto> searchAllByAuthorId(Integer authorId) {
		List<PostEntity> posts = this.postRepo.findByAuthorId(authorId);
		if (posts == null || posts.isEmpty())
			throw new CustomException(POST_NOT_FOUND);

		// 객체 변환
		return posts.stream().map(p -> modelMapper.map(p, PostResponseDto.class)).collect(Collectors.toList());
	}

	// 유저가 스크랩한 모든 포스트 검색
	public List<PostEntity> searchScrapByUserId(Integer userId) {
		List<ScrapEntity> scraps = this.scrapRepo.findByUserId(userId);
		// 찾지 못할 경우 "스크랩 없음" 예외 처리
		if (scraps == null || scraps.isEmpty())
			throw new CustomException(SCRAP_NOT_FOUND);

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
