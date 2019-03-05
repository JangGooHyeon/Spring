package kr.or.ddit.prod.service;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.util.model.PageVo;

public class LprodServiceTest extends LogicTestConfig {

	@Resource(name="lprodService")
	private ILprodService lprodService;

	@Resource(name="prodService")
	private IProdService prodService;
	
	@Test
	public void testGetAllLprod() {
		/***Given***/
		/***When***/
		List<LprodVo> list = lprodService.getAllLprod();
		/***Then***/
		assertNotNull(list);
	}
	
	@Test
	public void testLprodPagingList(){
		/***Given***/
		PageVo pageVo = new PageVo(1, 5);
		
		/***When***/
		Map<String, Object> resultMap = lprodService.selectLprodPagingList(pageVo);
		List<LprodVo>lprodList = (List<LprodVo>) resultMap.get("lprodList");
		
		/***Then***/
		assertNotNull(lprodList);
		assertEquals(5, lprodList.size());
	}
	
	@Test
	public void testprodList(){
		/***Given***/
		String prod_lgu = "P101";
		
		/***When***/
		List<ProdVo> prodList = prodService.selectLprod(prod_lgu);
		/***Then***/
		assertNotNull(prodList);
	}

}
