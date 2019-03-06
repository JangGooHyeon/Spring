package kr.or.ddit.user.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.or.ddit.user.model.UserVo;

public class UserVoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		//해당 객체에 할당하는 것이 가능한지 체크하는 메소드(Spring에서 호출)
		return UserVo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		//target은 검증할 객체를 spring이 넘겨주는 부분 -> 검증이 제대로 이루어 진다면 UserVo객체가 담긴다.
		UserVo userVo = (UserVo)target;
		
		//비밀번호는 8자리 이상 -> 검증
		if(userVo.getPass().length() < 8){
			//error에 인자로 받은 에러코드를 검증(컬럼명, 값)
			errors.rejectValue("pass","passLen");
		}
		
		//사용자 아이디 빈값이 아닌경우 ->검증
		if(userVo.getUserId().equals("")){
			errors.rejectValue("userId", "required");
		}
		
		if(userVo.getUserId().length() < 6){
			errors.rejectValue("userId", "userIdLen");
		}
	}

}
