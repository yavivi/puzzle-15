package com.puzzle15.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.puzzle15.board.Consts;
import com.puzzle15.board.Tile;
import com.puzzle15.board.Puzzle15Board;

public class GraphicalUI implements ActionListener, UI {

	//private final static Tile EMPTY_TILE = Tile.TILE_16;
	private JFrame window = new JFrame("Puzzle15");

	JButton[][] buttonsGrid = null; 
	Puzzle15Board board;

	public GraphicalUI(Puzzle15Board board) {
		this.board = board;
	}

	public void actionPerformed(ActionEvent a) {
		JButton button = (JButton)a.getSource();
		String buttonName = button.getName();
		String buttonText = button.getText();
		Tile t = board.getTile(buttonName);

		int targetRow = board.getEmptyTile().getRow();
		int targetCol = board.getEmptyTile().getCol();

		boolean legalMove = this.board.moveTile(t);
		if (legalMove) {
			button.setText(board.getEmptyTile().getDisplayValue());
			button.setName("" + board.getEmptyTile().getValue());
			buttonsGrid[targetRow][targetCol].setName(buttonName);
			buttonsGrid[targetRow][targetCol].setText(buttonText);
		}

		if (board.isGameOver()){
			JOptionPane.showMessageDialog(null, "Well Done, all tiles are in place");
		}
	}

	@Override
	public void start() {
		buttonsGrid = new JButton[board.getDim()][board.getDim()];

		window.setSize(300, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(new GridLayout(board.getDim(), board.getDim()));

		for (int i = 0; i < board.getDim(); i++) {
			for (int j = 0; j < board.getDim(); j++) {
				JButton b = new JButton("");
				b.setName(""+board.getTile(i,j).getValue());
				b.addActionListener(this);
				b.setText(""+board.getTile(i,j).getDisplayValue());
				window.add(b);
				buttonsGrid[i][j] = b;
			}
		}

		window.setVisible(true);
	}

}