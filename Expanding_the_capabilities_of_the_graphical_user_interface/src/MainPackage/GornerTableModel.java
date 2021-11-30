package MainPackage;

import javax.swing.table.AbstractTableModel;
import java.util.Scanner;

public class GornerTableModel extends AbstractTableModel {

    private Double[] _coefficients;
    private Double _from;
    private Double _to;
    private Double _step;

    private Double MainValue1 = 0d;
    private Double MainValue2 = 0d;



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
        double MainValueColumn1 = _coefficients[0];
        double MainValueColumn2 = 0;

        if(column == 0) {
            return x;
        }

        if(column == 1){
            for(int i = 1; i < _coefficients.length; i++){
                MainValueColumn1 = _coefficients[i] + MainValueColumn1 * x;
            }
            MainValue1 = MainValueColumn1;
            return MainValueColumn1;
        }

         if (column == 2){
            for(int i = 0; i < _coefficients.length; i++){
                MainValueColumn2+= (Math.pow(x, _coefficients.length - 1 - i) * _coefficients[i]);
            }
             MainValue2 = MainValueColumn2;
            return MainValueColumn2;
        }else{
            return Math.abs(MainValue1 - MainValue2);
        }
    }

}
