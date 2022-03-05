package sch.cqre.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PasswordResponseDto {
	private Integer userId;

	public PasswordResponseDto(Integer userId) {
		this.userId = userId;
	}
}
