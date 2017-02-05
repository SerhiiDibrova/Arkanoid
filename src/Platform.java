import java.awt.*;

public class Platform {

	public static Color platformColor = new Color(255, 0, 0);

	private boolean isDestroyed = false;

	private Rectangle hitBox;

	public Platform(int x, int y, int width, int height) {
		hitBox = new Rectangle(x, y, width, height);

	}

	public boolean collidesWith(Rectangle object) {
		return (isDestroyed) ? false : hitBox.intersects(object);
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void destroy() {
		isDestroyed = true;
	}

	public void paintGraphics(Graphics g) {

		if (!isDestroyed) {
			g.setColor(platformColor);
			g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
			g.setColor(new Color(0, 0, 0));
			g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);

		}
	}

}
