<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<META http-equiv="Content-type" content="text/html; charset=UTF-8">
<f:view>
    <html>
    <link rel="stylesheet" type="text/css" href="petstore.css"/>

    <head>
        <title>YAPS PetStore</title>
    </head>

    <body>

        <%--HEADER--%>
    <div id="header">
        <div class="panel">
            <%@ include file="common/header.jspf" %>
        </div>
    </div>

        <%--NAVIGATION--%>
    <div id="sidebar">
        <div class="panel">
            <%@ include file="common/navigation.jspf" %>
        </div>
    </div>

        <%--BODY--%>
    <div id="body">
        <div class="panel">

            <h2>Items found for keyword :
                <h:outputText value="#{catalog.keyword}"/>
            </h2>

            <h:messages layout="table" styleClass="error"/>

            <h:form>
                <h:dataTable value="#{catalog.items}" var="item">
                    <h:column>
                        <h:commandLink action="#{catalog.doFindItem}">
                            <h:outputText value="#{item.name}"/>
                            <f:param name="itemId" value="#{item.id}"/>
                        </h:commandLink>
                    </h:column>
                    <h:column>
                        <i>(
                            <h:outputText value="#{item.product.category.name}"/>
                            -
                            <h:outputText value="#{item.product.name}"/>
                            )</i>
                    </h:column>
                    <h:column>
                        <h:outputText value="#{item.unitCost}"/>
                        $
                    </h:column>
                </h:dataTable>
            </h:form>
        </div>
    </div>

        <%--FOOTER--%>
    <div id="footer">
        <div class="panel">
            <%@ include file="common/footer.jspf" %>
        </div>
    </div>


    </body>
    </html>
</f:view>