package com.thoughtworks.train;

import java.io.File;

import com.thoughtworks.train.impl.Town;
import com.thoughtworks.train.impl.TrainLine;

/**
 * @description 城镇线路图
 *
 * @author don 2015年4月23日 上午12:10:26
 */
public interface TrainMap {

	/**
	 * @description 向城镇新增一条线路
	 * @param trainLine
	 * @return TrainMap
	 * @author don
	 * @time 2015年4月23日 下午3:59:11
	 */
	boolean add(TrainLine trainLine);

	/**
	 * @description 从文件创建城镇
	 * @param file
	 * @return TrainMap
	 * @author don
	 * @time 2015年4月23日 下午2:55:04
	 */
	TrainMap createMapFromFile(File file);

	/**
	 * @description 从string创建城镇
	 * @param str
	 * @param splitStr
	 *            分隔符
	 * @return TrainMap
	 * @author don
	 * @time 2015年4月23日 下午2:54:41
	 */
	TrainMap createMapFromStr(String str, String splitStr);

	/**
	 * @description 城镇数量
	 * @return int
	 * @author don
	 * @time 2015年4月23日 下午2:53:47
	 */
	int townNum();

	/**
	 * @description 线路数量
	 * @return int
	 * @author don
	 * @time 2015年4月23日 下午2:53:59
	 */
	int routeNum();

	/**
	 * @description 城镇扩容
	 * @param incre
	 * @return int[][] 新城镇图
	 * @author don
	 * @time 2015年4月23日 下午5:47:13
	 */
	int[][] expansion(int incre);

	/**
	 * @description 两点间距离
	 * @param town
	 * @return int
	 * @author don
	 * @time 2015年4月24日 下午8:38:56
	 */
	int getDistanceOfLine(Town from, Town to);

	/**
	 * @description 计算特定路线长度
	 * @param town
	 * @return int
	 * @author don
	 * @time 2015年4月24日 下午8:33:31
	 */
	int getDistanceOfRoute(char... town);
}
