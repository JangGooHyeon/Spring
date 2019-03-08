package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Repository("userDao")
public class UserDaoImpl implements IUserDao{

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * Method : getAllUser
	 * 작성자 : goo84
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 조회
	 */
	public List<UserVo> getAllUser(){
		return sqlSessionTemplate.selectList("user.getAllUser");
	}

	@Override
	public UserVo selectUser(String userId) {
		return sqlSessionTemplate.selectOne("user.selectUser", userId);
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
	public List<UserVo> selectUserPagingList(PageVo pageVo) {
		return sqlSessionTemplate.selectList("user.selectUserPagingList", pageVo);
	}

	/**
	 * Method : getUserCnt
	 * 작성자 : goo84
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 수 조회
	 */
	@Override
	public int getUserCnt() {
		return sqlSessionTemplate.selectOne("user.getUserCnt");
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
		return sqlSessionTemplate.insert("user.insertUser", userVo);
	}
	
	/**
	 * Method : updateUser
	 * 작성자 : goo84
	 * 변경이력 :
	 * @param sqlSession
	 * @param userVo
	 * @return
	 * Method 설명 : 사용자 정보 수정
	 */
	@Override
	public int updateUser(UserVo userVo) {
		return sqlSessionTemplate.update("user.updateUser", userVo);
	}
	
	/**
	 * Method : updateUserPass
	 * 작성자 : goo84
	 * 변경이력 :
	 * @param sqlSession
	 * @param userVo
	 * @return
	 * Method 설명 : 사용자 비밀번호 암호화 작업
	 */
	@Override
	public int updateUserPass(UserVo userVo) {
		return sqlSessionTemplate.update("user.updateUserPass", userVo);
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
		return sqlSessionTemplate.delete("user.deleteUser", userId);
	}

	
}
