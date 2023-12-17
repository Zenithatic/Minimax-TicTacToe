import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * AI = min
 * Player = max
 * 
 */

public class Main extends JFrame{
	private static final long serialVersionUID = 1L;
	
	// gamestate array (-1 = AI, 1 = player)
	public static int[][] gamestate;
	public static JPanel panel;
	public static int curPlayer;
	public static boolean gameFinished = false;
	public static Main game;
	
	public static final int BSIZE = 3;
	
	public static void main(String[] args) {
		game = new Main();
		game.setVisible(true);
		
		// determine who goes first (0 = AI, 1 = player)
		curPlayer = (int) (Math.random() * 2);
		
		if (curPlayer == 0) {
			makeOptimalMin();
		}
	}
	
	public Main() {
		// initialize JFrame
		this.setSize(new Dimension(400, 400));
		this.setTitle("Minimax TicTacToe");
		this.setResizable(false);
		
		// initialize gameState
		gamestate = new int[3][3];
		
		// add panel with buttons
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setLayout(new GridLayout(3, 3, 5, 5));
		
		MouseListener buttonPress = new ClickAdapter();
		
		JButton b1 = new JButton();
		b1.setName("0,0");
		b1.addMouseListener(buttonPress);
		JButton b2 = new JButton();
		b2.setName("0,1");
		b2.addMouseListener(buttonPress);
		JButton b3 = new JButton();
		b3.setName("0,2");
		b3.addMouseListener(buttonPress);
		JButton b4 = new JButton();
		b4.setName("1,0");
		b4.addMouseListener(buttonPress);
		JButton b5 = new JButton();
		b5.setName("1,1");
		b5.addMouseListener(buttonPress);
		JButton b6 = new JButton();
		b6.setName("1,2");
		b6.addMouseListener(buttonPress);
		JButton b7 = new JButton();
		b7.setName("2,0");
		b7.addMouseListener(buttonPress);
		JButton b8 = new JButton();
		b8.setName("2,1");
		b8.addMouseListener(buttonPress);
		JButton b9 = new JButton();
		b9.setName("2,2");
		b9.addMouseListener(buttonPress);

		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		panel.add(b5);
		panel.add(b6);
		panel.add(b7);
		panel.add(b8);
		panel.add(b9);
		
		Font font = new Font("Arial", Font.BOLD, 20);
		Component[] buttons = panel.getComponents();
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setFont(font);
		}
		
		this.add(panel);
	}
	
	// returns {isTerminal (1 = true, 0 = false), value (1 = player win, -1 = AI win)}
	public static int[] evaluate() {	
		// determine if someone has won via row
		if (gamestate[0][0] == gamestate[0][1] && gamestate[0][1] == gamestate[0][2]) {
			if (gamestate[0][0] == 1) {
				int[] toReturn = {1, 1};
				return toReturn;
			}
			else if (gamestate[0][0] == -1){
				int[] toReturn = {1, -1};
				return toReturn;
			}
		}
		if (gamestate[1][0] == gamestate[1][1] && gamestate[1][1] == gamestate[1][2]) {
			if (gamestate[1][0] == 1) {
				int[] toReturn = {1, 1};
				return toReturn;
			}
			else if (gamestate[1][0] == -1){
				int[] toReturn = {1, -1};
				return toReturn;
			}
		}
		if (gamestate[2][0] == gamestate[2][1] && gamestate[2][1] == gamestate[2][2]) {
			if (gamestate[2][0] == 1) {
				int[] toReturn = {1, 1};
				return toReturn;
			}
			else if (gamestate[2][0] == -1){
				int[] toReturn = {1, -1};
				return toReturn;
			}
		}
		
		// determine if someone has won via column
		if (gamestate[0][0] == gamestate[1][0] && gamestate[1][0] == gamestate[2][0]) {
			if (gamestate[0][0] == 1) {
				int[] toReturn = {1, 1};
				return toReturn;
			}
			else if (gamestate[0][0] == -1){
				int[] toReturn = {1, -1};
				return toReturn;
			}
		}
		if (gamestate[0][1] == gamestate[1][1] && gamestate[1][1] == gamestate[2][1]) {
			if (gamestate[0][1] == 1) {
				int[] toReturn = {1, 1};
				return toReturn;
			}
			else if (gamestate[0][1] == -1){
				int[] toReturn = {1, -1};
				return toReturn;
			}
		}
		if (gamestate[0][2] == gamestate[1][2] && gamestate[1][2] == gamestate[2][2]) {
			if (gamestate[0][2] == 1) {
				int[] toReturn = {1, 1};
				return toReturn;
			}
			else if (gamestate[0][2] == -1){
				int[] toReturn = {1, -1};
				return toReturn;
			}
		}
		
		// determine if someone has won via diagonal
		if (gamestate[0][0] == gamestate[1][1] && gamestate[1][1] == gamestate[2][2]) {
			if (gamestate[0][0] == 1) {
				int[] toReturn = {1, 1};
				return toReturn;
			}
			else if (gamestate[0][0] == -1){
				int[] toReturn = {1, -1};
				return toReturn;
			}
		}
		if (gamestate[0][2] == gamestate[1][1] && gamestate[1][1] == gamestate[2][0]) {
			if (gamestate[0][2] == 1) {
				int[] toReturn = {1, 1};
				return toReturn;
			}
			else if (gamestate[0][2] == -1){
				int[] toReturn = {1, -1};
				return toReturn;
			}
		}
		
		// determine if everything has filled up
		loop: {
			for (int i = 0; i < BSIZE; i++) {
				int j;
				
				for (j = 0; j < BSIZE; j++) {
					if (gamestate[i][j] == 0) {
						break loop;
					}
					
					if (i == 2 && j == 2) {
						int[] toReturn = {1, 0};
						return toReturn;
					}
				}
			}
		}
		
		// if none
		int[] toReturn = {0, 0};
		return toReturn;
	}
	
	// return value of board
	public static int minimax(boolean isMax) {
		int[] evaluation = evaluate();
		
		// if someone has won, return value
		if (evaluation[0] == 1) {
			return evaluation[1];
		}
		
		if (isMax) {
			int maxValue = -100;
			
			// find possible actions
	        for (int i = 0; i < 3; i++) { 
	            for (int j = 0; j < 3; j++) { 
	                // Check if cell is empty 
	                if (gamestate[i][j] == 0) { 
	                    // make test move
	                    gamestate[i][j] = 1; 
	  
	                    // evaluate move recursively using minimax & find max value
	                    maxValue = Math.max(maxValue, minimax(!isMax)); 
	  
	                    // undo test move 
	                    gamestate[i][j] = 0;  
	                } 
	            } 
	        } 
	        
	        return maxValue;
		}
		else {
			int minValue = 100;
			
			for (int i = 0; i < 3; i++) { 
	            for (int j = 0; j < 3; j++) { 
	                // Check if cell is empty 
	                if (gamestate[i][j]== 0) { 
	                    // make test move
	                    gamestate[i][j] = -1; 
	  
	                    // evaluate move recursively using minimax & find min value
	                    minValue = Math.min(minValue, minimax(!isMax)); 
	  
	                    // undo test move 
	                    gamestate[i][j] = 0;  
	                } 
	            } 
	        } 
			
			return minValue;
		}
	}
	
	public static void makeOptimalMin() {
		int row = -1;
		int col = -1;
		int minVal = 100;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				// check if cell has no move
				if (gamestate[i][j] == 0) {
					// make temporary move
					gamestate[i][j] = -1;
					
					// evaluate best move for player
					int evaluation = minimax(true);
					
					// undo move
					gamestate[i][j] = 0;
					
					// check if move is more optimal than current move
					if (evaluation < minVal) {
						minVal = evaluation;
						row = i;
						col = j;
					}
				}
			}
		}
		
		// make the move and update panel
		gamestate[row][col] = -1;
		int index = (row * 3 + col);
		
		JButton button = (JButton) panel.getComponent(index);
		button.setText("AI");
	}
}
