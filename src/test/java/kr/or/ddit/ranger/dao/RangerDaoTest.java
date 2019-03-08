package kr.or.ddit.ranger.dao;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import kr.or.ddit.test.LogicTestConfig;

public class RangerDaoTest extends LogicTestConfig{

	@Resource(name="rangerDao")
	private IRangerDao rangerDao;
	
	@Resource(name="datasource")
	private DataSource datasource;
	
	@Before
	public void setup(){
		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
		rdp.addScript(new ClassPathResource("kr/or/ddit/config/db/init.sql"));
		rdp.setContinueOnError(false);
		DatabasePopulatorUtils.execute(rdp, datasource);
	}
	
	@Test
	public void testGetRanger_MinusIndex() {
		/***Given***/
		int index = -1;
		
		/***When***/
		String ranger = rangerDao.getRanger(index);
		
		/***Then***/
		assertEquals("brown", ranger);
	}
	
	@Test
	public void testGetRanger_OverLowIndex() {
		/***Given***/
		int index = 5;
		
		/***When***/
		String ranger = rangerDao.getRanger(index);
		
		/***Then***/
		assertEquals("james", ranger);
	}

	@Test
	public void testGetRanger() {
		/***Given***/
		int index = 2;
		
		/***When***/
		String ranger = rangerDao.getRanger(index);
		
		/***Then***/
		assertEquals("sally", ranger);
	}
	
	/**
	 * Method : testGetRangersDb
	 * 작성자 : goo84
	 * 변경이력 :
	 * Method 설명 : db에서 전체 레인저 조회
	 */
	@Test
	public void testGetRangersDb(){
		/***Given***/
		
		/***When***/
		List<Map<String, String>> rangerList = rangerDao.getRnagersDb();
		
		/***Then***/
		assertEquals(5, rangerList.size());
	}
	
	/**
	 * Method : testGetRangerId
	 * 작성자 : goo84
	 * 변경이력 :
	 * Method 설명 : 아이디로 레인저 조회
	 */
	@Test
	public void testGetRangerId(){
		/***Given***/
		
		/***When***/
		Map<String, String> ranger = rangerDao.getRanger("cony");
		
		/***Then***/
		assertEquals("코니", ranger.get("NAME"));
	}
	
	/**
	 * Method : testInsertRanger
	 * 작성자 : goo84
	 * 변경이력 :
	 * Method 설명 : 신규 레인저 등록
	 */
	@Test
	public void testInsertRanger(){
		/***Given***/
		
		/***When***/
		Map<String, String> ranger = new HashMap<String, String>();
		ranger.put("id", "cogi");
		ranger.put("name", "코기새기");
		
		int insertCnt = rangerDao.insertRanger(ranger);
		
		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	 * Method : testDeleteRanger
	 * 작성자 : goo84
	 * 변경이력 :
	 * Method 설명 : 레인저 삭제
	 */
	@Test
	public void testDeleteRanger(){
		/***Given***/
		rangerDao.deleteRangerDept("brown");
		
		/***When***/
		int deleteCnt = rangerDao.deleteRanger("brown");
		
		/***Then***/
		assertEquals(1, deleteCnt);
	}
	
	
}
