package ru.aspu.oop.life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

// ���� ��� ������������ ����������� ����� 
public class GridView {

	// ������ ����� (� �������)
	private int height;
	
	// ������ ����� (� �������)
	private int width;
	
	// ������ ������ (� ��������)
	private int cellSize;
	
	// �����
	private Grid grid;
	
	// ������� ��������� ����� �����
	private boolean showGridLines = false;
	
	// ��������� - ���� ����
	public final static Color BACKGROUND_COLOR = Color.WHITE;
	
	// ��������� - ���� ����� �����
	public final static Color LINE_COLOR = Color.LIGHT_GRAY;
	
	// ���������� ����
	private JFrame frame;
	private GridPanel canvasPanel; 
	private Image canvasImage;

	// ������ � ������ ����� (� ��������)
	private int frameHeight, frameWidth;
	
	// ������� ����� ����, ����������� ����� � ������ ������ ����� � ��������
	public GridView(Grid grid, int cellSize) {
		super();
		this.grid = grid;
		this.height = grid.getHeight();
		this.width = grid.getWidth();
		this.cellSize = cellSize;
		determineCellSize();
		buildFrame();
	}

	private void determineCellSize() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		
	}

	// ���������� ����
	private void buildFrame() {
		frame = new JFrame("World");
		frameHeight = height * cellSize;
		frameWidth = width * cellSize;
		canvasPanel = new GridPanel();
		canvasPanel.setPreferredSize(new Dimension(frameWidth, frameHeight));
		canvasImage = canvasPanel.createImage(frameWidth, frameHeight);
		frame.add(canvasPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}
	
	// ��������� ����� ����� � ������� ������
	public void setGrid(Grid grid, int cellSize) {
		this.grid = grid;
		//this.height = grid.getHeight();
		this.width = grid.getWidth();
		this.height = grid.getHeight();
		this.cellSize = cellSize;
		frameHeight = height * cellSize;
		frameWidth = width * cellSize;
		canvasPanel.setPreferredSize(new Dimension(frameWidth, frameHeight));
		frame.pack();
	}
	
	// ���������� (�����������) ����
	public void updateView() {
		if (canvasImage == null)
			canvasImage = canvasPanel.createImage(frameWidth, frameHeight);
		drawGrid();
		if (showGridLines)
			drawLines();
		canvasPanel.repaint();
	}

	// ��������� �������� ��������� ����� �����
	public void setShowGridLines(boolean showGridLines) {
		this.showGridLines = showGridLines;
	}
	
	// ��������� ����� �����
	private void drawGrid() {
		Graphics2D g = (Graphics2D)canvasImage.getGraphics();
		// ������ ������������� - ���
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, frameWidth, frameHeight);
		// ������ �����
		g.setColor(LINE_COLOR);
		g.drawRect(0, 0, frameWidth - 1, frameHeight - 1);
		// ������ ������
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				if (grid.getCellAt(i, j) == null)
					continue;
				int left = i * cellSize;
				int top = j * cellSize;
				g.setColor(grid.getCellAt(i, j).getColor());
				g.fillRect(left, top, cellSize, cellSize);
			}
	}

	// ��������� ����� �����
	private void drawLines() {
		Graphics2D g = (Graphics2D)canvasImage.getGraphics();
		g.setColor(LINE_COLOR);
		for (int i = 0; i < width * cellSize; i += cellSize)
			g.drawLine(i, 0, i, frameHeight);
		for (int j = 0; j < height * cellSize; j+=cellSize) 
			g.drawLine(0, j, frameWidth, j);
	}

	// ��������� ��������� ��� ���������
	private class GridPanel extends JPanel {

		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(canvasImage, 0, 0, null);
		}
	}


}


