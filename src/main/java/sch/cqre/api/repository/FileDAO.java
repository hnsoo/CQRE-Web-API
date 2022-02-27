package sch.cqre.api.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sch.cqre.api.domain.FileEntity;
import sch.cqre.api.domain.PostEntity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FileDAO {
    private final FileRepository fileRepository;

    @Transactional
    public int addFile(String orignalName, String filepath, int size, String filetype){

        //db에 파일정보 저장
        FileEntity fileForm = new FileEntity();
        fileForm.setOriginalname(orignalName);
        fileForm.setFilepath(filepath);
        fileForm.setSize(size);
        fileForm.setFiletype(filetype);

        return fileRepository.save(fileForm).getFileId();
    }

    @Transactional
    public String getFilePath(String fileUUID){
        return "";
    }

    public FileEntity getFileDB(String fileUUID){
        return fileRepository.findOneByfilepathIsContaining(fileUUID);

    }

}
