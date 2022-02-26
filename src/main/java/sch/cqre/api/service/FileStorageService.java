package sch.cqre.api.service;

import lombok.NoArgsConstructor;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageService {

    private final Path fileStorageLocation;
    private final FileDAO fileDAO;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig, FileDAO fileDAO) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();
        this.fileDAO = fileDAO;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, String fileSource, int sourceUID) {

        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        UUID uuid = UUID.randomUUID();
        String randomUploadFileName = uuid.toString() + "_" + fileName;


        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(randomUploadFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);



            // db에 작성
            fileDAO.addFile(fileName, randomUploadFileName, String.valueOf(this.fileStorageLocation),
                    Long.valueOf(file.getSize()).intValue(), file.getContentType(), fileSource + String.valueOf(sourceUID));

            return randomUploadFileName;

        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
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
