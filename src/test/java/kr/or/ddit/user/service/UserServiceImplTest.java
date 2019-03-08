package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.test.LogicTestConfig;
import kr.or.ddit.user.dao.UserDaoImpl;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceImplTest extends LogicTestConfig {

	private Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);
	
	@Resource(name="userService")
	private IUserService userService;
	
	
	@Test
	public void testGetAllUser() {
		/***Given***/
		
		/***When***/
		List<UserVo> list = userService.getAllUser();
		for(UserVo userVo : list){
			System.out.println(userVo);
		}

		/***Then***/
		assertNotNull(list);
//		assertEquals(5, list.size());
	}
	
	@Test
	public void testSelectUser() {
		/***Given***/
		
		/***When***/
		UserVo vo = userService.selectUser("cony");
		System.out.println(vo.getPass());
		
		/***Then***/
		assertNotNull(vo);
	}
	
	@Test
	public void testSelectUserPagingList(){
		/***Given***/
		PageVo vo = new PageVo(1, 10);
		
		/***When***/
		Map<String, Object> resultMap = userService.selectUserPagingList(vo);
		List<UserVo> list = (List<UserVo>)resultMap.get("userList");
		int cnt = (Integer) resultMap.get("userCnt");
		
		for(int i=0; i<list.size(); i++) {
			System.out.println("page 1 -> " + list.get(i).getUserId());
		}
		System.out.println("페이지당 건수 : " + list.size());
		System.out.println("전체 사용자 수 : " + cnt);
		/***Then***/
		//userList
		assertNotNull(list);
		assertEquals(10, list.size());
		
		//userCnt
//		assertEquals(105, cnt);
		assertNotNull(list);
	}
	
	@Test
	public void testInsertUser(){
		
		/***Given***/
		String userId = "goo7451";
		String userNm = "장구현";
		String alias = "짱구";
		String addr1 = "경북 김천시 시청2길 30";
		String addr2 = "금류아파트 2동 1402호";
		String zipcode = "39531";
		String pass = "testpass1234";
		
		UserVo userVo = new UserVo();
		userVo.setUserId(userId);
		userVo.setUserNm(userNm);
		userVo.setAlias(alias);
		userVo.setAddr1(addr1);
		userVo.setAddr2(addr2);
		userVo.setZipcode(zipcode);
		userVo.setPass(pass);
		
		/***When***/
		
		int cnt = userService.insertUser(userVo);
		
		/***Then***/
		assertEquals(cnt, 1);
	}
	
	@Test
	public void testDeleteUser(){
		/***Given***/
		String userId = "goo7451";
		
		/***When***/
		int cnt = userService.deleteUser(userId);
		System.out.println(cnt);
		
		/***Then***/
		assertEquals(cnt, 1);
	}
	
	@Test
	public void testUpdateUser(){
		/***Given***/
		String userId = "1234";
		
		UserVo vo = userService.selectUser(userId);
		
		vo.setUserNm("qwe1");
		vo.setAlias("qwe2");
		vo.setAddr1("qwe3");
		vo.setAddr2("qwe4");
		vo.setZipcode("12345");
		vo.setPass("qwe123");
		
		/***When***/
		int updateCnt = userService.updateUser(vo);
		System.out.println(updateCnt);
		
		/***Then***/
		assertEquals(updateCnt, 1);

	}
	
//	@Test
	public void testUpdateUserPass(){
		/***Given***/
		List<UserVo> list = userService.getAllUser();
		UserVo vo = new UserVo();
		String pass = "";
		
		int cnt = 0;
		
		/***When***/
		for(int i=0; i<list.size(); i++){
			pass = KISA_SHA256.encrypt(list.get(i).getPass());
			vo.setUserId(list.get(i).getUserId());
			vo.setPass(pass);
			
			cnt += userService.updateUserPass(vo);
		}
		
		/***Then***/
		assertEquals(108, cnt);
		
	}
	
}
