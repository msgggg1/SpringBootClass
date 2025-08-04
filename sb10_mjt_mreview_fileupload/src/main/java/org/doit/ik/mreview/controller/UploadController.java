package org.doit.ik.mreview.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.doit.ik.mreview.dto.UploadResultDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Log4j2
public class UploadController {

	/*
	//[1]
	@PostMapping("/uploadAjax")
	   public void uploadFile( @RequestParam("uploadFiles") MultipartFile[] uploadFiles ) {
	      for (MultipartFile uploadFile : uploadFiles) {
	         String originalName = uploadFile.getOriginalFilename();
	         String fileName = originalName.substring(originalName.lastIndexOf("\\")+1);
	         log.info("fileName: " + fileName);
	      }      
	   }
	 */

	// 서버에 실제 업로드 폴더
	// application.properties 변수(키) 값
	@Value("${org.doit.upload.path}")
	private String uploadPath;

	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		String folderPath = str.replace("/", File.separator);

		File uploadPathFolder = new File(uploadPath, folderPath);

		if (uploadPathFolder.exists() == false) {
			uploadPathFolder.mkdirs();
		}
		return folderPath;
	}

	@PostMapping("/uploadAjax")
	public ResponseEntity<List<UploadResultDTO>> uploadFile(@RequestParam("uploadFiles") MultipartFile[] uploadFiles) {
		log.info("🎶🎶 UploadController.uploadFile()...");

		List<UploadResultDTO> resultDTOList = new ArrayList<>();  // 1

		for (MultipartFile uploadFile : uploadFiles) {
			// 이미지 파일만 업로드 가능
			if(uploadFile.getContentType().startsWith("image") == false) {
				log.warn("this file is not image type.");   
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}

			//
			String originalName = uploadFile.getOriginalFilename();
			String fileName = originalName.substring(originalName.lastIndexOf("\\")+1);
			log.info("fileName: " + fileName);

			// 날짜 폴더 생성
			String folderPath = makeFolder();
			// UUID
			String uuid = UUID.randomUUID().toString();
			// 저장할 파일 이름 중간에 _ 이용해서 구분
			String saveName = String.format("%s%s%s%s%s_%s"
					, uploadPath, File.separator,folderPath, File.separator, uuid, fileName);
			Path savePath = Paths.get(saveName);

			try {
				uploadFile.transferTo(savePath);   // 실제 이미지 저장

				// 섬네일 생성
				String thumbnailSaveName = uploadPath+File.separator+folderPath+File.separator+"s_"+uuid+"_"+fileName;
				File thumbnailFile = new File(thumbnailSaveName);
				Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 100,100);
				//

				resultDTOList.add(new UploadResultDTO(fileName, uuid, folderPath)); // 추가 코딩.
			} catch (IllegalStateException e) { 
				e.printStackTrace();
			} catch (IOException e) { 
				e.printStackTrace();
			}

		}      
		return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
	}

	@GetMapping("/display")
	public ResponseEntity<byte[]> getFile(@RequestParam("fileName") String fileName){   // p401 서버에서 이미지 파일 데이터를 브라우저로 전송
		ResponseEntity<byte[]> result = null;

		try {
			String srcFileName = URLDecoder.decode(fileName, "UTF-8");
			log.info("fileName: "+srcFileName);

			File file = new File(uploadPath+File.separator+srcFileName);
			log.info("file: "+file);

			HttpHeaders headers = new HttpHeaders();

			// MIME타입 처리
			headers.add("Content-Type", Files.probeContentType(file.toPath()));
			// 파일 데이터 처리
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK);

		} catch (IOException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}

	@PostMapping("/removeFile")
	public ResponseEntity<Boolean> removeFile(@RequestParam("fileName") String fileName) {

		String srcFileName = null;
		try {
			srcFileName = URLDecoder.decode(fileName, "UTF-8");
			File file = new File(uploadPath + File.separator+srcFileName);
			boolean result = file.delete(); // 파일 삭제
			File thumbnail = new File(file.getParent(), "s_"+file.getName());
			result = thumbnail.delete(); // 섬네일 파일 삭제

			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (UnsupportedEncodingException e) { 
			e.printStackTrace();
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);   // 500오류
		}

	}


}
