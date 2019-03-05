package kr.or.ddit.prod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.dao.ILprodDao;
import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.util.model.PageVo;

@Service("lprodService")
public class LprodServiceImpl implements ILprodService {
	
	@Resource(name="lprodDao")
	private ILprodDao dao;

	public LprodServiceImpl(){
	}
	
	@Override
	public List<LprodVo> getAllLprod() {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		List<LprodVo> list = dao.getAllLprod(sqlSession);
		
		sqlSession.close();
		
		return list;
	}

	@Override
	public Map<String, Object> selectLprodPagingList(PageVo pageVo) {
		SqlSessionFactory sqlSessionFactory = MybatisSqlSessionFactory.getSqlSessionFactory();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("lprodList", dao.selectLprodPagingList(sqlSession, pageVo));
		resultMap.put("lprodCnt", dao.getLprodCnt(sqlSession));
		
		sqlSession.close();
		
		return resultMap;
	}

}
