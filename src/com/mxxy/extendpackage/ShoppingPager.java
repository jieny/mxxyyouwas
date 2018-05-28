package com.mxxy.extendpackage;

import java.awt.event.MouseEvent;

import com.mxxy.game.handler.AbstractPanelHandler;
import com.mxxy.game.modler.MedicineMolder;
import com.mxxy.game.utils.SpriteFactory;
import com.mxxy.game.widget.ItemDetailLabel;
import com.mxxy.game.widget.ItemLabel;
import com.mxxy.game.widget.Label;

final public class ShoppingPager extends AbstractPanelHandler<MedicineMolder> {

	
	private String[] items = { "四叶花", "七叶莲", "天青地白", "草果", "九香虫", "水黄莲", "紫丹罗",
			"佛手", "旋复花", "百色花", "香叶", "龙须草", "灵脂", "白玉骨头", "鬼切草", "曼佗罗花",
			"山药", "八角莲叶", "人参", "月见草" };
	
	
	private Label selectedBorder;
	
	private Label selectingBorder;
	
	private ItemDetailLabel detailLabel = new ItemDetailLabel();
	
	@Override
	protected void initView() {
		
		selectedBorder = new Label(SpriteFactory.loadAnimation("res/wzife/button/itemselected.tcp"));
		
		selectingBorder = new Label(SpriteFactory.loadAnimation("res/wzife/button/itemselecting.tcp"));
		
		int x0 = 8, y0 = 36;

		int rows = 4, cols = 5;

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				ItemLabel label = new ItemLabel(modler.createItem(items[y * cols + x]));
				label.setName("item-" + items[y * cols + x]);
				label.setSize(50, 50);
				label.setLocation(x0 + x * 51, y0 + y * 51);
				panel.add(label, 0);
				label.addMouseListener(this);
				label.addMouseMotionListener(this);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		super.mouseExited(e);
		uihelp.hideToolTip(detailLabel);
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		ItemLabel label = (ItemLabel) e.getSource();
		selectingBorder.setLocation(label.getX()-1, label.getY()-1);
		panel.add(selectingBorder,0);
		detailLabel.setItem(label.getItem());
		uihelp.showToolTip(detailLabel, label, e);
	}
}
