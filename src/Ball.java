import java.awt.*;

public class Ball {
// zminu cho i rozrobuv
	public static Color ballColor = new Color(0, 0, 0);

	public static int standartBallRadius = 6;
	private int radius;

	private Game instance;
	private Dimension vector = new Dimension(0, 0);
	private Point pos = new Point(0, 0);

	public Ball(Game inst, int x, int y, int radius) {
		this.instance = inst;
		pos = new Point(x, y);
		this.radius = radius;
	}

	public void setVector(int xMove, int yMove) {
		vector = new Dimension(xMove, yMove);
	}

	public Point getPosiyion() {
		return pos;
	}

	public void setPosition(int x, int y) {
		pos = new Point(x, y);
	}

	public void collision() {
		if (pos.x - radius <= 0 && vector.width < 0)
			vector.width = -vector.width;
		if (pos.x + radius >= instance.getGameDimension().width
				&& vector.width > 0)
			vector.width = -vector.width;
		if (pos.y - radius <= 0 && vector.height < 0)
			vector.height = -vector.height;
		if (pos.y + radius >= instance.getGameDimension().height
				&& vector.height > 0)
			instance.loseBall();

		if (instance.getPlayer() != null) {
			if (instance.getPlayer().collidesWith(
					new Rectangle(pos.x - radius + vector.width, pos.y - radius
							+ vector.height, radius * 2, radius * 2))) {
				vector.height = -vector.height;
			}
		}

		pos.move(pos.x + vector.width, pos.y + vector.height);

		for (Platform[] pls : instance.getPlatform()) {
			for (Platform p : pls) {
				if (p.collidesWith(new Rectangle(pos.x - radius,
						pos.y - radius, radius * 2, radius * 2))) {
					p.destroy();
					vector.height = -vector.height;
					boolean won = true;
					for (Platform[] pltfs : instance.getPlatform()) {
						for (Platform pl : pltfs) {
							if (!pl.isDestroyed())
								won = false;
						}
					}
					if (won)
						instance.playerWon();
				}
			}
		}

	}

	public void paintGraphics(Graphics g) {

		g.setColor(ballColor);
		g.fillOval(pos.x - radius, pos.y - radius, radius * 2, radius * 2);
	}

}
