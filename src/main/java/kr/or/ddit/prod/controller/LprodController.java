package kr.or.ddit.prod.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.model.LprodVo;
import kr.or.ddit.prod.model.ProdVo;
import kr.or.ddit.prod.service.ILprodService;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.util.model.PageVo;

@Controller
public class LprodController {
	
	@Resource(name="lprodService")
	private ILprodService lprodService;
	
	@Resource(name="prodService")
	private IProdService prodService;
	
	
	@RequestMapping(path="/lprodAllList", method=RequestMethod.GET)
	public String getAllLprod(Model model){
		List<LprodVo> lprodList = lprodService.getAllLprod();
		model.addAttribute("lprodList", lprodList);
		
		return "prod/lprodList";
	}
	
	
	@RequestMapping(path="/lprodPagingList", method=RequestMethod.GET)
	public String lprodPagingList(PageVo pageVo, Model model){
		
		pageVo.setPageSize(5);
		
		Map<String, Object> resultMap = lprodService.selectLprodPagingList(pageVo);
		
		model.addAllAttributes(resultMap);
		model.addAttribute("pageSize", pageVo.getPageSize());
		model.addAttribute("page", pageVo.getPage());
		
		return "prod/lprodPagingList";
	}
	
	
	@RequestMapping(path="/prodList", method=RequestMethod.GET)
	public String prodList(@RequestParam("lprodgu")String prod_lgu, Model model){
		List<ProdVo> prodList = prodService.selectLprod(prod_lgu);
		model.addAttribute("prodList", prodList);
		
		return "prod/prodList";
	}
	
	
}
