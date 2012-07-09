package org.agoncal.book.javaee5.petstore.client.ui.order.model;


import org.agoncal.book.javaee5.petstore.client.ui.util.YapsCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsTableModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.agoncal.book.javaee5.petstore.entity.order.OrderLine;

import java.util.List;


public class OrderLineTableModel extends YapsTableModel<OrderLine> {

    private static final long serialVersionUID = 5873433953337672576L;


    private List<OrderLine> orderLines;


    public OrderLineTableModel(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }


    @Override
    protected List<OrderLine> buildDataList() {
        return orderLines;
    }

    @Override
    protected Object[][] getColumnProperties() {
        return new Object[][]{
                {
                        "ID", Long.class, 80
                }, {
                "Item", String.class, 320
        }, {
                "Quantity", Integer.class, 100
        }, {
                "Sub-total", Float.class, 200
        }
        };
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        OrderLine data = dataList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return data.getId();
            case 1:
                return data.getItem().getName();
            case 2:
                return data.getQuantity();
            case 3:
                return data.getSubTotal();
            default:
                return null;
        }
    }


    @Override
    public String getDefaultTitle() {
        return "Lists all the order lines";
    }

    @Override
    public YapsCrudFrame crudFrameFactory(Integer selectedRowIndex,
                                          YapsViewType viewType) {
        return null;
    }


    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;

        initDataList();
    }

    public List<OrderLine> getOrderlines() {
        return orderLines;
    }

}