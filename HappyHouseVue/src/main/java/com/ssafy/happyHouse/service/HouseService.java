package com.ssafy.happyHouse.service;

import java.util.List;

import com.ssafy.happyHouse.dto.HouseDeal;
import com.ssafy.happyHouse.dto.HouseInfo;

public interface HouseService {
	List<HouseInfo> searchHouseInfo(String AptName);
	List<HouseInfo> searchHouseInfoLoc(String dong);
	List<HouseInfo> searchAllHouseInfo();
	List<HouseDeal> searchRecentHouseDeal(String AptName);
	List<HouseDeal> searchRecentHouseDealLoc(String dong);
	List<HouseDeal> searchAllRecentHouseDeal();
	void insertHouseInfo(HouseInfo houseinfo);
	void upateHouseinfoLoc(String dong);
	void regAttentionLocation(String attentionLocation);
	void removeAttentionLocation(String attentionLocation);
	void updateAttentionLocation(String attentionLocation, String new_attentionLocation);
	//List<AttentionLocation> searchAttentionLocation(String attentionLocation);
}
