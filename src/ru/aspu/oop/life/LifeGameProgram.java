package ru.aspu.oop.life;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// ��������� ��� ������� ����
public class LifeGameProgram {

	private BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));

	private MemoryGridStorage mgs = new MemoryGridStorage();

	//����������, ���� ��������� ��� ������ ��������� ����� ����������
	private TextFileLifeGridStorage tgs = new TextFileLifeGridStorage("D:/Study/���-22/���������� JAVA/");

	private GridFactory gf = new GridFactory();

	public void run() {
		int selection;
		//�������������� ����������, ���������� �� ������/������/������ �������
		int width = 600;
		int height = 300;
		int cellsize = 3;
//		// ������� ����� ����� LoopedGrid �������� 200�100 �����
//		LoopedGrid grid = new LoopedGrid(200, 100);

		// ������� ����� ����� LoopedGrid ����� GridFactory
		Grid grid = gf.createGrid(width, height);
		// ������� ����� ���� ��� ����������� �����, ������ ������ - 5 ��������
		GridView view = new GridView(grid, cellsize);
//		// ����� � ������ ���������, ������� ����� ������� ��������� ��� �������� ����� ������
//		int[] ruleB = new int[]{3};
//		// ����� � ������ ���������, ������� ����� ������� ���������, ����� ����� ������ ������
//		int[] ruleS = new int[]{2,3};
		LifeGame game = new LifeGame(grid, view); //, ruleB, ruleS
		game.populate();

		//���������� ��� �������� �������� ����������/�������� ������
		String name_save_slot;
		String name_load_slot;

		try {
			do {
				view.updateView();
				selection = menu();
				if (selection == 1) {
					game.step();
				}
				if (selection == 2) {
					System.out.println("������� ���������� �����");
					int n = Integer.parseInt(br.readLine());
					game.multipleSteps(n);
				}
				if (selection == 3) {
					view.setShowGridLines(true);
				}
				if (selection == 4) {
					view.setShowGridLines(false);
				}

				if (selection == 5) {
					System.out.println("������� �������� ������ ����������");
					name_save_slot = br.readLine();
					tgs.storeGrid(grid, name_save_slot);
				}

				if (selection == 6) {
					System.out.println("������� �������� ����������, ������� ������ ���������");
					name_load_slot = br.readLine();
					grid = tgs.loadGrid(name_load_slot);
					view.setGrid(grid, cellsize);
					game = new LifeGame(grid, view); //, ruleB, ruleS
				}

				if (selection == 7) {
					game.multipleSteps(1000);
				}

				if (selection == 8) {
					game.multipleSteps(2000);
				}

				if (selection == 9) {
					game = new LifeGame(grid, view); //, ruleB, ruleS
					game.populate();
				}
			} while (selection != 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	// ����� ������� ���� � ����������� � ������������ ����� ��������
	public int menu() throws IOException {
		System.out.println("�������� ��������:");
		System.out.println("1 - ���� ���");
		System.out.println("2 - N �����");
		System.out.println("3 - �������� �����");
		System.out.println("4 - ��������� �����");
		System.out.println("5 - ��������� ���� � ����");
		System.out.println("6 - ��������� ���� �� �����");
		System.out.println("7 - ��������� 1000 �����");
		System.out.println("8 - ��������� 2000 �����");
		System.out.println("9 - ������ ����� ���� (������������ ������ ��������� �������)");


		System.out.println("0 - ��������� ������");
		String input = br.readLine();
		int selection = Integer.parseInt(input);
		return selection;
	}
}
