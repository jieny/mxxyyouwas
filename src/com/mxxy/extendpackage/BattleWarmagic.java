package com.mxxy.extendpackage;

import java.awt.event.MouseEvent;

import com.mxxy.game.event.PanelEvent;
import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.modler.MagicModle;
import com.mxxy.game.modler.MagicModle.MagicConfig;
import com.mxxy.game.sprite.Cursor;
import com.mxxy.game.ui.BattlePanel;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.ImageComponentButton;
import com.mxxy.game.widget.Label;
/**
 * 战斗法术
 * @author dell
 *
 */
final public class BattleWarmagic extends AbstractPanelHandler<MagicModle>{

	private ImageComponentButton[] magicArrays;
	
	private BattlePanel battlepanel;
	
	private Label defaultMagic;
	@Override
	public void init(PanelEvent evt) {
		super.init(evt);
		if(super.iWindows.getPanel() instanceof BattlePanel) {
			this.battlepanel = (BattlePanel) super.iWindows.getPanel();
		}
		defaultMagic=findViewById("defaultMagic");
	}
	@Override
	protected void initView() {
		MagicConfig[] schoolMagic = modler.getSchoolMagic(player.getSchoolCharacter());
		magicArrays=new ImageComponentButton[schoolMagic.length];
		for (int i = 0; i < magicArrays.length; i++) {
			magicArrays[i] = new ImageComponentButton();
			magicArrays[i].init(SpriteFactory.loadSprite("/magic/"+player.getSchoolCharacter()+"/"+schoolMagic[i].getName()+"icon.tcp"));
			magicArrays[i ].setLocation(28 + 88 * i ,30);
			magicArrays[i].setSize(45, 45);
			magicArrays[i].setName(schoolMagic[i].getName());
			magicArrays[i].setGroup(schoolMagic[i].isGroup());
			magicArrays[i].addMouseListener(this);
			panel.add(magicArrays[i],0);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		battlepanel.setGameCursor(Cursor.SELECT_CURSOR);
		ImageComponentButton source=(ImageComponentButton) e.getSource();
		defaultMagic.setText(source.getName());
		MagicModle.MagicConfig magicBean=new MagicModle().new MagicConfig(source.getName(), source.isGroup());
		battlepanel.setSelectMagic(magicBean);
	}
}
