package sch.cqre.api.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {

    private String token;
}
