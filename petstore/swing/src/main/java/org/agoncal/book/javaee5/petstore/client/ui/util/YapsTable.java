package org.agoncal.book.javaee5.petstore.client.ui.util;


import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsUIConstants.*;


public class YapsTable extends JTable {

    private static final long serialVersionUID = 7626631528727042580L;


    public YapsTable(YapsTableModel model) {
        super(model);

        getTableHeader().setReorderingAllowed(false);

        for (int i = 0; i < model.getColumnRatios().length; i++) {
            TableColumn column = getColumnModel().getColumn(i);
            column.setPreferredWidth(model.getColumnRatios()[i]);

            setPreferredScrollableViewportSize(new Dimension(500, 70));
        }
    }


    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row,
                                     int column) {
        Component innerTableCellRendererComponent = super.prepareRenderer(
                renderer, row, column);

        innerTableCellRendererComponent.setBackground(getCellBackground(row,
                column, isCellSelected(row, column), isRowSelected(row)));
        innerTableCellRendererComponent.setForeground(getCellForeground(row,
                column, isCellSelected(row, column), isRowSelected(row)));

        return innerTableCellRendererComponent;
    }


    protected Color getCellBackground(int row, int column, boolean isSelected,
                                      boolean hasFocus) {
        if (row == (row / 2) * 2) {
            return isSelected ? TABLE_ROW_SELECTED_COLOR_1 : TABLE_ROW_COLOR_1;
        }
        return isSelected ? TABLE_ROW_SELECTED_COLOR_2 : TABLE_ROW_COLOR_2;
    }

    protected Color getCellForeground(int row, int column, boolean isSelected,
                                      boolean hasFocus) {
        if (isSelected) {
            if (column == 0) {
                return Color.yellow;
            }
            return Color.white;
        }
        if (column == 0) {
            return Color.blue;
        }
        return Color.black;
    }


    public YapsTableModel getYapsModel() {
        return (YapsTableModel) getModel();
    }

}