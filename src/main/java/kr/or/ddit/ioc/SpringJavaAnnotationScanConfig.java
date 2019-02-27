package kr.or.ddit.ioc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//스프링 빈 설정 파일임을 알려주는 어노테이션
@Configuration
@ComponentScan(basePackages= {"kr.or.ddit"})
public class SpringJavaAnnotationScanConfig {
	
}
