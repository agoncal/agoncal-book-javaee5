<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>


<META http-equiv="Content-type" content="text/html; charset=UTF-8">
<f:view>
    <html>
    <link rel="stylesheet" type="text/css" href="petstore.css"/>

    <head>
        <title>YAPS PetStore - Confirm Order</title>
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

            <h2>Confirm Order</h2>

            <h:messages layout="table" styleClass="error"/>

            <h:form>

                <div class="zoneEditor">
                    <h3>Personal information</h3>

                    <h:panelGrid columns="2">
                        <h:outputText value="Firstname :"/>
                        <h:inputText value="#{cart.customer.firstname}" readonly="true"/>

                        <h:outputText value="Lastname :"/>
                        <h:inputText value="#{cart.customer.lastname}" readonly="true"/>

                        <h:outputText value="Email :"/>
                        <h:inputText value="#{cart.customer.email}" readonly="true"/>

                    </h:panelGrid>
                </div>

                <div class="zoneEditor">
                    <h3>Delivery Address</h3>

                    <h:panelGrid columns="2">
                        <h:outputText value="*Street1 :"/>
                        <h:inputText value="#{cart.deliveryAddress.street1}"/>

                        <h:outputText value="Street2 :"/>
                        <h:inputText value="#{cart.deliveryAddress.street2}"/>

                        <h:outputText value="*City :"/>
                        <h:inputText value="#{cart.deliveryAddress.city}"/>

                        <h:outputText value="State :"/>
                        <h:inputText value="#{cart.deliveryAddress.state}"/>

                        <h:outputText value="*Zipcode :"/>
                        <h:inputText value="#{cart.deliveryAddress.zipcode}"/>

                        <h:outputText value="*Country :"/>
                        <h:inputText value="#{cart.deliveryAddress.country}"/>

                    </h:panelGrid>
                </div>

                <div class="zoneEditor">
                    <h3>Credit Card</h3>

                    <h:panelGrid columns="2">
                        <h:outputText value="*Credit card number :"/>
                        <h:inputText value="#{cart.creditCard.creditCardNumber}"/>

                        <h:outputText value="*Type :"/>
                        <h:selectOneMenu value="#{cart.creditCard.creditCardType}">
                            <f:selectItem itemValue="visa" itemLabel="Visa"/>
                            <f:selectItem itemValue="visa_gold" itemLabel="Visa Gold"/>
                            <f:selectItem itemValue="master" itemLabel="Master Card"/>
                            <f:selectItem itemValue="american" itemLabel="American Express"/>
                        </h:selectOneMenu>

                        <h:outputText value="*Expiry date (MM/YY):"/>
                        <h:inputText value="#{cart.creditCard.creditCardExpDate}"/>
                    </h:panelGrid>
                </div>

                <div class="zoneButton">
                    <h:commandButton value="Submit" action="#{cart.confirmOrder}" type="submit"/>
                </div>
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