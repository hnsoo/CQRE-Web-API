package sch.cqre.api.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sch.cqre.api.domain.PostEntity;
import sch.cqre.api.domain.ScrapEntity;
import sch.cqre.api.dto.BoardDto;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.repository.BoardRepository;
import sch.cqre.api.repository.ScrapRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static sch.cqre.api.exception.ErrorCode.POST_NOT_FOUND;
import static sch.cqre.api.exception.ErrorCode.SCRAP_NOT_FOUND;

@Service
@AllArgsConstructor
public class PostService {
	private final ScrapRepository scrapRepo;
	private final BoardRepository postRepo;
	private final UserService userService;

	private ModelMapper modelMapper;

	// 작성자 정보를 가지고 모든 포스트 검색
	public List<BoardDto.PostResponseDto> searchAllByAuthorId(Long authorId) {

		List<PostEntity> posts = this.postRepo.findPostListByUserId(userService.getMyInfo());
		if (posts == null || posts.isEmpty())
			throw new CustomException(POST_NOT_FOUND);

		// 객체 변환
		return posts.stream().map(p -> modelMapper.map(p, BoardDto.PostResponseDto.class)).collect(Collectors.toList());
	}

	// 유저가 스크랩한 모든 포스트 검색
	public List<BoardDto.PostResponseDto> searchScrapByUserId(Long userId) {
		List<ScrapEntity> scraps = this.scrapRepo.findByUserId(userId);
		// 찾지 못할 경우 "스크랩 없음" 예외 처리
		if (scraps == null || scraps.isEmpty())
			throw new CustomException(SCRAP_NOT_FOUND);

		List<PostEntity> posts = new ArrayList<>();


		// 스크랩 엔티티에서 포스트 ID를 추출하고 리스트에 담아서 출력
		for (ScrapEntity scrapEntity : scraps) {
			posts.add(postRepo.findOne(scrapEntity.getPostId()));
		}
		return posts.stream().map(p -> modelMapper.map(p, BoardDto.PostResponseDto.class)).collect(Collectors.toList());
	}
}
