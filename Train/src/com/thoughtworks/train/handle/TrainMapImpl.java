package com.thoughtworks.train.handle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

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

	private int routeNum;// dfs线路数

	private int routeLength;// 路径长度
	private int minlength;// 最短路径

	// 初始化
	public TrainMapImpl() {
		scale = 0;
		towns = new LinkedList<Town>();
		townMap = new int[scale][scale];
		minlength = Integer.MAX_VALUE;
	}

	@Override
	public boolean add(TrainLine trainLine) {

		if (trainLine.isRightLine()) {
			// 添加城镇线路
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

	@Override
	public void querstion6(Town town, int condition, String route) {
		// 回溯
		if (route.length() - 1 > condition) {
			return;
		}

		if (route.length() > 1 && route.endsWith(town.toString())) {
			routeNum++;
			// System.out.println(route + ", " + route.length());
		}

		// 获取下一个起点
		Town nextTown = new Town(route.charAt(route.length() - 1));
		int lastNodeIndex = towns.indexOf(nextTown);

		for (int i = 0; i < towns.size(); i++) {
			// 有路
			if (townMap[lastNodeIndex][i] > 0) {
				querstion6(town, condition, route + towns.get(i).toString());
			}
		}

	}

	@Override
	public void querstion7(Town starttown, Town endtown, int condition,
			String route) {
		// 回溯
		if (route.length() - 1 > condition) {
			return;
		}

		if ((route.length() - 1) == condition
				&& route.endsWith(endtown.toString())) {
			routeNum++;
			// System.out.println(route + ", " + route.length());
		}

		// 获取当前应该遍历的行
		Town nextTown = new Town(route.charAt(route.length() - 1));
		int start = towns.indexOf(nextTown);

		for (int i = 0; i < towns.size(); i++) {
			// 有路
			if (townMap[start][i] > 0) {
				querstion7(nextTown, endtown, condition, route
						+ towns.get(i).toString());
			}
		}
	}

	@Override
	public void querstion8(Town town, int lengthloop, String route) {
		int loopRouteLength = route.length();
		// 回溯
		if (route.endsWith(town.toString()) && lengthloop > 0) {
			if (lengthloop < minlength) {
				minlength = lengthloop;
			}
			return;
		}
		// 获取当前应该遍历的行
		Town nextTown = new Town(route.charAt(loopRouteLength - 1));
		int start = towns.indexOf(nextTown);
		

		//防止死循环
		//出现循环路线 回溯
		if(route.length()>3&&route.charAt(loopRouteLength-1)==route.charAt(loopRouteLength-3)){
			return;
		}
		
		for (int i = 0; i < towns.size(); i++) {
			int length = townMap[start][i];
			
			
			if (length > 0) {
				querstion8(town, lengthloop + length, route
						+ towns.get(i).toString());
			}
		}
	}

	@Override
	public void querstion10(Town town, int condition, String route,
			int lengthloop) {
		// 回溯
		if (lengthloop >= condition) {
			return;
		}

		if (route.length() > 1 && route.endsWith(town.toString())) {
			routeNum++;
			System.out.println(route + ", " + route.length()
					+ "   loopLength: " + lengthloop);
		}

		// 获取当前应该遍历的行
		Town nextTown = new Town(route.charAt(route.length() - 1));
		int start = towns.indexOf(nextTown);

		for (int i = 0; i < towns.size(); i++) {
			// 有路
			int length = townMap[start][i];
			if (length > 0) {
				querstion10(town, condition, route + towns.get(i).toString(),
						lengthloop + length);// 坑爹！！！
			}
		}

	}

	@Override
	public int getRouteLength() {
		return routeLength;
	}

	@Override
	public int getRouteNum() {
		return routeNum;
	}

	@Override
	public int getMinLength() {
		return minlength;
	}

}
