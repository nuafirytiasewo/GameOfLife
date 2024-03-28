package ru.aspu.oop.life;

import java.util.List;
import java.util.Random;

// �����, ����������� ������� ���� "�����"
public class LifeGame {

	// �����
	private Grid grid;

	// ����� ��� ��������� �����
	private GridView gridView;

	//������ ��� ��������, ������� ����� ������� ��������� ��� �������� ����� ������
	private int[] ruleB;

	//������ ��� ��������, ������� ����� ������� ���������, ����� ����� ������ ������
	private int[] ruleS;

	// ������� ����� ��������� ���� � �������� ������ � ����� ����������� � ��������� ����
	public LifeGame(Grid grid, GridView gridView) {
		super();
		this.grid = grid;
		this.gridView = gridView;
		// ����� � ������ ���������, ������� ����� ������� ��������� ��� �������� ����� ������
		this.ruleB = new int[]{4,5,6,7,8};
		// ����� � ������ ���������, ������� ����� ������� ���������, ����� ����� ������ ������
		this.ruleS = new int[]{5,6,7,8};
		// ��������� ������� ���������� ������
		//populate();
	}

	// ��������� ���� ��� ����
	public void step() {
		updateGrid();
		gridView.updateView();
	}

	// ��������� �������� ���������� ����� ����
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

	// ��������� ���������� ����� � ������ ������ ����
	private void updateGrid() {
		int width = grid.getWidth();
		int height = grid.getHeight();
		// ��� ����������� ��������� � ����� ��������� �����
		Grid tempGrid = new Grid(width, height);
		// ���������� ������ ������ �����
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				// �������� ������� ������ � ������ LifeCell
				LifeCell currentCell = (LifeCell) grid.getCellAt(i, j);
				// �������� ������ �������� � ������� �����
				List<Cell> neighbours = grid.get8Neighbours(i, j);
				// ������� ���������� ����� �������
				int aliveNeighbours = countAliveCells(neighbours);
				// ������� ����� ������ ��� ����� �������
				LifeCell newCell = new LifeCell(currentCell.isAlive());
				// �������� ��������� ��������� ������ � ������������ �
				// ���������

				//������ ����� ������ �� ��������� "�������"
				newCell.setAlive(false);
				//���� ������� ������ "����"
				if (currentCell.isAlive()) {
					//�� ���������� ���� ���� �� ������� s (������� ����� ������� ���������, ����� "�����" ������ "������")
					for (int s : ruleS) {
						//���� ������ ������� ������ ��������������� ���������� "�����" �������
						if (aliveNeighbours == s) {
							//�� ����� ������ ���������� "�����" (������� ������ "��������")
							newCell.setAlive(true);
						}
					}
				}
				//���� ������� ������ "����"
				else {
					//�� ���������� ���� ���� �� ������� b (������� "�����" ������� ��������� ��� "��������" ����� ������)
					for (int b : ruleB) {
						//���� ������ ������� ������ ��������������� ���������� "�����" �������
						if (aliveNeighbours == b) {
							//�� ����� ������ ���������� "�����" (������� ������ "���������")
							newCell.setAlive(true);
						}
					}
				}

				//������ �������
//				if (currentCell.isAlive()) {
//					if ((aliveNeighbours == 2) || (aliveNeighbours == 3))
//						newCell.setAlive(true);
//				}
//				if ((!currentCell.isAlive()) && (aliveNeighbours == 3))
//					newCell.setAlive(true);

				// �������� ����� ������ � ����� �����
				tempGrid.setCellAt(i, j, newCell);
			}
		// ��������� ��� ������ �� ��������� ����� � �������
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				grid.setCellAt(i, j, tempGrid.getCellAt(i, j));
	}

	// ������������ "�����" ������ � �������� ������ �����
	private int countAliveCells(List<Cell> cells) {
		int aliveNeighbours = 0;
		// ���������� ��� ������ � ������
		for (Cell c : cells)
			// ���������, ��� ������ ���������� � �������� ������� ���� "�����"
			if ((c != null) && (c instanceof LifeCell)) {
				// �������� ������ � ������ LifeCell
				LifeCell lcell = (LifeCell) c;
				if (lcell.isAlive())
					aliveNeighbours++;
			}
		return aliveNeighbours;
	}

	// �������� ����� �������� ��������� �������
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
