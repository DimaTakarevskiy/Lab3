package MainPackage;

import javax.swing.table.AbstractTableModel;
import java.util.Scanner;

public class GornerTableModel extends AbstractTableModel {

    private Double[] _coefficients;
    private Double _from;
    private Double _to;
    private Double _step;




    public GornerTableModel(Double[] coefficients, Double from, Double to, Double step){
        _coefficients = coefficients;
        _from = from;
        _to = to;
        _step = step;

    }

    public Double getFrom() {
        return _from;
    }
    public Double getTo() {
        return _to;
    }
    public Double getStep() {
        return _step;
    }
    @Override
    public int getRowCount() {
        return  ((int) (Math.ceil((_to - _from) / _step)) + 1);
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Class<?> getColumnClass(int arg0) {
        return Double.class;

    }

    @Override
    public String getColumnName(int col) {
        switch (col){
            case 0:
                return "Значение X: ";
            case 1:
                return "Значение Многочлена";
            case 2:
                return "Значение в степени";
            default:
                return "Разница между Горнером и степенью";
        }
    }


    @Override
    public Object getValueAt(int row, int column) {
        double x = _from + _step * row;

        if(column == 0) {
            return x;
        }
        return x;
    }

}
