<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<META http-equiv="Content-type" content="text/html; charset=UTF-8">
<f:view>
    <html>
    <link rel="stylesheet" type="text/css" href="petstore.css"/>

    <head>
        <title>YAPS PetStore - Products</title>
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

            <h2>Products for category :
                <h:outputText value="#{catalog.category.name}"/>
            </h2>

            <h:messages layout="table" styleClass="error"/>

            <h:form>
                <h:dataTable value="#{catalog.products}" var="product">
                    <h:column>
                        <h:commandLink action="#{catalog.doFindItems}">
                            <h:outputText value="#{product.name}"/>
                            <f:param name="productId" value="#{product.id}"/>
                        </h:commandLink>
                        <br/>
                        <h:outputText value="#{product.description}"/>
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