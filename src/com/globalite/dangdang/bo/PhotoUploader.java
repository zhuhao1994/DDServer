package com.globalite.dangdang.bo;

/**
 * Created by zhu on 2016/9/5.
 */

import org.apache.struts.upload.FormFile;

        import java.io.File;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.Random;
        import org.apache.struts.upload.FormFile;

public class PhotoUploader {
    private String basePath = null;
    private FormFile file = null;
    private static final int RANDOM_LENGTH = 8;
    public static final Long DEFAULT_IMG_INDEX = Long.valueOf(7L);
    public static final Long PHOTO_SIZE = Long.valueOf(307200L);
    public static final String[] PHOTO_TYPE = new String[]{".jpg", ".gif", ".png"};
    public static final String IMG_DIR_NAME = "img/heads/";
    public static final String ERROR_IMG_NAME = "img_big.bmp";
    public static final String ERROR_IMG_PATH = "img/heads/img_big.bmp";

    public PhotoUploader(String basePath, FormFile file) {
        this.basePath = basePath;
        this.file = file;
    }

    public String getFullFileName(String fileName) {
        return this.file != null && !"".equals(this.file.getFileName()) && this.file.getFileSize() > 0?(this.basePath == null?fileName:this.basePath + "/" + fileName):"";
    }

    public String createFileName() {
        return this.file != null && !"".equals(this.file.getFileName()) && this.file.getFileSize() > 0?getPhotoName() + this.file.getFileName().substring(this.file.getFileName().lastIndexOf(".")):"";
    }

    public static String getPhotoName() {
        String randomNumber = "";
        String ret = "";
        Random r = new Random();

        int formater;
        for(formater = 65; formater < 91; ++formater) {
            randomNumber = randomNumber + (char)formater;
        }

        for(formater = 97; formater < 123; ++formater) {
            randomNumber = randomNumber + (char)formater;
        }

        for(formater = 48; formater < 58; ++formater) {
            randomNumber = randomNumber + (char)formater;
        }

        for(formater = 0; formater < 8; ++formater) {
            int num = r.nextInt(randomNumber.length() - 1);
            ret = ret + (char)randomNumber.getBytes()[num];
        }

        SimpleDateFormat var5 = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        return var5.format(new Date()) + ret;
    }

    public static boolean checkPhotoSize(int size) {
        return size <= 0?true:(long)size <= PHOTO_SIZE.longValue();
    }

    public static boolean checkPhotoType(String fileName) {
        for(int i = 0; i < PHOTO_TYPE.length; ++i) {
            if(fileName.toLowerCase().endsWith(PHOTO_TYPE[i])) {
                return true;
            }
        }

        return false;
    }

    public void removePhoto(String fileName) {
        if(fileName != null && !"".equals(fileName)) {
            File f = new File(this.getFullFileName(fileName));
            f.delete();
        }
    }

    public boolean isExisted(String fileName) {
        if(fileName != null && !"".equals(fileName)) {
            File f = new File(this.getFullFileName(fileName));
            return f.exists();
        } else {
            return false;
        }
    }

    public String upload(String fileName) throws IOException {
        if(fileName != null && !"".equals(fileName)) {
            File f = new File(this.getFullFileName(this.createFileName()));
            if(f.exists()) {
                return null;
            } else {
                InputStream is = this.file.getInputStream();
                byte[] data = new byte[this.file.getFileSize()];
                FileOutputStream fos = new FileOutputStream(f);
                is.read(data, 0, this.file.getFileSize());
                fos.write(data);
                is.close();
                fos.close();
                return f.getName();
            }
        } else {
            return null;
        }
    }
}
