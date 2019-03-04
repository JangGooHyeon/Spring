package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.test.WebTestConfig;
import kr.or.ddit.user.model.UserVo;

public class UserControllerTest extends WebTestConfig {
	
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
		assertEquals(8, userList.size());
		assertEquals(11, page);
		assertEquals(10, pageSize);
		
	}

}
