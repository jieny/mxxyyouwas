package com.mxxy.game.utils;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class GameSoftReference extends SoftReference<Object>{

	public GameSoftReference(Object referent, ReferenceQueue<? super Object> q) {
		super(referent, q);
	}

}
