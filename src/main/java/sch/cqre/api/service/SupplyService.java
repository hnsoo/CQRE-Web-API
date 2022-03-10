package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sch.cqre.api.dto.SupplyDto;
import sch.cqre.api.domain.SupplyEntity;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.repository.SupplyRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static sch.cqre.api.exception.ErrorCode.SUPPLY_NOT_EXIST;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplyService {
    @Autowired
    private final SupplyRepository Supplydao;
    @Autowired
    private ModelMapper modelMapper;
    
    //비품 전체 조회 서비스
    public List<SupplyDto> findAll() {
        return Supplydao.findAll().stream().map(p -> modelMapper.map(p, SupplyDto.class)).collect(Collectors.toList());
    }
    
    //비품 이름으로 검색 서비스
    public List<SupplyDto> findSupplyByName(String keyword) {
        List<SupplyEntity> supply = Supplydao.findByNameContainingIgnoreCase(keyword);
        if (supply.isEmpty()) {
            throw new CustomException(SUPPLY_NOT_EXIST);
        }
        return supply.stream().map(p->modelMapper.map(p, SupplyDto.class)).collect(Collectors.toList());
    }

}
