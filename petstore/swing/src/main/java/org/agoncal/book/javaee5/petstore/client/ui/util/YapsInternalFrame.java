package org.agoncal.book.javaee5.petstore.client.ui.util;


import org.agoncal.book.javaee5.petstore.util.Constants;

import javax.swing.*;
import java.util.logging.Logger;

import static org.agoncal.book.javaee5.petstore.util.ExceptionUtils.getRootCause;
import static org.agoncal.book.javaee5.petstore.util.ExceptionUtils.isApplicationException;


public class YapsInternalFrame extends JInternalFrame {

    private static final long serialVersionUID = -7476477854043392884L;


    // ======================================
    // = Constructeurs =
    // ======================================
    public YapsInternalFrame() {
        super("", true, true, true, true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }


    protected void setInnerPane(JPanel innerPane) {
        getContentPane().add(innerPane);
    }


    // ======================================
    // = Attributs =
    // ======================================
    protected final transient String className = getClass().getName();
    protected Logger logger = Logger.getLogger(Constants.LOGGER_CLIENT);


    // ======================================
    // = Methodes Prot�g�es =
    // ======================================
    protected void displayException(String sourceClass, String sourceMethod,
                                    Throwable throwable) {
        Throwable cause = getRootCause(throwable);
        if (isApplicationException(cause)) {
            displayWarning(cause.getMessage());
        } else {
            displayError(throwable.getMessage());
            logger.throwing(sourceClass, sourceMethod, throwable);
        }
    }

    protected void displayWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning",
                JOptionPane.WARNING_MESSAGE);
    }

    protected void displayError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error",
                JOptionPane.ERROR_MESSAGE);
    }

}