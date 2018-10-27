package com.mxxy.game.config;

import java.io.Serializable;

public interface Item extends Serializable {

	public abstract Long getId();

	public abstract String getName();

	public abstract String getType();

	public abstract String getDescription();

	public abstract short getLevel();

	public abstract long getPrice();

}