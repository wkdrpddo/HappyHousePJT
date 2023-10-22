package com.ssafy.happyHouse.algo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Repository
public class tspFromClickLocation {
	private double[][] matrix;
	private int len;
	private double[][] memoi;
	private int[][] visit;
	private List<Integer> list;
	
	public List<Integer> getPath() {
		memoi = new double[len][(1<<len)-1];
		visit = new int[len][(1<<len)-1];
		
		for(int i = 0; i < len; i++) {
			Arrays.fill(memoi[i], 100);
			Arrays.fill(visit[i], -1);
		}
		
		dfs(0, 1);
		
		list = new LinkedList<Integer>();
		makeList(0, 1);
		
		return list;
	}
	
	private void makeList(int node, int visited) {
		if(node == -1) return;
		list.add(node);
		if(visited == (1<<len)-1) return;
		int nextNode = visit[node][visited];
		makeList(nextNode, visited | 1 << nextNode);
		
	}
	
	private double dfs(int city, int mask) {
		// 기저조건
		if( mask == (1<<len)-1 ) {
			if(matrix[city][0] == 0) return 100;
			return matrix[city][0];
		}
		
		if(memoi[city][mask] != 100) {
			return memoi[city][mask];
		}
		
		for(int i = 0; i < len; i++) {
			if( (mask & (1<<i)) != 0 || matrix[city][i] == 0) continue;
			
			if( memoi[city][mask] > matrix[city][i] + dfs(i, mask | (1<<i)) ) {
				memoi[city][mask] = matrix[city][i] + dfs(i, mask | (1<<i));
				visit[city][mask] = i;
			}
		}
		
		return memoi[city][mask];
	}
	
	static class Data{
		double length;
		LinkedList<Integer> list;
		public Data(double length, LinkedList<Integer> list ) {
			this.length = length;
			this.list = list;
		}
	}

}
