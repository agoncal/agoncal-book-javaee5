package org.agoncal.book.javaee5.petstore.client.ui.util;


import org.agoncal.book.javaee5.petstore.client.ui.util.event.YapsViewerEvent;
import org.agoncal.book.javaee5.petstore.client.ui.util.event.YapsViewerListener;
import org.vstm.fwk.client.ui.xswing.core.XSComponent;
import org.vstm.fwk.client.ui.xswing.core.event.XSListener;
import org.vstm.fwk.client.ui.xswing.core.model.XSModel;

import javax.swing.event.EventListenerList;

import static org.agoncal.book.javaee5.petstore.client.ui.util.YapsViewType.FIND_OR_CREATE;


public abstract class YapsComponentPane<M extends XSModel<L, E>, L extends XSListener<E>, E extends Enum>
        extends XSComponent<M, L, E> {

    protected final EventListenerList listenerList = new EventListenerList();

    protected YapsViewType viewType;


    public YapsComponentPane() {
        this(FIND_OR_CREATE);
    }

    public YapsComponentPane(M model) {
        this(model, FIND_OR_CREATE);
    }

    public YapsComponentPane(YapsViewType viewType) {
        setViewType(viewType);
    }

    public YapsComponentPane(M model, YapsViewType viewType) {
        super(model);

        setViewType(viewType);
    }


    public void addYapsViewListener(YapsViewerListener listener) {
        listenerList.add(YapsViewerListener.class, listener);
    }

    public void removeYapsViewListener(YapsViewerListener listener) {
        listenerList.remove(YapsViewerListener.class, listener);
    }

    public YapsViewerListener[] getYapsViewListeners() {
        return listenerList.getListeners(YapsViewerListener.class);
    }


    protected void fireYapsViewChanged(Object source, YapsViewType oldValue,
                                       YapsViewType newValue) {
        YapsViewerListener[] listeners = getYapsViewListeners();
        YapsViewerEvent evt = null;

        for (int i = listeners.length - 1; i >= 0; i--) {
            if (evt == null) {
                evt = new YapsViewerEvent(this, oldValue, newValue);
            }

            listeners[i].yapsViewChanged(evt);
        }
    }

    public YapsViewType getViewType() {
        return viewType;
    }

    public void setViewType(YapsViewType viewType) {
        if (viewType == null) {
            viewType = FIND_OR_CREATE;
        }

        synchronizeViewType(viewType);

        YapsViewType oldValue = this.viewType;
        YapsViewType newValue = viewType;
        this.viewType = viewType;

        fireYapsViewChanged(this, oldValue, newValue);
    }

    protected abstract void synchronizeViewType(YapsViewType viewType);

}