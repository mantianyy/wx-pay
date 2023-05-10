package com.wx.util;

import org.apache.commons.io.IOUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtil {

    public static String getValue(String path) throws IOException {
        File file = ResourceUtils.getFile(path);
        FileInputStream inputStream = new FileInputStream(file);
        int length = inputStream.available();
        byte bytes[] = new byte[length];
        IOUtils.read(inputStream, bytes);
        return new String(bytes);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = ResourceUtils.getFile("classpath:" + "cert/apiclient_key.pem").getPath();
        System.out.println(path);
    }
}
