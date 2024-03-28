package ru.aspu.oop.life;

//класс-фабрика, определяющая размеры сетки и возвращающая новый LoopedGrid
public class GridFactory {
    public static Grid createGrid(int width, int height){

        // создаем новую сетку LoopedGrid размером 200х100 ячеек
        return new LoopedGrid(width, height);
    }
}
