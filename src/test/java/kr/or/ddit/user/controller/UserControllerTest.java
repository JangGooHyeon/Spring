package kr.or.ddit.user.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;

public class UserControllerTest extends WebTestConfig {
	
	private static final String USER_INSERT_TEST_ID = "cogitest3";
	@Resource(name="userService")
	private IUserService userService;
	
	@Before
	public void initData(){
		userService.deleteUser(USER_INSERT_TEST_ID);
	}
	
	/**
	 * Method : testUserAllList
	 * 작성자 : goo84
	 * 변경이력 :
	 * Method 설명 : 사용자 전체조회 테스트
	 * @throws Exception 
	 */
	@Test
	public void testUserAllList() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userAllList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<UserVo>userList = (List<UserVo>) mav.getModel().get("userList");
		
		
		/***Then***/
		assertEquals("user/userAllList", viewName);
		assertNotNull(userList);
		assertTrue(userList.size() > 105);
	}
	
	/**
	 * Method : testUserPagingList
	 * 작성자 : goo84
	 * 변경이력 : 사용자 조회 페이징 테스트
	 * @throws Exception
	 * Method 설명 :
	 */
	@Test
	public void testUserPagingList() throws Exception {
		/***Given***/
		int pageParam = 11; 
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userPagingList?page=" + pageParam)).andReturn();
//		MvcResult mvcResult = mockMvc.perform(get("/user/userPagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		Map<String, Object> modelMap = mav.getModelMap();
		
		List<UserVo>userList = (List<UserVo>) mav.getModelMap().get("userList");
		int userCnt = (int) modelMap.get("userCnt");
		int page = (int) modelMap.get("page");
		int pageSize = (int) modelMap.get("pageSize");
		
		
		/***Then***/
		assertEquals("user/userPagingList", viewName);
		assertNotNull(userList);
		assertEquals(10, userList.size());
		assertEquals(11, page);
		assertEquals(10, pageSize);
		
	}
	
	@Test
	public void testUser() throws Exception{
		/***Given***/
		String userId = "sally";
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/user").param("userId", userId)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		UserVo user = (UserVo) mav.getModel().get("userVo");
		
		
		/***Then***/
		assertNotNull(user);
		assertEquals("user/user", viewName);
		assertEquals("샐리", user.getUserNm());
	}
	
	@Test
	public void testUserForm() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userForm")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("user/userForm", viewName);
	}

	/**
	 * Method : testUserForm_post_success
	 * 작성자 : goo84
	 * 변경이력 :
	 * Method 설명 : 사용자 등록 요청 테스트
	 * @throws Exception 
	 */
	@Test
	public void testUserForm_post_success() throws Exception{
		/***Given***/
		File profileFile = new File("C:\\Users\\goo84\\Desktop\\picture\\smilecogi.jpg");
		FileInputStream fis = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "smilecogi.jpg", "image/jpg", fis);
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
																			.param("userId", USER_INSERT_TEST_ID)
																			.param("userNm", "코기테스트")
																			.param("alias", "코기새끼")
																			.param("addr1", "대전시 중구 대흥로 1234")
																			.param("addr2", "앞마당")
																			.param("zipcode", "12345")
																			.param("pass", "qwer1234"))
																		.andExpect(view().name("redirect:/user/userPagingList"))
																		.andReturn();
		HttpSession session = mvcResult.getRequest().getSession();
		
		/***Then***/
		assertEquals("정상 등록 되었습니다.", session.getAttribute("msg"));
	}
	
	/**
	 * Method : testUserForm_post_duplicateUser
	 * 작성자 : goo84
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 등록요청(사용자 중복으로 인한 등록 실패 케이스 테스트)
	 */
	@Test
	public void testUserForm_post_duplicateUser() throws Exception{
		/***Given***/
		File profileFile = new File("C:\\Users\\goo84\\Desktop\\picture\\smilecogi.jpg");
		FileInputStream fis = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "smilecogi.jpg", "image/jpg", fis);
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
																			.param("userId", "brown")
																			.param("userNm", "코기테스트")
																			.param("alias", "코기새끼")
																			.param("addr1", "대전시 중구 대흥로 1234")
																			.param("addr2", "앞마당")
																			.param("zipcode", "12345")
																			.param("pass", "qwer1234"))
																		.andExpect(view().name("user/userForm"))
																		.andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String msg = (String) mav.getModel().get("msg");
		
		/***Then***/
		assertEquals("중복된 사용자가 있습니다.", msg);
	}
	
	/**
	 * Method : testUserForm_post_fail_insertError
	 * 작성자 : goo84
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 등록요청(zipcode 사이즈 sql 에러 -> 데이터 용량 허용범위 초과로 인한 insert실패 테스트)
	 */
	@Test
	public void testUserForm_post_fail_insertError() throws Exception{
		/***Given***/
		File profileFile = new File("C:\\Users\\goo84\\Desktop\\picture\\smilecogi.jpg");
		FileInputStream fis = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "smilecogi.jpg", "image/jpg", fis);
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userForm").file(file)
																			.param("userId", USER_INSERT_TEST_ID)
																			.param("userNm", "코기테스트")
																			.param("alias", "코기새끼")
																			.param("addr1", "대전시 중구 대흥로 1234")
																			.param("addr2", "앞마당")
																			.param("zipcode", "1234512345") //-> varchar2(5)까지가 허용범위 -> 10바이트를 입력할 시 sql에러발생
																			.param("pass", "qwer1234"))
																		.andExpect(view().name("user/userForm"))
																		.andReturn();
		
		/***Then***/
	}
	
	@Test
	public void testUserModifyForm() throws Exception{
		/***Given***/
		String userId = "cogitest2";
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/userModifyForm?userId=" + userId)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("user/userModify", viewName);
	}
	
	/**
	 * Method : testUserModifyForm_sucess
	 * 작성자 : goo84
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 사용자 정보 수정요청 처리 테스트(수정 성공 테스트)
	 */
	@Test
	public void testUserModifyForm_sucess() throws Exception{
		/***Given***/
		File profileFile = new File("C:\\Users\\goo84\\Desktop\\picture\\cogirun.jpg");
		FileInputStream fis = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "cogirun.jpg", "image/jpg", fis);
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userModifyForm").file(file)
																			.param("userId", "cogitest2")
																			.param("userNm", "코기테스트")
																			.param("alias", "코기새끼")
																			.param("addr1", "대전시 중구 대흥로 1234")
																			.param("addr2", "앞마당")
																			.param("zipcode", "12345")
																			.param("pass", "qwer1234"))
																		.andExpect(view().name("redirect:/user/user?userId=cogitest2"))
																		.andReturn();
		HttpSession session = mvcResult.getRequest().getSession();
		
		/***Then***/
		assertEquals("정상적으로 수정되었습니다.", session.getAttribute("msg"));
	}
	
	@Test
	public void testUserModifyForm_fail() throws Exception{
		/***Given***/
		File profileFile = new File("C:\\Users\\goo84\\Desktop\\picture\\cogirun.jpg");
		FileInputStream fis = new FileInputStream(profileFile);
		MockMultipartFile file = new MockMultipartFile("profile", "cogirun.jpg", "image/jpg", fis);
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(fileUpload("/user/userModifyForm").file(file)
																			.param("userId", "cogitest")
																			.param("userNm", "코기테스트")
																			.param("alias", "코기새끼")
																			.param("addr1", "대전시 중구 대흥로 1234")
																			.param("addr2", "앞마당")
																			.param("zipcode", "1234512345")
																			.param("pass", "qwer1234"))
																		.andExpect(view().name("user/userModify"))
																		.andReturn();
//		HttpSession session = mvcResult.getRequest().getSession();
		
		/***Then***/
//		assertEquals("정상적으로 수정되었습니다.", session.getAttribute("msg"));
	}
	
}
