package com.spring.instagram.resource;

import com.spring.instagram.models.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {
    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;
    private final Path rootLocation;
    private final ResourceRepository resourceRepository;

    @Autowired
    public FileSystemStorageService(ResourceRepository resourceRepository) {
//        System.out.println("==================upload path: " + uploadPath);
        this.rootLocation = Paths.get("/uploads/");
        this.resourceRepository = resourceRepository;
    }

    public long registerFileToDB(MultipartFile file) {
        String url = MvcUriComponentsBuilder.fromMethodName(ResourceService.class,
                "serveFile", file.getOriginalFilename()).build().toString();
        System.out.println("url: " + url);

        return this.resourceRepository.save(new com.spring.instagram.models.Resource(file.getContentType(), new Date(), url)).getId();
    }
    @Override
    public long store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }
            Path destinationFile = this.rootLocation.resolve(
                            Paths.get(file.getOriginalFilename()))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                // This is a security check
                throw new RuntimeException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
            return registerFileToDB(file);
//            return 0L;
        }
        catch (IOException e) {
//            throw new StorageException("Failed to store file.", e);
            System.out.println("Failed to store file" + e.getMessage());
        }
        return -1L;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
//            throw new RuntimeException("Failed to read stored files", e);
            System.out.println("Failed to read stored files" + e.getMessage());
        }
        return null;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new RuntimeException(
                        "Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
//            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
            System.out.println("Could not read file: " + filename + " " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
//            throw new StorageException("Could not initialize storage", e);
            System.out.println("Could not initialized storage " + e.getMessage());
        }
    }
}
