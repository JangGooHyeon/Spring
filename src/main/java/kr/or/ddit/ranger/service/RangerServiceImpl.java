package kr.or.ddit.ranger.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.ranger.dao.IRangerDao;

@Service("rangerService")
public class RangerServiceImpl implements IRangerService{
	
	@Resource(name="rangerDao")
	private IRangerDao rangerDao;

	public RangerServiceImpl() {
	}
	
	public RangerServiceImpl(IRangerDao rangerDao) {
		this.rangerDao = rangerDao; 
	}

	/**
	 * Method : setRangerDao
	 * 작성자 : goo84
	 * 변경이력 :
	 * @param rangerDao
	 * Method 설명 : dao객체에 대한 setter
	 */
	public void setRangerDao(IRangerDao rangerDao) {
		this.rangerDao = rangerDao;
	}

	/**
	 * Method : getRangers
	 * 작성자 : goo84
	 * 변경이력 :
	 * @return
	 * Method 설명 : 전체 레인저스 조회(임의의 값 : DB에서 조회했다는 가정 하에 실행)
	 */
	public List<String> getRangers() {
		return rangerDao.getRangers();
	}

	@Override
	public IRangerDao getrangerDao() {
		return this.rangerDao;
	}

	@Override
	public String getRanger(int index) {
		return rangerDao.getRanger(index);
	}
	
}
