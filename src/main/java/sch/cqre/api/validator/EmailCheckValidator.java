/*
package sch.cqre.api.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import sch.cqre.api.dto.UserDto;
import sch.cqre.api.repository.UserRepository;

@RequiredArgsConstructor
@Component
public class EmailCheckValidator extends AbstractValidator <UserDto> {
    private final UserRepository userRepository;

    protected void doValidate(UserDto dto, Errors errors) {
        if (userRepository.findByEmail(dto.toEntity().getEmail())) {
            errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
        }
    }
}

*/

