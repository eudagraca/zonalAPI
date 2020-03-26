package mz.co.zonal.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;


@Component
public class Disk {

    private static String folder;

    public Disk() {
    }

    public Disk(String folder) {
        Disk.folder = folder;
    }

    public String saveImage(MultipartFile file) throws IOException {
        String fileExtension = getFileExtension(file);
        String filename = getRandomString();
        File targetFile = getTargetFile(fileExtension, filename);
//        byte[] bytes = file.getBytes();
        file.transferTo(targetFile);
        return targetFile.getAbsolutePath();
    }

    private String getRandomString() {
        return new SimpleDateFormat("yyyy-MM-dd-HHmmss")
                .format(new Date())+"-"+UUID.randomUUID();
    }

    public static File getTargetFile(String fileExtension, String fileName) {
        String UPLOAD_DIR = "/home/euclidio/Desktop/zonal/images/";
        return new File(UPLOAD_DIR + folder+"/"+ fileName + fileExtension);
    }

    public static String getFileExtension(MultipartFile inFile) {
        return Objects.requireNonNull(inFile.getOriginalFilename())
                .substring(inFile.getOriginalFilename().lastIndexOf('.'));
    }

    public static String extractFileName(String path) {
        String[] split = path.split("_");
        return split[1];
    }

}