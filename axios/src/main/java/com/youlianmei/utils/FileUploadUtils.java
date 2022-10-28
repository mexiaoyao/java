package com.youlianmei.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {

    /**
     * 判断是否为空
     * @param file
     * @return
     */
    public static Boolean isEmpty(final MultipartFile file) {
        return file.isEmpty();
    }

    /**
     * 判断是否是EXCEL
     * @param file
     * @return
     */
    public static Boolean isExcel(final MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if(StringUtils.isEmpty(fileName)){
            return false;
        }
        if(fileName.contains(".xls") || fileName.contains(".xlsx") ){
            return true;
        }
        return false;
    }


}
