package com.ssafy.happyHouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyHouse.dao.HouseDao;
import com.ssafy.happyHouse.dto.HouseDeal;
import com.ssafy.happyHouse.dto.HouseInfo;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	HouseDao house;
	
	@Override
	public List<HouseInfo> searchHouseInfo(String AptName) {
		// TODO Auto-generated method stub
		return house.searchHouseInfo(AptName);
	}

	@Override
	public List<HouseInfo> searchAllHouseInfo() {
		// TODO Auto-generated method stub
		return house.searchAllHouseInfo();
	}

	@Override
	public List<HouseDeal> searchRecentHouseDeal(String AptName) {
		// TODO Auto-generated method stub
		return house.searchRecentHouseDeal(AptName);
	}

	@Override
	public List<HouseDeal> searchAllRecentHouseDeal() {
		// TODO Auto-generated method stub
		return house.searchAllHouseDeal();
	}

	@Override
	public void insertHouseInfo(HouseInfo houseinfo) {
		// TODO Auto-generated method stub
		house.insertHouseInfo(houseinfo);
	}

	@Override
	public void upateHouseinfoLoc(String dong) {
		// TODO Auto-generated method stub
		house.updateHouseInfoLoc(dong);
	}

	@Override
	public void regAttentionLocation(String attentionLocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeAttentionLocation(String attentionLocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAttentionLocation(String attentionLocation, String new_attentionLocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<HouseInfo> searchHouseInfoLoc(String dong) {
		// TODO Auto-generated method stub
		return house.searchHouseInfoLoc(dong);
	}

	@Override
	public List<HouseDeal> searchRecentHouseDealLoc(String dong) {
		// TODO Auto-generated method stub
		return house.searchRecentHouseDealLoc(dong);
	}

}
