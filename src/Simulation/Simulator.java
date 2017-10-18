package Simulation;

import Entities.CellObject;
import Entities.Creature;
import Entities.Grass;
import Utility.Callback;
import Utility.Coord;
import java.awt.*;
import java.util.*;


// Симулятор.
public class Simulator extends Thread
{
    // Данные.
    private static Simulator instance;
    private final int FIELD_WIDTH = 40;
    private final int FIELD_HEIGHT = 20;
    private final int PAUSE = 200;
    private Callback callback;
    private boolean running = false;
    private Set<CellObject> cellObjects;


    // Выполнение.
    @Override
    public void run()
    {
        // Запуск.
        running = true;

        // Выполнение.
        while (!isInterrupted())
        {
            if (running)
            {
                // Обработка.
                if (cellObjects == null)
                {
                    // Инициализация.
                    cellObjects = new HashSet<CellObject>();

                    // Генерация существ.
                    generateObjects(10, true);

                    // Генерация травы.
                    generateObjects(50, false);
                }
                else proceedCreatures();

                // Вызов callback.
                if (callback != null) callback.call();
            }

            // Пауза.
            try
            {
                sleep(PAUSE);
            }
            catch (InterruptedException e)
            {
                // Прерывание.
                cellObjects = null;
                if (callback != null) callback.call();
                return;
            }
        }
    }

    // Пауза.
    public void pause()
    {
        running = false;
    }

    // Продолжение.
    public void resum()
    {
        running = true;
    }

    // Геттер.
    public static Simulator getInstance()
    {
        if (instance == null) instance = new Simulator();
        return instance;
    }

    // Врзврат разметов поля.
    public Dimension getFieldSize()
    {
        return new Dimension(FIELD_WIDTH, FIELD_HEIGHT);
    }

    // Возврат объектов.
    public Collection<CellObject> getCellObjects()
    {
        return cellObjects;
    }

    // Получение коллбека.
    public void setCallback(Callback callback)
    {
        this.callback = callback;
    }

    // Перемещение существ.
    public void moveCreature(Creature creature, int x, int y)
    {
        // Проверка на выход за границы..
        if (creature.getX() + x < 0 || creature.getX() + x >= FIELD_WIDTH) return;
        if (creature.getY() + y < 0 || creature.getY() + y >= FIELD_HEIGHT) return;

        // Получение объекта.
        CellObject object = getObjectByCoordinates(creature.getX() + x, creature.getY() + y);

        // Проверка на существо.
        if (object instanceof Creature) return;
        else if (object instanceof Grass)
        {
            // Получение энергии.
            int enetgy = ((Grass)object).getEnergy();

            // Поедание травы.
            creature.increaseEnergy(enetgy);

            // Удаление травы.
            //cellObjects.remove(object);
        }

        // Перемещение.
        creature.move(x, y);
    }

    // Конструктор.
    private Simulator()
    {

    }

    // Обработка существ.
    private void proceedCreatures()
    {
        // Получение итератора.
        Iterator<CellObject> iterator = cellObjects.iterator();

        // Обход объектов.
        while (iterator.hasNext())
        {
            // Получение элемента.
            CellObject object = iterator.next();

            // Проверка.
            if (object instanceof Creature)
            {
                // Каст.
                Creature creature = (Creature) object;

                // Обработка.
                if (creature.getEnergy() < 2) iterator.remove();
                else
                {
                    // Выполнение.
                    GeneExecutor.execute(creature, this);
                    creature.decreaseEbergy(1);
                }
            }
        }
    }

    // Получение свободных координат.
    private Coord getFreeCoord(int fieldWidth, int fieldHeight)
    {
        Coord coord;
        while (true)
        {
            // Генервция координат.
            coord = Coord.getRandom(fieldWidth, fieldHeight);

            // Проверка.
            if (isFree(coord.getX(), coord.getY())) return coord;
        }
    }

    // Проверка координат.
    private boolean isFree(int x, int y)
    {
        // Обход объектов.
        for(CellObject object : cellObjects)
        {
            if (object.getX() == x && object.getY() == y) return false;
        }

        return true;
    }

    // Генерация объектов.
    private void generateObjects(int count, boolean creture)
    {
        for (int i = 0; i < count; i++)
        {
            // Получение координат.
            Coord coord = getFreeCoord(FIELD_WIDTH, FIELD_HEIGHT);

            // Генерация.
            CellObject object = (creture)? new Creature(coord.getX(), coord.getY(), 50) : new Grass(coord.getX(), coord.getY(), 10);

            // Добавление.
            cellObjects.add(object);
        }
    }

    // Получение объекта по координатам.
    private CellObject getObjectByCoordinates(int x, int y)
    {
        for(CellObject object : cellObjects)
        {
            if (object.getX() == x && object.getY() == y) return object;
        }
        return null;
    }
}
