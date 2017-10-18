package Entities;


// Существо.
public class Creature extends CellObject
{
    // Данные.
    private int energy;
    private int direction;
    private GeneSequence genes;


    // Конструктор.
    public Creature(int x, int y, int energy)
    {
        // Конструктор.
        super(x, y);

        this.energy = energy;
        direction = 0;
        genes = new GeneSequence();
    }

    // Уменьшение энергии.
    public void decreaseEbergy(int value)
    {
        energy -= value;
    }

    // Увеличение энергии.
    public void increaseEnergy(int value)
    {
        energy += value;
    }

    // Геттер энергии.
    public int getEnergy()
    {
        return energy;
    }

    // Геттер генов.
    public int[] getGenes()
    {
        return genes.getData();
    }
}
