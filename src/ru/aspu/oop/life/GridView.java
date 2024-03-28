package ru.aspu.oop.life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

// ќкно дл€ графического отображени€ сетки 
public class GridView {

	// ширина сетки (в €чейках)
	private int height;
	
	// высота сетки (в €чейках)
	private int width;
	
	// размер €чейки (в пиксел€х)
	private int cellSize;
	
	// сетка
	private Grid grid;
	
	// признак отрисовки линий сетки
	private boolean showGridLines = false;
	
	// константа - цвет фона
	public final static Color BACKGROUND_COLOR = Color.WHITE;
	
	// константа - цвет линий стеки
	public final static Color LINE_COLOR = Color.LIGHT_GRAY;
	
	//  омпоненты окна
	private JFrame frame;
	private GridPanel canvasPanel; 
	private Image canvasImage;

	// ширина и высота формы (в пиксел€х)
	private int frameHeight, frameWidth;
	
	// создает новое окно, указываетс€ сетка и размер €чейки сетки в пиксел€х
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

	// построение окна
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
	
	// установка новой сетки и размера €чейки
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
	
	// обновление (перерисовка) окна
	public void updateView() {
		if (canvasImage == null)
			canvasImage = canvasPanel.createImage(frameWidth, frameHeight);
		drawGrid();
		if (showGridLines)
			drawLines();
		canvasPanel.repaint();
	}

	// установка признака отрисовки линий сетки
	public void setShowGridLines(boolean showGridLines) {
		this.showGridLines = showGridLines;
	}
	
	// отрисовка €чеек сетки
	private void drawGrid() {
		Graphics2D g = (Graphics2D)canvasImage.getGraphics();
		// рисуем пр€моугольник - фон
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, frameWidth, frameHeight);
		// рисуем рамку
		g.setColor(LINE_COLOR);
		g.drawRect(0, 0, frameWidth - 1, frameHeight - 1);
		// рисуем €чейки
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

	// отрисовка линий сетки
	private void drawLines() {
		Graphics2D g = (Graphics2D)canvasImage.getGraphics();
		g.setColor(LINE_COLOR);
		for (int i = 0; i < width * cellSize; i += cellSize)
			g.drawLine(i, 0, i, frameHeight);
		for (int j = 0; j < height * cellSize; j+=cellSize) 
			g.drawLine(0, j, frameWidth, j);
	}

	// служебный компонент дл€ рисовани€
	private class GridPanel extends JPanel {

		@Override
		public void paintComponent(Graphics g) {
			g.drawImage(canvasImage, 0, 0, null);
		}
	}


}


