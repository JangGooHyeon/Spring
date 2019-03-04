package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public String user(@RequestParam("userId")String userId, Model model){
		
		UserVo userVo = userService.selectUser(userId);
		model.addAttribute("userVo", userVo);
		
		return "user/user";
	}
	
	@RequestMapping("/profileImg")
	public void profileIng(HttpServletResponse response, HttpServletRequest request, @RequestParam("userId")String userId) throws IOException{
		response.setHeader("Content-Disposition", "attachment; filename=profile.png");
		response.setContentType("image/png");
//		response.setContentType("application/octet-stream");
//		response.setContentType("image");
		
		//1.사용자 아이디 파라미터 확인
//		String userId = request.getParameter("userId");
		
		//2. 해당 사용자 아이디로 사용자 정보 조회(realFileName)
		UserVo userVo = userService.selectUser(userId);
		
		//3-1. realFileName이 존재할 경우
		//3-1-1. 해당 경로의 파일을  FileInputStream으로 읽는다
		FileInputStream fis;
		if(userVo != null && userVo.getRealFileName() != null){
			fis = new FileInputStream(new File(userVo.getRealFileName()));
		}
		
		//3-2. realFileName이 존재하지 않을 경우
		//3-2-1. /upload/no_image.jpg (application.getRealPath())
		else {
			ServletContext application = request.getServletContext();
			String noimagePath = application.getRealPath("/upload/no_image.jpg");
			fis = new FileInputStream(new File(noimagePath));
		}
		
		//4. FileInputStream을 response객체의 outputStream객체에 write
		ServletOutputStream sos = response.getOutputStream();
		
		byte[] buff = new byte[512];
		int len = 0;
		
		while((len = fis.read(buff)) > -1){
			sos.write(buff);
		}
		
		sos.close();
		fis.close();
	}
	
}
