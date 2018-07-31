package com.mxxy.game.config;

import java.awt.Point;
import java.io.Serializable;
import java.util.Arrays;

import com.mxxy.game.sprite.Weapon;

/**
 * 任务配置对象 用于后期的 存档和读档
 * 
 * @author ZAB
 */
public class PlayerVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String STATE_STAND = "stand";

	public static final String STATE_WALK = "walk";

	public String id;

	public int hp;
	/**
	 * 人物文件
	 */
	public String character;
	/**
	 * 人物名字
	 */
	public String name;

	/**
	 * 人物染色
	 */
	public int[] colorations;
	/**
	 * 人物状态
	 */
	public String state = STATE_STAND;
	/**
	 * 方向
	 */
	public int direction;
	/**
	 * 坐标
	 */
	public Point sceneLocation;
	/**
	 * 当前场景编号
	 */
	public String sceneId;
	/**
	 * 武器对象
	 */
	private Weapon mWeapon;
	/**
	 * 称谓
	 */
	private String describe;
	
	
	private long moeny;
	
	private int speed;

	public int getSpeed() {
		return speed;
	}
	
	public void setMoeny(long moeny) {
		this.moeny = moeny;
	}
	public long getMoeny() {
		return moeny;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setmWeapon(Weapon mWeapon) {
		this.mWeapon = mWeapon;
	}

	public Weapon getmWeapon() {
		return mWeapon;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getDescribe() {
		return describe;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getColorations() {
		return colorations;
	}

	public void setColorations(int[] colorations) {
		this.colorations = colorations;
	}

	public void setSceneLocation(Point sceneLocation) {
		this.sceneLocation = sceneLocation;
	}

	public Point getSceneLocation() {
		return sceneLocation;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "PlayerVO [id=" + id + ", character=" + character + ", name=" + name + ", colorations="
				+ Arrays.toString(colorations) + ", state=" + state + ", direction=" + direction + ", sceneLocation="
				+ sceneLocation + ", describe=" + describe + "]";
	}
}
