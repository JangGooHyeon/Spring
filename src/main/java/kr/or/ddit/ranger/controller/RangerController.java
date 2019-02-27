package kr.or.ddit.ranger.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.ranger.model.RangerVo;
import kr.or.ddit.ranger.service.IRangerService;

@RequestMapping("/ranger")
@Controller
public class RangerController {

	@Resource(name="rangerService")
	private IRangerService rangerService;
	
	/**
	 * Method : getRangers
	 * 작성자 : goo84
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 레인저스 리스트를 조회
	 */
	//주소줄에서 localhost/ranger/getRangers 요청시 밑의 메소드에서 요청을 처리
	@RequestMapping("/getRangers")
	public String getRangers(Model model) {
		
		List<String> rangers = rangerService.getRangers();
		
		//request.setAttribute("rangers", rangers)와 동일
		model.addAttribute("rangers", rangers);
		
		return "/ranger/rangerList";
	}
	
//	@RequestMapping("/getRanger")
//	//localhost/ranger/getRanger?listIndex=2 요청시 밑의 메소드에서 요청을 처리
//	public String getRanger(HttpServletRequest request, Model model) {
//		
//		int listIndex = Integer.parseInt(request.getParameter("listIndex"));
//		String ranger = rangerService.getRanger(listIndex);
//		
//		model.addAttribute("ranger", ranger);
//		
//		return "ranger/ranger";
//	}
	
	
	//vo객체의 파라미터명과 동일한 이름의 필드가 존재하면 파라미터를 해당 필드에 바인딩 시켜준다. 
	//	--> handler adapter가 vo클래스의 필드를 검색하여 자동으로 바인딩 해준다.
	@RequestMapping("/getRanger")
	//localhost/ranger/getRanger?listIndex=2 요청시 밑의 메소드에서 요청을 처리
	public String getRanger(RangerVo rangerVo, Model model) {
		
		String ranger = rangerService.getRanger(rangerVo.getListIndex());
		
		model.addAttribute("ranger", ranger);
		
		return "ranger/ranger";
	}
	
}
