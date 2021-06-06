package com.mustafakaplan.hrms.core.utilities;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileUtil {

    public static String getFilePathFromMultipartFile(MultipartFile file) {

        String filePath = null;
        try {
            filePath = file.getResource().getFile().getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }
}
