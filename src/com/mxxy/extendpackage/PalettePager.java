package com.mxxy.extendpackage;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.sprite.Players;
import com.mxxy.game.sprite.Sprite;
import com.mxxy.game.ui.ContainersPanel;
import com.mxxy.game.widget.ImageComponentButton;

/**
 * 调色板
 * 
 * @author dell
 *
 */
final public class PalettePager extends AbstractPanelHandler {

	private Players person;

	@Override
	protected void initView() {
		person = new Players();
		person.setColorations(player.getColorations(), true);
		person.setCharacter(player.getCharacter());
		person.setShadow(true);
		person.setDirection(Sprite.DIRECTION_BOTTOM);
		person.setState(Players.STATE_STAND);
	}

	private ImageComponentButton changeindex;

	public void switchColor(ActionEvent e) {
		changeindex = (ImageComponentButton) e.getSource();
	}

	int[] colorations = new int[3];

	int index;

	public void changeColor(ActionEvent e) {
		String name = changeindex == null ? "one" : changeindex.getName();
		switch (name) {
		case "one":
			index++;
			if (index > 6) {
				index = 0;
			}
			colorations[0] = index;
			break;
		case "two":
			index++;
			if (index > 6) {
				index = 0;
			}
			colorations[1] = index;
			break;
		case "three":
			index++;
			if (index > 6) {
				index = 0;
			}
			colorations[2] = index;
			break;
		}
		if (person != null) {
			person.setColorations(colorations, true);
		}
	}

	public void confirm(ActionEvent e) {
		player.setColorations(colorations, true);
	}

	int direction = 0;

	public void changeDirection(ActionEvent e) {
		person.setDirection(direction);
		direction += 1;
	}

	@Override
	public void dispose(PanelEvent evt) {
		super.dispose(evt);
		person = null;
	}

	@Override
	public JPanel getContainersPanel() {
		return new ContainersPanel(0, 0, 200, 200) {
			@Override
			protected void draw(Graphics2D g, long elapsedTime) {
				if (person != null && g != null) {
					person.draw(g, 80, 160);
					person.setDirection(direction);
					person.update(elapsedTime);
				}
			}
		};
	}
}
