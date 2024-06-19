<%@ include file="includes/header.jsp" %>
<jsp:useBean id="pageTitle" scope="page" class="java.lang.String" />
<%
    pageTitle = "About";
%>
<%@ include file="includes/navbar.jsp" %>

<div class="container">
    <div class="left-menu">
        <%@ include file="includes/left-menu.jsp" %>
    </div>
    <div class="main-content">
        <main>
            <h2>About Page</h2>
            <p>Welcome to the about page!</p>
        </main>
    </div>
</div>

<%@ include file="includes/footer.jsp" %>
