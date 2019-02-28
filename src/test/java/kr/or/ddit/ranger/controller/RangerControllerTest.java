package kr.or.ddit.ranger.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

/*
 * 1. 스프링 컨테이너 설정 필요
 * 		-> 테스트 대상 : RangerController
 * 		   RangerController는 servlet-context.xml component scan 설정 되어있음
 * 		   RangerController는 RangerService 객체를 주입받음
 * 		   RangerService는 RangerDao객체를 주입받음
 * 		   
 * 		   **** RangerController를 만들기 위해서는 RangerService, RangerDao 스프링 빈이 필요하다.
 * 		   ****	따라서, RangerController를 스캔하는 servlet-context.xml 뿐만 아니라 RangerService, 
 * 				RangerDao를 스캔하는 application-context.xml도 필요하다. -> 복수의 ContextConfiguration필요
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:kr/or/ddit/config/spring/servlet-context.xml",
					   "classpath:kr/or/ddit/config/spring/application-context.xml"})
@WebAppConfiguration	//스프링 컨테이너를 만들때 WebApplicationContext로 생성
						//미적용시  applicationContext
public class RangerControllerTest {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;
	
	//--------테스트 코드 실행순서--------
	//@BeforeClass
	//@Before --> @Test --> @After
	//@Before --> @Test --> @After
	// ...
	//@AfterClass (static --> 사용 빈도가 떨어짐)
	
	
	//MockMvc에 url주소와 parameter를 넘겨주는 방식으로 테스트를 진행
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	/**
	 * Method : testGetRangers
	 * 작성자 : goo84
	 * 변경이력 :
	 * @throws Exception
	 * Method 설명 : 전체 레인저스 조회 테스트
	 */
	@Test
	public void testGetRangers() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/ranger/getRangers")).andReturn();
//		mockMvc.perform(post(""))
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		Map<String, Object> model = mav.getModel();
		List<String> rangers = (List<String>) model.get("rangers");
		
		
		/***Then***/
		assertEquals("ranger/rangerList", viewName);
		assertNotNull(rangers);
		assertEquals(5, rangers.size());
	}

	@Test
	public void getRanger() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/ranger/getRanger").param("listIndex", "3")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		ModelMap modelMap = mav.getModelMap();
		String ranger = (String) modelMap.get("ranger");
		
		/***Then***/
		assertEquals("ranger/ranger", viewName);
		assertEquals("moon", ranger);
		
	}
	
}


