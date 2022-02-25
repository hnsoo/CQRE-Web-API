package sch.cqre.api.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.PostEntity;
import sch.cqre.api.domain.UserEntity;
import sch.cqre.api.dto.BoardDto;
import sch.cqre.api.dto.UserDto;
import sch.cqre.api.jwt.Role;

@Repository
@RequiredArgsConstructor
public class BoardDAO {
    private final BoardRepository boardRepository;

    @Transactional
    public PostEntity add(BoardDto form){
        PostEntity boardForm = form.toEntity();
        return boardRepository.save(boardForm);
    }

}
