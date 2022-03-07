package sch.cqre.api.domain;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

@Data
public class PostHashTagEntityId implements Serializable {

    @Column(name = "post_id")
    private long postId;

    @Column(name = "hashtag_id")
    private long hashtagId;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PostHashTagEntityId that = (PostHashTagEntityId)o;
        return postId == that.postId && hashtagId == that.hashtagId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, hashtagId);
    }
}
