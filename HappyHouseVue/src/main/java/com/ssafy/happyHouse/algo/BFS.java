package com.ssafy.happyHouse.algo;

import java.util.List;

import com.ssafy.happyHouse.dto.MarkerDto;

public class BFS {
	private List<MarkerDto> list;
	public void getList(List<MarkerDto> list) {
		this.list = list;
	}
	public int execute() {
//		double cost = 100000.0;
//		int point = 0;
//		double pointZero_lat = Double.parseDouble(list.get(0).getLat());
//		double pointZero_lng = Double.parseDouble(list.get(0).getLng());
//		double R = 6371e3;
//		System.out.println("Zero point lat : " + pointZero_lat + " lng : " + pointZero_lng);
//		for (int i = 1; i < list.size(); i++) {
//			double x = Double.parseDouble(list.get(i).getLat());
//			double y = Double.parseDouble(list.get(i).getLng());
//			
//			double radian1 = pointZero_lat * Math.PI / 180;
//			double radian2 = x * Math.PI / 180;
//			
//			double deltaRadian1 = (x - pointZero_lat) * Math.PI / 180;
//			double deltaRadian2 = (y - pointZero_lng) * Math.PI / 180;
//			
//			double a = Math.sin(deltaRadian1 / 2) * Math.sin(deltaRadian1 / 2) + Math.cos(radian1) * Math.cos(radian2) *
//					Math.sin(deltaRadian2 / 2) * Math.sin(deltaRadian2 / 2);
//			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//			
//			double distance = R * c; 
//			System.out.println(i+" cost : " + cost+" distance : " + distance);
//			if(distance < cost) {
//				cost = distance;
//				point = i;
//			}
//		}
//		System.out.println("point : " + point);
//		return point;
		return calc();
	}
	private int calc() {
		double count = 100000;
		int point = 0;
		for (int i = 1; i < list.size(); i++) {
			double x = Math.abs(Double.parseDouble(list.get(0).getLat()) - Double.parseDouble(list.get(i).getLat()));
			double y = Math.abs(Double.parseDouble(list.get(0).getLng()) - Double.parseDouble(list.get(i).getLng()));
			System.out.println(i + " count : " + (x+y) + " lat : " + list.get(i).getLat() + " lng : " + list.get(i).getLng());
			if(count > x + y) {
				count = x + y;
				point = i;
			}
		}
		System.out.println(list.get(point).toString()+" point : " + point);
		return point;
	}
}
