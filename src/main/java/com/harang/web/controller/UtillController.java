package com.harang.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.harang.web.domain.FileDTO;
import com.harang.web.utill.MediaUtil;
import com.harang.web.utill.UploadBean;


@Controller
public class UtillController {

	/*--------------작은 이미지 보이기 컨트롤러-------------------*/
	@ResponseBody
	@RequestMapping(value="/displayFile", produces="text/plain;charset=UTF-8")
	public ResponseEntity<byte[]> displayFile(String fileName, HttpServletRequest request)throws Exception{
		
		//웹 서버에 저장된 파일 경로 불러오기
		String uploadPath = request.getSession().getServletContext().getRealPath("/");
		
		System.out.println("displayFile"+fileName);
		System.out.println("uploadPath"+uploadPath);
		
		InputStream in=null;
		
		ResponseEntity<byte[]> entity=null;
		
		try{
			String formatName=fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType= MediaUtil.getMediaType(formatName);
			HttpHeaders headers=new HttpHeaders();
			
			in = new FileInputStream(uploadPath + fileName);
			
			if(mType != null){
				headers.setContentType(mType);	
			}else{
				fileName=fileName.substring(fileName.indexOf('_')+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment;filename=\""+
						new String(fileName.getBytes("UTP-8"),"UTF-8")+"\"");
			}
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),
					headers,HttpStatus.CREATED);
			
		}catch(Exception e){
			e.printStackTrace();
			entity=new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally{
			in.close();
		}
		return entity;
	}
	
}
