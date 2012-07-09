package org.agoncal.book.javaee5.petstore.client.ui.catalog.product.model;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.ProductCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.ProductPane;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsTableModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.agoncal.book.javaee5.petstore.delegate.CatalogDelegate;
import org.agoncal.book.javaee5.petstore.entity.catalog.Product;

import java.util.List;


public class ProductTableModel extends YapsTableModel<Product> {

    private static final long serialVersionUID = 840315127122345076L;


    @Override
    protected List<Product> buildDataList() {
        return CatalogDelegate.findProducts();
    }

    @Override
    protected Object[][] getColumnProperties() {
        return new Object[][]{
                {
                        "ID", Long.class, 80
                }, {
                "Name", String.class, 210
        }, {
                "Description", String.class, 400
        }, {
                "Category Name", String.class, 210
        }
        };
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Product data = dataList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return data.getId();
            case 1:
                return data.getName();
            case 2:
                return data.getDescription();
            case 3:
                return data.getCategory().getName();
            default:
                return null;
        }
    }


    @Override
    public String getDefaultTitle() {
        return "Lists all the products";
    }

    @Override
    public YapsCrudFrame crudFrameFactory(Integer selectedRowIndex,
                                          YapsViewType viewType) {
        ProductModel model = null;

        if (selectedRowIndex != null) {
            Product product = CatalogDelegate.findProduct(dataList.get(
                    selectedRowIndex).getId());

            model = new DefaultProductModel(product);
        }

        ProductPane component = new ProductPane(model, viewType);
        YapsCrudFrame frame = new ProductCrudFrame(component);
        frame.pack();

        return frame;
    }

}