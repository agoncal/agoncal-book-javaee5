package org.agoncal.book.javaee5.petstore.client.ui.customer.model;


import org.agoncal.book.javaee5.petstore.client.ui.customer.CustomerCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.customer.CustomerPane;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsTableModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.agoncal.book.javaee5.petstore.delegate.CustomerDelegate;
import org.agoncal.book.javaee5.petstore.entity.customer.Customer;

import java.util.List;


public class CustomerTableModel extends YapsTableModel<Customer> {

    private static final long serialVersionUID = 319512146319841375L;


    @Override
    protected List<Customer> buildDataList() {
        return CustomerDelegate.findCustomers();
    }

    @Override
    protected Object[][] getColumnProperties() {
        return new Object[][]{
                {
                        "ID", Long.class, 80
                }, {
                "First Name", String.class, 170
        }, {
                "Last Name", String.class, 170
        }, {
                "Telephone", String.class, 130
        }, {
                "Email", String.class, 240
        }, {
                "City", String.class, 130
        }, {
                "Country", String.class, 130
        }
        };
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Customer data = dataList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return data.getId();
            case 1:
                return data.getFirstname();
            case 2:
                return data.getLastname();
            case 3:
                return data.getTelephone();
            case 4:
                return data.getEmail();
            case 5:
                return data.getHomeAddress().getCity();
            case 6:
                return data.getHomeAddress().getCountry();
            default:
                return null;
        }
    }


    @Override
    public String getDefaultTitle() {
        return "Lists all the customers";
    }

    @Override
    public YapsCrudFrame crudFrameFactory(Integer selectedRowIndex,
                                          YapsViewType viewType) {
        CustomerModel model = null;

        if (selectedRowIndex != null) {
            Customer customer = CustomerDelegate.findCustomer(dataList.get(
                    selectedRowIndex).getId());

            model = new DefaultCustomerModel(customer);
        }

        CustomerPane component = new CustomerPane(model, viewType);
        YapsCrudFrame frame = new CustomerCrudFrame(component);
        frame.pack();

        return frame;
    }

}