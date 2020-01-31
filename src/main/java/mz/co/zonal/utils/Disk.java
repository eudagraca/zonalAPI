package mz.co.zonal.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Component
public class Disk {

    private String folder;

    public Disk() {
    }

    public Disk(String folder) {
        this.folder = folder;
    }

    private String UPLOAD_DIR = "/home/euclidio/Desktop/zonal/images/";

    public String salvar(MultipartFile file) throws IOException {

        String fileExtension = getFileExtension(file);
        String filename = getRandomString();

        File targetFile = getTargetFile(fileExtension, filename);

        byte[] bytes = file.getBytes();
        file.transferTo(targetFile);
        String UploadedDirectory = targetFile.getAbsolutePath();

        return UploadedDirectory;
    }

    private String getRandomString() {
        return String.valueOf(UUID.randomUUID());
    }

    private File getTargetFile(String fileExtn, String fileName) {
        File targetFile = new File(UPLOAD_DIR+this.folder+"/"+ fileName + fileExtn);
        return targetFile;
    }

    private String getFileExtension(MultipartFile inFile) {
        String fileExtention = inFile.getOriginalFilename().substring(inFile.getOriginalFilename().lastIndexOf('.'));
        return fileExtention;
    }

}