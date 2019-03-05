package kr.or.ddit.prod.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.util.model.PageVo;

public class LprodDaoTest extends LogicTestConfig {

	@Resource(name="lprodDao")
	private ILprodDao lprodDao;
	
	@Resource(name="prodDao")
	private IProdDao prodDao;
	
	SqlSession sqlSession;
	
	@Before
	public void setup(){
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		sqlSession = sqlSessionFactory.openSession();
	}
	
	@After
	public void testDown(){
		sqlSession.commit();
		sqlSession.close();
	}
	
	@Test
	public void testGetAllProd() {
		/***Given***/
		/***When***/
		List<LprodVo> list = lprodDao.getAllLprod(sqlSession);
		
		/***Then***/
		assertNotNull(list);
	}
	
	@Test
	public void testLprodPagingList(){
		/***Given***/
		PageVo pageVo = new PageVo(1, 5);
		/***When***/
		List<LprodVo> list = lprodDao.selectLprodPagingList(sqlSession, pageVo);
		
		/***Then***/
		assertNotNull(list);
		assertEquals(5, list.size());
	}
	
	@Test
	public void testGetLprodCnt(){
		/***Given***/
		/***When***/
		int cnt = lprodDao.getLprodCnt(sqlSession);
		
		/***Then***/
		assertEquals(14, cnt);
	}
	
	@Test
	public void testSelectProdList(){
		/***Given***/
		String prod_lgu = "P101";
		/***When***/
		List<ProdVo> list = prodDao.selectLprod(sqlSession, prod_lgu);

		/***Then***/
		assertNotNull(list);
		
	}
	
	

}
