


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Buttons extends JPanel implements ActionListener {
	//private Dimension buttonsField = new Dimension(400, 300);
	
	private JPanel tools;
	private JButton jplay, jstop;
	private String[] elementsBall, elementsBox;
	private JComboBox comboBall;
	private JComboBox comboBox;
	private JFrame  jframe; 
	private Game game;


   public Buttons(JFrame jframe, Game game){
		this.jframe=jframe;
		this.game=game;
	   
	   addTools();
   }
   

	private void addTools() {
		
        setLayout(null);
    
		jplay = new JButton("play");
		add(jplay);
		jplay.addActionListener(this);
		
		jstop = new JButton("stop");
		add(jstop);
		jstop.addActionListener(this);
		
		
		elementsBall = new String[] {
				"<html><font size = +1,5 color = black>ColorBall</font>",
				"<html><font size = +1,5 color = blue>Blue</font>",
				"<html><font size = +1,5 color = green>Green</font>",
				"<html><font size = +1,5 color = yellow>Yellow</font>" };
		comboBall = new JComboBox(elementsBall);
		comboBall.setSelectedIndex(0);
		add(comboBall);
		comboBall.addActionListener(this);

		elementsBox = new String[] {
				"<html><font size = 1,5 color = black>ColorBox</font>",
				"<html><font size = 1,5 color = blue>Blue</font>",
				"<html><font size = 1,5 color = green>Green</font>",
				"<html><font size = 1,5 color = yellow>Yellow</font>" };
		comboBox = new JComboBox(elementsBox);
		comboBox.setSelectedIndex(0);
		add(comboBox);
		comboBox.addActionListener(this);
		
		
		int height = 50;
		Dimension size = jstop.getPreferredSize();
		
		jstop.setBounds(jframe.getWidth(), height ,
	             size.width + 50, size.height + 20);
		
		
		size = jplay.getPreferredSize();
		jplay.setBounds(jframe.getWidth(), height+50 ,
	             size.width+50, size.height+20);

		
		size = comboBox.getPreferredSize();
		comboBox.setBounds(jframe.getWidth(), height+100 ,
	             size.width+30, size.height+20);
		
		size = comboBall.getPreferredSize();
		comboBall.setBounds(jframe.getWidth(), height+150 ,
	             size.width+30, size.height+20);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jplay) {
			play();
		} else if (e.getSource() == jstop) {
			stop();
		} else if (e.getSource() == comboBall) {
			BallColor();
		} else if (e.getSource() == comboBox) {
			BoxColor();
		}

	}

	private void BoxColor() {
		String currentColor = (String) comboBox.getSelectedItem();
        Platform.platformColor=getSelectColor(currentColor);
	}

	private void BallColor() {
		String currentColor = (String) comboBall.getSelectedItem();
		Ball.ballColor= getSelectColor(currentColor);

		
	}

	private Color getSelectColor(String currentColor){
		Color color ;
		

		if (ColorBall.blueColor.equals(currentColor)) {
			color = Color.BLUE;
			return color;
			
		}
		if (ColorBall.blackColor.equals(currentColor)) {
			 color = Color.BLACK;
			return color;
			
		}
		if (ColorBall.greenColor.equals(currentColor)) {
			 color= new Color (0,0,255);
			 return color;
			
		}
		if (ColorBall.YellowColor.equals(currentColor)) {
			color = Color.YELLOW;
			return color;
			
		}
		return null;
	}

	private void stop() {
      game.pause();
		

	}

	private void play() {
		game.start();

	}




}
