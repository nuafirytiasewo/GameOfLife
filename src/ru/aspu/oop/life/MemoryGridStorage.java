package ru.aspu.oop.life;

import java.util.HashMap;
import java.util.Map;

//класс, реализующий интерфейс GridStorage и описывающий методы сохранения и загрузки
public class MemoryGridStorage implements GridStorage{
    Map<String, Grid> slots = new HashMap<String, Grid>();

    @Override
    public void storeGrid(Grid grid, String gridName) {
        slots.put(gridName, grid);
    }

    @Override
    public Grid loadGrid(String gridName) {
        return slots.getOrDefault(gridName, null);
    }
}
