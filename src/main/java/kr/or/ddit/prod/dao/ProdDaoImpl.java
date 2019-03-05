package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.or.ddit.prod.model.ProdVo;

@Repository("prodDao")
public class ProdDaoImpl implements IProdDao {

	@Override
	public List<ProdVo> selectLprod(SqlSession sqlSession, String prod_lgu) {
		List<ProdVo> selectLprod = sqlSession.selectList("prod.selectLprod", prod_lgu); 
		return selectLprod;
	}
}
