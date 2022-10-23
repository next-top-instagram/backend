package com.spring.instagram.resource;

import com.spring.instagram.login.SessionCheck;
import com.spring.instagram.models.BasicResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/resource")
public class ResourceService {
    private final StorageService storageService;
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Autowired
    public ResourceService(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping()
    public ResponseEntity listUploadedFiles() throws IOException {
        List<FileInfoModel> fileInfos = storageService.loadAll()
                .map(path ->{
                    FileInfoModel data = new FileInfoModel();
                    String filename = path.getFileName().toString();
                    data.setFilename(filename);
                    data.setUrl(MvcUriComponentsBuilder.fromMethodName(ResourceService.class,
                            "serveFile", filename).build().toString());
                    try {
                        data.setSize(Files.size(path));
                    } catch (IOException e) {
//                        log.error(e.getMessage());
                        System.out.println("err" + e.getMessage());
                    }
                    return data;
                })
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping(value = "upload")
    @ResponseBody
    @SessionCheck
    public ResponseEntity handleFileUpload(String userName, @RequestPart("file") MultipartFile file) {

        log.info("[handleFileUpload] user: " + userName + " is trying to upload file: " + file.getOriginalFilename());
        storageService.store(file);
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return ResponseEntity.status(HttpStatus.CREATED).body(new BasicResponseModel(true, null, MvcUriComponentsBuilder.fromMethodName(ResourceService.class,
                "serveFile", file.getOriginalFilename()).build().toString()));
    }

}
