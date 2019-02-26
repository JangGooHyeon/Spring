package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context-collection.xml")
public class CollectionBeanTest {
	
	private Logger logger = LoggerFactory.getLogger(CollectionBeanTest.class);
	
	@Resource(name="collectionBean")
	private CollectionBean collectionBean;
	
	/**
	 * Method : testCollectionBean
	 * 작성자 : goo84
	 * 변경이력 :
	 * Method 설명 : 스프링 설정파일을 통해 컬렉션 객체를 생성하고, 주입받는다.
	 * 				->> list. set, map, properties
	 */
	@Test
	public void testCollectionBean() {
		/***Given***/
		
		/***When***/
		//List
		List<String> list = collectionBean.getList();
		logger.debug("====================================");
		for(String str : list) {
			logger.debug("list : {}", str);
		}
		logger.debug("list size : {}", list.size());
		logger.debug("====================================");
		
		//Set
		Set<String> set = collectionBean.getSet();
		for(String str : set) {
			logger.debug("set : {}", str);
		}
		logger.debug("set size : {}", set.size());
		logger.debug("====================================");
		
		//Map
		Map<String, String> map = collectionBean.getMap();
		logger.debug("map : {}", map.get("brown"));
		logger.debug("map : {}",map.get("sally"));
		logger.debug("map : {}",map.get("cony"));
		logger.debug("map size : {}", map.size());
		logger.debug("====================================");
		
		//Properties
		Properties properties = collectionBean.getProperties();
		logger.debug("properties : {}", properties.get("brown"));
		logger.debug("properties : {}", properties.get("sally"));
		logger.debug("properties : {}", properties.get("cony"));
		logger.debug("properties size : {}", properties.size());
		logger.debug("====================================");
		
		/***Then***/
		//List
		assertNotNull(list);
		assertEquals(3, list.size());
		
		//Set
		assertNotNull(set);
		assertEquals(3, set.size());
		
		//Map
		assertNotNull(map);
		assertEquals(3, map.size());
		
		//Properties
		assertNotNull(properties);
		assertEquals(3, properties.size());
	}
	

}
