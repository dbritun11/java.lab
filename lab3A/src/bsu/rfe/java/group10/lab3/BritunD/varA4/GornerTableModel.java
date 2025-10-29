package bsu.rfe.java.group10.lab3.BritunD.varA4;

import javax.swing.table.AbstractTableModel;
import java.util.List;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private final List<Double> coefficients;
    private final double from;
    private final double to;
    private final double step;

    public GornerTableModel(double from, double to, double step, List<Double> coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public double getFrom() {
        return from;
    }

    public double getTo() {
        return to;
    }

    public double getStep() {
        return step;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public int getRowCount() {
        return (int) Math.ceil((to - from) / step) + 1;
    }

    @Override
    public Object getValueAt(int row, int col) {
        double x = from + step * row;
        //double result = coefficients[0];
        double result = coefficients.get(0);
        for (Double coefficient : coefficients.subList(1, coefficients.size())) {
            result = result * x + coefficient;
        }
        // for each
        return switch (col) {
            case 0 -> x;
            case 1 -> result;
            case 2 -> Math.floor(Math.abs(result)) == 0;
            default -> null;
        };
    }


    @Override
    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            case 2:
                return "Малое число?";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int col) {
        return (col == 2) ? Boolean.class : Double.class;
    }
}
