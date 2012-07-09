package org.agoncal.book.javaee5.petstore.client.ui.catalog.category.model;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.CategoryCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.CategoryPane;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsTableModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType;
import org.agoncal.book.javaee5.petstore.delegate.CatalogDelegate;
import org.agoncal.book.javaee5.petstore.entity.catalog.Category;

import java.util.List;


public class CategoryTableModel extends YapsTableModel<Category> {

    private static final long serialVersionUID = -1390218879148406609L;


    @Override
    protected List<Category> buildDataList() {
        return CatalogDelegate.findCategories();
    }

    @Override
    protected Object[][] getColumnProperties() {
        return new Object[][]{
                {
                        "ID", Long.class, 80
                }, {
                "Name", String.class, 320
        }, {
                "Description", String.class, 600
        }
        };
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Category data = dataList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return data.getId();
            case 1:
                return data.getName();
            case 2:
                return data.getDescription();
            default:
                return null;
        }
    }


    @Override
    public String getDefaultTitle() {
        return "Lists all the categories";
    }

    @Override
    public YapsCrudFrame crudFrameFactory(Integer selectedRowIndex,
                                          YapsViewType viewType) {
        CategoryModel model = null;

        if (selectedRowIndex != null) {
            Category category = CatalogDelegate.findCategory(dataList.get(
                    selectedRowIndex).getId());

            model = new DefaultCategoryModel(category);
        }

        CategoryPane component = new CategoryPane(model, viewType);
        YapsCrudFrame frame = new CategoryCrudFrame(component);
        frame.pack();

        return frame;
    }

}