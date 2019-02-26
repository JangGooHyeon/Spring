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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-palceholder.xml")
public class DbPropertiesTest {

	private Logger logger = LoggerFactory.getLogger(DbPropertiesTest.class);
	
	@Resource(name="dbProperties")
	private DbProperties dbProperties;
	
	@Test
	public void testPlaceHolder() {
		/***Given***/
		
		/***When***/
		String url 			   = dbProperties.getUrl();
		String username 	   = dbProperties.getUsername();
		String password 	   = dbProperties.getPassword();
		String driverClassName = dbProperties.getDriverClassName();
		
		logger.debug("url : {}", url);
		logger.debug("username : {}", username);
		logger.debug("password : {}", password);
		logger.debug("driverClassName : {}", driverClassName);
		
		/***Then***/
		assertNotNull(dbProperties);
		assertEquals("jdbc:oracle:thin:@localhost:1521:XE", url);
		assertEquals("sabsaldog", username);
		assertEquals("java", password);
		assertEquals("oracle.jdbc.driver.OracleDriver", driverClassName);
	}

}
