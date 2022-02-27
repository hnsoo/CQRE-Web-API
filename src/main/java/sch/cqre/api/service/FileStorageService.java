package sch.cqre.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sch.cqre.api.config.FileStorageConfig;
import sch.cqre.api.exception.FileStorageException;
import sch.cqre.api.exception.MyFileNotFoundException;
import sch.cqre.api.repository.FileDAO;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageService {

    private final Path fileStorageLocation;
    private final FileDAO fileDAO;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig, FileDAO fileDAO) {
        //init

        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        this.fileDAO = fileDAO;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public Path storeFile(MultipartFile file) {

        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Check if the file's name contains invalid characters
        if(fileName.contains("..")) {
            throw new FileStorageException("invaildInput");
        }


        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);


        UUID uuid = UUID.randomUUID();
        String randomUUID = uuid.toString();

        // Copy file to the target location (Replacing existing file with the same name)
        Path targetLocation = this.fileStorageLocation.resolve(randomUUID);
        File Folder = new File(String.valueOf(targetLocation));

        uuid_collision:
        while(true) { //혹시라도 uuid가 겹칠 수 있기때문
            // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
            if (!Folder.exists()) {
                try {
                    Folder.mkdir(); //폴더 생성합니다.
                    break;
                } catch (Exception e) {
                    throw new FileStorageException("uncheckedError", e);
                }
            } else { //uuid가 겹치면
                uuid = UUID.randomUUID(); //uuid 재발급
                randomUUID = uuid.toString();
                targetLocation = this.fileStorageLocation.resolve(randomUUID);
                Folder = new File(String.valueOf(targetLocation));
                break uuid_collision; //다시 롤백
            }
        }

        try {

            //fileStorageLocation.
            targetLocation = targetLocation.resolve(fileName);

            //확장자 검사
            if (!chkAllowedExtension(extension))
                throw new FileStorageException("notSupportFileFormat");

            Path formac = Paths.get(fileName); //맥에서 파일이름 자모음 분리되는 현상때문에 추가

            // Copy file to the target location (Replacing existing file with the same name)
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // db에 작성
            fileDAO.addFile(String.valueOf(formac), String.valueOf(targetLocation),
                    Long.valueOf(file.getSize()).intValue(), extension);

            return targetLocation;

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public boolean chkAllowedExtension(String extension){
        String[] allowedExtension = {"zip", "alz", "tar", //압축파일
                "jpeg", "jpg", "bmp", "png", "gif", "tif", //이미지
                "hwp", "ppt", "pptx", "doc", "xls", "xlsx", "pdf", "txt" //문서
        };

        for (int i=0; i<allowedExtension.length; i++) {
            if (Objects.equals(extension, allowedExtension[i]))
                return true;
        }
        return false;
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}
