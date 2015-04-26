package com.thoughtworks.train.homework;

import java.io.File;

import com.thoughtworks.train.handle.Town;
import com.thoughtworks.train.handle.TrainMap;
import com.thoughtworks.train.handle.TrainMapImpl;

/**
 * @description homework答案输出
 *
 * @author don 2015年4月26日 下午12:02:09
 */
public class Answer {
	private static TrainMap trainMap = new TrainMapImpl();

	public static void main(String[] args) {
		String out = "Output #";
		trainMap.createMapFromFile(new File("train.txt"));

		// Q1~Q5 getDistanceOfRoute 计算特定路线距离
		System.out.println(out + "1: "
				+ trainMap.getDistanceOfRoute('A', 'B', 'C'));
		System.out.println(out + "2: " + trainMap.getDistanceOfRoute('A', 'D'));
		System.out.println(out + "3: "
				+ trainMap.getDistanceOfRoute('A', 'D', 'C'));
		System.out.println(out + "4: "
				+ trainMap.getDistanceOfRoute('A', 'E', 'B', 'C', 'D'));
		System.out.println(out + "5: "
				+ trainMap.getDistanceOfRoute('A', 'E', 'D'));

		// Q6
		trainMap.querstion6(new Town('C'), 3, "C");
		System.out.println(out + "6: " + trainMap.getRouteNum());

		// Q7
		trainMap.setRouteNum(0);
		trainMap.querstion7(new Town('A'), new Town('C'), 4, "A");
		System.out.println(out + "7: " + trainMap.getRouteNum());

		// Q8
		trainMap.querstion8(new Town('C'), 0, "A");
		System.out.println(out + "8: " + trainMap.getMinLength());

		// Q9
		trainMap.querstion8(new Town('B'), 0, "B");
		System.out.println(out + "9: " + trainMap.getMinLength());

		// Q10
		trainMap.setRouteNum(0);
		trainMap.querstion10(new Town('C'), 30, "C", 0);
		System.out.println(out + "10: " + trainMap.getRouteNum());

	}
}
