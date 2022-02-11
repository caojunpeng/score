package com.cao.score.utiles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScoreStringUtils extends StringUtils {

    private static Logger logger = LoggerFactory.getLogger(ScoreStringUtils.class);

    /**
     * 判断字符串是否为空
     * @param value
     * @return
     */
    public static boolean isBlank(String value){
        boolean flag = false;
        if(value == null || value.trim().equals("null") || value.trim().equals("")){
            flag = true;
        }
        return flag;
    }

    /**
     * 判断字符串是否不为空
     * @param value
     * @return
     */
    public static boolean isNotBlank(String value){
        boolean flag = false;
        if(!isBlank(value)){
            flag = true;
        }
        return flag;
    }

    /**
     *
     * @param data
     * @return
     */
    public static String getJsonObj(Object data){
        ObjectMapper mapper = new ObjectMapper();
        String returnValue = "";
        try {
            returnValue = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            logger.error("json转换失败"+e.getMessage(),e);
        }
        return returnValue;
    }

}
