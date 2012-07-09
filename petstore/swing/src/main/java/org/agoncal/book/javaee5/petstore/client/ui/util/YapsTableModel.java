package org.agoncal.book.javaee5.petstore.client.ui.util;


import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


public abstract class YapsTableModel<E> extends AbstractTableModel {

    protected List<E> dataList;

    protected String[] columnNames;
    protected Class[] columnClasses;
    protected int[] columnRatios;


    public YapsTableModel() {
        buildColumnProperties();
        initDataList();
    }


    private void buildColumnProperties() {
        Object[][] columnProperties = getColumnProperties();

        columnNames = new String[columnProperties.length];
        columnClasses = new Class[columnProperties.length];
        columnRatios = new int[columnProperties.length];

        for (int i = 0; i < columnProperties.length; i++) {
            columnNames[i] = (String) columnProperties[i][0];
            columnClasses[i] = (Class) columnProperties[i][1];
            columnRatios[i] = (Integer) columnProperties[i][2];
        }
    }

    protected abstract Object[][] getColumnProperties();


    public void initDataList() {
        this.dataList = nonNullList(buildDataList());

        fireTableStructureChanged();
    }

    protected abstract List<E> buildDataList();

    public void add(E element) {
        dataList.add(element);
        fireTableStructureChanged();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return dataList.size();
    }

    private static <E> List<E> nonNullList(List<E> list) {
        return (list != null) ? list : new ArrayList<E>();
    }


    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public int[] getColumnRatios() {
        return columnRatios;
    }


    public abstract String getDefaultTitle();

    public abstract YapsCrudFrame crudFrameFactory(Integer selectedRowIndex,
                                                   YapsViewType viewType);

}