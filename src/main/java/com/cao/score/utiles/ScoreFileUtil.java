package com.cao.score.utiles;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class ScoreFileUtil extends FileUtils {

    private static Logger logger = LoggerFactory.getLogger(ScoreFileUtil.class);

    public static final String EXCEL = ".xlsx";

    /**
     * MultipartFile 转 File
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file, String toPath) throws Exception {

        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(toPath);
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件导出
     * @param response
     * @param path
     */
    public static  void downloadFile(HttpServletResponse response, String path) {
        File localFile = new File(path);
        if(!localFile.exists()) {
            return;
        }
        InputStream stream = null;
        OutputStream os=null;
        /* 设置文件ContentType类型，这样设置，会自动判断下载文件类型 */
        response.setContentType("multipart/form-data");
        /* 设置文件头：最后一个参数是设置下载文件名 */
        response.setHeader("Content-Disposition", "attachment;filename="+localFile.getName());
        try{
            stream=new FileInputStream(localFile);
            os = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            while((len = stream.read(b)) > 0){
                os.write(b,0,len);
            }
            os.flush();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }finally {
            try {
                os.close();
                stream.close();
            } catch (IOException e) {
                logger.info("文件流关闭异常"+e.getMessage());
            }
        }

    }

    public void exportOriginalModel(HttpServletResponse response,String fileName) {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("static/template/" + fileName);
        /* 设置文件ContentType类型，这样设置，会自动判断下载文件类型 */
        response.setContentType("multipart/form-data");
        /* 设置文件头：最后一个参数是设置下载文件名 */
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        try {
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[1024];
            int len;
            while ((len = stream.read(b)) > 0) {
                os.write(b, 0, len);
            }
            os.flush();
            os.close();
            stream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


}
