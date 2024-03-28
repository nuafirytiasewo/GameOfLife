package ru.aspu.oop.life;

import java.awt.Color;

// �����, ����������� ��������� ������,
// �������� ������ ��� ���� "�����"
// � ������ ���� ������ ����� ����� ��� ���������:
// ����� ������ � ������� ������
public class LifeCell implements Cell {

	// �������, �������� �� ������ �����
	private boolean alive;
	
	// ������� ����� ������, �������� �� ���������
	public LifeCell(boolean alive) {
		super();
		this.alive = alive;
	}

	// ���������� ��������� ������
	public boolean isAlive() {
		return alive;
	}

	// ������������� ��������� ������
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	// ���������� ���� ������ (���������� ������ ���������� Cell)
	// ���� ������ ������� �� �� ���������
	public Color getColor() {
		if (isAlive())
			return Color.RED;
		else
			return Color.WHITE;
	}

}
