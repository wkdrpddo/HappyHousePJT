package com.ssafy.happyHouse.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyHouse.dto.HouseDeal;
import com.ssafy.happyHouse.dto.HouseInfo;

@Mapper
public interface HouseDao {
	List<HouseInfo> searchHouseInfo(String AptName);
	List<HouseInfo> searchHouseInfoLoc(String dong);
	List<HouseInfo> searchAllHouseInfo();
	List<HouseDeal> searchRecentHouseDeal(String AptName);
	List<HouseDeal> searchRecentHouseDealLoc(String dong);
	List<HouseDeal> searchAllHouseDeal();
	void insertHouseInfo(HouseInfo houseInfo);
	void updateHouseInfoLoc(String dong);
	void regAttentionLocation(String attentionLocation);
	void removeAttentionLocation(String attentionLocation);
	void updateAttentionLocation(String attentionLocation, String new_attentionLocation);
	//List<AttentionLocation> searchAttentionLocation(String attentionLocation);
	
}
