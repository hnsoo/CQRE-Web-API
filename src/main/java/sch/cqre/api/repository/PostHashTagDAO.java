package sch.cqre.api.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.HashTagEntity;
import sch.cqre.api.domain.PostHashTagEntity;
import sch.cqre.api.domain.PostHashTagEntityPK;

@Repository
@RequiredArgsConstructor
public class PostHashTagDAO {

    private final PostHashTagRepository postHashTagRepository;

    @Transactional
    public int taggingPost(int postId, int hashtag_id){

        PostHashTagEntity postHashTagForm = new PostHashTagEntity();
        postHashTagForm.setPostId(postId);
        postHashTagForm.setHashtagId(hashtag_id);

        return postHashTagRepository.save(postHashTagForm).getHashtagId();

    }


}
