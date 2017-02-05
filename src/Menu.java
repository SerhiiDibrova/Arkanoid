import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.MenuDragMouseListener;

public class Menu extends JPanel implements ActionListener {

	private JMenuBar menuBar;

	private JMenu menuOpen;
	private JMenu menuEditor;

	private JMenuItem menuStart;
	private JMenuItem menuPause;
	private JMenuItem menuStop;
	private JMenuItem menuExit;

	private JMenuItem colorBall;
	private JMenuItem colorPlayer;
	private JMenuItem colorBox;

	private Game instanceGames;
	private JFrame frame;
	private MenuListener menuListener;

	Menu(JFrame frame, Game game) {
		this.frame = frame;
		this.instanceGames = game;
		createMenu();
	}

	private void createMenu() {
		menuListener = new MenuListener();
		menuBar = new JMenuBar();

		menuOpen = new JMenu("File");
		menuEditor = new JMenu("Editor");

		menuStart = new JMenuItem("Start");
		menuOpen.add(menuStart);
		menuStart.addActionListener(this);
		menuStart.setActionCommand("start");

		menuStop = new JMenuItem("Stop");
		menuOpen.add(menuStop);
		menuStop.addActionListener(this);
		menuStop.setActionCommand("stop");

		menuExit = new JMenuItem("Exit prorgamm");
		menuOpen.addSeparator();// Разделитель
		menuOpen.add(menuExit);
		menuExit.addActionListener(menuListener);
		menuExit.setActionCommand("exit");

		colorBall = new JMenuItem("Ball Color Random");
		menuEditor.add(colorBall);
		colorBall.addActionListener(menuListener);
		colorBall.setActionCommand("ballColorRandom");

		colorPlayer = new JMenuItem("Player Color Random");
		menuEditor.add(colorPlayer);
		colorPlayer.addActionListener(menuListener);
		colorPlayer.setActionCommand("playerColorRandom");

		colorBox = new JMenuItem("Box Color Random ");
		menuEditor.add(colorBox);
		colorBox.addActionListener(menuListener);
		colorBox.setActionCommand("boxColorRandom");

		menuBar.add(menuOpen);
		menuBar.add(menuEditor);

		frame.setJMenuBar(menuBar);

	}

	public void startGame() {
		instanceGames.start();

	}

	public void stopGame() {
		instanceGames.pause();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String comand = e.getActionCommand();

		if (comand.equalsIgnoreCase("start")) {
			startGame();

		} else if (comand.equalsIgnoreCase("stop")) {
			stopGame();
		}
	}
}
