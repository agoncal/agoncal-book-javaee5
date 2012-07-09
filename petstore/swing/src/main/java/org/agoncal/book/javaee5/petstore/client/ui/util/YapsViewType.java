package org.agoncal.book.javaee5.petstore.client.ui.util;


public enum YapsViewType {

    FIND {
        @Override
        public String getLabel() {
            return "Find";
        }

    },
    CREATE {
        @Override
        public String getLabel() {
            return "Create";
        }

    },
    FIND_OR_CREATE {
        @Override
        public String getLabel() {
            return "Find or Create";
        }

    },
    READ {
        @Override
        public String getLabel() {
            return "Read only";
        }

    },
    UPDATE {
        @Override
        public String getLabel() {
            return "Update";
        }

    },
    DELETE {
        @Override
        public String getLabel() {
            return "Delete";
        }

    },
    UPDATE_OR_DELETE {
        @Override
        public String getLabel() {
            return "Update or Delete";
        }

    },
    LIST {
        @Override
        public String getLabel() {
            return "List";
        }

    };

    public abstract String getLabel();

}