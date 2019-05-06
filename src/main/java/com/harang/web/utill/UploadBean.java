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
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;

public class UploadBean {

	//파일이름을 렌덤으로 생성 해서 저장 + 날짜별 디렉토리 변환 <!>입력 순서에 주의!<!> 요것만 있으면 파일 저장 가능!
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws IOException{
		
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);

		//파일이름을 랜덤으로 생성해버리고 한글은 사용 못하게 만듬
		UUID uid =  UUID.randomUUID();
		//한글 문제로 인해 원래 파일의 이름은 없에기로함
		//String savedName = uid.toString()+"_"+originalName;
		String savedName = uid.toString()+"."+formatName;
		
		String savedPath = calcPath(uploadPath);
		
		File target = new File(uploadPath+savedPath,savedName);
		
		FileCopyUtils.copy(fileData,target);
		
		
		String uploadedFileName = null;
		
		if(MediaUtil.getMediaType(formatName) != null){
			//사진일 경우 현제 섬네일 이미지로 저장되어 보여짐
			//선택 1] 클릭을 해서 이미지를 크게 할수 있을 것인가? -> 내일 도전! 2017-07-28
			//선택 2] 그냥 아예 처음부터 큰 이미지를 띄울 것인가? -> makeIcon() 사용하면 해결됨. 
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		}
		else{
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		
		return uploadedFileName;
		
	}
	
	public static void deleteFile(String filename, String uploadPath){
		
		//이전 파일 삭제
		String beforeFileName = filename;
		String formatName = beforeFileName.substring(beforeFileName.lastIndexOf(".")+1);
		MediaType mtType = MediaUtil.getMediaType(formatName);
		
		if(mtType != null){
			String front = beforeFileName.substring(0, 12);
			String end = beforeFileName.substring(14);
			new File(uploadPath+(front+end).replace('/', File.separatorChar)).delete();
		}
		new File(uploadPath + beforeFileName.replace('/', File.separatorChar)).delete();
		
	}
	
	//날짜 별로 업로드 폴더 생성
	public static String calcPath(String uploadPath){
		
		Calendar cal = Calendar.getInstance();
		
		String yearPath = "/"+cal.get(Calendar.YEAR);
		
		String monthPath = yearPath + "/" + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);

		String datePath = monthPath + "/" + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath,yearPath,monthPath,datePath);
		
		return datePath;
		
	}
	//실제 폴더 생성
	public static void makeDir(String uploadPath, String... paths){
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
		
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar,'/');
		
		
	}
	//[이미지x] 이미지가 아닌 파일을 문자로 치환
	private static String makeIcon(String uploadPath, String path, String fileName){
		
		String iconName = uploadPath + path + File.separator + fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	
}
