package kr.or.ddit.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.model.PageVo;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Resource(name="userService")
	private IUserService userService;
	
	/**
	 * Method : userAllList
	 * 작성자 : goo84
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 전체 조회
	 */
	@RequestMapping("userAllList")
	public String userAllList(Model model){
		List<UserVo> userList = userService.getAllUser();
		
		//사용자 전체 정보를 request객체에 속성으로 설정
		//request.setAttribute("userList", userList);
		model.addAttribute("userList", userList);
		return "user/userAllList";
	}
	
	@RequestMapping("userPagingList")
	public String userPagingList(PageVo pageVo, Model model){
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize", pageVo.getPageSize());
		model.addAttribute("page", pageVo.getPage());
		
		return "user/userPagingList";
	}
	
	
}
