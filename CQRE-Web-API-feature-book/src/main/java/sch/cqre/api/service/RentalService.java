package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sch.cqre.api.domain.RentalEntity;
import sch.cqre.api.repository.RentalRepository;

import javax.transaction.Transactional;
import java.lang.management.OperatingSystemMXBean;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalService {
    @Autowired
    private final RentalRepository Rentaldao;

    public List<RentalEntity> findAll() {
        return Rentaldao.findAll();
    }

    public RentalEntity insertRent(RentalEntity rental) {
        return Rentaldao.save(rental);
    }


}
