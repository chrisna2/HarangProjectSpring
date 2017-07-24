package com.harang.web.utill;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.util.FileCopyUtils;

public class UploadBean {

	//파일이름을 렌덤으로 생성
	public String uploadFile(String originalName, String uploadPath, byte[] fileData) throws IOException{
		
		UUID uid =  UUID.randomUUID();
		
		String savedName = uid.toString()+"_"+originalName;
		
		File target = new File(uploadPath,savedName);
		
		FileCopyUtils.copy(fileData,target);
		
		return savedName;
		
	}
	
	//날짜 별로 업로드 폴더 생성
	private static String calcPath(String uploadPath){
		
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);

		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath,monthPath,datePath);
		
		return datePath;
		
	}
	//실제 폴더 생성
	private static void makeDir(String uploadPath, String... paths){
		//이미 이전의 파일의 폴더가 존재 한다면...
		if(new File(paths[paths.length-1]).exists()){
			return;
		}
		
		for(String path:paths){
			File dirPath = new File(uploadPath+path);
			if(!dirPath.exists()){
				dirPath.mkdirs();				
			}
		}
	}
	
	//[이미지] 썸네일 생성
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws IOException{
		
		BufferedImage soruceImg = ImageIO.read(new File(uploadPath+path, fileName));
		
		BufferedImage destImg = Scalr.resize(soruceImg,
											 Scalr.Method.AUTOMATIC,
											 Scalr.Mode.FIT_TO_HEIGHT,
											 100);
		
		//-*-*-*-*-*-*-*-*-*
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		//-*-*-*-*-*-*-*-*-*
		
		return null;
		
		
	}
	
	
	
}
