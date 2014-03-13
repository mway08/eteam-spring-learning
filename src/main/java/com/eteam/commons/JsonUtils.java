package com.eteam.commons;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;

public abstract class JsonUtils {

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String toJson(Object bean) {
		StringWriter stringWriter = new StringWriter();
		try {
			JsonGenerator jsonGenerator = objectMapper.getJsonFactory()
					.createJsonGenerator(stringWriter);
			objectMapper.writeValue(jsonGenerator, bean);
			return stringWriter.toString();
		} catch (IOException e) {
		}
		return "{}";
	}
}
