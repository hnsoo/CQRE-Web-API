package sch.cqre.api.model.entity;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Notification", schema = "main", catalog = "")
public class NotificationEntity {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "not_id")
	private Long notId;
	@Basic
	@Column(name = "receiver_id")
	private Long receiverId;
	@Basic
	@Column(name = "sender_id")
	private Long senderId;
	@Basic
	@Column(name = "not_type")
	private String notType;
	@Basic
	@Column(name = "not_post")
	private Long notPost;
	@Basic
	@Column(name = "not_content")
	private String notContent;
	@Basic
	@Column(name = "not_url")
	private String notUrl;
	@Basic
	@Column(name = "not_datetime")
	private Timestamp notDatetime;
	@Basic
	@Column(name = "not_read_datetime")
	private Timestamp notReadDatetime;
	@Basic
	@Column(name = "read_or_not")
	private Integer readOrNot;

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		NotificationEntity that = (NotificationEntity)o;
		return notId == that.notId && receiverId == that.receiverId && senderId == that.senderId
			&& notPost == that.notPost
			&& Objects.equals(notType, that.notType) && Objects.equals(notContent, that.notContent)
			&& Objects.equals(notUrl, that.notUrl) && Objects.equals(notDatetime, that.notDatetime)
			&& Objects.equals(notReadDatetime, that.notReadDatetime) && Objects.equals(readOrNot,
			that.readOrNot);
	}

	@Override
	public int hashCode() {
		return Objects.hash(notId, receiverId, senderId, notType, notPost, notContent, notUrl, notDatetime,
			notReadDatetime,
			readOrNot);
	}
}
