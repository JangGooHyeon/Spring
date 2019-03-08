package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.dao.UserDaoImpl;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource(name="userDao")
	private IUserDao dao;

	public UserServiceImpl() {
	}

	/**
	 * Method : getAllUser
	 * 작성자 : goo84
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 정보 조회
	 */
	@Override
	public List<UserVo> getAllUser() {
		return dao.getAllUser();
	}

	/**
	 * Method : selectUser
	 * 작성자 : goo84
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 특정 사용자 정보 조회
	 */
	@Override
	public UserVo selectUser(String userId) {
		return dao.selectUser(userId);
	}

	/**
	 * Method : selectUserPagingList
	 * 작성자 : goo84
	 * 변경이력 :
	 * @param pageVo
	 * @return
	 * Method 설명 : 사용자 페이징 리스트 조회
	 */
	@Override
	public Map<String, Object> selectUserPagingList(PageVo pageVo) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("userList", dao.selectUserPagingList(pageVo));
		resultMap.put("userCnt", dao.getUserCnt());
		
		return resultMap;
	}

	/**
	 * Method : insertUser
	 * 작성자 : goo84
	 * 변경이력 :
	 * @param userVo
	 * @return
	 * Method 설명 : 사용자 등록
	 */
	@Override
	public int insertUser(UserVo userVo) {
		return dao.insertUser(userVo);
	}
	
	/**
	 * Method : updateUser
	 * 작성자 : goo84
	 * 변경이력 :
	 * @param userVo
	 * @return
	 * Method 설명 : 사용자 정보 수정
	 */
	@Override
	public int updateUser(UserVo userVo) {
		return dao.updateUser(userVo);
	}

	/**
	 * Method : updateUserPass
	 * 작성자 : goo84
	 * 변경이력 :
	 * @param userVo
	 * @return
	 * Method 설명 : 사용자 비밀번호 암호화 작업
	 */
	@Override
	public int updateUserPass(UserVo userVo) {
		return  dao.updateUserPass(userVo);
	}
	
	/**
	 * Method : deleteUser
	 * 작성자 : goo84
	 * 변경이력 :
	 * @param userId
	 * @return
	 * Method 설명 : 사용자 삭제
	 */
	@Override
	public int deleteUser(String userId) {
		return dao.deleteUser(userId);
	}


}
