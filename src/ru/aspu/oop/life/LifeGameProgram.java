package ru.aspu.oop.life;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// программа для запуска игры
public class LifeGameProgram {

	private BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));

	private MemoryGridStorage mgs = new MemoryGridStorage();

	//директория, куда сохранять или откуда загружать файлы сохранений
	private TextFileLifeGridStorage tgs = new TextFileLifeGridStorage("D:/Study/ВИТ-22/Технологии JAVA/");

	private GridFactory gf = new GridFactory();

	public void run() {
		int selection;
		//инициализируем переменные, отвечающие за высоту/ширину/размер пикселя
		int width = 600;
		int height = 300;
		int cellsize = 3;
//		// создаем новую сетку LoopedGrid размером 200х100 ячеек
//		LoopedGrid grid = new LoopedGrid(200, 100);

		// создаем новую сетку LoopedGrid через GridFactory
		Grid grid = gf.createGrid(width, height);
		// создаем новое окно для отображения сетки, размер ячейки - 5 пикселей
		GridView view = new GridView(grid, cellsize);
//		// Числа в списке описывают, сколько живых соседей требуется для рождения новой клетки
//		int[] ruleB = new int[]{3};
//		// Числа в списке описывают, сколько живых соседей требуется, чтобы живая клетка выжила
//		int[] ruleS = new int[]{2,3};
		LifeGame game = new LifeGame(grid, view); //, ruleB, ruleS
		game.populate();

		//переменные для хранения названия сохранения/загрузки слотов
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
					System.out.println("Введите количество шагов");
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
					System.out.println("Введите название нового сохранения");
					name_save_slot = br.readLine();
					tgs.storeGrid(grid, name_save_slot);
				}

				if (selection == 6) {
					System.out.println("Введите название сохранения, которое хотите загрузить");
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

	// Метод выводит меню и запрашивает у пользователя выбор операции
	public int menu() throws IOException {
		System.out.println("Выберите операцию:");
		System.out.println("1 - один шаг");
		System.out.println("2 - N шагов");
		System.out.println("3 - включить сетку");
		System.out.println("4 - выключить сетку");
		System.out.println("5 - сохранить игру в файл");
		System.out.println("6 - загрузить игру из файла");
		System.out.println("7 - выполнить 1000 шагов");
		System.out.println("8 - выполнить 2000 шагов");
		System.out.println("9 - начать новую игру (генерировать ячейки случайным образом)");


		System.out.println("0 - завершить работу");
		String input = br.readLine();
		int selection = Integer.parseInt(input);
		return selection;
	}
}
