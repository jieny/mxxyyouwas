package com.mxxy.game.ui.battle;


import java.util.LinkedList;
import java.util.Queue;

import com.mxxy.game.ui.BattlePanel;

/**
 * 指令管理
 * @author dell
 */
public class CommandManager {

	private BattlePanel battlePanel;

	private CommandInterpreter interpretor;

	private Queue<Command> cmdQueue;

	public CommandManager(BattlePanel battlePanel){
		this.battlePanel=battlePanel;
		cmdQueue = new LinkedList<Command>();
		interpretor=new CommandInterpreter(battlePanel);
	}

	/**
	 * 添加指令
	 * @param cmd
	 */
	synchronized public void addCmd(Command cmd) {
		cmdQueue.offer(cmd);
	}

	/**战斗开始*/
	public void turnBattle() {
		for (Command command : cmdQueue) {
			this.interpretor.exce(command);
		}
		turnEnd();
	}

	public void turnEnd() {
		cmdQueue.clear();
		battlePanel.roundStartNew();
		battlePanel.timerManager.cleanTimer();
	}
}
