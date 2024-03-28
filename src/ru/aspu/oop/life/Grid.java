package ru.aspu.oop.life;

import java.util.ArrayList;
import java.util.List;

//  ласс, описывающий сетку, состо€щую из €чеек
public class Grid {

	// константы, описывающие направлени€ поиска соседних €чеек
	public static final int WEST = 1;
	public static final int EAST = 2;
	public static final int NORTH = 3;
	public static final int SOUTH = 4;
	public static final int NORTHWEST = 5;
	public static final int NORTHEAST = 6;
	public static final int SOUTHWEST = 7;
	public static final int SOUTHEAST = 8;
	
	// двумерный массив €чеек private
	public Cell[][] cells;
	
	// ширина сетки (в €чейках) private
	public int width;

	// высота сетки (в€чейках) private
	public int height;
	
	// —оздает сетку с заданным количеством €ччек по ширине и высоте
	public Grid(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		cells = new Cell[width][height];
	}

	// возвращает ширину сетки
	public int getWidth() {
		return width;
	}

	// возвращает высоту сетки
	public int getHeight() {
		return height;
	}
	
	// возвращает €чейку в заданном столбце и строке
	// если заданы координаты, выход€щие за границы сетки, возвращает null
	public Cell getCellAt(int x, int y) {
		if ((x >= width) || (x < 0))
			return null;
		if ((y >= height) || (y < 0))
			return null;
		return cells[x][y];
	}
	
	// устанавливает €чейку в заданном столбце и строке
	public void setCellAt(int x, int y, Cell cell) {
		cells[x][y] = cell;
	}
	
	// очищает €чейку в заданном столбце и строке
	public void clearCellAt(int x, int y) {
		cells[x][y] = null;
	}
	
	// возвращает соседнюю €чейку дл€ €чейки с заданными координатами x и y
	// в заданном направлении 
	// (т.е. дл€ параметров x=4, y=6, direction = SOUTH 
	// соседней €чейкой будет €чейка с координатами x=4, y=7)
	public Cell getNeigbour(int x, int y, int direction) {
		int xres = findNeighbourX(x, direction);
		int yres = findNeighbourY(y, direction);
		return getCellAt(xres, yres);
	}

	// определ€ет координату x дл€ соседней €чейки по указанному направлению
	private int findNeighbourX(int x, int direction) {
		int xres = x;
		if ((direction == NORTHWEST) || (direction == NORTH) || (direction == NORTHEAST))
			xres = x - 1;
		if ((direction == SOUTHWEST) || (direction == SOUTH) || (direction == SOUTHEAST))
			xres = x + 1;
		return xres;
	}

	// определ€ет координату y дл€ соседней €чейки по указанному направлению
	private int findNeighbourY(int y, int direction) {
		int yres = y;
		if ((direction == NORTHWEST) || (direction == WEST) || (direction == SOUTHWEST))
			yres = y - 1;
		if ((direction == NORTHEAST) || (direction == EAST) || (direction == SOUTHEAST))
			yres = y + 1;
		return yres;
	}

	// возвращает список восьми соседних €чеек дл€ €чейки с заданными координатами
	// гарантируетс€, что результирующий список не будет содержать null,
	// т.е. метод вернет только непустые (существующие) €чейки
	public List<Cell> get8Neighbours(int x, int y) {
		List<Cell> result = new ArrayList<Cell>();
		Cell [] res = new Cell[8];
		res[0] = getNeigbour(x, y, NORTHWEST);
		res[1] = getNeigbour(x, y, NORTH);
		res[2] = getNeigbour(x, y, NORTHEAST);
		res[3] = getNeigbour(x, y, EAST);
		res[4] = getNeigbour(x, y, SOUTHEAST);
		res[5] = getNeigbour(x, y, SOUTH);
		res[6] = getNeigbour(x, y, SOUTHWEST);
		res[7] = getNeigbour(x, y, WEST);
		for (Cell c: res)
			if (c != null)
				result.add(c);
		return result;
	}

	// возвращает список четырех соседних €чеек дл€ €чейки с заданными координатами
	// (соседи по диагонали не учитываютс€)
	// гарантируетс€, что результирующий список не будет содержать null,
	// т.е. метод вернет только непустые (существующие) €чейки
	public List<Cell> get4Neighbours(int x, int y) {
		List<Cell> result = new ArrayList<Cell>();
		Cell [] res = new Cell[4];
		res[0] = getNeigbour(x, y, NORTH);
		res[1] = getNeigbour(x, y, EAST);
		res[2] = getNeigbour(x, y, SOUTH);
		res[3] = getNeigbour(x, y, WEST);
		for (Cell c: res)
			if (c != null)
				result.add(c);
		return result;
	}

}
