package com.wx.pay.util;

import org.apache.commons.io.IOUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtil {

    public static String getValue(String path) throws IOException {
        File file = ResourceUtils.getFile(path);
        FileInputStream inputStream = new FileInputStream(file);
        int length = inputStream.available();
        byte bytes[] = new byte[length];
        IOUtils.read(inputStream, bytes);
        return new String(bytes);
    }

    /**
     * 将输入流输出到页面
     *
     * @param resp
     * @param inputStream
     * @date: 2020年11月17日
     */
    public static void writeFile(HttpServletResponse resp, InputStream inputStream) {
        OutputStream out = null;
        try {
            out = resp.getOutputStream();
            int len = 0;
            byte[] b = new byte[1024];
            while ((len = inputStream.read(b)) != -1) {
                out.write(b, 0, len);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
