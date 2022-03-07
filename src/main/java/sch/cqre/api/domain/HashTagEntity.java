package sch.cqre.api.domain;

import lombok.*;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@Table(name = "HashTag", schema = "main")
@Getter @Setter
@Builder
@AllArgsConstructor
public class HashTagEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "hashtag_id")
	private long hashtagId;

	@Basic(optional = false)
	@Column(name = "name")
	private String name;

	@Builder
	public HashTagEntity(String name) {
		this.name = name;
	}

}
