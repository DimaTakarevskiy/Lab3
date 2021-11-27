package MainPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBar extends JFrame {

    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;

    public MenuBar() {
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        Toolkit kit = Toolkit.getDefaultToolkit();

        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);

        JMenuBar menuBar = new JMenuBar();
        getContentPane().add(menuBar, BorderLayout.NORTH);
        JMenu SpravkaMenu = new JMenu("Справка");
        menuBar.add(SpravkaMenu);

        JMenuItem openFileMenuItem = SpravkaMenu.add("О программе");

        JFileChooser fileChooser = new JFileChooser();
        JOptionPane filePane = new JOptionPane();

        openFileMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ev) {
            //    System.out.println("Вы нажали на кнопку \"Открыть\"");
                ImageIcon imageIcon;
                imageIcon = new ImageIcon("E://Java3//Фото.jpg");
                //JOptionPane.showMessageDialog(MainFrame.this,"Такаревский, 6 группа" + imageIcon,"О программе",JOptionPane.PLAIN_MESSAGE);
                JOptionPane.showMessageDialog(MenuBar.this, "Такаревский, 6 группа", "О программе", JOptionPane.INFORMATION_MESSAGE,
                        imageIcon);
            }
        });



    }

}
