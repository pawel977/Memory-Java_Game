package memory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class WindowEntry extends JFrame implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	
	JButton newGame = new JButton("New Game");
	JButton highScores = new JButton("High Scores");
	JButton exit = new JButton("Exit");
	
	public WindowEntry() {
		super("Memory Game");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Exit");
		getRootPane().getActionMap().put("Exit", new AbstractAction() {
		
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				new WindowConfirmExit();
			}	
		
		});
		
			JPanel title = new JPanel();
			title.setPreferredSize(new Dimension(500, 100));
			title.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
			title.setBackground(Color.ORANGE);
				JLabel label = new JLabel("MEMORY the game");
				label.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
			title.add(label);
				
			JPanel buttons = new JPanel();
			buttons.setPreferredSize(new Dimension(200, 200));
			buttons.setLayout(new GridLayout(3, 1, 0, 10));
			buttons.setBackground(Color.ORANGE);
				newGame.setBackground(Color.GREEN);
				newGame.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.magenta));
				highScores.setBackground(Color.PINK);
				highScores.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
				exit.setBackground(Color.CYAN);
				exit.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.red));
			buttons.add(newGame);
			buttons.add(highScores);
			buttons.add(exit);
				newGame.addActionListener(this);
				newGame.addKeyListener(this);
				highScores.addActionListener(this);
				highScores.addKeyListener(this);
				exit.addActionListener(this);
				exit.addKeyListener(this);
				
			JPanel parent1 = new JPanel();
			parent1.setBackground(Color.orange);
			parent1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 200));
			parent1.add(title);
			
			JPanel parent2 = new JPanel();
			parent2.setBackground(Color.ORANGE);
			parent2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			parent2.add(buttons);
			
			JPanel parent = new JPanel();
			parent.setLayout(new GridLayout(2, 1));
			parent.add(parent1);
			parent.add(parent2);
			
		this.add(parent);
		setVisible(true);
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object obj = e.getSource();
		
		if(obj == newGame && e.getKeyCode()==KeyEvent.VK_ENTER){
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					new WindowNewGameOptions();
				}
			});
		} else if(obj == highScores && e.getKeyCode()==KeyEvent.VK_ENTER) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					new WindowHighScores();
				}
			});
		} else if(obj == exit && e.getKeyCode()==KeyEvent.VK_ENTER) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					new WindowConfirmExit();
				}
			});
		}
		
		if(obj == newGame && e.getKeyCode()==KeyEvent.VK_DOWN) {
			highScores.requestFocus();
			highScores.setBackground(Color.yellow);
			newGame.setBackground(Color.green);
		} else if(obj == newGame && e.getKeyCode()==KeyEvent.VK_UP) {
			exit.requestFocus();
			exit.setBackground(Color.YELLOW);
			newGame.setBackground(Color.green);
		} else if(obj == highScores && e.getKeyCode()==KeyEvent.VK_DOWN) {
			exit.requestFocus();
			exit.setBackground(Color.YELLOW);
			highScores.setBackground(Color.PINK);
		} else if(obj == highScores && e.getKeyCode()==KeyEvent.VK_UP) {
			newGame.requestFocus();
			newGame.setBackground(Color.yellow);
			highScores.setBackground(Color.PINK);
		} else if(obj == exit && e.getKeyCode()==KeyEvent.VK_DOWN) {
			newGame.requestFocus();
			newGame.setBackground(Color.yellow);
			exit.setBackground(Color.MAGENTA);
		} else if(obj == exit && e.getKeyCode()==KeyEvent.VK_UP) {
			highScores.requestFocus();
			highScores.setBackground(Color.yellow);
			exit.setBackground(Color.magenta);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == newGame) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					new WindowNewGameOptions();
				}
			});
		} else if (obj == highScores) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					new WindowHighScores();
				}
			});
		} else if (obj == exit) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					new WindowConfirmExit();
				}
			});
		}
		
	}

}


