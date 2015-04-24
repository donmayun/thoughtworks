package com.thoughtworks.train.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.thoughtworks.train.TrainMap;

/**
 * @description 城镇线路图
 *
 * @author don 2015年4月22日 下午11:34:54
 */
public class TrainMapImpl implements TrainMap {

	private final static int SCALEINCREMENT = 5; // 城镇扩容增量

	private int scale;// 城镇规模
	private int[][] townMap;// 城镇线路图 ( 方向：x->y )

	private LinkedList<Town> towns;// 城镇列表
	private List<TrainLine> routeList; // 输入的线路

	// 初始化
	public TrainMapImpl() {
		scale = 0;
		towns = new LinkedList<Town>();
		routeList = new ArrayList<TrainLine>();
		townMap = new int[scale][scale];
	}

	@Override
	public boolean add(TrainLine trainLine) {

		if (trainLine.isRightLine()) {
			// 添加城镇线路
			routeList.add(trainLine);
			int fromIndex = addTown(trainLine.getFrom());
			int toIndex = addTown(trainLine.getTo());

			// 城镇图容量判定
			if (scale < towns.size()) {
				townMap = expansion(SCALEINCREMENT);
				scale = townMap.length;
			}

			townMap[fromIndex][toIndex] = trainLine.getLength();
			return true;
		}

		return false;
	}

	/**
	 * @description 添加城镇
	 * @param town
	 * @return int 返回该城镇的序号
	 * @author don
	 * @time 2015年4月23日 下午5:53:27
	 */
	private int addTown(Town town) {
		// 验证是否存在
		if (towns.contains(town)) {
			return towns.indexOf(town);
		}
		// 不存在，新增
		towns.add(town);
		return towns.indexOf(town);
	}

	@Override
	public TrainMap createMapFromFile(File file) {
		TrainLine trainLine = new TrainLine();
		try {
			String line = "";
			Scanner sca = new Scanner(file);
			while (sca.hasNext()) {
				line = sca.next();
				trainLine.createByString(line);
				add(trainLine);
			}
			sca.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return this;
	}

	@Override
	public TrainMap createMapFromStr(String str, String splitStr) {
		TrainLine trainLine = new TrainLine();
		String[] trainLineStr = str.split(splitStr);

		for (String string : trainLineStr) {
			trainLine.createByString(string);
			add(trainLine);
		}
		return this;
	}

	@Override
	public int townNum() {
		return towns.size();
	}

	@Override
	public int routeNum() {
		return routeList.size();
	}

	@Override
	public String toString() {
		StringBuffer mapTOstr = new StringBuffer();
		if (townNum() == 0) {
			return "No town !";
		}

		for (Town town : towns) {
			mapTOstr.append("\t" + town.toString());
		}
		mapTOstr.append("\n");

		for (int i = 0; i < townNum(); i++) {
			mapTOstr.append(towns.get(i));
			for (int j = 0; j < townNum(); j++) {
				mapTOstr.append("\t" + townMap[i][j]);
			}
			mapTOstr.append("\n");
		}

		return mapTOstr.toString();
	}

	@Override
	public int[][] expansion(int incre) {

		int[][] newMap = new int[scale + incre][scale + incre];
		for (int i = 0; i < townMap.length; i++) {
			for (int j = 0; j < townMap.length; j++) {
				newMap[i][j] = townMap[i][j];
			}
		}
		return newMap;
	}

	@Override
	public int getDistanceOfLine(Town from, Town to) {
		int first = towns.indexOf(from);
		int second = towns.indexOf(to);
		if (first < 0 || second < 0) {
			return 0;
		}
		return townMap[first][second];
	}

	@Override
	public int getDistanceOfRoute(char... town) {
		int distance = 0;
		int lineDistance = 0;
		Town from;
		Town to;
		for (int i = 0; i < town.length - 1; i++) {
			from = new Town(town[i]);
			to = new Town(town[i + 1]);
			lineDistance = getDistanceOfLine(from, to);
			if (lineDistance == 0) {
				// 这条路不通
				return 0;
			}
			distance += lineDistance;
		}
		return distance;
	}

}