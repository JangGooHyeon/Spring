package kr.or.ddit.prod.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import kr.or.ddit.db.mybatis.MybatisSqlSessionFactory;
import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.prod.model.ProdVo;

@Service("prodService")
public class ProdServiceImpl implements IProdService {

	@Resource(name="prodDao")
	private IProdDao dao;
	
	@Override
	public List<ProdVo> selectLprod(String prod_lgu) {
		List<ProdVo> list = dao.selectLprod(prod_lgu);
		return list;
	}

}
