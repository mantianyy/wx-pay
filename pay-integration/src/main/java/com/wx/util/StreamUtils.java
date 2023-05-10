package com.wx.util;


import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

public class StreamUtils {

    public static InputStream getInputStream(InputStream inputStream,String contentEncoding) throws IOException {
        //ServletInputStream stream = request.getInputStream();
        //String contentEncoding = request.getHeader("Content-Encoding");
        if (null != contentEncoding && contentEncoding.indexOf("gzip") != -1) {
            try {
                final GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
                ServletInputStream newStream = new ServletInputStream() {
                    @Override
                    public int read() throws IOException {
                        return gzipInputStream.read();
                    }

                    @Override
                    public boolean isFinished() {
                        return false;
                    }

                    @Override
                    public boolean isReady() {
                        return false;
                    }

                    @Override
                    public void setReadListener(ReadListener readListener) {}
                };
                return newStream;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return inputStream;
    }


    public static String stream2String(InputStream is) {
        // TODO Auto-generated method stub
        //在读取的过程中，将读取的内容存储在缓存中，然后一次性的转化成字符串返回
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //读流
        byte[] buffer =new byte[1024];
        int temp=-1;
        try {
            while((temp = is.read(buffer))!=-1){
                bos.write(buffer,0,temp);
            }
            return bos.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                is.close();
                bos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;

    }


}
