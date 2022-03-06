package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sch.cqre.api.DTO.RentalDTO;
import sch.cqre.api.domain.BookEntity;
import sch.cqre.api.domain.RentalEntity;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.exception.ErrorCode;
import sch.cqre.api.repository.BookRepository;
import sch.cqre.api.repository.RentalRepository;

import javax.transaction.Transactional;
import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static sch.cqre.api.exception.ErrorCode.Book_Not_Exist;
import static sch.cqre.api.exception.ErrorCode.Not_Remained;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalService {
    @Autowired
    private final RentalRepository Rentaldao;
    private final BookRepository Bookdao;
    @Autowired
    private ModelMapper modelMapper;

    public List<RentalDTO> findAll() {
//      List<RentalEntity> rentalEntityList = Rentaldao.findAll();
        return Rentaldao.findAll().stream().map(p -> modelMapper.map(p, RentalDTO.class)).collect(Collectors.toList());
    }

    public void insertRent(Integer bookId, Integer userId) {
        //빌릴 도서가 존재하는지
        Optional<BookEntity> book = Bookdao.findById(bookId);
        if (!book.isPresent())
            throw new CustomException(Book_Not_Exist);
        //남아있는 수량이 1개 이상인지 확인
        else if (book.get().getRemainAmount() <= 0)
            throw new CustomException(Not_Remained);
        else {
            //도서 bookId, userId 값 설정
            RentalEntity rental = new RentalEntity();
            rental.setBookId(bookId);
            rental.setUserId(userId);
            Rentaldao.save(rental);

            //remainAmount -1
            book.get().setRemainAmount((byte) (book.get().getRemainAmount() - 1));
            Bookdao.save(book.get());
        }
    }


}
