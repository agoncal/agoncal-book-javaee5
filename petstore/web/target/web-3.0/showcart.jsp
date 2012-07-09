<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>


<META http-equiv="Content-type" content="text/html; charset=UTF-8">
<f:view>
    <html>
    <link rel="stylesheet" type="text/css" href="petstore.css"/>

    <head>
        <title>YAPS PetStore - Shopping Cart</title>
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

            <h2>Shopping Cart</h2>

            <h:messages layout="table" styleClass="error"/>

            <c:choose>
                <%-- Shopping Cart is empty --%>
                <c:when test="${empty sessionScope.cart.cartItems}">
                    <p><b>The Shopping Cart is empty</b></p>
                </c:when>

                <%-- There are items in the Shopping Cart --%>
                <c:otherwise>


                    <h:form>
                        <%--<h:dataTable value="#{cart.content.cartItems}" var="cartItem">--%>
                        <h:dataTable value="#{cart.cartItems}" var="cartItem">
                            <h:column>
                                <h:outputText value="#{cartItem.item.product.name}"/>
                                <br/>
                                <h:commandLink action="#{catalog.doFindItem}">
                                    <h:outputText value="#{cartItem.item.name}"/>
                                    <f:param name="itemId" value="#{cartItem.item.id}"/>
                                </h:commandLink>
                            </h:column>
                            <h:column>
                                <h:commandLink action="#{cart.updateQuantity}">
                                    <h:outputText value="Update"/>
                                    <f:param name="itemId" value="#{cartItem.item.id}"/>
                                </h:commandLink>
                            </h:column>
                            <h:column>
                                <h:inputText value="#{cartItem.quantity}"/>
                            </h:column>
                            <h:column>
                                x
                                <h:outputText value="#{cartItem.item.unitCost}"/> $
                            </h:column>
                            <h:column>
                                = <h:outputText value="#{cartItem.subTotal}"/> $
                            </h:column>
                            <h:column>
                                &nbsp;
                                <h:commandLink action="#{cart.removeItemFromCart}">
                                    <h:outputText value="Remove"/>
                                    <f:param name="itemId" value="#{cartItem.item.id}"/>
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>

                        <br/>

                        <div class="zoneButton">
                            Total $
                            <h:outputText value="#{cart.total}"/>

                            &nbsp;
                            <h:commandLink action="#{cart.checkout}">
                                <h:outputText value="Check Out"/>
                            </h:commandLink>
                        </div>
                    </h:form>

                </c:otherwise>
            </c:choose>


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