package Entities;

import java.util.Random;


// Гены.
public class GeneSequence
{
    // Данные.
    private final int COUNT = 10;
    private int[] data;


    // Конструктор.
    public GeneSequence()
    {
        // Генерация генов.
        data = generate(COUNT);
    }

    // Создание случайной последовательности генов.
    private int[] generate(int count)
    {
        // Инициализация.
        int[] temp = new int[count];

        // Заполнение.
        Random random = new Random();
        for (int i = 0; i < count; i++)
        {
            temp[i] = random.nextInt(21);
        }

        // Возврат.
        return temp;
    }

    // Геттер данных.
    public int[] getData()
    {
        return data;
    }
}
