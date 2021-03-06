package kr.or.ddit.mvc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.or.ddit.exception.NoFileException;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.validator.UserVoValidator;

@Controller
public class MvcController {

	private Logger logger = LoggerFactory.getLogger(MvcController.class);
	
	/**
	 * Method : view
	 * 작성자 : goo84
	 * 변경이력 :
	 * @return
	 * Method 설명 : part를 테트스 할 view를 요청
	 */
	@RequestMapping("/mvc/view")
	public String view(){
		
		return "mvc/view";
	}
	
	/**
	 * Method : fileipload
	 * 작성자 : goo84
	 * 변경이력 :
	 * @return
	 * Method 설명 : fileupload 요청 테스트
	 */
	//파라미터 : userId(text), profile(file)
	@RequestMapping("/mvc/fileupload")
	public String fileipload(@RequestParam("userId") String userId, @RequestPart("profile") MultipartFile multipartFile){
		
		logger.debug("userId : {}", userId);
		logger.debug("getOriginalFilename : {}",multipartFile.getOriginalFilename());
		logger.debug("getName : {}",multipartFile.getName());
		logger.debug("size : {}",multipartFile.getSize());
		
		String fileName = multipartFile.getOriginalFilename() + "_" + UUID.randomUUID().toString();
		File profile = new File("c:\\picture\\" + fileName);
		try {
			multipartFile.transferTo(profile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "mvc/view";
	}
	
	@RequestMapping("/textView")
	public String textView(){
		return "mvc/textView";
	}
	
	
	//@RequestParam 어노테이션을 적용하지 않더라도 인스턴스명(변수명)과 동일하면 바인딩 처리한다.
	//파라미터명과 인스턴스명이랑 다를 경우에는 반드시 @RequestParam 어노테이션을 적용해야 바인딩 처리된다.
	//BindingResult객체는 command객체(vo)에 바인딩 과정에서 발생한 결과를 담는 객체로, 반드시 command객체 메소드 인자 뒤에 위치해다 한다.
	// 적절한 경우    : UserVo userVo, BindingResult result, Model model
	// 부적절한 경우 : UserVo userVo, Model model, BindingResult result
	// *** BindingResult는 validator에서 사용하는 Error객체와 같은 객체로 볼 수 있다.(BindingResult는 Error객체를 상속받는다.)
	@RequestMapping("/textReq")
	public String textReq(UserVo userVo, BindingResult result, Model model){
		logger.debug("userId : {}", userVo.getUserId());
		logger.debug("pass : {}", userVo.getPass());
		
		//검증을 위해 생성했던 UserVoValidator를 통해 검증하기 위해 인자를 설정
		new UserVoValidator().validate(userVo, result);
		
		//.hasErrors는 boolean타입
		if(result.hasErrors()){
			logger.debug("hasError!!!");
			return "mvc/textView";
		}
		
		//pass : 8자리 이상 -> 검증
//		if(userVo.getPass().length()<8){
//			model.addAttribute("passwordLengthMsg", "비밀번호는 8자리 이상으로 작성해야 합니다.");
//		}
		
		return "mvc/textView";
	}
	
	@RequestMapping("/textReqJsr303")
	public String textReqjsr303(@Valid UserVo userVo, BindingResult result){
		//@Valid어노테이션을 사용하여 맵핑, BindingResult 객체를 통해 검증
		logger.debug("has errors(jsr303) : {}", result.hasErrors());
		return "mvc/textView";
	}
	
	@RequestMapping("/textReqValJsr303")
	public String textReqValJsr303(@Valid UserVo userVo, BindingResult result){
		logger.debug("has errors(ValJsr303) : {}", result.hasErrors());
		return "mvc/textView";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		binder.addValidators(new UserVoValidator());
	}
	
	/**
	 * Method : throwArithmeticException
	 * 작성자 : goo84
	 * 변경이력 :
	 * @return
	 * Method 설명 : Arithmetic 예외 강제발생 메소드
	 */
	@RequestMapping("/throwArith")
	public String throwArithmeticException(){
		
		if(1==1){
			throw new ArithmeticException();
		}
		return "mvc/textView";
	}
	
	@RequestMapping("/throwNoFileException")
	public String throwNoFileException() throws NoFileException{
		Resource resource = new ClassPathResource("kr/or/ddit/config/spring/no-exsist.xml");
		
		try {
			resource.getFile();
		} catch (IOException e) {
			e.printStackTrace();
			throw new NoFileException();
		}
		
		return "mvc/textView";
	}
	
	@RequestMapping("/jsonResponse")
	public String jsonResponse(Model model){
		List<String> list = new ArrayList<String>();
		list.add("brown");
		list.add("cony");
		list.add("sally");
		list.add("james");
		list.add("moon");
		
		model.addAttribute("list", list);
		
		return "jsonView";
	}
	
	@RequestMapping("jsonResponseViewObj") 
	public View jsonResponseViewObj(Model model){
		List<String> list = new ArrayList<String>();
		list.add("brown");
		list.add("cony");
		list.add("sally");
		list.add("james");
		list.add("moon");
		list.add("cogi");
		
		model.addAttribute("list", list);
		
		return new MappingJackson2JsonView();
	}
	
	@RequestMapping("/profileImgView")
	public String profileImgView(@RequestParam(name="userId", defaultValue="brown")String userId, Model model){
		model.addAttribute("userId", userId);
		
		return "profileImgView";
	}
	
	@RequestMapping("/helloTiles")
	public String helloTiles(){
		//1. BeanNameViewResolver
		//		helloTiles()에서 리턴하는 문자열에 해당하는 bean id를 갖는 스프링 빈이 있는지 확인
		//		있으면 -> 해당 스프링 객체를 사용하여 응답이 전달
		//		없으면 -> 다음 view Resolver에서 처리
		//2. TilesViewResolver
		//		helloTiles()에서 리턴하는 문자열이 tilesConfigurer에 설정한 타일즈 설정파일의 definition의 name과 동일한 선언이 있는지 확인
		//		있으면 -> 해당 tiles 설정대로(layout extends) 응답을 생성
		//		없으면 -> 다음 view Resolver에서 처리
		
		//-> <definition name="helloTiles" extends="testLayout">에서 name이 view의 이름이 된다.
		return "helloTiles";
	}
	
	@RequestMapping("/msgView")
	public String msgView(){
		
		return "mvc/msgView";
	}
	
}
