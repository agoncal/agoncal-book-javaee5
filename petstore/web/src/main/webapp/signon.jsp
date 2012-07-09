<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<META http-equiv="Content-type" content="text/html; charset=UTF-8">
<f:view>
    <html>
    <link rel="stylesheet" type="text/css" href="petstore.css"/>

    <head>
        <title>YAPS PetStore - Sign In</title>
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

            <h2>Sign In</h2>

            <h:messages layout="table" styleClass="error"/>

            <p><b>Are you a returning customer ?</b></p>

            <TABLE cellSpacing=0 cellPadding=20 width="100%" border=1>
                <TR>
                    <TD vAlign=top>
                        <h:form>
                            <TABLE cellSpacing=0 cellPadding=5 border=0>
                                <TR>
                                    <TD align=middle colSpan=2><B>Yes.</B></TD>
                                </TR>
                                <TR>
                                    <TD align=right><B>Login :</B></TD>
                                    <TD>
                                        <h:inputText value="#{account.login}" maxlength="10" size="12"/>
                                    </TD>
                                </TR>
                                <TR>
                                    <TD align=right><B>Password:</B></TD>
                                    <TD>
                                        <h:inputSecret value="#{account.password}" maxlength="10" size="12"/>
                                    </TD>
                                </TR>
                                <TR>
                                    <TD align=middle colSpan=2>
                                        <h:commandButton value="Sign In"
                                                         action="#{account.doSignIn}"
                                                         type="submit"/>
                                    </TD>
                                </TR>
                            </TABLE>
                        </h:form>
                    </TD>
                    <TD vAlign=top>
                        <h:form>
                            <TABLE cellSpacing=0 cellPadding=5 border=0>
                                <TR>
                                    <TD align=middle colSpan=2><B>No. I would like to sign up for an account.</B></TD>
                                </TR>
                                <TR>
                                    <TD align=right><B>Login:</B></TD>
                                    <TD>
                                        <h:inputText value="#{account.customer.login}" maxlength="10" size="12"/>
                                    </TD>
                                </TR>
                                <TR>
                                    <TD align=right><B>Password:</B></TD>
                                    <TD>
                                        <h:inputSecret value="#{account.customer.password}" maxlength="10" size="12"/>
                                    </TD>
                                </TR>
                                <TR>
                                    <TD align=right><B>Password (Repeat):</B></TD>
                                    <TD>
                                        <h:inputSecret value="#{account.password2}" maxlength="10" size="12"/>
                                    </TD>
                                </TR>
                                <TR>
                                    <TD align=middle colSpan=2>
                                        <h:commandButton value="Create new account"
                                                         action="#{account.doCreateNewAccount}"
                                                         type="submit"/>
                                    </TD>
                                </TR>
                            </TABLE>
                        </h:form>
                    </TD>
                </TR>
            </TABLE>
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