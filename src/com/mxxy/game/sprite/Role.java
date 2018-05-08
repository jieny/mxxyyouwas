//package com.mxxy.game.sprite;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics2D;
//import java.awt.Point;
//import java.awt.Rectangle;
//import java.awt.RenderingHints;
//import java.util.ArrayList;
//import java.util.EventObject;
//import java.util.Queue;
//import java.util.concurrent.ConcurrentLinkedQueue;
//
//import javax.swing.event.EventListenerList;
//
//import org.w3c.dom.events.EventException;
//
//import com.mxxy.game.astar.Searcher;
//import com.mxxy.game.config.PlayerVO;
//import com.mxxy.game.event.IEventTask;
//import com.mxxy.game.event.PlayerEvent;
//import com.mxxy.game.listener.IPlayerListener;
//import com.mxxy.game.utils.Constant;
//import com.mxxy.game.utils.SpriteFactory;
//
///**
// * 角色
// * @author ZAB
// * 邮箱 ：624284779@qq.com
// */
//public class Role implements IEventTask,IDrawSprite{
//	/** 站立 */
//	public static final String STATE_STAND = "stand";
//	/** 移动 */
//	public static final String STATE_WALK = "walk";
//	/** 哭泣 **/
//	public static final String STATE_WEEP = "weep";
//	/** 舞蹈 */
//	public static final String STATE_DANCE = "dance";
//	/** 发怒 */
//	public static final String STATE_ANGRY = "angry";
//	/** 人物状态 */
//	private String state;
//	/** 人物坐标 */
//	private int x, y;
//	/** 人物 */
//	private Sprite person;
//	/** 阴影 */
//	private Sprite shadow;
//	/** 方向 */
//	private int direction;
//	/** 人物名字 */
//	private String personName;
//	/** 人物称谓 */
//	private String describe;
//	/** 人物标识如0001(代表逍遥生) */
//	private String character;
//	/** 姓名字体 */
//	private Font nameFont;
//	/** 鼠标是否悬停 */
//	private boolean isHover;
//
//	private int sceneX, sceneY;
//	/** 名字颜色 */
//	private Color nameBackground;
//	/** 移动锁 */
//	private Object MOVE_LOCK = new Object();
//	/** 当前的移动量[x,y] */
//	private Point nextStep;
//	/** 路径队列 */
//	private Queue<Point> path = new ConcurrentLinkedQueue<Point>();
//	/** 事件集合 */
//	private EventListenerList listenerList = new EventListenerList();
//	/** 搜索路径 */
//	private Searcher searcher;
//	/** 人物数据 */
//	private PlayerVO data;
//	/** 人物染色 */
//	private int[] colorations = null;
//	/** 获取8个方向三角正切 */
//	private static double k1 = Math.tan(Math.PI / 8);
//
//	private ArrayList<IDrawSprite> IDrawSprites=new ArrayList<IDrawSprite>();
//	/*** 坐骑*/
//	private Mount mMount;   
//	/*** 武器*/
//	private Weapon mWeapon; 
//	/**是否显示名字**/
//	public boolean showNameFlage;
//
//	public Rectangle rect;
//	
//	public Role(String id, String personName, String character,Mount mount,Weapon weapon) {
//		this.personName = personName;
//		this.character = character;
//		this.mMount=mount;
//		this.mWeapon=weapon;
//		IDrawSprites.add(mMount);
//		IDrawSprites.add(mWeapon);
//	}
//
//	@Override
//	public void update() {
//		for (IDrawSprite iDrawSprite : IDrawSprites) {
//			iDrawSprite.update();
//		}
//	}
//	@Override
//	public void draw(Graphics2D g, int x, int y) {
//		g.setFont(nameFont);
//		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//		if(mMount!=null) {
//			mMount.draw(g, x, y);
//		}
//		if (person != null)
//			person.drawBitmap(g, x, y);
//		if (shadow != null)
//			shadow.drawBitmap(g, x, y);
//		int textY = y + 25;
//		int texts = y + 43;
//		Graphics2D g2d = (Graphics2D) g.create();
//		if (personName != null && showNameFlage) {
//			int textX = x - g.getFontMetrics().stringWidth(personName) / 2;
//			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//			g2d.setColor(getNameBackground());
//			g2d.setColor(isHover ? Color.red : getNameBackground());
//			g2d.drawString(personName, textX, describe == null ? textY : texts);
//		}
//		if (describe != null && showNameFlage) {  
//			int textsX = x - g.getFontMetrics().stringWidth(describe) / 2;
//			g2d.setColor(isHover ? Color.red : Constant.DESCRIBE_COLOR);
//			g2d.drawString(describe, textsX, textY);
//		}
//		g2d.dispose();
//	}
//	
//	public void setData(PlayerVO data) {
//		this.data = data;
//		this.setDescribe(data.getDescribe());
//		this.setDirection(data.getDirection());
//		if(mWeapon!=null){
//			mWeapon.setWeaponIndex(data.getWeaponIndex());
//		}
//		this.setColorations(data.getColorations(), true);
//		this.setSceneLocation(data.getSceneLocation());
//		this.setState(data.getState());
//		this.setNameBackground(Constant.PLAYER_NAME_COLOR);
//	}
//	
//	/**
//	 * 设置场景坐标
//	 * @param point
//	 */
//	public void setSceneLocation(Point point) {
//		this.sceneX = point.x;
//		this.sceneY = point.y;
//	}
//	
//	/**
//	 * 设置角色状态
//	 * @param state
//	 */
//	private void setState(String state) {
//		if(this.state!=state){
//			this.state = state;
//			this.person = createSprite(state);
//			this.person.setDirection(this.direction);
//			this.person.resetFrames();
//		}
//	}
//
//
//	@Override
//	public Sprite createSprite(String state) {
//		String mountIndex = mMount!=null ? mMount.getMountCharacter() : "";
//		String value = mountIndex.length() > 0 ? mountIndex + "/" : "";
//		Sprite sprite = SpriteFactory.loadSprite("/shape/char/" + this.character + "/" + value + state + ".tcp",this.colorations);
//		return sprite;
//	}
//	
//	@Override
//	public boolean handleEvent(EventObject evt) throws EventException {
//		if (evt instanceof PlayerEvent) {
//			PlayerEvent playerEvt = (PlayerEvent) evt;
//			handleEvent(playerEvt);
//		}
//		return false;
//	}
//	
//	/**
//	 * 人物事件处理
//	 * @param event
//	 */
//	private void handleEvent(PlayerEvent event) {
//		final IPlayerListener[] listeners = listenerList.getListeners(IPlayerListener.class);
//		switch (event.getId()) {
//		case PlayerEvent.STEP_OVER:
//			for (IPlayerListener listener : listeners) {
//			}
//			break;
//		case PlayerEvent.WALK:
//			for (IPlayerListener listener : listeners) {
//			}
//			break;
//		case PlayerEvent.MOVE:
//			for (IPlayerListener listener : listeners) {
//			}
//			break;
//		case PlayerEvent.CLICK:
//			for (IPlayerListener listener : listeners) {
//			}
//			break;
//		case PlayerEvent.TALK:
//			for (IPlayerListener listener : listeners) {
//			}
//			break;
//		default:
//			break;
//		}
//	}
//	
//	public Color getNameBackground() {
//		return nameBackground;
//	}
//	
//	public void setNameBackground(Color nameBackground) {
//		this.nameBackground = nameBackground;
//	}
//	
//	public void setDescribe(String describe) {
//		this.describe = describe;
//	}
//	
//	public String getDescribe() {
//		return describe;
//	}
//	
//	public void setDirection(int direction) {
//		this.direction = direction;
//	}
//	public int getDirection() {
//		return direction;
//	}
//	
//	public void setColorations(int[] colorations, boolean recreate) {
//		this.colorations = colorations;
//		if (recreate)
//			coloring(colorations);
//	}
//
//	private void coloring(int[] colorations2) {
//		
//	}
//}
