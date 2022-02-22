// package sch.cqre.api.dto.mypage;
//
// import java.sql.Timestamp;
// import java.util.List;
//
// import com.google.gson.Gson;
// import com.google.gson.GsonBuilder;
//
// import lombok.Data;
// import sch.cqre.api.domain.PostEntity;
//
// @Data
// public class PostDTO {
// 	private Integer postId;
// 	private Integer authorId;
// 	private String postTitle;
// 	private String postContent;
// 	private int views;
// 	private int likes;
// 	private String thumbnail;
// 	private Timestamp createdAt;
// 	private Timestamp updatedAt;
//
// 	public PostDTO(List<PostEntity> postList){
// 		Gson gson = new GsonBuilder().create();
// 		return gson.toJson(postList);
// 	}
// }
