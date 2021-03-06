<%-- 
    Document   : Master
    Created on : 09-dic-2015, 12:41:50
    Author     : Joe
--%>

<%@tag description="Pagina Maestra" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${sessionScope.user== null}">
   <c:redirect url="login.htm"/>
</c:if>

${sessionScope.valid}
<%@attribute name="header" fragment="true" %>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<jsp:include page="/WEB-INF/jsp/template/head.jsp" />
    <body class="page-header-fixed page-quick-sidebar-over-content">
        <jsp:include page="/WEB-INF/jsp/template/header.jsp" />
        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <jsp:doBody/>
        </div>
        <jsp:include page="/WEB-INF/jsp/template/footer.jsp" />

    </body>

</html>
