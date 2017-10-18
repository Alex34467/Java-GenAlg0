package Utility;

import java.util.Random;

// Класс координат.
public class Coord
{
    // Данные.
    private int x;
    private int y;


    // Конструктор.
    public Coord(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    // Геттеры.
    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    // Получение случайных координат.
    public static Coord getRandom(int maxX, int maxY)
    {
        // Данные.
        Random random = new Random();

        // Возврат.
        return new Coord(random.nextInt(maxX), random.nextInt(maxY));
    }
}
