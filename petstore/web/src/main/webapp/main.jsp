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
    <div id="body" style="text-align: center;">
        <div class="panel">

            <c:if test="${not empty sessionScope.account}">
                <center>Welcome back
                    <b>
                        <h:outputText value="#{account.customer.firstname}"/>
                        &nbsp;
                        <h:outputText value="#{account.customer.lastname}"/>
                    </b>
                </center>
            </c:if>

            <h:messages layout="table" styleClass="error"/>

            <p/>

            <map name="pestoremap">
                <area alt="Birds" coords="72,2,280,250" shape="RECT"
                      href="<%= request.getContextPath() %>/findproducts?categoryId=5"/>
                <area alt="Fish" coords="2,180,72,250" shape="RECT"
                      href="<%= request.getContextPath() %>/findproducts?categoryId=1"/>
                <area alt="Dogs" coords="60,250,130,320" shape="RECT"
                      href="<%= request.getContextPath() %>/findproducts?categoryId=2"/>
                <area alt="Reptiles" coords="140,270,210,340" shape="RECT"
                      href="<%= request.getContextPath() %>/findproducts?categoryId=3"/>
                <area alt="Cats" coords="225,240,295,310" shape="RECT"
                      href="<%= request.getContextPath() %>/findproducts?categoryId=4"/>
                <area alt="Birds" coords="280,180,350,250" shape="RECT"
                      href="<%= request.getContextPath() %>/findproducts?categoryId=5"/>
            </map>
            <img src="images/splash.gif"
                 border="0" width="350" height="355"
                 align="center" usemap="#pestoremap" alt=""/>

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