package ru.aspu.oop.life;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//класс, реализующий интерфейс GridStorage и описывающий методы сохранения и загрузки в текстовый файл
public class TextFileLifeGridStorage implements GridStorage{
    private final String directory;
    private GridFactory gf = new GridFactory(); //ссылка на GridFactory, потому что нужно менять все вызовы вида new Grid() и new LoopedGrid()
    public TextFileLifeGridStorage(String directory){
        this.directory = directory;
    }
    @Override
    public void storeGrid(Grid grid, String gridName) {
        try {
            //создание потока записи в файл  + ".txt"
            PrintWriter pw = new PrintWriter(directory + gridName);
            //запись состояния игры в файл
            int width = grid.getWidth();
            int height = grid.getHeight();
            pw.println(width);
            pw.println(height);
            // перебираем каждую ячейку сетки
            for (int i = 0; i < height; i++){
                for (int j = 0; j < width; j++){
                    // приводим текущую ячейку к классу LifeCell
                    // почему-то стороны были инверсированы, и i и j в grid.getCellAt(i, j) пришлось поменять местами,
                    // а также высоту и ширину в циклах
                    LifeCell currentCell = (LifeCell) grid.getCellAt(j, i);
                    //если текущая ячейка жива, то выводим символ @
                    if (currentCell.isAlive()) {
                        pw.print("@");
                    }
                    //если текущая ячейка мертва, то выводим символ .
                    else {
                        pw.print(".");
                    }
                }
                //переходим на следующую строку
                pw.println();
            }
            //закрытие потока
            pw.close();
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл!");
        }

    }
    @Override
    public Grid loadGrid(String gridName) {
        try {
            //создание потока чтения из файла
            BufferedReader br = new BufferedReader(new FileReader(directory + gridName));
            //чтение состояния игры из файла
            int width = Integer.parseInt(br.readLine());
            int height = Integer.parseInt(br.readLine());

//            // все модификации выполняем в новой временной сетке
//            Grid tempGrid = new Grid(width, height);

            // создаем новую сетку LoopedGrid через GridFactory
            Grid tempGrid = gf.createGrid(width, height);

            // перебираем каждую ячейку сетки
            for (int i = 0; i < height; i++) {
                //считываем каждую строку высоты
                String currentCell = br.readLine();
                //если строка не нулевая, то переводим строку в массив символов
                if (currentCell != null){
                    char[] strToArray = currentCell.toCharArray();
                    //перебираем каждый символ в цикле по ширине, сравниваем с @ или .
                    for (int j = 0; j < width; j++) {
                        char condition = '@';
                        //если в строке символ @, то в сетку помещаем живую клетку, и наоборот
                        if (strToArray[j] == condition) {
                            tempGrid.setCellAt(j, i, new LifeCell(true));
                        }
                        else {
                            tempGrid.setCellAt(j, i, new LifeCell(false));
                        }
                    }
                }
            }
            //закрытие потока
            br.close();
            return tempGrid;
        } catch (IOException e) {
            System.out.println("Ошибка чтения из файла!");
        }
        return null;
    }
}
