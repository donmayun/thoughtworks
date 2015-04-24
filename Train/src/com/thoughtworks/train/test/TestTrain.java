/**
 * @description 
 */
package com.thoughtworks.train.test;

import java.io.File;

import org.junit.Test;

import com.thoughtworks.train.TrainMap;
import com.thoughtworks.train.impl.Town;
import com.thoughtworks.train.impl.TrainLine;
import com.thoughtworks.train.impl.TrainMapImpl;

/**
 * @description
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
			System.out.println(trainLine);
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
		creatTrainLine(20);

		// 打印矩阵
		System.out.println(trainMap);
		// 点数
		System.out.println("townNum:" + trainMap.townNum());
		// 线路数
		System.out.println("routeNum:" + trainMap.routeNum());
	}

	@Test
	public void countDistance() {
		// creatTrainLine(20);
		trainMap.createMapFromFile(new File("train.txt"));
		System.out.println(trainMap);

		System.out.println(trainMap.getDistanceOfRoute('A', 'E','B','C','D'));
	}
}
