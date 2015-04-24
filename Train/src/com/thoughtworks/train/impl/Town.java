/**
 * @description 
 */
package com.thoughtworks.train.impl;

/**
 * @description 城镇
 *
 * @author don 2015年4月23日 下午4:51:57
 */
public class Town {
	private Character town;

	public Character getTown() {
		return town;
	}

	public void setTown(Character town) {
		this.town = town;
	}

	public Town() {
	}

	public Town(Character c) {
		this.town = c;
	}

	@Override
	public String toString() {
		return town.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return town.toString().equals(obj.toString());
	}

}
