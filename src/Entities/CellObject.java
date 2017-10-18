package Entities;

// Объект на поле.
public abstract class CellObject
{
    // Данные.
    protected int x;
    protected int y;

    // Конструктор.
    public CellObject(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    // Перемещение.
    public void move(int dx, int dy)
    {
        x += dx;
        y += dy;
    }

    // Геттер Х.
    public int getX()
    {
        return x;
    }

    // Геттер Y.
    public int getY()
    {
        return y;
    }
}
