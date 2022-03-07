package sch.cqre.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PasswordResponseDto {
	private Long userId;

	public PasswordResponseDto(Long userId) {
		this.userId = userId;
	}
}
