package com.cao.score.utiles;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.thymeleaf.expression.Maps;

public class ResponseUtil {

private static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);
	

	// 请求错误编码
	/** 服务器内部异常 */
	public static final int SERVERUPLOAD = 10000;
	/** 参数异常 */
	public static final int PARAMS_ERROR = 10001;
	/** 结果集异常 */
	public static final int RESULT_ERROR = 10002;


	
	/**
	 * 失败json
	 * @param code
	 * @param message
	 * @param data
	 * @return
	 */
	public static String printFailJson(int code,String message,Object data){
		Map<String, Object> printMap = new HashMap<>();
		printMap.put("code", code);
		printMap.put("message", message);
		if (data==null || StringUtils.isBlank(data+"")) {
			printMap.put("data", new HashMap<>());
		}else {
			printMap.put("data", data);
		}
		ObjectMapper mapper = new ObjectMapper();
		String returnValue = "";
		try {
			returnValue = mapper.writeValueAsString(printMap);
//			logger.info("json up = "+returnValue);
		} catch (JsonProcessingException e) {
			logger.error("json转换失败"+e.getMessage(),e);
		}
		return returnValue;
	}
	
	
	/**
	 * 成功json
	 * @param message
	 * @param data
	 * @return
	 */
	public static String printJson(String message,Object data){
		Map<String, Object> printMap = new HashMap<>();
		printMap.put("code", 200);
		printMap.put("message", message);
		if(data==null){
			printMap.put("data", new HashMap<>());
		}else{
			printMap.put("data", data);
		}
		ObjectMapper mapper = new ObjectMapper();
		String returnValue = "";
		try {
			returnValue = mapper.writeValueAsString(printMap);
		} catch (JsonProcessingException e) {
			logger.error("json转换失败"+e.getMessage(),e);
		}
		return returnValue;
	}

	
}
