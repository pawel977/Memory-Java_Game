package memory;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;
import java.util.List;
import java.util.Map.Entry;

public class WindowHighScores extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	
	private JTextArea scoreArea;
	private JPanel panel2;
	private StringBuilder sb;
	private String str;
	private int l;
	private	int m = 1;
	
	WindowHighScores() {
		super("New Game");
		setSize(600, 400);
		setLocation(400, 150);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
			JPanel panel1 = new JPanel();
			panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));
			panel1.setBackground(Color.CYAN);
				JLabel label1 = new JLabel("Scores:");
				label1.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
			panel1.add(label1);
			
			panel2 = new JPanel();
			panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			panel2.setBackground(Color.CYAN);
			
				EventQueue.invokeLater(() -> {
					File file = new File("./score.txt");

					if(!file.exists()) {

						String brak = "\n Brak wynikow";
						scoreArea = new JTextArea(brak);

						scoreArea.setBackground(Color.CYAN);
						scoreArea.setEditable(false);

						panel2.add(scoreArea);

					} else {

						try {

							FileInputStream fi = new FileInputStream(file);
							@SuppressWarnings("resource")
							ObjectInputStream oi = new ObjectInputStream(fi);

								@SuppressWarnings("unchecked")
								Map <String, Double> scores = (Map<String, Double>) oi.readObject();

								Map <String, Double> sortedScores = sortByValue(scores);

								sb = new StringBuilder();

								sb.append("\n");

								for (Entry<String, Double> entry : sortedScores.entrySet()){
									setL(0);

									if(m == 1) {
										str = "ST PLACE: ";
									} else if(m == 2) {
										str = "ND PLACE: ";
									} else if(m == 3) {
										str = "RD PLACE: ";
									} else {
										str = ") ";
									}

									sb.append(m).append(str).append(entry.getKey()).append(" - ").append(entry.getValue()).append(" points");
									sb.append("\n");

									setL(l++);
									m++;
								}

								String wynik = sb.toString();
								scoreArea = new JTextArea(wynik);

								DefaultHighlightPainter gold = new DefaultHighlightPainter(Color.YELLOW);
								DefaultHighlightPainter silver = new DefaultHighlightPainter(Color.LIGHT_GRAY);
								DefaultHighlightPainter bronze = new DefaultHighlightPainter(Color.getHSBColor(26.09f, 68.05f, 10f));

								DefaultHighlightPainter[] painter = new DefaultHighlightPainter[3];
									painter[0] = gold;
									painter[1] = silver;
									painter[2] = bronze;

								for(int i = 0; (i < 3 || i < scoreArea.getRows()); i++) {
									try {
										Highlighter highlighter = scoreArea.getHighlighter();
										highlighter.addHighlight(scoreArea.getLineStartOffset(i+1), scoreArea.getLineEndOffset(i+1), painter[i]);
									} catch (BadLocationException e) {
									}
								}


								scoreArea.setEditable(false);
								scoreArea.setBackground(Color.CYAN);

								panel2.add(scoreArea);

						}catch (IOException | ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				});
				
			
			
			JPanel panel3 = new JPanel();
			panel3.setBackground(Color.cyan);
			panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
				JScrollPane pane = new JScrollPane(panel2);
				pane.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.black));
				pane.setPreferredSize(new Dimension(400, 230));
				pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			panel3.add(pane);
			
			JPanel parent = new JPanel();
			parent.setLayout(new BorderLayout());
			parent.setBackground(Color.cyan);
			parent.add(panel1, BorderLayout.PAGE_START);
			parent.add(panel3, BorderLayout.CENTER);
	
		this.add(parent);
		setVisible(true);		
	}
	
	private Map<String, Double> sortByValue(Map<String, Double> scores) {
		
		List <Entry<String, Double>> sortedList = new LinkedList<>(scores.entrySet());
		
		sortedList.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
		
		Map<String, Double> sortedMap = new LinkedHashMap<>();
		for(Entry<String, Double> entry : sortedList) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		
		return sortedMap;
		
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

	}

	private void setL(int l) {
		this.l = l;
	}
}

