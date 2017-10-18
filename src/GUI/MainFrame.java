package GUI;

import Simulation.Simulator;
import Utility.Callback;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Основное окно приложения.
public class MainFrame extends JFrame implements ActionListener, Callback
{
    // Данные.
    private Simulator simulator = Simulator.getInstance();
    private JButton startButton;
    private JButton pauseButton;
    private JButton stopButton;
    private FieldPanel fieldPanel;

    // Конструктор.
    public  MainFrame()
    {
        // Вызов конструктора.
        super("Main Window");

        // Создание интерфейа.
        makeGUI();

        // Настройка.
        fieldPanel.setFieldSize(simulator.getFieldSize());
        simulator.setCallback(this);
        fieldPanel.repaint();
    }

    // Обработка событий.
    @Override
    public void actionPerformed(ActionEvent event)
    {
        // Получение действия.
        String action = event.getActionCommand();

        // Выбор.
        if (action.equals("Start"))
        {
            // Включение кнопок.
            startButton.setEnabled(false);
            pauseButton.setEnabled(true);
            stopButton.setEnabled(true);

            // Запуск симуляции.
            simulator.start();
        }
        else if (action.equals("Pause"))
        {
            // Изменение кнопки.
            pauseButton.setText("Resume");
            pauseButton.setActionCommand("Resume");

            // Приостановка.
            simulator.pause();
        }
        else if (action.equals("Resume"))
        {
            // Изменение кнопки.
            pauseButton.setText("Pause");
            pauseButton.setActionCommand("Pause");

            // Возобновление.
            simulator.resum();
        }
        else if (action.equals("Stop"))
        {
            // Включение кнопок.
            //startButton.setEnabled(true);
            pauseButton.setEnabled(false);
            stopButton.setEnabled(false);

            // Изменение кнопки паузы.
            if (pauseButton.getText().equals("Resume"))
            {
                pauseButton.setText("Pause");
                pauseButton.setActionCommand("Pause");
            }

            // Остановка симуляции.
            simulator.interrupt();
        }
    }

    // Обратный вызов.
    @Override
    public void call()
    {
        // Отрисовка.
        fieldPanel.draw(simulator.getCellObjects());
    }

    // Создание интерфейса.
    private void makeGUI()
    {
        // Настройка.
        setBounds(500, 200, 767, 486);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Создание, настройка и добавление панелей.
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout());
        getContentPane().add(buttonsPanel, BorderLayout.NORTH);
        fieldPanel = new FieldPanel();
        getContentPane().add(fieldPanel, BorderLayout.CENTER);

        // Создание и добавление кнопок.
        startButton = new JButton("Start");
        startButton.setActionCommand("Start");
        startButton.addActionListener(this);
        buttonsPanel.add(startButton);
        pauseButton = new JButton("Pause");
        pauseButton.setActionCommand("Pause");
        pauseButton.addActionListener(this);
        pauseButton.setEnabled(false);
        buttonsPanel.add(pauseButton);
        stopButton = new JButton("Stop");
        stopButton.setActionCommand("Stop");
        stopButton.addActionListener(this);
        stopButton.setEnabled(false);
        buttonsPanel.add(stopButton);

        // Показ.
        setVisible(true);
    }
}