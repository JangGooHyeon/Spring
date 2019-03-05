package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.prod.model.ProdVo;

public interface IProdDao {
	List<ProdVo> selectLprod(SqlSession sqlSession, String prod_lgu);
}
