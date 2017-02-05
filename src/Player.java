import java.awt.*;

public class Player {

	public static Color playerColor = new Color(0, 200, 200);

	public static int standartPlayerWidth = 100;
	public static int standartPlayerHeight = 20;

	private Rectangle hitBox;
	private Game instance;

	public Player(Game instance, int x, int y, int width, int height) {
		hitBox = new Rectangle(x, y, width, height);
		this.instance = instance;
	}

	public void moveOnX(int speed) {
		this.hitBox.x += speed;
		if (hitBox.x > instance.getGameDimension().width
				- instance.getPlayer().hitBox.width)
			hitBox.x = instance.getGameDimension().width
					- instance.getPlayer().hitBox.width;
		if (hitBox.x < 0)
			hitBox.x = 0;
	}

	public boolean collidesWith(Rectangle object) {
		return hitBox.intersects(object);
	}

	public void setY(int y) {
		this.hitBox.y = y;
	}

	public void setX(int x) {
		this.hitBox.x = x;

	}

	public int getWidth() {
		return this.hitBox.width;
	}

	public int getHeight() {
		return this.hitBox.height;
	}

	public void paintGraphics(Graphics g) {
		g.setColor(playerColor);
		g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
	}

}
