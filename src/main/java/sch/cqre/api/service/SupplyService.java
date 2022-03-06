package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sch.cqre.api.DTO.SupplyDTO;
import sch.cqre.api.domain.SupplyEntity;
import sch.cqre.api.exception.CustomException;
import sch.cqre.api.exception.ErrorCode;
import sch.cqre.api.repository.SupplyRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static sch.cqre.api.exception.ErrorCode.Supply_Not_Exist;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplyService {
    @Autowired
    private final SupplyRepository Supplydao;
    @Autowired
    private ModelMapper modelMapper;

    public List<SupplyDTO> findAll() {
        return Supplydao.findAll().stream().map(p -> modelMapper.map(p,SupplyDTO.class)).collect(Collectors.toList());
    }

    public List<SupplyDTO> findSupplyByName(String keyword) {
        List<SupplyEntity> supply = Supplydao.findByNameContainingIgnoreCase(keyword);
        if (supply.isEmpty()) {
            throw new CustomException(Supply_Not_Exist);
        }
        return supply.stream().map(p->modelMapper.map(p, SupplyDTO.class)).collect(Collectors.toList());
    }

}
