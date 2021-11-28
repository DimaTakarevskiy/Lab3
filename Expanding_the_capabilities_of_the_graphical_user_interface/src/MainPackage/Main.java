package MainPackage;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String coef = scanner.nextLine();
        String[] coef2 = coef.split(" ");
        if (coef2.length==0) {
            System.out.println("Невозможно табулировать многочлен, для которого не задано ни одного коэффициента!");
            System.exit(-1);
        }
        Double[] coefficients = new Double[coef2.length];
        int i = 0;
        try {
            for (; i < coef2.length; i++) {
                coefficients[i] = Double.parseDouble(coef2[i]);
            }
        }
        catch (NumberFormatException ex) {
            System.out.println("Ошибка преобразования строки '" + coef2[i] + "' в число типа Double");
            System.exit(-2);
        }

        MainFrame frame = new MainFrame(coefficients);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
