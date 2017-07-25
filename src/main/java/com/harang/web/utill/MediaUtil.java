package com.harang.web.utill;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

public class MediaUtil {
	
	private static Map<String,MediaType> medidMap;
	
	static{
		medidMap = new HashMap<String, MediaType>();
		medidMap.put("JPG", MediaType.IMAGE_JPEG);
		medidMap.put("GIF", MediaType.IMAGE_GIF);
		medidMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	public static MediaType getMediaType(String type){
		return medidMap.get(type.toUpperCase());
	}

}
