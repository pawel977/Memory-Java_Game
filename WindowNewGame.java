package memory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class WindowNewGame extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private JLabel timerLabel;
	private int time = 0;
	private int timeMin = 0;
	private int userTime;
	private JLabel score;
	private int scoreNum = 0;
	private JButton exit;
	private JPanel [] cells;
	private JButton [] gameCards;
	private ImageIcon [] gameIcons;
	private int clicks = 0;
	private int allClicks = 0;
	private int [] clickIndex = new int [2];
	private int grid;
	
	WindowNewGame(int rows, int cols) {
		super("Memory Game");
		this.grid = rows*cols;
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setResizable(false);
		
		this.userTime = time;
		
			JPanel top = new JPanel();
			top.setLayout(new GridLayout(1, 3));
			top.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 150));
			top.setBackground(Color.green);
				
				JPanel panelTimer = new JPanel();
				panelTimer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 40));
				panelTimer.setBackground(Color.green);
					timerLabel = new JLabel();
					timerLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
		Timer timer = new Timer(1000, e -> EventQueue.invokeLater(() -> {
			String timeStr = "Time: ";
			String timeStrMin = "min ";
			String timeStrSec = "s";

			if (timeMin == 0) {
				timerLabel.setText(timeStr + time + timeStrSec);
				time++;
				userTime = time;
			} else {
				timerLabel.setText(timeStr + timeMin + timeStrMin + time + timeStrSec);
				time++;
				userTime = 60 * timeMin + time;
			}

			if (time == 60) {
				timeMin++;
				time = 1;
			}

			timerLabel.repaint();
		}));
						timer.start();						
						
				panelTimer.add(timerLabel);
					
				JPanel panelScore = new JPanel();
				panelScore.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
				panelScore.setBackground(Color.GREEN);
					score = new JLabel("Matches: "+this.scoreNum);
					score.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
				panelScore.add(score);
				
				JPanel panelExit = new JPanel();
				panelExit.setLayout(new GridBagLayout());
				panelExit.setBackground(Color.green);
					exit = new JButton("Exit");
				panelExit.add(exit);
					exit.setPreferredSize(new Dimension(100, 50));
					exit.setBackground(Color.magenta);
					exit.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
					exit.addActionListener(this);
				
			top.add(panelTimer);
			top.add(panelScore);
			top.add(panelExit);
			
			
		//GAME PANEL
				
				
			
			//number of cards
			int cards = getGrid();
							
			//creating paths for pictures
			File dir = new File("./src/main/java/memory/cardPics");
			File [] files = dir.listFiles();
				
			//creating arrays with pics
			assert files != null;
			ImageIcon[] cardIcons = new ImageIcon[files.length];

			//adding pics
			for(int i = 0; i < files.length; i++) {
				cardIcons[i] = new ImageIcon(files[i].toString());
				cardIcons[i].setDescription(Integer.toString(i));
			}
		 
			//creating cards set for a game
			gameCards = new JButton [cards];
			gameIcons = new ImageIcon [cards];
			
			//array with indexes of second cards from pairs - we cant put second card from one pair on place of other card
			ArrayList <Integer> taken = new ArrayList<>();
			
			//shuffling cards
			for(int i = 0; i < cards; i++) {
				
				//checking if index isnt already taken
				if(gameCards[i] == null) {
						
					//choosing random card from card set
					Random random = new Random();
					int rand1 = random.nextInt(cards);

					//creating pair of buttons
					JButton card1 = new JButton();
					JButton card2 = new JButton();
					
					//creating image for pair of cards
					ImageIcon icon1 = cardIcons[rand1];
					ImageIcon icon2 = cardIcons[rand1];
					
					//adding first card from created pair
					gameCards[i] = card1;					
					gameIcons[i] = icon1;
					taken.add(i);
					
					//getting random int for index of the second card from the pair
					int rand2 = i + random.nextInt(gameCards.length - i);
					
					if(taken.contains(rand2)) {
						while(taken.contains(rand2)) {
							rand2 = i + random.nextInt(gameCards.length - i);
						}
							}
					taken.add(rand2);
							
					//a	dding second card from pair to game set
					gameCards[rand2] = card2;
					gameIcons[rand2] = icon2;		
					}
				}
				
				//adding listener and covering all cards
				for (JButton gameCard : gameCards) {
					gameCard.addActionListener(this);
					gameCard.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - top.getHeight() / rows, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - top.getHeight() / rows));
				}
				
				//creating panel for each card in new game 
				cells = new JPanel [gameCards.length];
				for(int i = 0; i < gameCards.length; i++) {
					cells[i] = new JPanel();
					cells[i].setLayout(new BorderLayout());
					cells[i].setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.ORANGE));
					cells[i].add(gameCards[i], BorderLayout.CENTER);
				}
				
				//creating game panel
				JPanel game = new JPanel();
				
				//setting layout - gridbag rowsXcolumns
				game.setLayout(new GridLayout(rows, cols, 5, 5));
				game.setBackground(Color.orange);
				
				//adding panels with cards to game panel
				for(int i = 0; i < gameCards.length; i++) {
					game.add(cells[i]);
				}
				
			//panel with game board, timer, and points	
			JPanel parent = new JPanel();
			parent.setLayout(new BorderLayout());
			parent.add(top, BorderLayout.NORTH);
			parent.add(game, BorderLayout.CENTER);
				
		//adding everything to main frame	
		this.add(parent);	
		setVisible(true);
	}
	
	private int getUserTime() {
		return userTime;
	}

	private int getGrid() {
		return grid;
	}

	private int setScore() {
		scoreNum++;
		setScoreLab(scoreNum);

		return scoreNum;
	}

	private void setScoreLab(int newScore) {
		score.setText("Matches: "+newScore);
	}

	private int getScore() {
		return this.scoreNum;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == exit) {
			dispose();
		}
		
		for(int i = 0; i < getGrid(); i++) {			
			if(obj == gameCards[i]) {				
				gameCards[i].setIcon(gameIcons[i]);
				gameCards[i].removeActionListener(this);
				clickIndex[clicks] = i;
				allClicks++;
				clicks++;
				
				if(clicks == 2) {
					for(int j = 0; j < getGrid(); j++) {
						gameCards[j].setEnabled(false);
					}	
					gameCards[clickIndex[0]].setEnabled(true);
					gameCards[clickIndex[1]].setEnabled(true);
					gameCards[clickIndex[0]].addActionListener(this);
					gameCards[clickIndex[1]].addActionListener(this);
				}
			}
		}

		EventQueue.invokeLater(() -> {
			Timer cover = new Timer(2000, e1 -> {

				if(clicks == 2) {

					if(gameCards[clickIndex[0]].getIcon() != gameCards[clickIndex[1]].getIcon()) {
						gameCards[clickIndex[0]].setIcon(null);
						gameCards[clickIndex[1]].setIcon(null);
						clicks = 0;
						clickIndex[0] = 0;
						clickIndex[1] = 0;

						for(int i = 0; i < getGrid(); i++) {
							gameCards[i].setEnabled(true);
						}

					} else {

						for(int i = 0; i < getGrid(); i++) {
							gameCards[i].setEnabled(true);
						}

						gameCards[clickIndex[0]].setVisible(false);
						gameCards[clickIndex[1]].setVisible(false);
						cells[clickIndex[0]].setBackground(Color.GREEN);
						cells[clickIndex[1]].setBackground(Color.GREEN);

						setScore();
						clicks = 0;
						clickIndex[0] = 0;
						clickIndex[1] = 0;


						int score = getScore();

						if(score == getGrid()/2) {
							EventQueue.invokeLater(() -> {
								new WindowSaveScore(getUserTime(), getGrid(), allClicks);
								dispose();
							});
						}

					}
				}
			});
			cover.setRepeats(false);
			cover.start();
			});
	}					
}								
