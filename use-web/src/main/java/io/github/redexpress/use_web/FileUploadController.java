package io.github.redexpress.use_web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

// curl -v -X POST -F file=@hello.txt  http://localhost:8080/upload
@RestController
public class FileUploadController {
    static Logger log = LoggerFactory.getLogger(FileUploadController.class);
    @PostMapping("/upload")
    public String upload(@RequestParam MultipartFile file) throws IllegalStateException, IOException {
        if (file.isEmpty()) {
            // allow empty
        }
        String contentType = file.getContentType();
        String fileName = file.getName();
        log.info("Server fileName：" + fileName);
        String origFileName = file.getOriginalFilename();
        Long fileSize = file.getSize();
        File target = new File(origFileName);
        log.info("target {}", target.getAbsolutePath());
        file.transferTo(target);
        return String.format(file.getClass().getName() + "Upload Successful\n%s %s %s",
                origFileName, contentType, fileSize);

    }
}
