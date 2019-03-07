package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
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
//		return "user/userAllList";
		return "userAllListTiles";
	}
	
	/**
	 * Method : userPagingList
	 * 작성자 : goo84
	 * 변경이력 :
	 * @param pageVo
	 * @param model
	 * @return
	 * Method 설명 : 사용자 조회 페이징
	 */
	@RequestMapping("userPagingList")
	public String userPagingList(PageVo pageVo, Model model){
		
		Map<String, Object> resultMap = userService.selectUserPagingList(pageVo);
		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize", pageVo.getPageSize());
		model.addAttribute("page", pageVo.getPage());
		
//		return "user/userPagingList";
		return "userPagingListTiles";
	}
	
	/**
	 * Method : user
	 * 작성자 : goo84
	 * 변경이력 :
	 * @param userId
	 * @param model
	 * @return
	 * Method 설명 : 사용자 상세 화면
	 */
	@RequestMapping(path="/user", method=RequestMethod.GET)
	public String user(@RequestParam("userId")String userId, Model model){
		
		UserVo userVo = userService.selectUser(userId);
		model.addAttribute("userVo", userVo);
		
//		return "user/user";
		return "userTiles";
	}
	
	@RequestMapping("/profileImg")
	public void profileImg(HttpServletResponse response, HttpServletRequest request, @RequestParam("userId")String userId) throws IOException{
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
//			String noimagePath = application.getRealPath("/upload/no_image.jpg");
			fis = new FileInputStream(new File("c:\\picture\\no_image.jpg"));
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
	
	/**
	 * Method : userForm
	 * 작성자 : goo84
	 * 변경이력 :
	 * @return
	 * Method 설명 : 사용자 등록화면 요청
	 */
	@RequestMapping(path="/userForm", method=RequestMethod.GET)
	public String userForm(){
		return "user/userForm";
	}
	
	@RequestMapping(path="/userForm", method=RequestMethod.POST)
	public String userForm(UserVo userVo, @RequestPart("profile")MultipartFile profile,
							HttpSession session, Model model) throws IllegalStateException, IOException{
		
		//중복체크 셋팅
		UserVo duplicateUserVo = userService.selectUser(userVo.getUserId());
		
		String filename = "";
		String realFileName = "";
		
		//중복체크를 통과했을 경우(신규등록 진행)
		if(duplicateUserVo == null){
			
			//사용자 프로필 사진읋 업로드 한 경우
			if(profile.getSize() > 0){
				filename 	= profile.getOriginalFilename();
				realFileName = "c:\\picture\\" + UUID.randomUUID().toString();
				
				profile.transferTo(new File(realFileName));
			}
			
			userVo.setFileName(filename);
			userVo.setRealFileName(realFileName);
			
			int insertCnt = 0;
			
			try {
				insertCnt = userService.insertUser(userVo);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(insertCnt == 1){ //입력 성공 시
				session.setAttribute("msg", "정상 등록 되었습니다.");
				return "redirect:/user/userPagingList"; //contextPath 작업 필요
			} else { //입력 실패시
				return "user/userForm";
			}
			
		} else { //중복체크를 통과하지 못했을 경우(중복된 경우)
			model.addAttribute("msg", "중복된 사용자가 있습니다.");
			return "user/userForm";
		}
		
	}
	
	/**
	 * Method : userModifyForm
	 * 작성자 : goo84
	 * 변경이력 :
	 * @param userId
	 * @param model
	 * @return
	 * Method 설명 : 사용자 정보수정 화면 요청
	 */
	@RequestMapping(path="/userModifyForm", method=RequestMethod.GET)
	public String userModifyForm(@RequestParam("userId")String userId, Model model){
		
		UserVo userVo = userService.selectUser(userId);
		model.addAttribute("userVo", userVo);
		
		return "user/userModify";
	}
	
	/**
	 * Method : userModifyForm
	 * 작성자 : goo84
	 * 변경이력 :
	 * @param userVo
	 * @param profile
	 * @param session
	 * @param model
	 * @return
	 * Method 설명 : 사용자 정보 수정 처리요청
	 */
	@RequestMapping(path="/userModifyForm", method=RequestMethod.POST)
	public String userModifyForm(UserVo userVo, @RequestPart("profile")MultipartFile profile,
			HttpSession session, HttpServletRequest request, Model model, RedirectAttributes ra)throws IllegalStateException, IOException{
		String filename = "";
		String realFileName = "";
		
		if(profile.getSize() > 0){
			filename 	= profile.getOriginalFilename();
			realFileName = "c:\\picture\\" + UUID.randomUUID().toString();
			
			profile.transferTo(new File(realFileName));
		}
		
		//사용자 비밀번호 암호화 로직
		userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		
		userVo.setFileName(filename);
		userVo.setRealFileName(realFileName);
		
		//비밀번호 수정 요청여부
		//사용자가 값읋 입력하지 않은 경우 => 기존 비밀번호 유지
		if(userVo.getPass().equals("")){
			UserVo userVoForPass = userService.selectUser(userVo.getUserId());
			userVo.setPass(userVoForPass.getPass());
			
		} else { // 사용자가 비밀번호를 신규 등록한 경우
			userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		}
		
		int updateCnt = 0;
		
		try {
			updateCnt = userService.updateUser(userVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(updateCnt == 1){ //입력 성공 시
			
//			model.addAttribute("userVo", userVo);
//			session.setAttribute("msg", "정상적으로 수정되었습니다.");
			
			//redirect로 파라미터를 보내는 방법
			//1. url로 작성
			// "redirect:/user/user?userId?userId=" + userVo.getUserId();
			//2. model객체의 속성 : 저장된 속성을 자동으로 파라미터로 변환
			// model.addAttribute("userId", userVo.getUserId());
			// return "redirect:/user/user";
			//3. RedirectAttributes(ra) 객체를 이용해서 파라미터를 전달
			// ra.addAttrbute("userId", userVo.getUserId());
			// return "redirect:/user/user";
			
//			return "redirect:/user/user?userId="+userVo.getUserId(); // 1번 방법
			
//			model.addAttribute("userId", userVo.getUserId());		 // 2번 방법
//			return "redirect:/user/user";
			
			ra.addAttribute("userId", userVo.getUserId());			 // 3번 방법
			ra.addFlashAttribute("msg", "정상 등록 되었습니다.");
//			return "redirect:/user/user";
			return "redirect:" + request.getContextPath() + "/user/user";
			
		} else { 
			return "user/userModify";
		}
			
	}
	
}













