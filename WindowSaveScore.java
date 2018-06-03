package memory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WindowSaveScore extends JFrame implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	
	JTextField nick;	
	JButton save;
	double userTime;
	double userGrid;
	double userClicks;
	
	public WindowSaveScore(int time, int grid, int clicks) {
		super("Save your score");
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		this.userClicks = clicks;
		this.userGrid = grid;
		this.userTime = time;
		
			JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
			panel1.setBackground(Color.green);
				JLabel label1 = new JLabel("Your score:");
				label1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 90));
			panel1.add(label1);
			
			JPanel panel2 = new JPanel(new GridLayout(4, 1, 0, 5));
			panel2.setBackground(Color.green);
			
				JPanel panelTime = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
				panelTime.setBackground(Color.green);
					JLabel timeLabel = new JLabel("Time: "+Integer.valueOf(time).toString()+" seconds");
					timeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
				panelTime.add(timeLabel);
	
				JPanel panelGrid = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
				panelGrid.setBackground(Color.green);
					JLabel gridLabel = new JLabel("Number of cards: "+grid);
					gridLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
				panelGrid.add(gridLabel);
				
				JPanel panelClicks = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
				panelClicks.setBackground(Color.green);
					JLabel clickLabel = new JLabel("Number of moves: "+clicks);
					clickLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
				panelClicks.add(clickLabel);
				
				JPanel panelScore = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
				panelScore.setBackground(Color.green);
					JLabel scoreLabel = new JLabel("Your score: "+getScore()+" points");
					scoreLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
				panelScore.add(scoreLabel);
				
			panel2.add(panelTime);
			panel2.add(panelGrid);
			panel2.add(panelClicks);
			panel2.add(panelScore);
			
			JPanel panel3 = new JPanel(new GridBagLayout());
			panel3.setBackground(Color.green);
				JLabel nickField = new JLabel("Enter your nick: ");
				nickField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			panel3.add(nickField);
				nick = new JTextField();
				nick.setPreferredSize(new Dimension(200, 30));
				nick.addActionListener(this);
				nick.addKeyListener(this);			
			panel3.add(nick);
			
			JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 50));
			panel4.setBackground(Color.GREEN);
			panel4.add(panel3);
			
			JPanel panelSave = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
			panelSave.setBackground(Color.green);
				save = new JButton("Save");
				save.setPreferredSize(new Dimension(150, 50));
				save.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
				save.setBackground(Color.cyan);
				save.addActionListener(this);
				save.addKeyListener(this);
			panelSave.add(save);
			
			JPanel panel5 = new JPanel(new GridLayout(2, 1));
			panel5.setBackground(Color.cyan);
			panel5.add(panel4);
			panel5.add(panelSave);
			
			
			JPanel parent = new JPanel();
			parent.setLayout(new GridLayout(3, 1));
			parent.setBackground(Color.cyan);
			parent.add(panel1);
			parent.add(panel2);
			parent.add(panel5);
			
				
		this.add(parent);
		setVisible(true);
	}
	
	public String getNick() {
		return nick.getText();
	}

	public double getUserTime() {
		return userTime;
	}

	public double getUserGrid() {
		return userGrid;
	}

	public double getUserClicks() {
		return userClicks;
	}
	
	private double getScore() {
		return (getUserGrid()/(getUserClicks()+getUserTime()))*1000;
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
		
		if(obj == save) {
			EventQueue.invokeLater(new Runnable() {				
				@SuppressWarnings("resource")
				@Override
				public void run() {
					
					String points = String.format("%.3f", getScore()); 					
					String user = getNick();
					
					try {
						
						Path path = Paths.get("./score.txt");
						File file = new File("./score.txt");
						
						if(file.exists()) {
						
							FileInputStream fi = new FileInputStream(file);
							ObjectInputStream oi = new ObjectInputStream(fi);
							@SuppressWarnings("unchecked")
							HashMap <String, Double> scores = (HashMap<String, Double>) oi.readObject();
							
							scores.put(user, Double.parseDouble(points));
							
							FileOutputStream fo = new FileOutputStream(file);
							ObjectOutputStream oo = new ObjectOutputStream(fo);
							oo.writeObject(scores);							
							
						} else {

							Files.createFile(path);
							
							FileOutputStream fo = new FileOutputStream(file);
							ObjectOutputStream oo = new ObjectOutputStream(fo);
							oo.writeObject(new HashMap<String, Double>());
							
							FileInputStream fi = new FileInputStream(file);
							ObjectInputStream oi = new ObjectInputStream(fi);
							@SuppressWarnings("unchecked")
							HashMap <String, Double> scores = (HashMap<String, Double>) oi.readObject();

							scores.put(user, Double.parseDouble(points));
							
							FileOutputStream fo2 = new FileOutputStream(file);
							ObjectOutputStream oo2 = new ObjectOutputStream(fo2);
							oo2.writeObject(scores);
						
						}
					} catch (IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}					
				}
			});			
			dispose();
		}		
	}
}
