<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
            crossorigin="anonymous"></script>
    <style>
        /* The side navigation menu */
        .sidebar {
            margin: 0;
            padding: 0;
            width: 200px;
            background-color: #f1f1f1;
            position: fixed;
            height: 100%;
            overflow: auto;
        }

        /* Sidebar links */
        .sidebar a {
            display: block;
            color: black;
            padding: 16px;
            text-decoration: none;
        }

        /* Active/current link */
        .sidebar a.active {
            background-color: #04AA6D;
            color: white;
        }

        /* Links on mouse-over */
        .sidebar a:hover:not(.active) {
            background-color: #555;
            color: white;
        }

        /* Page content. The value of the margin-left property should match the value of the sidebar's width property */
        div.content {
            margin-left: 200px;
            padding: 1px 16px;
            height: 1000px;
        }

        /* On screens that are less than 700px wide, make the sidebar into a topbar */
        @media screen and (max-width: 700px) {
            .sidebar {
                width: 100%;
                height: auto;
                position: relative;
            }

            .sidebar a {
                float: left;
            }

            div.content {
                margin-left: 0;
            }
        }

        /* On screens that are less than 400px, display the bar vertically, instead of horizontally */
        @media screen and (max-width: 400px) {
            .sidebar a {
                text-align: center;
                float: none;
            }
        }
    </style>
</head>
<body>
<!-- The sidebar -->
<div class="sidebar">
    <%--    <a class="active" href="/trang-chu">Trang chủ</a>--%>
    <a class="active" href="/chat-lieu">Chất liệu</a>
    <a href="/co-giay">Cổ giày</a>
    <a href="/day-giay">Dây giày</a>
    <a href="/de-giay">Đế giày</a>
<%--    <a href="/hinh-anh">Hình ảnh</a>--%>
    <a href="/kich-thuoc">Kích thước</a>
    <a href="/lot-giay">Lót giày</a>
    <a href="/mau-sac">Màu sắc</a>
    <a href="/mui-giay">Mũi giày</a>
    <a href="/thuong-hieu">Thương hiệu</a>
    <a href="/san-pham-chi-tiet">Sản phẩm chi tiết</a>
    <a href="/san-pham">Sản phẩm</a>
</div>

<!-- Page content -->
<div class="content">
    <div class="container">
        <h1 style="text-align: center">Lót giày</h1>
        <form:form action="/lot-giay/add" method="post" modelAttribute="lotGiay">
            <form:input path="lotGiayId" cssStyle="display: none"></form:input>

            <div class="mb-3">
                <label class="form-label">Tên</label>
                <form:input path="ten" cssClass="form-control"></form:input>
                <form:errors path="ten" cssClass="form-text" cssStyle="color: red"></form:errors>
            </div>

            <div class="mb-3">
                <label class="form-label">Mô tả</label>
                <form:input path="moTa" cssClass="form-control"></form:input>
                <form:errors path="moTa" cssClass="form-text" cssStyle="color: red"></form:errors>
                <p style="color: red">${error}</p>
            </div>

            <div class="mb-3">
                <label class="form-label">Trạng thái</label>
                <form:radiobutton cssClass="form-check-input" path="trangThai" value="true"
                                  label="Sử dụng"></form:radiobutton>
                <form:radiobutton cssClass="form-check-input" path="trangThai" value="false"
                                  label="Ngừng sử dụng"></form:radiobutton>
            </div>

            <form:button type="submit" class="btn btn-success">Thêm</form:button>
            <button type="submit" formaction="/lot-giay/update/${id}" class="btn btn-warning">Update</button>

        </form:form>

        <table class="table">
            <tr>
                <td scope="col">#</td>
                <td scope="col">Tên</td>
                <td scope="col">Mô tả</td>
                <td scope="col">Trạng thái</td>
                <td scope="col">Chức năng</td>
            </tr>
            <c:forEach items="${lst.content}" var="x" varStatus="vTri">
                <tr>
                    <td scope="row">${vTri.index + 1}</td>
                    <td>${x.ten}</td>
                    <td>${x.moTa}</td>
                    <td>${x.trangThai == true ? "Đang sử dụng" : "Ngừng sử dụng"}</td>
                    <td>
                        <button class="btn btn-info"><a href="/lot-giay/detail/${x.lotGiayId}"
                                                        style="text-decoration: none; color: white">Chi tiết</a>
                        </button>
                        <button class="btn btn-danger"><a href="/lot-giay/remove/${x.lotGiayId}"
                                                          style="text-decoration: none; color: white">Xóa</a>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <nav aria-label="Page navigation example"></nav>
        <ul class="pagination">
            <c:if test="${lst.number != 0}">
                <li class="page-item">
                    <a class="page-link" href="?p=${lst.number - 1}">←</a>
                </li>
            </c:if>

            <c:forEach begin="0" end="${lst.totalPages > 1 ? lst.totalPages - 1 : 0}" var="i">
                <li class="page-item <c:if test='${i == lst.number}'>active</c:if>">
                    <a class="page-link" href="?p=${i}">${i + 1}</a>
                </li>
            </c:forEach>

            <c:if test="${lst.number != lst.totalPages - 1}">
                <li class="page-item">
                    <a class="page-link" href="?p=${lst.number + 1}">→</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>

</body>
</html>