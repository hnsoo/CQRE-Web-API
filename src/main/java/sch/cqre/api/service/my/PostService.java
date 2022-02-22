package sch.cqre.api.service.my;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
// import sch.cqre.api.domain.NotificationEntity;
import sch.cqre.api.domain.PostEntity;
import sch.cqre.api.domain.ScrapEntity;
import sch.cqre.api.repository.PostRepository;
import sch.cqre.api.repository.ScrapRepository;

@Service
@AllArgsConstructor
public class PostService {
	private final ScrapRepository scrapRepo;
	private final PostRepository postRepo;

	public List<PostEntity> searchAllByAuthorId(Integer authorId) {
		List<PostEntity> result = this.postRepo.findByAuthorId(authorId);
		return result;
	}

	public List<Optional<PostEntity>> searchScrapByUserId(Integer userId) {
		List<ScrapEntity> scraps = this.scrapRepo.findByUserId(userId);
		List<Optional<PostEntity>> result = new ArrayList();
		if (scraps != null) {
			for (ScrapEntity scrapEntity : scraps) {
				result.add(postRepo.findById(scrapEntity.getPostId()));
			}
		}
		return result;
	}
}
