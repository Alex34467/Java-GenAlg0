package Entities;

// Класс травы.
public class Grass extends CellObject
{
    // Данные.
    private int energy;

    // Конструктор.
    public Grass(int x, int y, int energy)
    {
        super(x, y);
        this.energy = energy;
    }

    // Геттер энергии.
    public int getEnergy()
    {
        return energy;
    }
}
