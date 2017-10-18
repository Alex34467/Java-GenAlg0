package Simulation;

import Entities.Creature;


// Исполнитель кода.
public class GeneExecutor
{
    // Данные.
    private static final int MAX_STEPS = 10;
    private static int index;

    // Обработка.
    public static void execute(Creature creature, Simulator simulator)
    {
        // Получение генов.
        int[] genes = creature.getGenes();

        // Выполнение.
        index = 0;
        int steps = 0;
        while (steps < MAX_STEPS)
        {
            // Выполнение.
            boolean value = executeGene(genes[index], creature, simulator);

            // Завершение.
            if (value) return;

            // Переход.
            steps++;
            if (index >= genes.length) index -= genes.length;
        }
    }

    // Выполнение гена.
    private static boolean executeGene(int value, Creature creature, Simulator simulator)
    {
        switch (value)
        {
            // Пропуск гена.
            case 0:
                index++;
                return false;
            // Перемещение.
            case 1:
                simulator.moveCreature(creature, 0, -1);
                index++;
                return true;
            case 2:
                simulator.moveCreature(creature, 1, -1);
                index++;
                return true;
            case 3:
                simulator.moveCreature(creature, 1, 0);
                index++;
                return true;
            case 4:
                simulator.moveCreature(creature, 1, 1);
                index++;
                return true;
            case 5:
                simulator.moveCreature(creature, 0, 1);
                index++;
                return true;
            case 6:
                simulator.moveCreature(creature, -1, 1);
                index++;
                return true;
            case 7:
                simulator.moveCreature(creature, -1, 0);
                index++;
                return true;
            case 8:
                simulator.moveCreature(creature, -1, -1);
                index++;
                return true;
            // Завершение хода.
            case 9:
                index++;
                return true;
            // Переход.
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
                index = value - 10;
                return false;
            // Нестандаротное значение.
            default:
                return false;
        }
    }
}
