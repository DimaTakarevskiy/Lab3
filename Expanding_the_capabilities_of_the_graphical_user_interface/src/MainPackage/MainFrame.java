package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class MainFrame extends JFrame implements ActionListener {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    private DecimalFormat formatterNumber = (DecimalFormat) NumberFormat.getInstance();

    JMenu SpravkaMenu = new JMenu("Справка");
    JMenu File = new JMenu("Файл");
    JMenu saveItemAs = new JMenu("Сохранить как");

    JMenuItem loadItem = new JMenuItem("Открыть");
    JMenuItem saveItem = new JMenuItem("Сохранить");
    JMenuItem findItem = new JMenuItem("Найти близкие к простым");
    JMenuItem exitItem = new JMenuItem("Закрыть");
    JMenuItem infoItem = SpravkaMenu.add("О программе");
    JMenuItem saveTextItem = new JMenuItem("Сохранить данные в текстовый файл");
    JMenuItem saveGraphicItem = new JMenuItem("Сохранить данные для построения графика");

    JButton calculate = new JButton("Вычислить");
    JButton clear = new JButton("Очистить поля");

    private GornerTableCellRenderer renderer = new GornerTableCellRenderer();

    private GornerTableModel _data;

    private Box boxResult;

    private JTextField textFieldFrom;
    private JTextField textFieldTo;
    private JTextField textFieldStep;

    private Double[] _coefficients;


    public MainFrame(Double[] coefficients) {


        setTitle("Табулирование многочлена на отрезке по схеме Горнера");
        setSize(WIDTH, HEIGHT);
        setResizable(true);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);

        _coefficients = coefficients;

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(File);
        menuBar.add(SpravkaMenu);
        File.add(loadItem);
        File.add(saveItem);
        File.add(saveItemAs);
        File.add(findItem);
        File.add(exitItem);
        saveItemAs.add(saveTextItem);
        saveItemAs.add(saveGraphicItem);

        JOptionPane filePane = new JOptionPane();

        loadItem.addActionListener(this);
        saveItem.addActionListener(this);
        findItem.addActionListener(this);
        exitItem.addActionListener(this);
        saveTextItem.addActionListener(this);
        saveGraphicItem.addActionListener(this);

        infoItem.addActionListener(this);

        JLabel From = new JLabel("x изменяется в интервале от: ");
        textFieldFrom = new JTextField("0.0", 10);
        textFieldFrom.setMaximumSize(textFieldFrom.getPreferredSize());

        JLabel To = new JLabel("до: ");
        textFieldTo = new JTextField("1.0", 10);
        textFieldTo.setMaximumSize(textFieldTo.getPreferredSize());

        JLabel Step = new JLabel("с шагом: ");
        textFieldStep = new JTextField("0.1", 10);
        textFieldStep.setMaximumSize(textFieldStep.getPreferredSize());

        Box Values = Box.createHorizontalBox();


        Values.add(Box.createHorizontalGlue());
        Values.add(From);
        Values.add(Box.createHorizontalStrut(10));
        Values.add(textFieldFrom);
        Values.add(Box.createHorizontalStrut(30));
        Values.add(To);
        Values.add(Box.createHorizontalStrut(10));
        Values.add(textFieldTo);
        Values.add(Box.createHorizontalStrut(30));
        Values.add(Step);
        Values.add(Box.createHorizontalStrut(10));
        Values.add(textFieldStep);
        Values.add(Box.createHorizontalGlue());
        Values.setBorder(BorderFactory.createBevelBorder(1));
        Values.setPreferredSize(new Dimension(WIDTH, 80));
        //Values.setMaximumSize(Values.getPreferredSize());

        add(Values, BorderLayout.NORTH);
        calculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Visible(true);

                    Double from = Double.parseDouble(textFieldFrom.getText());
                    Double to = Double.parseDouble(textFieldTo.getText());
                    Double step = Double.parseDouble(textFieldStep.getText());

                    _data = new GornerTableModel(_coefficients, from, to, step);

                    JTable table = new JTable(_data);
                    table.setDefaultRenderer(Double.class, renderer);
                    table.setRowHeight(30);

                    boxResult.removeAll();

                    boxResult.add(new JScrollPane(table));

                    validate(_data, _coefficients);
                    getContentPane().validate();
                } catch (NumberFormatException ex) {
                    // В случае ошибки преобразования чисел показать сообщение об ошибке
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Visible(false);

                textFieldFrom.setText("0.0");
                textFieldTo.setText("1.0");
                textFieldStep.setText("0.1");

                boxResult.removeAll();

                boxResult.add(new JPanel());

                validate(null, _coefficients);
                getContentPane().validate();
            }
        });

        boxResult = Box.createHorizontalBox();
        boxResult.add(new JPanel());
        add(boxResult, BorderLayout.CENTER);

        Box jButtonBox = Box.createHorizontalBox();
        jButtonBox.add(Box.createHorizontalGlue());
        jButtonBox.add(calculate);
        jButtonBox.add(Box.createHorizontalStrut(100));
        jButtonBox.add(clear);
        jButtonBox.add(Box.createHorizontalGlue());

        jButtonBox.setBorder(BorderFactory.createBevelBorder(1));
        jButtonBox.setPreferredSize(new Dimension(WIDTH, 80));

        add(jButtonBox, BorderLayout.SOUTH);


        JTable DataTable = new JTable();
        JScrollPane DataTableScrollPane = new JScrollPane(DataTable);
        DataTableScrollPane.setPreferredSize(new Dimension(400, 400));


    }


    public void Visible(boolean arg) {
        saveItem.setEnabled(arg);
        saveItemAs.setEnabled(arg);
        findItem.setEnabled(arg);
    }

    public void validate(GornerTableModel tableModel, Double[] cofficient) {
        _data = tableModel;
        _coefficients = cofficient;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == loadItem) {
                JFileChooser fileChooser = new JFileChooser();

                int response = fileChooser.showOpenDialog(MainFrame.this);

                if (response == JFileChooser.APPROVE_OPTION) {
                    try {
                        FileReader reader = new FileReader(fileChooser.getSelectedFile().getAbsolutePath());
                        int data = reader.read();

                        while (data != -1) {
                            System.out.print((char) data);
                            data = reader.read();
                        }

                        reader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            if (e.getSource() == saveItem) {
                JFileChooser fileChooser = new JFileChooser();
                java.io.File file = new File("outTXT.txt");

                fileChooser.setCurrentDirectory(file);
                fileChooser.setSelectedFile(file);

                saveToTextFile(fileChooser.getSelectedFile());
            }
            if (e.getSource() == saveTextItem) {
                JFileChooser fileChooser = new JFileChooser();

                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                    saveToTextFile(fileChooser.getSelectedFile());
            }
            if (e.getSource() == saveGraphicItem) {
                JFileChooser fileChooser = new JFileChooser();

                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                    saveToGraphicsFile(fileChooser.getSelectedFile());
            }
            if (e.getSource() == findItem) {
                renderer.SetColor(true);
                this.repaint();
            }
            if (e.getSource() == exitItem) {
                System.exit(0);
            }
            if (e.getSource() == infoItem) {
                ImageIcon imageIcon = new ImageIcon("E://Java3//Фото.jpg");
                JOptionPane.showMessageDialog(MainFrame.this, "Такаревский, 6 группа", "О программе", JOptionPane.INFORMATION_MESSAGE,
                        imageIcon);
            }
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void saveToTextFile(File file) {

        DecimalFormatSymbols dottedDouble =
                formatterNumber.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');

        formatterNumber.setDecimalFormatSymbols(dottedDouble);
        try {
            FileWriter writer = new FileWriter(file.getAbsolutePath());

            writer.write("Результаты табулирования многочлена по схеме \"Горнера\"\n");
            writer.write("Многочлен: ");
            for (int i = 0; i < _coefficients.length; i++) {
                writer.write(_coefficients[i] + "*X^" +
                        (_coefficients.length - i - 1));
                if (i != _coefficients.length - 1)
                    writer.write(" + ");
            }
            writer.write("\n");
            writer.write("Интервал от " + _data.getFrom() + " до " +
                    _data.getTo() + " с шагом " + _data.getStep() + "\n");
            writer.write("====================================================\n");

            for (int i = 0; i < _data.getRowCount(); i++) {
                String number = formatterNumber.format(_data.getValueAt(i, 0));
                String number2 = formatterNumber.format(_data.getValueAt(i, 1));
                writer.write("Значение в точке " + number
                        + " равно " + number2 + "\n");
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveToGraphicsFile(File file){
        try {
            FileWriter writer =  new FileWriter(file.getAbsolutePath());

            for (int i = 0; i < _data.getRowCount(); i++) {
                writer.write(_data.getValueAt(i, 0).toString() + " ");
                writer.write(_data.getValueAt(i, 1).toString() + "\n");
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

