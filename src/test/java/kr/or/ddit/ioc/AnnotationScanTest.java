package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;
import kr.or.ddit.ranger.dao.RangerDaoImpl;
import kr.or.ddit.ranger.service.IRangerService;
import kr.or.ddit.ranger.service.RangerServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-annotation.xml")
public class AnnotationScanTest {

	private Logger logger = LoggerFactory.getLogger(AnnotationScanTest.class);
	
	@Resource(name="rangerDaoImpl")
	private IRangerDao rangerDao;
	
	@Resource(name="rangerServiceImpl")
	private IRangerService rangerService;
	
	
	/**
	 * Method : testRangerDaoBean
	 * 작성자 : goo84
	 * 변경이력 :
	 * Method 설명 : rangerDao 빈 컴포넌트 스캔 테스트
	 */
	@Test
	public void testRangerDaoBean() {
		/***Given***/
		
		/***When***/
		logger.debug("rangerDao : {}", rangerDao.getRangers());
		
		/***Then***/
		assertNotNull(rangerDao);
	}

	/**
	 * Method : testRangerServiceBean
	 * 작성자 : goo84
	 * 변경이력 :
	 * Method 설명 : rangerService 빈 컴포넌트 스캔 테스트
	 */
	@Test
	public void testRangerServiceBean() {
		/***Given***/
		
		/***When***/
		logger.debug("rangerService : {}", rangerService.getRangers());
		
		/***Then***/
		assertNotNull(rangerService);
	}

}
