package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sch.cqre.api.DTO.RentalDTO;
import sch.cqre.api.domain.BookEntity;
import sch.cqre.api.domain.RentalEntity;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.repository.BookRepository;
import sch.cqre.api.repository.RentalRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static sch.cqre.api.exception.ErrorCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalService {
    @Autowired
    private final RentalRepository Rentaldao;
    private final BookRepository Bookdao;
    @Autowired
    private ModelMapper modelMapper;
    
    //대여 정보 조회 서비스
    public List<RentalDTO> findAll() {
    //List<RentalEntity> rentalEntityList = Rentaldao.findAll();
        return Rentaldao.findAll().stream().map(p -> modelMapper.map(p, RentalDTO.class)).collect(Collectors.toList());
    }
    
    //대여 서비스 로직
    public void insertRent(Integer bookId, Integer userId) {
        Optional<BookEntity> book = Bookdao.findById(bookId);
        Optional<RentalEntity> rentalEntity = Optional.ofNullable(Rentaldao.findByBookIdAndUserId(bookId, userId));
        //빌릴 도서가 존재하는지
        if (!book.isPresent())
            throw new CustomException(BOOK_NOT_EXIST);
        //빌릴 도서가 중복인지 확인
        else if (rentalEntity.isPresent())
            throw new CustomException(DUPLICATE_RENT);
        //남아있는 수량이 1개 이상인지 확인
        else if (book.get().getRemainAmount() <= 0)
            throw new CustomException(NOT_REMAINED);
        else {
            //도서 bookId, userId 값 설정
            RentalEntity rental = new RentalEntity();
            rental.setBookId(bookId);
            rental.setUserId(userId);
            Rentaldao.save(rental);

            //수량 하나 빼기 remainAmount -1
            book.get().setRemainAmount((byte) (book.get().getRemainAmount() - 1));
            Bookdao.save(book.get());
        }
    }
}
