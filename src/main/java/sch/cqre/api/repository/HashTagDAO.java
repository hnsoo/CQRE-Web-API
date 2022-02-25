package sch.cqre.api.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.HashTagEntity;
import sch.cqre.api.domain.PostEntity;

import java.sql.Timestamp;

@Repository
@RequiredArgsConstructor
@Transactional
public class HashTagDAO {

    private final HashTagRepository hashTagRepository;

    @Transactional
    public int getTagId(String hashtag){
        if (hashTagRepository.countByName(hashtag) == 0){ //처음 쓰인 해시태그라면
            //HashTag테이블에 새로운 태그 생성 및 리턴

            HashTagEntity hashTagForm = new HashTagEntity();
            hashTagForm.setName(hashtag);

             return hashTagRepository.save(hashTagForm).getHashtagId();
        } else {
            //기존 HashTagId값 리턴
            return hashTagRepository.findOnceByName(hashtag).getHashtagId();

        }
    }



}
