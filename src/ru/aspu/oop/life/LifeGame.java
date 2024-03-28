package ru.aspu.oop.life;

import java.util.List;
import java.util.Random;

// класс, реализующий правила игры "Жизнь"
public class LifeGame {

	// сетка
	private Grid grid;

	// форма для отрисовки сетки
	private GridView gridView;

	//список для описания, сколько живых соседей требуется для рождения новой клетки
	private int[] ruleB;

	//список для описания, сколько живых соседей требуется, чтобы живая клетка выжила
	private int[] ruleS;

	// создает новый экземпляр игры с заданной сеткой и окном отображения и правилами игры
	public LifeGame(Grid grid, GridView gridView) {
		super();
		this.grid = grid;
		this.gridView = gridView;
		// Числа в списке описывают, сколько живых соседей требуется для рождения новой клетки
		this.ruleB = new int[]{4,5,6,7,8};
		// Числа в списке описывают, сколько живых соседей требуется, чтобы живая клетка выжила
		this.ruleS = new int[]{5,6,7,8};
		// случайным образом генерирует ячейки
		//populate();
	}

	// выполняет один шаг игры
	public void step() {
		updateGrid();
		gridView.updateView();
	}

	// выполняет заданное количество шагов игры
	public void multipleSteps(int n) {
		try {
			for (int i = 0; i < n; i++) {
				step();
				Thread.sleep(20);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// выполняет обновление сетки в рамках одного шага
	private void updateGrid() {
		int width = grid.getWidth();
		int height = grid.getHeight();
		// все модификации выполняем в новой временной сетке
		Grid tempGrid = new Grid(width, height);
		// перебираем каждую ячейку сетки
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				// приводим текущую ячейку к классу LifeCell
				LifeCell currentCell = (LifeCell) grid.getCellAt(i, j);
				// получаем список соседних с текущей ячеек
				List<Cell> neighbours = grid.get8Neighbours(i, j);
				// считаем количество живых соседей
				int aliveNeighbours = countAliveCells(neighbours);
				// создаем новую ячейку как копию текущей
				LifeCell newCell = new LifeCell(currentCell.isAlive());
				// изменяем состояние созданной ячейки в соответствии с
				// правилами

				//делаем новую клетку по умолчанию "мертвой"
				newCell.setAlive(false);
				//если текущая клетка "жива"
				if (currentCell.isAlive()) {
					//то перебираем цикл цифр по правилу s (сколько живых соседей требуется, чтобы "живая" клетка "выжила")
					for (int s : ruleS) {
						//если вокруг текущей клетки соответствующее количество "живых" соседей
						if (aliveNeighbours == s) {
							//то новая клетка становится "живой" (текущая клетка "выживает")
							newCell.setAlive(true);
						}
					}
				}
				//если текущая клетка "жива"
				else {
					//то перебираем цикл цифр по правилу b (сколько "живых" соседей требуется для "рождения" новой клетки)
					for (int b : ruleB) {
						//если вокруг текущей клетки соответствующее количество "живых" соседей
						if (aliveNeighbours == b) {
							//то новая клетка становится "живой" (текущая клетка "рождается")
							newCell.setAlive(true);
						}
					}
				}

				//старые правила
//				if (currentCell.isAlive()) {
//					if ((aliveNeighbours == 2) || (aliveNeighbours == 3))
//						newCell.setAlive(true);
//				}
//				if ((!currentCell.isAlive()) && (aliveNeighbours == 3))
//					newCell.setAlive(true);

				// помещаем новую ячейку в новую сетку
				tempGrid.setCellAt(i, j, newCell);
			}
		// переносим все ячейки из временной сетки в рабочую
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				grid.setCellAt(i, j, tempGrid.getCellAt(i, j));
	}

	// подсчитывает "живые" ячейки в заданном списке ячеек
	private int countAliveCells(List<Cell> cells) {
		int aliveNeighbours = 0;
		// перебираем все ячейки в списке
		for (Cell c : cells)
			// проверяем, что ячейка существует и является ячейкой игры "Жизнь"
			if ((c != null) && (c instanceof LifeCell)) {
				// приводим ячейку к классу LifeCell
				LifeCell lcell = (LifeCell) c;
				if (lcell.isAlive())
					aliveNeighbours++;
			}
		return aliveNeighbours;
	}

	// заселяет сетку ячейками случайным образом
	public void populate() {
		int width = grid.getWidth();
		int height = grid.getHeight();
		Random rnd = new Random();
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				grid.setCellAt(i, j, new LifeCell(rnd.nextBoolean()));
			}

	}

}
