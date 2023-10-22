package com.ssafy.happyHouse.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.ssafy.happyHouse.algo.BFS;
import com.ssafy.happyHouse.dto.HouseDeal;
import com.ssafy.happyHouse.dto.HouseInfo;
import com.ssafy.happyHouse.dto.MarkerDto;
import com.ssafy.happyHouse.service.HouseService;

@RestController
@CrossOrigin(
		   // localhost:5500 과 127.0.0.1 구분
		   origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		   allowCredentials = "true", 
		   allowedHeaders = "*", 
		   methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class HouseController {

	@Autowired
	HouseService house;
	
	@GetMapping(value="/RecentDong/{dong}")
	public List<HouseDeal> DealLoc(@PathVariable String dong){
		System.out.println("DealLoc : ;" + dong+";");
		List<HouseDeal> list = house.searchRecentHouseDealLoc(dong);
		List<HouseDeal> distinct = new ArrayList<>();
		List<String> saveName = new ArrayList<>();
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			if(!saveName.contains(list.get(i).getAptName())) {
				distinct.add(list.get(i));
				saveName.add(list.get(i).getAptName());
			}
		}
		for (HouseDeal houseDeal : distinct) {
			System.out.println(houseDeal);
		}
		return distinct;
	}
	
	@GetMapping(value="/RecentApt/{AptName}")
	public List<HouseDeal> DealAptName(@PathVariable String AptName){
		List<HouseDeal> list = house.searchRecentHouseDeal(AptName);
		for (HouseDeal houseDeal : list) {
			System.out.println(houseDeal);
		}
		return list;
	}
	
	@GetMapping(value="/RecentDong")
	public List<HouseDeal> DealAllLoc(){
		List<HouseDeal> list = house.searchAllRecentHouseDeal();
		return list;
	}
	
	@GetMapping(value="/InfoDong/{dong}")
	public List<HouseInfo> InfoLoc(@PathVariable String dong){
		List<HouseInfo> list = house.searchHouseInfoLoc(dong);
		return list;
	}
	
	@GetMapping(value="/InfoApt/{AptName}")
	public List<HouseInfo> InfoAptName(@PathVariable String AptName){
		List<HouseInfo> list = house.searchHouseInfo(AptName);
		for (HouseInfo houseInfo : list) {
			System.out.println(houseInfo);
		}
		return list;
	}
	
	@GetMapping(value="/loadDetailApt/{AptName}")
	public List<HouseDeal> LoadDetailApt(@PathVariable String AptName){
		if(AptName == null) {
			System.out.println("aptName is null");
		}
		else {
			List<HouseDeal> list = house.searchRecentHouseDeal(AptName);
			return list;
		}
		return null;
	}
	
	@GetMapping(value="/nearSearch")
	public List<HouseDeal> findNearApt(@RequestParam String lng, @RequestParam String lat) {
		List<HouseDeal> list = house.searchRecentHouseDeal("");
		System.out.println(lng + " " + lat);
		return list;
	}
	
	@PostMapping(value="/categorySearch")
	public String findNearCategory(@RequestBody List<MarkerDto> markers) {
		System.out.println("categorySearch");
		markers.forEach(m ->{
			//System.out.println("lng : " + m.getLng() + " lat : " + m.getLat());
			System.out.println(m.getLng()+","+m.getLat());
		});
		BFS bfs = new BFS();
		bfs.getList(markers);
		int result = bfs.execute();

		JsonObject obj = new JsonObject();
		obj.addProperty("result", result);
		obj.addProperty("lng", markers.get(result).getLng());
		obj.addProperty("lat", markers.get(result).getLat());
		System.out.println(obj.toString());
		return obj.toString();
	}
	
	@PostMapping(value="/testPost")
	public void testfunc() {
		System.out.println("hello?");
	}
	
}
