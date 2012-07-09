<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<META http-equiv="Content-type" content="text/html; charset=UTF-8">
<f:view>
    <html>
    <link rel="stylesheet" type="text/css" href="petstore.css"/>

    <head>
        <title>YAPS PetStore - Customer</title>
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

            <h2>Your Account Information</h2>

            <h:messages layout="table" styleClass="error"/>

            <h:form>

                <div class="zoneEditor">
                    <h3>Personal information</h3>

                    <h:panelGrid columns="2">
                        <h:outputText value="Firstname :"/>
                        <h:outputText value="#{account.customer.firstname}"/>

                        <h:outputText value="Lastname :"/>
                        <h:outputText value="#{account.customer.lastname}"/>

                        <h:outputText value="Email :"/>
                        <h:outputText value="#{account.customer.email}"/>

                        <h:outputText value="Telephone :"/>
                        <h:outputText value="#{account.customer.telephone}"/>

                        <h:outputText value="Date of birth (dd.MM.yyyy):"/>
                        <h:outputText value="#{account.customer.dateOfBirth}">
                            <f:convertDateTime pattern="dd.MM.yyyy"/>
                        </h:outputText>

                        <h:outputText value="Age :"/>
                        <h:outputText value="#{account.customer.age}"/>

                    </h:panelGrid>
                </div>

                <div class="zoneEditor">
                    <h3>Address</h3>

                    <h:panelGrid columns="2">
                        <h:outputText value="Street1 :"/>
                        <h:outputText value="#{account.homeAddress.street1}"/>

                        <h:outputText value="Street2 :"/>
                        <h:outputText value="#{account.homeAddress.street2}"/>

                        <h:outputText value="City :"/>
                        <h:outputText value="#{account.homeAddress.city}"/>

                        <h:outputText value="State :"/>
                        <h:outputText value="#{account.homeAddress.state}"/>

                        <h:outputText value="Zipcode :"/>
                        <h:outputText value="#{account.homeAddress.zipcode}"/>

                        <h:outputText value="Country :"/>
                        <h:outputText value="#{account.homeAddress.country}"/>

                    </h:panelGrid>
                </div>

                <div class="zoneButton">
                    <h:commandLink action="updateaccount">
                        <h:outputText value="Edit Your Account Information"/>
                    </h:commandLink>
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