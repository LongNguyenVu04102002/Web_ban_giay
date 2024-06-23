<%@ include file="includes/header.jsp" %>
<jsp:useBean id="pageTitle" scope="page" class="java.lang.String" />
<%
    pageTitle = "Home";
%>
<%@ include file="includes/navbar.jsp" %>

<div class="container">
    <div class="left-menu">
        <%@ include file="../layout/left-menu.jsp" %>
    </div>
    <div class="main-content">
        <main>
          <%@ include file="../views/khachhang.jsp" %>
        </main>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>
