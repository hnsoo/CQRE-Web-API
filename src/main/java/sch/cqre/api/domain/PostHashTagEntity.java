package sch.cqre.api.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Table(name = "PostHashTag", schema = "main")
@IdClass(PostHashTagEntityId.class)
@Setter @Getter
public class PostHashTagEntity {
    @Id
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "hashtag_id")
    private Long hashtagId;

    @Builder
    public PostHashTagEntity(Long postId, Long hashtagId) {
        this.postId = postId;
        this.hashtagId = hashtagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PostHashTagEntity that = (PostHashTagEntity)o;
        return postId == that.postId && hashtagId == that.hashtagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, hashtagId);
    }
}
