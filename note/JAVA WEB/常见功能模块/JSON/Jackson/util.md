

```javascript
package com.yss.acs.third.cqrcb.util;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yss.sofa.framework.exception.SOFARuntimeException;

/**
 * JSON工具
 * 
 * @author LiuZhongbin
 * @version 1.0, 2022-1-19
 * @since 1.0, 2022-1-19
 */
public class JSONUtil {

	private static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public static String toJson(Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			throw new SOFARuntimeException("JSON转换失败",e);
		} 
	}

	public static <T> T toObject(String value, Class<T> beanClass) {
		try {
			return mapper.readValue(value, beanClass);
		} catch (Exception e) {
			throw new SOFARuntimeException("JSON转换失败",e);
		} 
	}
	
	public static <T> T toObject(Map<String,String> map, Class<T> beanClass) {
		try {
			return mapper.convertValue(map, beanClass);
		} catch (Exception e) {
			throw new SOFARuntimeException("JSON转换失败",e);
		} 
	}
	
	public static <T> T toObject(String json,Class<T> type,Class<?> genericType) {
		try {
			return mapper.readValue(json, mapper.getTypeFactory().constructParametricType(type, genericType));
		} catch (Exception e) {
			throw new SOFARuntimeException("JSON转换失败",e);
		} 
	}
}

```

