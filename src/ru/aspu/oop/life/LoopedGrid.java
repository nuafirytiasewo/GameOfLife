package ru.aspu.oop.life;

//класс, наследующий от класса Grid и реализующий «замкнутость» границ поля
class LoopedGrid extends Grid {
    public LoopedGrid(int width, int height) {
        super(width, height);
    }

    // возвращает ячейку в заданном столбце и строке
    // если заданы координаты, выходящие за границы сетки, возвращает остаток от вычитания
    @Override
    public Cell getCellAt(int x, int y) {
        //проверка на то, что x выходит за границы
        if ((x >= width) || (x < 0)){
            //подставляем формулу для того, чтобы значения x принимали значения, как в примере
            int new_width = Math.abs(Math.abs(x) - width);

            //проверка на то, что y выходит за границы
            if ((y >= height) || (y < 0)){
                //подставляем формулу для того, чтобы значения y принимали значения, как в примере
                int new_height = Math.abs(Math.abs(y) - height);
                //возвращаем новую ячейку с новыми координатами x и y
                return cells[new_width][new_height];
            }

            //возвращаем новую ячейку с новой координатой x
            return cells[new_width][y];
        }

        //проверка на то, что y выходит за границы
        if ((y >= height) || (y < 0)){
            //подставляем формулу для того, чтобы значения y принимали значения, как в примере
            int new_height_2 = Math.abs(Math.abs(y) - height);

            //возвращаем новую ячейку с новой координатой y
            return cells[x][new_height_2];
        }

        //возвращаем ячейку с новой координатами x и y
        return cells[x][y];
    }
}
