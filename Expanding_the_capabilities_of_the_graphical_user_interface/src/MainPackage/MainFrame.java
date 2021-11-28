package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    JMenu SpravkaMenu = new JMenu("Справка");
    JMenu File = new JMenu("Файл");
    JMenu SaveAs = new JMenu("Сохранить как");

    JMenuItem openFileMenuItem = SpravkaMenu.add("О программе");

    JButton calculate = new JButton("Вычислить");
    JButton clear = new JButton("Очистить поля");

    private JTextField textFieldFrom;
    private JTextField textFieldTo;
    private JTextField textFieldStep;

    private Double[] _coefficients;

    private GornerTableModel data;


    public MainFrame(Double[] coefficients) {

        setSize(WIDTH, HEIGHT);
        setResizable(false);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH)/ 2, (kit.getScreenSize().height - HEIGHT) / 2);

        _coefficients = coefficients;

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(File);
        menuBar.add(SpravkaMenu);
        File.add(SaveAs);

        JFileChooser fileChooser = new JFileChooser();
        JOptionPane filePane = new JOptionPane();

        openFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
            //    System.out.println("Вы нажали на кнопку \"Открыть\"");
                ImageIcon imageIcon;
                imageIcon = new ImageIcon("E://Java3//Фото.jpg");
                //JOptionPane.showMessageDialog(MainFrame.this,"Такаревский, 6 группа" + imageIcon,"О программе",JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(MainFrame.this, "Такаревский, 6 группа", "О программе", JOptionPane.INFORMATION_MESSAGE,
                        imageIcon);
            }
        });

      //  TableCharacteristic table = new TableCharacteristic();

        JLabel From = new JLabel("x изменяется в интервале от: ");
        textFieldFrom = new JTextField("0.0", 10);
        textFieldFrom.setMaximumSize(textFieldFrom.getPreferredSize());

        JLabel To = new JLabel("до: ");
        textFieldTo = new JTextField("0.0", 10);
        textFieldTo.setMaximumSize(textFieldTo.getPreferredSize());

        JLabel Step = new JLabel("с шагом: ");
        textFieldStep = new JTextField("0.0", 10);
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

            }
        });

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

}
