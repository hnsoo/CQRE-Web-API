package sch.cqre.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sch.cqre.api.DTO.SupplyDTO;
import sch.cqre.api.service.SupplyService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/supply")
@Slf4j
@RequiredArgsConstructor
public class SupplyController {
    private final SupplyService supplyService;

    //비품 전체 출력
    @GetMapping
    public ResponseEntity<List<SupplyDTO>> findAll() {
        return ResponseEntity.ok(supplyService.findAll());
    }

    //비품 이름으로 검색
    @PostMapping("/search")
    public ResponseEntity<List<SupplyDTO>> supplySearch(@RequestParam(value = "keyword",required = false, defaultValue = "") String name) {
        return ResponseEntity.ok(supplyService.findSupplyByName(name));
    }
}
