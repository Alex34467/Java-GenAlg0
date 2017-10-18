package GUI;

import Entities.CellObject;
import Entities.Creature;
import Entities.Grass;
import javax.swing.*;
import java.awt.*;
import java.util.Collection;


// Панель, отрисовывающая поле.
public class FieldPanel extends JPanel
{
    // Данные.
    private Collection<CellObject> objects;
    private Dimension size;
    private Dimension fieldSize;
    private int cellWidth = 0;
    private int cellHeight = 0;
    private int thickness = 2;


    // Конструктор.
    public FieldPanel()
    {
    }

    // Отрисовка поля.
    public void draw(Collection<CellObject> cellObjects)
    {
        this.objects = cellObjects;
        repaint();
    }

    // Получение размеров поля.
    public void setFieldSize(Dimension fieldSize)
    {
        // Получение данных.
        this.fieldSize = fieldSize;
        size = getSize();

        // Вычисление размера клетки.
        cellHeight = size.height / fieldSize.height;
        cellWidth = size.width / fieldSize.width;
    }

    // Отрисовка.
    @Override
    protected void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);

        // Отрисовка поля.
        if (objects != null) drawField(graphics);

        // Отрисовка сетки.
        drawGrid(graphics);
    }

    // Отрисовка сетки.
    private void drawGrid(Graphics graphics)
    {
        // Данные.
        Graphics2D graphics2D = (Graphics2D)graphics;
        graphics2D.setStroke(new BasicStroke(thickness));

        // Отрисовка.
        int x = 0;
        int y = 0;
        graphics.setColor(Color.BLACK);
        for (int i = 0; i <= fieldSize.height; i++)
        {
            graphics2D.drawLine(0, y, size.width, y);
            y += cellHeight;
        }
        for (int j = 0; j <= fieldSize.width; j++)
        {
            graphics2D.drawLine(x, 0, x, size.height);
            x += cellWidth;
        }
    }

    // Отрисовка поля.
    private void drawField(Graphics graphics)
    {
        // Отрисовка.
        for (CellObject object : objects)
        {
            // Проверка на существо.
            if (object instanceof Creature)
            {
                // Каст.
                Creature creature = (Creature)object;
                int x = creature.getX();
                int y = creature.getY();

                // Отрисовка прямоугольника.
                graphics.setColor(Color.RED);
                graphics.fillRect(cellWidth * x, cellHeight * y, cellWidth, cellHeight);

                // Отрисовка энергии.
                String data = creature.getEnergy() + "";
                int textX = (int)(cellWidth * x + cellWidth / 5);
                int textY = (int)(cellHeight * y + cellHeight / 1.4);
                graphics.setColor(Color.BLACK);
                graphics.setFont(new Font("default", Font.BOLD, 12));
                graphics.drawString(data, textX, textY);
            }

            // Трава.
            if (object instanceof Grass)
            {
                // Отрисовка приямоугольника.
                graphics.setColor(Color.GREEN);
                graphics.fillRect(cellWidth * object.getX(), cellHeight * object.getY(), cellWidth, cellHeight);
            }
        }
    }
}