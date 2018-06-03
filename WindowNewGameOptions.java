package memory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

public class WindowNewGameOptions extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	
	JSpinner row;
	JSpinner col;
	JButton start = new JButton("Start");
	Integer a;
	Integer b;
	
	public WindowNewGameOptions() {
		super("New Game");
		setSize(400, 250);
		setLocation(490, 350);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
			JPanel panel1 = new JPanel();
			panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
			panel1.setBackground(Color.CYAN);
			panel1.add(new JLabel("Select grid size"));
			
			JPanel panel2 = new JPanel();
			panel2.setBackground(Color.cyan);
			panel2.setLayout(new GridLayout(2, 2, 50, 5));

			//rows				
				SpinnerModel rowModel = new SpinnerNumberModel(4, 2, 10, 1);
				row = new JSpinner(rowModel);
				row.setEditor(new JSpinner.NumberEditor(row));
				JFormattedTextField fieldRows = ((JSpinner.DefaultEditor)row.getEditor()).getTextField();
				fieldRows.setEditable(false);
									
			//columns
				SpinnerModel colModel = new SpinnerNumberModel(5, 2, 10, 1);
				col = new JSpinner(colModel);
				JFormattedTextField fieldCols = ((JSpinner.DefaultEditor)col.getEditor()).getTextField();
				fieldCols.setEditable(false);
					
			panel2.setBorder(BorderFactory.createMatteBorder(10, 50, 10, 50, Color.cyan));
			panel2.add(new JLabel("             rows"));
			panel2.add(new JLabel("           columns"));
			panel2.add(row);
			panel2.add(col);
			
			JPanel panel3 = new JPanel();
			panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
			panel3.setBackground(Color.cyan);
			panel3.add(start);
				start.addKeyListener(this);
				start.addActionListener(this);
					
			JPanel parent = new JPanel();
			parent.setLayout(new GridLayout(3, 1));
			parent.setBackground(Color.cyan);
			parent.add(panel1, BorderLayout.CENTER);
			parent.add(panel2, BorderLayout.CENTER);
			parent.add(panel3, BorderLayout.CENTER);
				
		this.add(parent);
		setVisible(true);
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object obj = e.getSource();
		
		if(obj == start && e.getKeyCode()==KeyEvent.VK_ESCAPE) {
			dispose();
		}
		
		if(obj == row && e.getKeyCode()==KeyEvent.VK_RIGHT) {
			col.requestFocus();
		} else if(obj == row && e.getKeyCode()==KeyEvent.VK_LEFT) {
			col.requestFocus();
		} else if(obj == row && e.getKeyCode()==KeyEvent.VK_DOWN) {
			start.requestFocus();
		} else if(obj == row && e.getKeyCode()==KeyEvent.VK_UP) {
			start.requestFocus();
		} else if(obj == col && e.getKeyCode()==KeyEvent.VK_RIGHT) {
			row.requestFocus();
		} else if(obj == col && e.getKeyCode()==KeyEvent.VK_LEFT) {
			row.requestFocus();
		} else if(obj == col && e.getKeyCode()==KeyEvent.VK_DOWN) {
			start.requestFocus();
		} else if(obj == col && e.getKeyCode()==KeyEvent.VK_UP) {
			start.requestFocus();
		} else if(obj == start && e.getKeyCode()==KeyEvent.VK_UP) {
			row.requestFocus();
		} else if(obj == start && e.getKeyCode()==KeyEvent.VK_DOWN) {
			col.requestFocus();
		} else if(obj == start && e.getKeyCode()==KeyEvent.VK_ENTER) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					
					boolean verify = true;
					
					try {
						a = (Integer) row.getValue();
						b = (Integer) col.getValue();
					}catch (Exception e) {
						e.printStackTrace();
						start.setEnabled(false);
						start.setText("BAAAAAAAAAAAAAAD - it must be number");
						start.setBackground(Color.RED);
						start.setEnabled(true);
						verify = false;
					}
						
					if((a*b)%2 != 0 || !(a instanceof Integer) || !(b instanceof Integer)) {
						start.setEnabled(false);
						start.setText("BAAAAAAAAAAAAAAD - odd card number");
						start.setBackground(Color.RED);
						start.setEnabled(true);
						verify = false;
					} else {
						verify = true;
					}
					
					if(verify == true) {
						new WindowNewGame(a, b);
					}	
				}
			});
		} 
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if(obj == start) {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					
					boolean verify = true;
					
					try {
						a = (Integer) row.getValue();
						b = (Integer) col.getValue();
					}catch (Exception e) {
						e.printStackTrace();
						start.setEnabled(false);
						start.setText("BAAAAAAAAAAAAAAD - it must be number");
						start.setBackground(Color.RED);
						start.setEnabled(true);
						verify = false;
					}
						
					if((a*b)%2 != 0 || !(a instanceof Integer) || !(b instanceof Integer)) {
						start.setEnabled(false);
						start.setText("BAAAAAAAAAAAAAAD - odd card number");
						start.setBackground(Color.RED);
						start.setEnabled(true);
						verify = false;
					} else {
						verify = true;
					}
					
					if(verify == true) {
						new WindowNewGame(a, b);
					}	
				}
			});
		}
	}
	
}
