package kr.or.ddit.prod.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.prod.service.ILprodService;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.test.WebTestConfig;

public class LprodControllerTest extends WebTestConfig {
	
	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	@Resource(name="prodService")
	private IProdService prodService;
	
	@Test
	public void testAllLprodList() throws Exception {
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprodAllList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("prod/lprodList", viewName);
	}
	
	@Test
	public void testLprodPagingList() throws Exception{
		/***Given***/
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprodPagingList")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("prod/lprodPagingList", viewName);
	}
	
	@Test
	public void testProdList() throws Exception{
		/***Given***/
		String prod_lgu = "P101";
		
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/prodList").param("lprodgu", prod_lgu)).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		
		/***Then***/
		assertEquals("prod/prodList", viewName);
	}

}
