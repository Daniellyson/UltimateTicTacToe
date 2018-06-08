package UltimateTicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Gui {

	/* Static Variables */
	private static final int BOARD_GAP = 30; //gap btw mini-boards
	private static final int MINI_GAP = 1; //gap btw cells in any mini-board
	private static final int ICON_SIZE = 30; //pixel size of square icons
	private static final BufferedImage X_OPAQUE;
	private static final BufferedImage X_TRANSPARENT;
	private static final BufferedImage O_OPAQUE;
	private static final BufferedImage O_TRANSPARENT;
	private static final BufferedImage EMPTY;
	public static final Color VERY_LIGHT_YELLOW = new Color(255, 255, 204);
	public static final Boolean ALL_FINE; // check it in at some point before use clicks start

	static {
		BufferedImage x, xt, o, ot, e;
		Boolean allFine = true;
		try {
			x = ImageIO.read(new File(
					"/home/kj/Documents/BOOKS/Computers/java/java_practice/UltimateTicTacToe/images/icons/X_opaque.png"));
			System.out.println("X found");
			xt = ImageIO.read(new File(
					"/home/kj/Documents/BOOKS/Computers/java/java_practice/UltimateTicTacToe/images/icons/X_transparent.png"));
			System.out.println("Xt found");
			o = ImageIO.read(new File(
					"/home/kj/Documents/BOOKS/Computers/java/java_practice/UltimateTicTacToe/images/icons/O_opaque.png"));
			System.out.println("O found");
			ot = ImageIO.read(new File(
					"/home/kj/Documents/BOOKS/Computers/java/java_practice/UltimateTicTacToe/images/icons/O_transparent.png"));
			System.out.println("Ot found");
			e = ImageIO.read(new File(
					"/home/kj/Documents/BOOKS/Computers/java/java_practice/UltimateTicTacToe/images/icons/Empty.png"));
			System.out.println("Empty found");
		} catch (IOException ex) {
			System.out.println("Some image file not found");
			x = xt = o = ot = e = null;
			allFine = false;
		}
		ALL_FINE = allFine;
		X_OPAQUE = x;
		X_TRANSPARENT = xt;
		O_OPAQUE = o;
		O_TRANSPARENT = ot;
		EMPTY = e;
	}

	/* Instance Variables */
	public final int BOARD_SIZE;
	private final GridLayout BOARD_GRID;
	private final GridLayout MINIBOARD_GRID;
	private String[] playerName;

	private MatchLocal match;

	private JFrame frame;
	private JPanel board;
	private JPanel[][] miniBoard; // 3 x 3 = localboards
	private MyCellPanel[][][][] cell; // (3 x 3) x (3 x 3) = localboard x cell
	private JLabel[][][][] cellLabel; // (3 x 3) x (3 x 3) = a label for each cell;
	private MyPoint lastPlayedPoint;

	/* Constructors */
	public Gui(int size, String[] name, MatchLocal m) {

		playerName = name;

		match = m;

		BOARD_SIZE = size;
		BOARD_GRID = new GridLayout(BOARD_SIZE, BOARD_SIZE, BOARD_GAP, BOARD_GAP);
		MINIBOARD_GRID = new GridLayout(BOARD_SIZE, BOARD_SIZE, MINI_GAP, MINI_GAP);

		frame = new JFrame("Match: X-" + playerName[MiniBoard.X - 1] + " vs O-" + playerName[MiniBoard.O -1]);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		board = new JPanel(BOARD_GRID);
		board.setBackground(Color.WHITE);
		frame.getContentPane().add(board);

		// add 9 mini board panels to the board panel
		miniBoard = new JPanel[BOARD_SIZE][BOARD_SIZE];
		for (int bD = 0; bD < BOARD_SIZE; bD++) { // bD = boardDown
			for (int bR = 0; bR < BOARD_SIZE; bR++) { // bR = boardRight
				miniBoard[bD][bR] = new JPanel(MINIBOARD_GRID);
				miniBoard[bD][bR].setBackground(Color.WHITE);
				board.add(miniBoard[bD][bR]);
			}
		}

		//add cells to each miniboard
		cell = new MyCellPanel[BOARD_SIZE][BOARD_SIZE][BOARD_SIZE][BOARD_SIZE];
		cellLabel = new JLabel[BOARD_SIZE][BOARD_SIZE][BOARD_SIZE][BOARD_SIZE];
		for (int bD = 0; bD < BOARD_SIZE; bD++) { // boardDown
			for (int bR = 0; bR < BOARD_SIZE; bR++) { //boardRight
				for (int mD = 0; mD < BOARD_SIZE; mD++) { //miniDown
					for (int mR = 0; mR < BOARD_SIZE; mR++) { //miniRight

						cellLabel[bD][bR][mD][mR] = new JLabel(new ImageIcon(Gui.EMPTY));

						cell[bD][bR][mD][mR] = new MyCellPanel(bD, bR, mD, mR);
						cell[bD][bR][mD][mR].add(cellLabel[bD][bR][mD][mR]);
						cell[bD][bR][mD][mR].setBackground(Color.WHITE);

						cell[bD][bR][mD][mR].addMouseListener(new CellListener());

						miniBoard[bD][bR].add(cell[bD][bR][mD][mR]);
					}
				}
			}
		}
		int fsize = (ICON_SIZE * BOARD_SIZE * BOARD_SIZE) + (BOARD_GAP * (BOARD_SIZE - 1))
				+ (MINI_GAP * (BOARD_SIZE - 1) * BOARD_SIZE) + 100;
		frame.setSize(fsize, fsize);
		frame.setVisible(true);
	}

	/*Static Methods*/
	// public static void main(String[] args) {
	// 	Gui gui = new Gui(4);
	// 	gui.go();
	// }

	/*Instance Methods*/
	// public void go() {
	//
	// 	frame.setVisible(true);
	// 	System.out.println("reached here");
	// }

	public void syncGui(Board board, boolean anywhere, MyPoint last) {

		frame.setTitle("Match: X-" + playerName[MiniBoard.X - 1] + " vs O-" + playerName[MiniBoard.O - 1] + " = Moves - " + match.getMoves());

		MyPoint p = new MyPoint();
		for (p.boardDown = 0; p.boardDown < BOARD_SIZE; (p.boardDown)++) { // boardDown
			for (p.boardRight = 0; p.boardRight < BOARD_SIZE; (p.boardRight)++) { //boardRight
				for (p.miniDown = 0; p.miniDown < BOARD_SIZE; p.miniDown++) { //miniDown
					for (p.miniRight = 0; p.miniRight < BOARD_SIZE; p.miniRight++) { //miniRight

						switch (board.getPosition(p)) {
						case MiniBoard.EMPTY:
							cellLabel[p.boardDown][p.boardRight][p.miniDown][p.miniRight]
									.setIcon(new ImageIcon(Gui.EMPTY));

							break;

						case MiniBoard.X:
							cellLabel[p.boardDown][p.boardRight][p.miniDown][p.miniRight]
									.setIcon(new ImageIcon(Gui.X_OPAQUE));
							break;

						case MiniBoard.O:
							cellLabel[p.boardDown][p.boardRight][p.miniDown][p.miniRight]
									.setIcon(new ImageIcon(Gui.O_OPAQUE));
							break;
						}
						cell[p.boardDown][p.boardRight][p.miniDown][p.miniRight]
								.setEligible(Referee.isPlacementAllowed(p, last, anywhere, board));
					}
				}
			}
		}

		if (last != null) {
			p = last;
			cell[p.boardDown][p.boardRight][p.miniDown][p.miniRight].setBackground(Color.LIGHT_GRAY);
		}

		if (lastPlayedPoint != null) {
			p = lastPlayedPoint;
			cell[p.boardDown][p.boardRight][p.miniDown][p.miniRight].setBackground(Color.WHITE);
		}

		lastPlayedPoint = last;

		SwingUtilities.updateComponentTreeUI(frame);
	}

	//Inner Classes
	class CellListener implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			if (match.isProcessing() || match.isDecided()) {
				return;
			}
			MyCellPanel cell = (MyCellPanel) e.getSource();
			if (cell.isEligible()) {
				// System.out.println("Clicked (" + cell.B_D + ", " + cell.B_R + ", " + cell.M_D + ", " + cell.M_R + ")");
				MyPoint p = new MyPoint();
				p.boardDown = cell.B_D;
				p.boardRight = cell.B_R;
				p.miniDown = cell.M_D;
				p.miniRight = cell.M_R;

				match.moveplaced(p);

				lastPlayedPoint = p;
			}
		}

		public void mousePressed(MouseEvent e) {
			// MyCellPanel cell = (MyCellPanel) e.getSource();
			// System.out.println("Pressed (" + cell.B_D + ", " + cell.B_R + ", " + cell.M_D + ", " + cell.M_R + ")");
		}

		public void mouseReleased(MouseEvent e) {
			// MyCellPanel cell = (MyCellPanel) e.getSource();
			// System.out.println("Released (" + cell.B_D + ", " + cell.B_R + ", " + cell.M_D + ", " + cell.M_R + ")");
		}

		public void mouseEntered(MouseEvent e) {
			if (match.isProcessing() || match.isDecided()) {
				return;
			}
			MyCellPanel cell = (MyCellPanel) e.getSource();
			if (cell.isEligible()) {
				// System.out.println("Entered (" + cell.B_D + ", " + cell.B_R + ", " + cell.M_D + ", " + cell.M_R + ")");

				switch (match.getTurn()) {
				case MiniBoard.X:
					cellLabel[cell.B_D][cell.B_R][cell.M_D][cell.M_R].setIcon(new ImageIcon(Gui.X_TRANSPARENT));
					break;

				case MiniBoard.O:
					cellLabel[cell.B_D][cell.B_R][cell.M_D][cell.M_R].setIcon(new ImageIcon(Gui.O_TRANSPARENT));
					break;
				}

				SwingUtilities.updateComponentTreeUI(frame);
			}
		}

		public void mouseExited(MouseEvent e) {
			if (match.isProcessing() || match.isDecided()) {
				return;
			}
			MyCellPanel cell = (MyCellPanel) e.getSource();
			if (cell.isEligible()) {
				// System.out.println("Exited (" + cell.B_D + ", " + cell.B_R + ", " + cell.M_D + ", " + cell.M_R + ")");
				cellLabel[cell.B_D][cell.B_R][cell.M_D][cell.M_R].setIcon(new ImageIcon(Gui.EMPTY));
				SwingUtilities.updateComponentTreeUI(frame);
				// System.out.println("isEligible");
			}
		}
	}

	class MyCellPanel extends JPanel {
		public final int B_D; //boardDown;
		public final int B_R; //boardRight;
		public final int M_D; //miniDown;
		public final int M_R; //miniRight;

		private boolean eligible;

		public MyCellPanel(int bD, int bR, int mD, int mR) {
			super();
			B_D = bD;
			B_R = bR;
			M_D = mD;
			M_R = mR;
			eligible = false;
		}

		public boolean isEligible() {
			return eligible;
		}

		public void setEligible(boolean b) {
			eligible = b;
		}
	}
}
