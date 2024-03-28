package ru.aspu.oop.life;

import java.awt.Color;

// класс, реализующий интерфейс ячейка,
// описыва€ €чейку дл€ игры "∆изнь"
// ¬ данной игре €чейка может иметь два состо€ни€:
// жива€ клетка и мертва€ клетка
public class LifeCell implements Cell {

	// признак, €вл€етс€ ли €чейка живой
	private boolean alive;
	
	// создает новую €чейку, указыва€ ее состо€ние
	public LifeCell(boolean alive) {
		super();
		this.alive = alive;
	}

	// возвращает состо€ние €чейки
	public boolean isAlive() {
		return alive;
	}

	// устанавливает состо€ние €чейки
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	// возвращает цвет €чейки (реализаци€ метода интерфейса Cell)
	// цвет €чейки зависит от ее состо€ни€
	public Color getColor() {
		if (isAlive())
			return Color.RED;
		else
			return Color.WHITE;
	}

}
