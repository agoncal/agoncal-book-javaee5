package org.agoncal.book.javaee5.petstore.client.ui.catalog.item.model;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.ItemCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.ItemPane;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsTableModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.agoncal.book.javaee5.petstore.delegate.CatalogDelegate;
import org.agoncal.book.javaee5.petstore.entity.catalog.Item;

import java.util.List;


public class ItemTableModel extends YapsTableModel<Item> {

    private static final long serialVersionUID = -7491304099108274347L;


    @Override
    protected List<Item> buildDataList() {
        return CatalogDelegate.findItems();
    }

    @Override
    protected Object[][] getColumnProperties() {
        return new Object[][]{
                {
                        "ID", Long.class, 80
                }, {
                "Name", String.class, 300
        }, {
                "Unit Cost", Float.class, 300
        }, {
                "Product Name", String.class, 300
        }
        };
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Item data = dataList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return data.getId();
            case 1:
                return data.getName();
            case 2:
                return data.getUnitCost();
            case 3:
                return data.getProduct().getName();
            default:
                return null;
        }
    }


    @Override
    public String getDefaultTitle() {
        return "Lists all the items";
    }

    @Override
    public YapsCrudFrame crudFrameFactory(Integer selectedRowIndex,
                                          YapsViewType viewType) {
        ItemModel model = null;

        if (selectedRowIndex != null) {
            Item item = CatalogDelegate.findItem(dataList.get(selectedRowIndex)
                    .getId());

            model = new DefaultItemModel(item);
        }

        ItemPane component = new ItemPane(model, viewType);
        YapsCrudFrame frame = new ItemCrudFrame(component);
        frame.pack();

        return frame;
    }

}