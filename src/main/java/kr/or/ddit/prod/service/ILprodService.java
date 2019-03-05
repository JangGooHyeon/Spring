package kr.or.ddit.prod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.util.model.PageVo;

public interface ILprodService {
	
	List<LprodVo> getAllLprod();
	
	Map<String, Object> selectLprodPagingList(PageVo pageVo);
}
