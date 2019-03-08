package kr.or.ddit.prod.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.prod.model.ProdVo;

@Repository("prodDao")
public class ProdDaoImpl implements IProdDao {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<ProdVo> selectLprod(String prod_lgu) {
		return sqlSessionTemplate.selectList("prod.selectLprod", prod_lgu); 
	}
}
