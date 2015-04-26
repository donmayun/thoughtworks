/**
 * @description 
 */
package com.thoughtworks.train.test;

import java.io.File;

import org.junit.Test;

import com.thoughtworks.train.handle.Town;
import com.thoughtworks.train.handle.TrainLine;
import com.thoughtworks.train.handle.TrainMap;
import com.thoughtworks.train.handle.TrainMapImpl;

/**
 * @description Test
 *
 * @author don 2015年4月23日 下午2:38:24
 */
public class TestTrain {
	private static TrainMap trainMap = new TrainMapImpl();// 城镇图

	/**
	 * @description 初始化一个特定大小的城镇图
	 * @param num
	 * @author don
	 * @time 2015年4月24日 下午8:48:27
	 */
	public void creatTrainLine(int num) {
		trainMap = new TrainMapImpl();
		TrainLine trainLine;
		Town town1;
		Town town2;

		for (int i = 0; i < num; i++) {
			trainLine = new TrainLine();
			town1 = new Town();
			town2 = new Town();
			// 生成点
			town1.setTown((char) ('A' + (int) (Math.random() * 26)));
			trainLine.setFrom(town1);
			town2.setTown((char) ('A' + (int) (Math.random() * 26)));
			trainLine.setTo(town2);
			// 生成线长
			trainLine.setLength((int) (Math.random() * 20 + 1));
			trainMap.add(trainLine);
		}

	}

	/**
	 * @description 城镇网络初始化
	 * @author don
	 * @time 2015年4月23日 下午2:58:56
	 */
	@Test
	public void transform() {
		// creatTrainLine(20);
		trainMap.createMapFromFile(new File("train.txt"));
		// trainMap.createMapFromStr(" AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7",
		// ",");

		// 打印矩阵
		System.out.println(trainMap);
		// 点数
		System.out.println("townNum:" + trainMap.townNum());
	}

	/**
	 * @description 计算给定路线的距离
	 * @author don
	 * @time 2015年4月25日 上午9:29:33
	 */
	@Test
	public void countDistance() {
		trainMap.createMapFromFile(new File("train.txt"));
		System.out.println(trainMap);

		System.out.println(trainMap.getDistanceOfRoute('C', 'E', 'B', 'C', 'D',
				'C'));
	}

	/**
	 * @description dfs遍历
	 * @author don
	 * @time 2015年4月25日 上午9:43:21
	 */
	@Test
	public void question6() {
		// creatTrainLine(2000);
		trainMap.createMapFromFile(new File("train.txt"));
		trainMap.querstion6(new Town('C'), 3, "C");
		System.out.println(trainMap.getRouteNum());

	}

	@Test
	public void question7() {
		trainMap.createMapFromFile(new File("train.txt"));
		// trainMap.querstion7(new Town('C'), new Town('C'), 3, "C");
		trainMap.querstion7(new Town('A'), new Town('C'), 4, "A");
		System.out.println(trainMap.getRouteNum());

	}

	@Test
	public void question10() {
		trainMap.createMapFromFile(new File("train.txt"));

		trainMap.querstion10(new Town('C'), 30, "C", 0);
		System.out.println(trainMap.getRouteNum());
	}

	@Test
	public void question8() {
		trainMap.createMapFromFile(new File("train.txt"));

		trainMap.querstion8(new Town('C'), 0, "A");
		System.out.println(trainMap.getMinLength());
	}

	@Test
	public void question9() {
		trainMap.createMapFromFile(new File("train.txt"));

		trainMap.querstion8(new Town('E'), 0, "C");
		System.out.println(trainMap.getMinLength());
	}
}
