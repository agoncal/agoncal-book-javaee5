package org.agoncal.book.javaee5.petstore.client.ui;


import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.CategoryCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.CategoryPane;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.category.model.CategoryTableModel;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.ItemCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.ItemPane;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.item.model.ItemTableModel;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.ProductCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.ProductPane;
import org.agoncal.book.javaee5.petstore.client.ui.catalog.product.model.ProductTableModel;
import org.agoncal.book.javaee5.petstore.client.ui.customer.CustomerCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.customer.CustomerPane;
import org.agoncal.book.javaee5.petstore.client.ui.customer.model.CustomerTableModel;
import org.agoncal.book.javaee5.petstore.client.ui.order.OrderCrudFrame;
import org.agoncal.book.javaee5.petstore.client.ui.order.OrderPane;
import org.agoncal.book.javaee5.petstore.client.ui.order.model.OrderTableModel;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsFrame;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsListFrame;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsMessageListener;
import org.agoncal.book.javaee5.petstore.client.ui.util.YapsUIConstants;
import org.agoncal.book.javaee5.petstore.client.util.jms.JmsListenerBootstrap;
import org.agoncal.book.javaee5.petstore.entity.order.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.EventObject;
import java.util.List;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.FIND;
import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.FIND_OR_CREATE;


/**
 * This class represents the main user interface that displays a menu from which
 * the employee can do some actions on the system.
 */
public class PetstoreFrame extends YapsFrame {

    private static final long serialVersionUID = -6750600430514708219L;


    private static final String BACKGROUND_IMAGE_NAME = "petstore.jpg";

    private static PetstoreFrame instance;


    // ======================================
    // = Attributs =
    // ======================================
    private int defaultWidth;
    private int defaultHeight;

    private int width;
    private int height;

    private JMenuBar menuBar = new JMenuBar();

    private JMenu menuFile = new JMenu();
    private JMenuItem menuItemExit = new JMenuItem();

    private JMenu menuCustomer = new JMenu();
    private JMenuItem menuItemManageCustomer = new JMenuItem();
    private JMenuItem menuListCustomer = new JMenuItem();

    private JMenu menuCatalog = new JMenu();
    private JMenuItem menuItemManageCategory = new JMenuItem();
    private JMenuItem menuItemManageProduct = new JMenuItem();
    private JMenuItem menuItemManageItem = new JMenuItem();
    private JMenuItem menuListCategory = new JMenuItem();
    private JMenuItem menuListProduct = new JMenuItem();
    private JMenuItem menuListItem = new JMenuItem();

    private JMenu menuOrder = new JMenu();
    private JMenuItem menuItemManageOrder = new JMenuItem();
    private JMenuItem menuListOrder = new JMenuItem();
    private JMenuItem menuWatchOrder = new JMenuItem();

    private JMenu menuLookAndFeel = new JMenu();
    private JMenuItem menuItemMetal = new JMenuItem();
    private JMenuItem menuItemMotif = new JMenuItem();
    private JMenuItem menuItemWindows = new JMenuItem();

    private JDesktopPane desktopPane = new JDesktopPane();

    // ======================================
    // = Constructeurs =
    // ======================================
    private PetstoreFrame() {
        defaultWidth = 640;
        defaultHeight = 480;

        width = defaultWidth;
        height = defaultHeight;

        initComponents();

        setTitle("Pet Store");
        setJMenuBar(menuBar);
        setSize(defaultWidth, defaultHeight);

        setExtendedState(MAXIMIZED_BOTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // ======================================
    // = Methodes publiques =
    // ======================================
    public static void main(String[] args) {
        PetstoreFrame petstoreFrame = getInstance();
        petstoreFrame.setVisible(true);
    }

    public static PetstoreFrame getInstance() {
        if (instance == null) {
            instance = new PetstoreFrame();
        }

        return instance;
    }

    // ======================================
    // = Methodes Priv�es =
    // ======================================
    private void initComponents() {
        // Menu File
        menuFile.setText("File");

        menuItemExit.setText("Exit");
        menuItemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemExitActionPerformed();
            }
        });

        menuFile.add(menuItemExit);
        menuBar.add(menuFile);

        // Menu Customer
        menuCustomer.setText("Customer");

        menuListCustomer.setText("List customers");
        menuListCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListCustomerActionPerformed();
            }
        });
        menuCustomer.add(menuListCustomer);

        menuCustomer.addSeparator();

        menuItemManageCustomer.setText("Manage customer");
        menuItemManageCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemManageCustomerActionPerformed();
            }
        });
        menuCustomer.add(menuItemManageCustomer);

        menuBar.add(menuCustomer);

        // Menu Catalog
        menuCatalog.setText("Catalog");

        menuListCategory.setText("List categories");
        menuListCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListCategoryActionPerformed();
            }
        });
        menuCatalog.add(menuListCategory);

        menuListProduct.setText("List products");
        menuListProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListProductActionPerformed();
            }
        });
        menuCatalog.add(menuListProduct);

        menuListItem.setText("List items");
        menuListItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListItemActionPerformed();
            }
        });
        menuCatalog.add(menuListItem);

        menuCatalog.addSeparator();

        menuItemManageCategory.setText("Manage category");
        menuItemManageCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemManageCategoryActionPerformed();
            }
        });
        menuCatalog.add(menuItemManageCategory);

        menuItemManageProduct.setText("Manage product");
        menuItemManageProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemManageProductActionPerformed();
            }
        });
        menuCatalog.add(menuItemManageProduct);

        menuItemManageItem.setText("Manage item");
        menuItemManageItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemManageItemActionPerformed();
            }
        });
        menuCatalog.add(menuItemManageItem);

        menuBar.add(menuCatalog);

        // Menu Order
        menuOrder.setText("Order");

        menuListOrder.setText("List orders");
        menuListOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuListOrderActionPerformed();
            }
        });
        menuOrder.add(menuListOrder);

        menuOrder.addSeparator();

        menuItemManageOrder.setText("Manage order");
        menuItemManageOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemManageOrderActionPerformed();
            }
        });
        menuOrder.add(menuItemManageOrder);

        menuOrder.addSeparator();

        menuWatchOrder.setText("Watch orders");
        menuWatchOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuWatchOrderActionPerformed();
            }
        });
        menuOrder.add(menuWatchOrder);

        menuBar.add(menuOrder);

        // Menu Look & Feel
        menuLookAndFeel.setText("Look&Feel");

        menuItemMetal.setText("Metal");
        menuItemMetal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemMetalActionPerformed();
            }
        });

        menuLookAndFeel.add(menuItemMetal);
        menuItemMotif.setText("Motif");
        menuItemMotif.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemMotifActionPerformed();
            }
        });

        menuLookAndFeel.add(menuItemMotif);
        menuItemWindows.setText("Windows");
        menuItemWindows.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                menuItemWindowsActionPerformed();
            }
        });

        menuLookAndFeel.add(menuItemWindows);
        menuBar.add(menuLookAndFeel);
        setName("frameMenu");

        // Body of the frame
        getContentPane().add(desktopPane);

        desktopPane.setBackground(YapsUIConstants.DEFAULT_BG_COLOR);

        ImageIcon imgIcon = new ImageIcon(BACKGROUND_IMAGE_NAME);

        final JLabel backgroundLabel = new JLabel(imgIcon, JLabel.CENTER);
        backgroundLabel.setSize(width, height);
        desktopPane.add(backgroundLabel);

        desktopPane.addComponentListener(new ComponentAdapter() {

            public void componentResized(ComponentEvent arg0) {
                width = (int) desktopPane.getSize().getWidth();
                height = (int) desktopPane.getSize().getHeight();

                backgroundLabel.setSize(width, height);
            }

        });
    }

    // Clicking on the 'Motif' menu changes the look and feel of the application
    private void menuItemMotifActionPerformed() {
        final String mname = "menuItemMotifActionPerformed";

        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            logger.throwing(className, mname, e);
        }
    }

    // Clicking on the 'Metal' menu changes the look and feel of the application
    private void menuItemMetalActionPerformed() {
        final String mname = "menuItemMetalActionPerformed";

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            logger.throwing(className, mname, e);
        }
    }

    // Clicking on the 'Windows' menu changes the look and feel of the
    // application
    private void menuItemWindowsActionPerformed() {
        final String mname = "menuItemWindowsActionPerformed";

        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            logger.throwing(className, mname, e);
        }
    }

    // This method opens the Manage Customer frame
    private void menuItemManageCustomerActionPerformed() {
        CustomerPane component = new CustomerPane(FIND_OR_CREATE);
        CustomerCrudFrame frame = new CustomerCrudFrame(component);
        frame.pack();

        addAndShowFrame(frame);
    }

    // This method opens the Manage Category frame
    private void menuItemManageCategoryActionPerformed() {
        CategoryPane component = new CategoryPane(FIND_OR_CREATE);
        CategoryCrudFrame frame = new CategoryCrudFrame(component);
        frame.pack();

        addAndShowFrame(frame);
    }

    // This method opens the Manage Product frame
    private void menuItemManageProductActionPerformed() {
        ProductPane component = new ProductPane(FIND_OR_CREATE);
        ProductCrudFrame frame = new ProductCrudFrame(component);
        frame.pack();

        addAndShowFrame(frame);
    }

    // This method opens the Manage Item frame
    private void menuItemManageItemActionPerformed() {
        ItemPane component = new ItemPane(FIND_OR_CREATE);
        ItemCrudFrame frame = new ItemCrudFrame(component);
        frame.pack();

        addAndShowFrame(frame);
    }

    private void menuItemManageOrderActionPerformed() {
        OrderPane component = new OrderPane(FIND);
        OrderCrudFrame frame = new OrderCrudFrame(component);
        frame.pack();

        addAndShowFrame(frame);
    }

    // This method opens the Customer List frame
    private void menuListCustomerActionPerformed() {
        final String actionName = "menuListCustomerActionPerformed";

        try {
            addAndShowFrame(new YapsListFrame(new CustomerTableModel()));
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }

    // This method opens the Category List frame
    private void menuListCategoryActionPerformed() {
        final String actionName = "menuListCategoryActionPerformed";

        try {
            addAndShowFrame(new YapsListFrame(new CategoryTableModel()));
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }

    // This method opens the Product List frame
    private void menuListProductActionPerformed() {
        final String actionName = "menuListProductActionPerformed";

        try {
            addAndShowFrame(new YapsListFrame(new ProductTableModel()));
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }

    // This method opens the Item List frame
    private void menuListItemActionPerformed() {
        final String actionName = "menuListItemActionPerformed";

        try {
            addAndShowFrame(new YapsListFrame(new ItemTableModel()));
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }

    private void menuListOrderActionPerformed() {
        final String actionName = "menuListOrderActionPerformed";

        try {
            addAndShowFrame(new YapsListFrame(new OrderTableModel()));
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }

    private void menuWatchOrderActionPerformed() {
        final String actionName = "menuWatchOrderActionPerformed";

        try {
            OrderTableModel tableModel = new OrderTableModel() {
                private static final long serialVersionUID = 5756141001105021844L;

                @Override
                protected List<Order> buildDataList() {
                    return null;
                }

                @Override
                public String getDefaultTitle() {
                    return "Watch for new orders containing reptiles";
                }
            };
            YapsMessageListener msgListener = new YapsMessageListener(
                    tableModel);
            final JmsListenerBootstrap listenerBootstrap = new JmsListenerBootstrap(
                    "jms/bookJavaEE5ConnectionFactory", "jms/topic/order",
                    "Reptiles=true", msgListener);
            listenerBootstrap.initJMS();
            YapsListFrame listFrame = new YapsListFrame(tableModel) {
                private static final long serialVersionUID = 1180941093214122194L;

                @Override
                public void closeActionPerformed(EventObject evt) {
                    listenerBootstrap.shutdownJMS();
                    super.closeActionPerformed(evt);
                }
            };
            addAndShowFrame(listFrame);
            listenerBootstrap.startListening();
        } catch (Exception exc) {
            displayException(className, actionName, exc);
        }
    }

    // This method exits the application
    private void menuItemExitActionPerformed() {
        dispose();
    }


    public void addAndShowFrame(JInternalFrame frame) {
        desktopPane.add(frame);
        frame.setLocation((width - frame.getWidth()) / 2, (height - frame
                .getHeight()) / 2);
        frame.setVisible(true);
    }

}