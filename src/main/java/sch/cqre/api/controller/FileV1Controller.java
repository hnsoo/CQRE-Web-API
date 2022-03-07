package sch.cqre.api.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sch.cqre.api.exception.CustomExeption;
import sch.cqre.api.exception.ErrorCode;
import sch.cqre.api.service.FileStorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Slf4j
@RequestMapping("/api/v1/file/*")
@RestController
@RequiredArgsConstructor
public class FileV1Controller {

    private final FileStorageService fileStorageService;

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file){

       if (file == null) {
            return "invaildInput";
        }

        String fileName = String.valueOf(fileStorageService.storeFile(file));

        String fileDownloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/file/download/")
                .path(fileName)
                .toUriString();
        //UploadFileResponse
        return fileDownloadUrl;//new UploadFileResponse(fileName, fileDownloadUri,
        //file.getContentType(), file.getSize());
    }

/*
    @DeleteMapping("/delete")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
//UploadFileResponse
        return fileDownloadUri;//new UploadFileResponse(fileName, fileDownloadUri,
        //file.getContentType(), file.getSize());
    }

 */

    /*

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
    */


    @GetMapping("/download/{fileUUID}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileUUID, HttpServletRequest request) {
        // Load file as Resource

        if(fileUUID.isBlank() || fileUUID.length() != 36) //fileUUID가 비어있거나 UUID(36자)가 아니면
            throw new CustomExeption(ErrorCode.INVALID_INPUT);

        Resource resource = fileStorageService.loadFileAsResource(fileUUID);
        if (resource == null)
            throw new CustomExeption(ErrorCode.INVALID_INPUT);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}

