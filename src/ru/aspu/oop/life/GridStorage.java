package ru.aspu.oop.life;

//интерфейс, в котором содержится описание сохранение и загрузка заданного поля с заданным именем
public interface GridStorage {
    void storeGrid(Grid grid, String gridName);
    Grid loadGrid(String gridName);
}
