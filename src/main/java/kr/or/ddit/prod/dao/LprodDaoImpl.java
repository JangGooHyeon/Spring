package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.util.model.PageVo;

@Repository("lprodDao")
public class LprodDaoImpl implements ILprodDao {

	@Override
	public List<LprodVo> getAllLprod(SqlSession sqlSession) {
		List<LprodVo> list = sqlSession.selectList("lprod.getAllLprod");
		return list;
	}

	@Override
	public List<LprodVo> selectLprodPagingList(SqlSession sqlSession, PageVo pageVo) {
		List<LprodVo> list = sqlSession.selectList("lprod.selectLprodPagingList", pageVo);
		return list;
	}

	@Override
	public int getLprodCnt(SqlSession sqlSession) {
		int lprodCnt = sqlSession.selectOne("lprod.getLprodCnt");
		return lprodCnt;
	}
	
}
