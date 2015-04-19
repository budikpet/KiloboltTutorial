package projectile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import core.Entity;
import core.MainClass;

/** Superclass of all projectiles in the game. */
public class Projectile implements Cloneable{
	private int x, y, speedX, distance, damage;
	private Rectangle rec;
	private boolean visible;
	private Color projectileColor;
	public Entity entity;
	
	public Projectile(int damage, Color projectileColor, int speedX) {
		this.damage = damage;
		this.projectileColor = projectileColor;
		this.speedX = speedX;
		this.rec = new Rectangle(x, y, 10, 5);
	}

	public static void update(ArrayList<Projectile> projectiles) {
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if (p.isVisible() == true) {
				p.update();
			} else {
				projectiles.remove(i);
			}
		}

	}
	
	private void update() {
		x += speedX;
		this.rec = new Rectangle(x, y, 10, 5);
		if (x > this.distance || x < 0) {
		   visible = false;
		} 
	}

	public static void paint(Graphics g, ArrayList<Projectile> projectiles) {
		for (Projectile p : projectiles) {
			g.setColor(p.getProjectileColor());
			g.fillRect(p.getX(), p.getY(), p.getRec().width, p.getRec().height);
		}
	}
	
	/**
	 * Creates projectile in game.
	 * @param startX
	 * @param startY
	 * @param speedX
	 */
	public void spawnProjectile(Entity entity) {
		this.entity = entity;
		this.x = entity.getWeaponX();
		this.y = entity.getWeaponY();
		this.visible = true;
		this.distance = entity.getCenterX() + 800;
		MainClass.getProjectiles().add((Projectile) this.clone());
	}

	/**
	 * Pot�eba, aby nevznikali projektily se stejn�mi sou�adnicemi. 
	 */
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Color getProjectileColor() {
		return projectileColor;
	}

	public Rectangle getRec() {
		return rec;
	}

	public int getDamage() {
		return damage;
	}

}
