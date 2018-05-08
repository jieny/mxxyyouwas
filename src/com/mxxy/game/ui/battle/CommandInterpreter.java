package com.mxxy.game.ui.battle;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mxxy.game.modler.MagicModle.MagicConfig;
import com.mxxy.game.sprite.Players;
import com.mxxy.game.sprite.Sprite;
import com.mxxy.game.ui.BattlePanel;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.was.Toolkit;

public class CommandInterpreter {

	private BattlePanel battlePanel;

	public CommandInterpreter(BattlePanel battlePanel) {
		this.battlePanel=battlePanel;
	}

	public void exce(Command command) {
		this.invokeMethod(command.getCmd(), command);
	}

	public void attack(Command command) {
		System.out.println("触发攻击指令");
		Players source = command.getSource();  
		Players target = command.getTarget();
		Point location = source.getLocation();
		battlePanel.setBattleMessage("#Y"+source.getPersonName()+"进行了攻击#18");
		Sprite s = SpriteFactory.loadSprite("shape/char/0001/attack.tcp");
		int dx = s.getWidth()-s.getCenterX();
		int dy = s.getHeight()-s.getCenterY();
		if(target.getX() > source.getX()) {
			dx = -dx;
			dy = -dy;
		}
		battlePanel.rush(source, target.getX()+dx,target.getY()+dy,Players.STATE_RUSHA);
		source.playOnce(Players.STATE_ATTACK);
		Toolkit.sleep(100);
		target.playOnce(Players.STATA_HIT);
		battlePanel.showPoints(target, -99);
		source.writFor();
		target.setState(Players.STATE_STAND);
		battlePanel.hidePoints(target);
		battlePanel.rush(source, location.x, location.y,Players.STATE_RUSHB);
		source.setState(Players.STATE_STAND);
		battlePanel.setBattleMessage("");
	}

	/**
	 * 施法指令
	 */
	public void magic(Command command){
		Players source = command.getSource();  
		Players target = command.getTarget();
		source.playOnce(Players.STATE_MAGIC);
		MagicConfig magicConfig = (MagicConfig) command.get("magicConfig");
		battlePanel.setBattleMessage("#Y"+source.getPersonName()+"施法法术 — "+magicConfig.getName()+"#32");
		Toolkit.sleep(300);
		if(magicConfig.isGroup()) {
			groupMagic(target);
		}else{
			singleMagic(target);
		}
		source.setState(Players.STATE_STAND);
	}

	/**
	 * 群法
	 * @param target 目标敌人
	 */
	public void groupMagic(Players target){
		List<Players> hostileTeam = battlePanel.getHostileTeam();
		List<Players> magic = getMagicHostileTeam(target,hostileTeam);
		magic.add(target);
		for (Players players : magic) {
			players.playOnce(Players.STATA_HIT);
			battlePanel.showPoints(players, -10);
		}
		Toolkit.sleep(800);
//		for (Players players : magic) {
//			players.playOnce(Players.STATE_DIE);
//		}
		for (Players players : magic) {
			players.setState(Players.STATE_STAND);
			battlePanel.hidePoints(players);
		}
	}

	/**
	 * 单法
	 * @param target
	 */
	public void singleMagic(Players target){
		target.playOnce(Players.STATA_HIT);
		battlePanel.showPoints(target, -10);
		Toolkit.sleep(1500);
		target.setState(Players.STATE_STAND);
		battlePanel.hidePoints(target);
	}
	/**
	 * 根据速度排序
	 * @param target
	 * @param hostileTeam
	 * @return
	 */
	public List<Players> getMagicHostileTeam(Players target,List<Players> hostileTeam){
		List<Players>list=new ArrayList<Players>(hostileTeam);
		list.remove(target);
		Collections.sort(list, new Comparator<Players>() {  
			@Override  
			public int compare(Players o1, Players o2) {  
				if (o1.getSpeed() < o2.getSpeed()) {  
					return 1;  
				}  
				if (o1.getSpeed() == o2.getSpeed()) {  
					return 0;  
				}  
				return -1;  
			}  
		});  
		ArrayList<Players>players=new ArrayList<Players>();
		players.add(list.get(0));
		players.add(list.get(1));
		return players;
	}

	public Object invokeMethod(String mName, Object arg)  {
		Method method;
		try {
			method = this.getClass().getDeclaredMethod(mName, arg.getClass());
			return method.invoke(this, arg);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
