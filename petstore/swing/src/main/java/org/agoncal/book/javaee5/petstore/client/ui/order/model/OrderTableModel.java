package org.agoncal.book.javaee5.petstore.client.ui.order.model;


import org.agoncal.book.javaee5.petstore.client.ui.order.OrderCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.order.OrderPane;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsTableModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.agoncal.book.javaee5.petstore.delegate.OrderDelegate;
import org.agoncal.book.javaee5.petstore.entity.order.Order;

import java.util.Date;
import java.util.List;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsUIConstants.DATE_PATTERN;
import static org.apache.commons.lang.time.DateFormatUtils.format;


public class OrderTableModel extends YapsTableModel<Order> {

    private static final long serialVersionUID = -8869063785530249431L;


    @Override
    protected List<Order> buildDataList() {
        return OrderDelegate.findOrders();
    }

    @Override
    protected Object[][] getColumnProperties() {
        return new Object[][]{
                {
                        "ID", Long.class, 80
                }, {
                "Date", Date.class, 300
        }, {
                "Customer Name", String.class, 320
        }, {
                "Number of items", Integer.class, 150
        }, {
                "Total", Float.class, 150
        }
        };
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Order data = dataList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return data.getId();
            case 1:
                Date date = data.getOrderDate();

                if (date == null) {
                    return "";
                }

                return format(date, DATE_PATTERN);
            case 2:
                return data.getCustomer().getLastname() + " "
                        + data.getCustomer().getFirstname();
            case 3:
                return data.getOrderLines().size();
            case 4:
                return data.getTotal();
            default:
                return null;
        }
    }


    @Override
    public String getDefaultTitle() {
        return "Lists all the orders";
    }

    @Override
    public YapsCrudFrame crudFrameFactory(Integer selectedRowIndex,
                                          YapsViewType viewType) {
        OrderModel model = null;

        if (selectedRowIndex != null) {
            Order order = OrderDelegate.findOrder(dataList
                    .get(selectedRowIndex).getId());

            model = new DefaultOrderModel(order);
        }

        OrderPane component = new OrderPane(model, viewType);
        YapsCrudFrame frame = new OrderCrudFrame(component);
        frame.pack();

        return frame;
    }

}