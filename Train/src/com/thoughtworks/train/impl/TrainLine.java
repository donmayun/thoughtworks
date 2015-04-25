package com.thoughtworks.train.impl;

/**
 * @description A->B的一条线路
 *
 * @author don 2015年4月22日 下午11:47:39
 */
public class TrainLine {

	private Town from;// 线路起点
	private Town to;
	private Integer length;// 距离

	public Town getFrom() {
		return from;
	}

	public void setFrom(Town from) {
		this.from = from;
	}

	public Town getTo() {
		return to;
	}

	public void setTo(Town to) {
		this.to = to;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public TrainLine createByString(String str) {
		try {
			str = str.trim();
			this.from = new Town(str.charAt(0));
			this.to = new Town(str.charAt(1));

			char last = str.charAt(str.length() - 1);
			if (last >= '0' && last <= '9') {
				this.length = Integer.parseInt(str.substring(2, str.length()));
			} else {
				// 逗号结尾
				this.length = Integer.parseInt(str.substring(2,
						str.length() - 1));
			}
		} catch (Exception e) {

		}
		return this;
	}

	@Override
	public String toString() {
		return "TrainLine [from=" + from + ", to=" + to + ", length=" + length
				+ "]";
	}

	/**
	 * @description 判断当前线路是否正确
	 * @return boolean
	 * @author don
	 * @time 2015年4月23日 下午4:04:22
	 */
	public boolean isRightLine() {
		if (from == null || to == null || length == null) {
			return false;
		}
		if (from.equals(to)) {
			return false;
		}
		return true;
	}

}
