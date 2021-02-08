package com.jt.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author wangning
 * @create 2021-02-07 16:12
 * 该工具类实现了ObjectMapper的对象的转换，
 * 同时优化了try-catch方法
 */
public class MapperUtil {
	//将我们的对象转化为json的方法
	public static String toJSON(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = null;
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 将json数据转化为对象
	 */
	public static Object toObject(String json, Class<?> targetClass) {
		ObjectMapper objectMapper = new ObjectMapper();
		Object object = null;
		try {
			object = objectMapper.readValue(json, targetClass);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return object;
	}

	/**
	 * 将json数据转化为对象
	 * 这样写与上面的相比又是在哪里
	 * 这样写不用再业务中强转
	 */
	public static <T> T toObject1(String json, Class<?> targetClass) {
		ObjectMapper objectMapper = new ObjectMapper();
		T object = null;
		try {
			object = (T) objectMapper.readValue(json, targetClass);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return object;
	}

}
