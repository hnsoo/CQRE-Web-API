package sch.cqre.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteUserResponseDto {
	private Long userId;

	public DeleteUserResponseDto(Long userId) {
		this.userId = userId;
	}
}
