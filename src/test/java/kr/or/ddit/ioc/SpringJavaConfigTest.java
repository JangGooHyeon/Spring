package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.service.IRangerService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SpringJavaConfig.class})
public class SpringJavaConfigTest {

	private Logger logger = LoggerFactory.getLogger(SpringJavaConfigTest.class);
	
	//Autowired를 사용할 경우 타입을 보고 주입받음 -> 중복되는 타입이 없을 경우 주입에 대한 맵핑이 필요 없다.
//	@Autowired
	@Resource(name="getRangerDao")
	private IRangerDao rangerDao;
	
	@Resource(name="getRangerService")
	private IRangerService rangerService;
	
	@Test
	public void testRangerDao() {
		/***Given***/
		
		/***When***/
		logger.debug("rangerDao rangers : {}", rangerDao.getRangers());
		
		/***Then***/
		assertNotNull(rangerDao);
	}

	@Test
	public void testRangerservice() {
		/***Given***/
		
		/***When***/
		logger.debug("rangerService rangers : {}", rangerService.getRangers());
		
		/***Then***/
		assertNotNull(rangerService);
	}
	
	/**
	 * Method : testRangerDaoEquals
	 * 작성자 : goo84
	 * 변경이력 :
	 * Method 설명 : rangerService 스프링 빈에 주입된 rangerDao객체와 rangerDao 스프링 빈의 일치여부 테스트
	 */
	@Test
	public void testRangerDaoEquals() {
		/***Given***/
		
		/***When***/
		IRangerDao rangerServiceDao = rangerService.getrangerDao();
		
		/***Then***/
		assertEquals(rangerDao, rangerServiceDao);
	}

}
