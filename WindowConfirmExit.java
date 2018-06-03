package memory;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WindowConfirmExit extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	JButton butt1 = new JButton("Confirm");
	JButton butt2 = new JButton("Resign");
	
	public WindowConfirmExit() {
		super("Confirm Exit");
		setSize(400, 220);
		setLocation(490, 280);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
			JPanel panel1 = new JPanel();
			panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 40));
			panel1.setBackground(Color.YELLOW);
			panel1.add(new JLabel("Please Confirm"));
			
			JPanel panel2 = new JPanel();
			panel2.setBackground(Color.YELLOW);
			panel2.add(butt1, BorderLayout.WEST);
			panel2.add(butt2, BorderLayout.EAST);
				butt1.addActionListener(this);
				butt1.addKeyListener(this);
				butt2.addActionListener(this);
				butt2.addKeyListener(this);
				
			JPanel parent = new JPanel();
			parent.setLayout(new GridLayout(2, 1));
			parent.setBackground(Color.YELLOW);
			parent.add(panel1, BorderLayout.CENTER);
			parent.add(panel2, BorderLayout.CENTER);
				
		this.add(parent);
		setVisible(true);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Object obj = e.getSource();
			
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE) {
				dispose();
			}
			
			if(obj == butt1 && e.getKeyCode()==KeyEvent.VK_ENTER) {
				System.exit(0);
			} else if (obj == butt2 && e.getKeyCode()==KeyEvent.VK_ENTER) {
				dispose();
			}
			
			if(obj == butt1 && e.getKeyCode()==KeyEvent.VK_RIGHT) {
				butt2.requestFocus();
			} else if(obj == butt2 && e.getKeyCode()==KeyEvent.VK_RIGHT) {
				butt1.requestFocus();
			} else if(obj == butt1 && e.getKeyCode()==KeyEvent.VK_LEFT) {
				butt2.requestFocus();
			} else if(obj == butt2 && e.getKeyCode()==KeyEvent.VK_LEFT) {
				butt1.requestFocus();
			}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == butt1) {
			System.exit(0);
		} else if(obj == butt2) {
			dispose();
		}

	}

}
