package UltimateTicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.URL;

class Run {
	/*Instance Variables*/
	private JFrame frame = new JFrame("Ultimate-TicTacToe");

	private JFrame setupFrame;
	private JTextField xField;
	private JTextField oField;
	private JTextField sizeField;
	private JLabel info;

	/*Static Methods*/
	public static void main(String[] args) {
		if (!(Gui.ALL_FINE)) {
			System.out.println("some gui icons not found");
			return;
		}
		Run run = new Run();
		run.intro();
		run.mainMenu();
	}

	/*Instance Methods*/
	public void intro() {

		JPanel panel = new JPanel();
		frame.setSize(400, 400);
		BufferedImage intro;
		try {
			URL url = getClass().getResource("./images/intro.png");
			intro = ImageIO.read(new File(url.getPath()));
		} catch (IOException e) {
			System.out.println("intro image not found");
			return;
		}
		JLabel introLabel = new JLabel(new ImageIcon(intro));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(introLabel);
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
		try {
			Thread.sleep(5000);
		} catch (Exception e) {

		}
		frame.remove(panel);
	}

	public void mainMenu() {

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		BufferedImage startIcon;
		BufferedImage rulesIcon;
		BufferedImage quitIcon;

		try {
			URL url = getClass().getResource("./images/menu/start.png");
			startIcon = ImageIO.read(new File(url.getPath()));
		} catch(IOException e) {
			System.out.println("menu image 'start' not found");
			return;
		}

		try {
			URL url = getClass().getResource("./images/menu/rules.png");
			rulesIcon = ImageIO.read(new File(url.getPath()));
		} catch(IOException e) {
			System.out.println("menu image 'rules' not found");
			return;
		}

		try {
			URL url = getClass().getResource("./images/menu/quit.png");
			quitIcon = ImageIO.read(new File(url.getPath()));
		} catch (IOException e) {
			System.out.println("menu image 'quit' not found");
			return;
		}

		JLabel startLabel = new JLabel(new ImageIcon(startIcon));
		JLabel rulesLabel = new JLabel(new ImageIcon(rulesIcon));
		JLabel quitLabel = new JLabel(new ImageIcon(quitIcon));

		JPanel startPanel = new JPanel();
		JPanel rulesPanel = new JPanel();
		JPanel quitPanel = new JPanel();

		startPanel.add(startLabel);
		rulesPanel.add(rulesLabel);
		quitPanel.add(quitLabel);

		startPanel.setOpaque(false);
		rulesPanel.setOpaque(false);
		quitPanel.setOpaque(false);

		startPanel.setBackground(Gui.VERY_LIGHT_YELLOW);
		rulesPanel.setBackground(Gui.VERY_LIGHT_YELLOW);
		quitPanel.setBackground(Gui.VERY_LIGHT_YELLOW);

		panel.add(startPanel);
		panel.add(rulesPanel);
		panel.add(quitPanel);

		panel.setBackground(Color.WHITE);

		frame.getContentPane().add(panel);

		SwingUtilities.updateComponentTreeUI(frame);

		startPanel.addMouseListener(new StartListener());
		rulesPanel.addMouseListener(new RulesListener());
		quitPanel.addMouseListener(new QuitListener());

	}

	public void startNewMatch() {
		setupFrame = new JFrame("Match Settings");
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		setupFrame.getContentPane().add(mainPanel);
		setupFrame.setSize(800, 300);
		setupFrame.setVisible(true);
		setupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel xPanel = new JPanel();
		JLabel xLabel = new JLabel("Who is gonna play X ? ");
		xField = new JTextField(20);
		xField.requestFocus();
		xPanel.add(xLabel);
		xPanel.add(xField);
		mainPanel.add(xPanel);

		JPanel oPanel = new JPanel();
		JLabel oLabel = new JLabel("Who is gonna play O ? ");
		oField = new JTextField(20);
		oPanel.add(oLabel);
		oPanel.add(oField);
		mainPanel.add(oPanel);

		JPanel sizePanel = new JPanel();
		JLabel sizeLabel = new JLabel("Board Size ? ");
		sizeField = new JTextField(20);
		sizePanel.add(sizeLabel);
		sizePanel.add(sizeField);
		mainPanel.add(sizePanel);

		JButton submit = new JButton("Start");
		submit.addActionListener(new NewMatchListener());
		mainPanel.add(submit);

		info = new JLabel("");
		mainPanel.add(info);
	}

	/*Inner Classes*/

	class NewMatchListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String[] name = new String[2];
			name[MiniBoard.X - 1] = xField.getText();
			name[MiniBoard.O - 1] = oField.getText();

			String sizeString = sizeField.getText();

			if (name[0].length() > 10 || name[1].length() > 10 || name[0].length() < 3 || name[1].length() < 3) {
				info.setText("*names should be less than 11 characters and more than 2");
				SwingUtilities.updateComponentTreeUI(setupFrame);
				return;
			}

			if (sizeString.length() != 1) {
				info.setText("*size should be less than 10 and it should be mentioned in digits ");
				SwingUtilities.updateComponentTreeUI(setupFrame);
				return;
			}

			if (!((name[0].chars().allMatch(Character::isLetter)) && (name[1].chars().allMatch(Character::isLetter)))) {
				info.setText("*names can only contain letters");
				SwingUtilities.updateComponentTreeUI(setupFrame);
				return;
			}

			if (!(sizeString.chars().allMatch(Character::isDigit))) {
				info.setText("*size in digits only");
				SwingUtilities.updateComponentTreeUI(setupFrame);
				return;
			}

			int size;
			size = Integer.parseInt(sizeString);

			if (size < 3 || size > 5) {
				info.setText("*only size 3 to 5 allowed");
				SwingUtilities.updateComponentTreeUI(setupFrame);
				return;
			}

			MatchLocal match = new MatchLocal(size, name);
			match.start();
			setupFrame.dispatchEvent(new WindowEvent(setupFrame, WindowEvent.WINDOW_CLOSING));

		}
	}

	class StartListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			startNewMatch();
		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {
			JPanel panel = (JPanel) e.getSource();
			panel.setOpaque(true);
			SwingUtilities.updateComponentTreeUI(frame);
		}

		public void mouseExited(MouseEvent e) {
			JPanel panel = (JPanel) e.getSource();
			panel.setOpaque(false);
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}

	class RulesListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			JFrame ruleFrame = new JFrame();
			JPanel rulePanel = new JPanel();
			rulePanel.setLayout(new BoxLayout(rulePanel, BoxLayout.Y_AXIS));
			JEditorPane rulesEP = new JEditorPane("text/plain",
					"Each small 3-by-3 tic-tac-toe board is referred to as a local board, and the larger 3-by-3 board is referred to as the global board.\n\nThe game starts with X playing wherever they want in any of the 81 empty spots. This move 'sends' their opponent to its relative location. For example, if X played in the top right square of their local board, then O needs to play next in the local board at the top right of the global board. O can then play in any one of the nine available spots in that local board, each move sending X to a different local board.\n\nIf a move is played so that it is to win a local board by the rules of normal tic-tac-toe, then the entire local board is marked as a victory for the player in the global board.\n\nOnce the outcome of a local board is decided (win or draw), no more moves may be played in that board. If a player is sent to such a board, then that player may play in any other board.\n\nGame play ends when either a player wins the global board, or there are no legal moves remaining.");

			rulesEP.setEditable(false);

			rulePanel.add(rulesEP);
			rulePanel.setBackground(Color.WHITE);

			ruleFrame.getContentPane().add(rulePanel);
			ruleFrame.setSize(500, 500);
			ruleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			ruleFrame.setVisible(true);
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
			JPanel panel = (JPanel) e.getSource();
			panel.setOpaque(true);
			SwingUtilities.updateComponentTreeUI(frame);
		}

		public void mouseExited(MouseEvent e) {
			JPanel panel = (JPanel) e.getSource();
			panel.setOpaque(false);
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}

	class QuitListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
			JPanel panel = (JPanel) e.getSource();
			panel.setOpaque(true);
			SwingUtilities.updateComponentTreeUI(frame);
		}

		public void mouseExited(MouseEvent e) {
			JPanel panel = (JPanel) e.getSource();
			panel.setOpaque(false);
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
}
