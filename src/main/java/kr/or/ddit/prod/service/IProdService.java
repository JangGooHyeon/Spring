package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.util.model.PageVo;

public interface IProdService {
	
	List<ProdVo> selectLprod(String prod_lgu);
	
}
