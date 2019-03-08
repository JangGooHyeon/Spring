package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.util.model.PageVo;

@Repository("lprodDao")
public class LprodDaoImpl implements ILprodDao {
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<LprodVo> getAllLprod() {
		return sqlSessionTemplate.selectList("lprod.getAllLprod");
	}

	@Override
	public List<LprodVo> selectLprodPagingList(PageVo pageVo) {
		return sqlSessionTemplate.selectList("lprod.selectLprodPagingList", pageVo);
	}

	@Override
	public int getLprodCnt() {
		return sqlSessionTemplate.selectOne("lprod.getLprodCnt");
	}

	
}
