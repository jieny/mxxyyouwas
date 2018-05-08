package com.mxxy.game.modler;

import com.mxxy.game.utils.Constant;

public class MagicModle {
	
	/**
	 * 获取门派对应的法术
	 * @param string
	 * @return
	 */
	public MagicConfig[] getSchoolMagic(String string) {
		MagicConfig [] magic= null;
		switch (string) {
		case Constant.HUMAN:
			magic=new MagicConfig[]{new MagicConfig("zzz", false),new MagicConfig("kkk", false)};
			break;
		case Constant.DEMON:
			magic=new MagicConfig[]{new MagicConfig("飞砂走石", true),new MagicConfig("三味真火", false)};
			break;
		case Constant.IMMORTAL:
			magic=new MagicConfig[]{new MagicConfig("龙腾", false),new MagicConfig("雷霆万钧", true)};
			break;
		}
		return magic;
	}
	
	public class MagicConfig {
		private String name;
		private boolean isGroup;  
		/**
		 * 
		 * @param name  id
		 * @param isGroup  是否群攻法术
		 */
		public MagicConfig(String name, boolean isGroup) {
			super();
			this.name = name;
			this.isGroup = isGroup;
		}
		public String getName() {
			return name;
		}
		public boolean isGroup() {
			return isGroup;
		}
	}
}
