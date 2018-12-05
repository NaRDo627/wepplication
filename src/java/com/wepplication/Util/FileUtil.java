package com.wepplication.Util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

    public void makeBasePath(String path) {
        File dir = new File(path); 
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    public String saveFile(MultipartFile file, String basePath, String fileName){
        if (file == null || file.getName().equals("") || file.getSize() < 1) {
            return null;
        }
     
        makeBasePath(basePath);
        String serverFullPath = basePath + fileName;
  
        File file1 = new File(serverFullPath);
        try {
            file.transferTo(file1);
        } catch (IllegalStateException ex) {
            System.out.println("IllegalStateException: " + ex.toString());
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.toString());
        }
        
        return serverFullPath;
    }
    
    public String getNewName() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        return ft.format(new Date()) + (int) (Math.random() * 10);
    }
    
    public String getFileExtension(String filename) {
          Integer mid = filename.lastIndexOf(".");
          return filename.substring(mid, filename.length());
    }

    public String getRealPath(String path, String filename) {
        return path + filename.substring(0,4) + "/";
    }
    
    public void fileDownload(HttpServletResponse response, String path, String filename) {
        String realPath = "";
        
        try {
            filename = URLEncoder.encode(filename, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            System.out.println("UnsupportedEncodingException");
        }
        
        realPath = path + filename;

        File file1 = new File(realPath);
        if (!file1.exists()) {
            return ;
        }
        
        response.setHeader("Content-Disposition", "attachment; filename=\""+filename+"\"");
        response.setContentType("application/octet-stream;");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setDateHeader("Expires", 0);
        
		try {
			OutputStream os = response.getOutputStream();
	        FileInputStream fis = new FileInputStream(realPath);
	
	        int ncount = 0;
	
	        byte[] bytes = new byte[512];
	
	        while((ncount = fis.read(bytes)) != -1 ) {
	            os.write(bytes, 0, ncount);
	        }
	
	        fis.close();
	        os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public void imageDownload(HttpServletRequest request,
                             HttpServletResponse response, String path, String filename) {
        String realPath = "";

        try {
            filename = URLEncoder.encode(filename, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            System.out.println("UnsupportedEncodingException");
        }


        // 다운로드 파일 정보 조회
        String filenameNoExt = filename.substring(0, filename.lastIndexOf('.'));
        realPath = path + filenameNoExt;
        File file1 = new File(realPath);
        if (!file1.exists()) {
            return ;
        }

       /* response.setHeader("Content-Disposition", "attachment; filename=\""+filename+"\"");
        response.setContentType("application/octet-stream;");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setDateHeader("Expires", 0);*/

        try {
            OutputStream os = response.getOutputStream();
            FileInputStream fis = new FileInputStream(realPath);

            ServletContext context = request.getSession().getServletContext();

            int contentLengt = fis.available();
            // HTTP 다운로드 프로토콜 정의
            setHttpDowndloadProtocol(context, response , realPath, contentLengt, filename);

            // 다운로드 파일 쓰기
            writeToResponse(fis, os);


            fis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setHttpDowndloadProtocol(
            ServletContext context
            , HttpServletResponse response
            , String downloadFilePath
            , int contentLength, String fileName) {
        String mimeType = getMimeType(context, downloadFilePath);

        response.setContentType(mimeType);
        response.setContentLength(contentLength);


        String contentDisposition = String.format("attachment; filename=\"%s\"", fileName);
        response.setHeader("Content-Disposition", contentDisposition);
    }


    private String getMimeType(
            ServletContext context
            , String downloadFilePath) {
        String mimeType = context.getMimeType(downloadFilePath);
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }
        return mimeType;
    }

    private void writeToResponse(
            FileInputStream inputStream
            , OutputStream outStream) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead = -1;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
    }
}
