import javax.swing.JFrame;



public class Main {

	public static JFrame jframe;
	public static Game game;
	
	

	public static void main(String[] arg) {
		jframe = new JFrame("game");
		jframe.setSize(900, 700);
		jframe.setLocationRelativeTo(null);
        
		game = new Game(jframe, 10, 3);
		Menu menu = new Menu(jframe, game);
	//	Buttons buttons = new Buttons(jframe,game);
		game.setSize(jframe.getSize());

		jframe.add(game);
		jframe.add(menu);
		//jframe.add(buttons);
		

		jframe.setVisible(true);
	}
}
