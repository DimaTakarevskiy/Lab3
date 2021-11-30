package MainPackage;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

public class GornerTableCellRenderer implements TableCellRenderer {

    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();

    private boolean _PrimeNumber = false;

    private DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();



    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        String formattedString = formatter.format(value);
        Double formattedDouble = Double.parseDouble(formattedString);

        if (formattedDouble > 0.0) {
            panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
            label.setText(formattedString);
        }
        else if (formattedDouble < 0.0){
            panel.setLayout(new FlowLayout(FlowLayout.LEFT));
            label.setText(formattedString);
        }
        else {
            panel.setLayout(new FlowLayout(FlowLayout.CENTER));
            label.setText(formattedString);
        }

        if(_PrimeNumber) {
            double todouble = Double.parseDouble(formattedString);
            int step = (int)(todouble);
            System.out.print(step + " ");
            todouble = todouble * 10;
            int toint = (int)(todouble);
            int toint2 = (int)(todouble * 1000);
            System.out.print(todouble + " ");
            int temp;
            boolean isPrime = true;

            if (step == 0 || (step == 1 &&  toint - step * 10 <= 1))
                isPrime = false;
            else
            for (int i = 2; i <= step / 2; i++) {
                temp = step % i;
                if (temp == 0) {
                    isPrime = false;
                    break;
                }
            }

            System.out.print(isPrime + " ");

            if (isPrime && (toint - step * 10 >= 9 || toint - step * 10 <= 1) && (toint2  - step * 10000 <= 1000 || toint2  - step * 10000 >= 9000)) {
                panel.setBackground(Color.RED);
            }
            if (!isPrime){
                panel.setBackground(Color.getColor(null));
            }
        }

        return panel;
    }

    public GornerTableCellRenderer() {


        formatter.setMaximumFractionDigits(5);

        formatter.setGroupingUsed(false);

        DecimalFormatSymbols dottedDouble =
                formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');

        formatter.setDecimalFormatSymbols(dottedDouble);

        panel.add(label);

        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public void SetColor(boolean PrimeNumber){
        _PrimeNumber = PrimeNumber;
    }

}
