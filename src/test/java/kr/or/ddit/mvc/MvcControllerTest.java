package kr.or.ddit.mvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;

import kr.or.ddit.test.WebTestConfig;

public class MvcControllerTest extends WebTestConfig {

	/**
	 * Method : testView
	 * 작성자 : goo84
	 * 변경이력 :
	 * Method 설명 : 파일 업로드 뷰 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void testView() throws Exception {
		/***Given***/
		mockMvc.perform(get("/mvc/view")).andExpect(status().isOk()).andExpect(view().name("mvc/view"));
		
		/***When***/
		/***Then***/
	}
	
	/**
	 * Method : testFileupload
	 * 작성자 : goo84
	 * 변경이력 :
	 * Method 설명 : 파일 업로드 테스트
	 * @throws Exception 
	 */
	@Test
	public void testFileupload() throws Exception{
		/***Given***/
		File profileFile = new File("C:\\Users\\goo84\\Desktop\\picture\\cogi.jpg");
		FileInputStream fis = new FileInputStream(profileFile);
		
		MockMultipartFile file = new MockMultipartFile("profile", "cogi.jpg", "image/jpg", fis);
		mockMvc.perform(fileUpload("/mvc/fileupload").file(file).param("userId", "brown")).andExpect(status().isOk()).andExpect(view().name("mvc/view"));
		
		/***When***/
		
		
		
		/***Then***/
	}

}











