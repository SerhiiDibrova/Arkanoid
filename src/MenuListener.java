import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class MenuListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		String comand = e.getActionCommand();

		if (comand.equalsIgnoreCase("ballColorRandom")) {
			ballColorRandom();
		} else if (comand.equalsIgnoreCase("playerColorRandom")) {
			playerColorRandom();
		} else if (comand.equalsIgnoreCase("boxColorRandom")) {
			boxColorRandom();
		} else if (comand.equalsIgnoreCase("exit")) {
			exitMenu();
		}

	}

	private void ballColorRandom() {
		Ball.ballColor = randomColor();

	}

	private void boxColorRandom() {
		Platform.platformColor = randomColor();

	}

	private void playerColorRandom() {
		Player.playerColor = randomColor();

	}

	private void exitMenu() {
		Object[] options = { "Конечно:)", "Ноу:(" };
		int option = JOptionPane.showOptionDialog(null,
				"Что устал)? Хочешь выйти)? " + "", "Вопрос?",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, options[1]);
		if (option == 0) {

			System.exit(0);
		}

	}

	private Color randomColor() {
		int a = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		int c = (int) (Math.random() * 256);
		return new Color(a, b, c);
	}

}
