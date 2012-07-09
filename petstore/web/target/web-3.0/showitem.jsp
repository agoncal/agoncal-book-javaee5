<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<META http-equiv="Content-type" content="text/html; charset=UTF-8">
<f:view>
    <html>
    <link rel="stylesheet" type="text/css" href="petstore.css"/>

    <head>
        <title>YAPS PetStore - Item</title>
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

            <h2>
                <h:outputText value="#{catalog.item.name}"/>
            </h2>

            <h:messages layout="table" styleClass="error"/>

            <h:form>

                <h:panelGrid columns="2">
                    <h:column>
                        <h:graphicImage url="images/#{catalog.item.imagePath}"/>
                    </h:column>
                    <h:column>
                        <h:outputText value="Unit Cost:"/>
                        <h:outputText value="#{catalog.item.unitCost}"/>
                        $
                        <c:if test="${not empty sessionScope.account}">
                            <br/>
                            <h:commandLink action="#{cart.addItemToCart}">
                                <h:outputText value="Add to cart"/>
                                <f:param name="itemId" value="#{catalog.item.id}"/>
                            </h:commandLink>
                        </c:if>
                    </h:column>

                </h:panelGrid>

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