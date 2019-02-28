package kr.or.ddit.user.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

@Repository("userDao")
public class UserDaoImpl implements IUserDao{
	
	/**
	 * Method : getAllUser
	 * 작성자 : goo84
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 조회
	 */
	public List<UserVo> getAllUser(SqlSession sqlSession){
		List<UserVo> userList = sqlSession.selectList("user.getAllUser");
		return userList;
	}

	@Override
	public UserVo selectUser(SqlSession sqlSession, String userId) {
		UserVo selectUser = sqlSession.selectOne("user.selectUser", userId); 
		return selectUser;
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
	public List<UserVo> selectUserPagingList(SqlSession sqlSession, PageVo pageVo) {
		List<UserVo> userList = sqlSession.selectList("user.selectUserPagingList", pageVo);
		return userList;
	}

	/**
	 * Method : getUserCnt
	 * 작성자 : goo84
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 사용자 수 조회
	 */
	@Override
	public int getUserCnt(SqlSession sqlSession) {
		int userCnt = sqlSession.selectOne("user.getUserCnt");
		return userCnt;
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
	public int insertUser(SqlSession sqlSession, UserVo userVo) {
		int cnt = sqlSession.insert("user.insertUser", userVo);
		return cnt;
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
	public int updateUser(SqlSession sqlSession, UserVo userVo) {
		int cnt = sqlSession.update("user.updateUser", userVo);
		return cnt;
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
	public int updateUserPass(SqlSession sqlSession, UserVo userVo) {
		int cnt = sqlSession.update("user.updateUserPass", userVo);
		return cnt;
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
	public int deleteUser(SqlSession sqlSession, String userId) {
		int cnt = sqlSession.delete("user.deleteUser", userId);
		return cnt;
	}

	
}
