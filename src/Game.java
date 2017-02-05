import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Game extends JPanel {

	private Dimension gameField = new Dimension(400, 300);
	private boolean isRunning = false;
	private boolean isPaused = false;
	private boolean won = false;
	private boolean lost = false;

	private int ballCount;

	private Platform[][] platforms;
	private Player player;
	private Ball ball;

	public Game(JFrame jframe, int platformOnX, int platformOnY) {
		jframe.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {

				if (won || lost) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
						System.exit(0);
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					if (!isRunning)
						start();
					else if (isPaused)
						isPaused = false;
				} else {
					if (e.getKeyCode() == KeyEvent.VK_RIGHT)
						player.moveOnX(20);
					if (e.getKeyCode() == KeyEvent.VK_LEFT)
						player.moveOnX(-20);

				}

			}

		});

		platforms = new Platform[platformOnX][platformOnY];
		for (int x = 0; x != platforms.length; x++) {
			for (int y = 0; y != platforms[0].length; y++) {
				int platformWidth = gameField.width / platformOnX;
				int platformHeight = (gameField.height / 4) / platformOnY;
				platforms[x][y] = new Platform(x * platformWidth, y
						* platformHeight, platformWidth, platformHeight);
			}
		}

		ball = new Ball(this, gameField.width / 2, gameField.height / 2,
				Ball.standartBallRadius);

		player = new Player(
				this,
				(int) ((gameField.getWidth() - Player.standartPlayerWidth) / 2),
				gameField.height - Player.standartPlayerHeight,
				Player.standartPlayerWidth, Player.standartPlayerHeight);

		ballCount = 3;

	}

	public void loseBall() {
		pause();
		ballCount -= 1;
		if (ballCount <= 0)
			lost = true;
		ball.setVector(10, 10);
		ball.setPosition(gameField.width / 2, gameField.height / 2);
		player.setX((int) ((gameField.getWidth() - Player.standartPlayerWidth) / 2));
		player.setY(gameField.height - Player.standartPlayerHeight);

		repaint();
	}

	public void start() {
		
		isPaused = false;
		if (!isRunning) {
			
			gameThreadStart.start();
		}
	}

	public void playerWon() {
		won = true;
	}

	public void pause() {
		isPaused = true;

	}

	public void stop() {
		isRunning = false;
	
	}

	public Platform[][] getPlatform() {
		return this.platforms;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getPlayer() {
		return this.player;
	}

	public Dimension getGameDimension() {
		return gameField;
	}

	public void setSize(Dimension size) {
		super.setSize(size);
		if (!isRunning) {

			gameField = new Dimension(size.width - 200, size.height - 100);
			player.setX((int) ((gameField.getWidth() - Player.standartPlayerWidth) / 2));
			ball.setPosition(gameField.width / 2, gameField.height / 2);
			player.setY(gameField.height - Player.standartPlayerHeight);
			for (int x = 0; x != platforms.length; x++) {
				for (int y = 0; y != platforms[0].length; y++) {
					int platformWidth = gameField.width / platforms.length;
					int platformHeight = (gameField.height / 4)
							/ platforms[0].length;
					platforms[x][y] = new Platform(x * platformWidth, y
							* platformHeight, platformWidth, platformHeight);
				}
			}
		}
	}

	private Thread gameThreadStart = new Thread(new Runnable() {
		public void run() {
			System.out.println("run strat");
			isRunning = true;
			ball.setVector(10, 10);
			while (isRunning) {
				try {
					Thread.sleep(1);
				} catch (Exception e) {
				}
				if (!isPaused) {
					ball.collision();

					repaint();
					try {
						Thread.sleep(50);
					} catch (Exception e) {
					}
				}
			}
		}
	});

	public void paint(Graphics g) {
		super.paint(g);

		g.translate((getWidth() - gameField.width) / 2,
				(getHeight() - gameField.height) / 2);

		g.setColor(new Color(0, 0, 0));
		for (int i = 0; i != ballCount; i++) {
			int radius = 4;
			g.fillOval(i * radius * 2, -(radius * 2 + 3), radius * 2,
					radius * 2);
		}

		g.setColor(new Color(255, 255, 255, 100));
		g.fillRect(0, 0, gameField.width, gameField.height);

		ball.paintGraphics(g);
		player.paintGraphics(g);

		for (Platform[] pls : platforms) {
			for (Platform p : pls) {
				p.paintGraphics(g);
			}
		}

		g.setColor(new Color(0, 0, 0));
		g.drawRect(0, 0, gameField.width, gameField.height);

		if (won) {
			g.drawString("You Won!!", gameField.width / 2, gameField.height / 2);
			stop();
		}

		if (lost) {
			g.drawString("You Lost!!", gameField.width / 2,
					gameField.height / 2);
			stop();
		}

	}

}
