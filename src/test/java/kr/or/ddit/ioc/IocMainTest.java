package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ranger.dao.IRangerDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-context.xml")
public class IocMainTest {

	private Logger logger = LoggerFactory.getLogger(IocMainTest.class);
	
	//DI(Dependancy Injection)
	@Resource(name="rangerDaoSpringBean")
	private IRangerDao rangerDao;
	
	@Resource(name="rangerDaoSpringBean")
	private IRangerDao rangerDao2;
	
	@Resource(name="rangerDao")
	private IRangerDao rangerDao3;
	
	@Resource(name="rangerDaoPrototype")
	private IRangerDao rangerDaoPrototype1;

	@Resource(name="rangerDaoPrototype")
	private IRangerDao rangerDaoPrototype2;
	

	@Test
	public void test() {
		//기존의 방법
		//ApplicationContext context = new ...
		//DL을 통해 스프링 컨테이너에 스프링 빈을 요청
		//IRangerDao rangerDao = context.getBean("rangerDao");
		
		//변경방법
		//스프링 컨테이너 생성을 junit runner에게 위임
		//우리가 사용하고자 하는 객체를 DI(Dependancy Injection)를 통해 주입받는다.
		//@Autowired(스프링), @Resource(java표준)
		
		//테스트 대상
		//1. 스프링 빈이 정상적으로 생성되고, 주입에서의 문제가 없는지
		
		assertNotNull(rangerDao);
		
	}
	
	/**
	 * Method : testSpringSingletonBean
	 * 작성자 : goo84
	 * 변경이력 :
	 * Method 설명 : 같은 이름의 스프링 빈(scope = singleton)을 두번 주입 받았을 때, 해당 객체가 동일한 객체인지 비교
	 */
	@Test
	public void testSpringSingletonBean() {
		/***Given***/
		
		/***When***/
		
		/***Then***/
		assertEquals(rangerDao, rangerDao2);
	}
	
	/**
	 * Method : testSpringSingletonBean2
	 * 작성자 : goo84
	 * 변경이력 :
	 * Method 설명 : 같은 클래스로 선언된 서로 다른 스프링 빈(singleton)이 디자인 패턴의 정의대로 두 스프링 빈이 동일한지 테스트
	 */
	@Test
	public void testSpringSingletonBean2() {
		/***Given***/

		/***When***/
		
		/***Then***/
		//디자인 패턴에 의하면 같은 클래스로부터 하나의 인스턴스만 존재하게 하는 패턴 - 싱글톤
		//rangerDaoSpringBean과 rangerDao 스프링 빈은 같은 클래스(RangerDaoImpl)로 부터 생성된 객체
		//디자인 패턴 정의에 의해서 두 객체는 서로 같아야 한다.
		
		//스프링 bean scope에서 정의하는 singleton은 스프링 이름 단위로 객체가 생성된다는 것을 의미한다.
		// --> rangerDaoSpringBean과 rangerDao는 같은 클래스로부터 생성되었지만 spring bean이름을 다르게 선언했기 때문에 스프링 컨테이너 내에서는 서로 다른 객체로 인식한다.
		assertNotEquals(rangerDao, rangerDao3);
	}
	
	@Test
	public void testSpringPrototypeBean() {
		/***Given***/

		/***When***/
		logger.debug("rangerDaoPrototype1 : {}, rangerDaoPrototype1 : {}", rangerDaoPrototype1, rangerDaoPrototype2);
		
		/***Then***/
		assertNotEquals(rangerDaoPrototype1, rangerDaoPrototype2);
	}
	

}
