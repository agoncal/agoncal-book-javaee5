<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>


<META http-equiv="Content-type" content="text/html; charset=UTF-8">
<f:view>
    <html>
    <link rel="stylesheet" type="text/css" href="petstore.css"/>

    <head>
        <title>YAPS PetStore - Items</title>
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

            <h2>Your Order is Complete :
                <h:outputText value="#{cart.order.id}"/>
            </h2>

            <h:messages layout="table" showDetail="true"/>

            <p><b>Your order id is <h:outputText value="#{cart.order.id}"/></b></p>

            <h:form>
                <h:dataTable value="#{cart.order.orderLines}" var="orderLine">
                    <h:column>
                        <b><h:outputText value="#{orderLine.item.product.name}"/></b>
                        <br/>
                        <h:outputText value="#{orderLine.item.name}"/>
                    </h:column>
                    <h:column>
                        <h:outputText value="#{orderLine.quantity}"/>
                    </h:column>
                    <h:column>
                        x
                        <h:outputText value="#{orderLine.item.unitCost}"/>
                        $
                    </h:column>
                    <h:column>
                        =
                        <h:outputText value="#{orderLine.subTotal}"/>
                        $
                    </h:column>
                </h:dataTable>
                <br/>

                <div class="zoneButton">
                    Total $
                    <h:outputText value="#{cart.order.total}"/>
                </div>
            </h:form>


            <P>You will receive shortly an email confirming your order</P>

            <P>Thank you for shopping with the YAPS Pet Store</P>


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