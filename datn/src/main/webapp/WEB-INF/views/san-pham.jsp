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
    <a href="/chat-lieu">Chất liệu</a>
    <a href="/co-giay">Cổ giày</a>
    <a href="/day-giay">Dây giày</a>
    <a href="/de-giay">Đế giày</a>
    <a href="/hinh-anh">Hình ảnh</a>
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
        <h1 style="text-align: center">Sản phẩm</h1>
        <form:form action="/san-pham/add" method="post" modelAttribute="sanPham">
            <form:input path="sanPhamId" cssStyle="display: none"></form:input>

            <div class="mb-3">
                <label class="form-label">Đế giày</label>
                <form:select path="deGiay.deGiayId" cssClass="form-select" aria-label="Default select example">
                    <c:forEach items="${lstDeGiay}" var="x">
                        <form:option value="${x.deGiayId}">${x.ten}</form:option>
                    </c:forEach>
                </form:select>
            </div>

            <div class="mb-3">
                <label class="form-label">Thương hiệu</label>
                <form:select path="thuongHieu.thuongHieuId" cssClass="form-select" aria-label="Default select example">
                    <c:forEach items="${lstThuongHieu}" var="x">
                        <form:option value="${x.thuongHieuId}">${x.ten}</form:option>
                    </c:forEach>
                </form:select>
            </div>

            <div class="mb-3">
                <label class="form-label">Cổ giày</label>
                <form:select path="coGiay.coGiayId" cssClass="form-select" aria-label="Default select example">
                    <c:forEach items="${lstCoGiay}" var="x">
                        <form:option value="${x.coGiayId}">${x.ten}</form:option>
                    </c:forEach>
                </form:select>
            </div>

            <div class="mb-3">
                <label class="form-label">Lót giày</label>
                <form:select path="lotGiay.lotGiayId" cssClass="form-select" aria-label="Default select example">
                    <c:forEach items="${lstLotGiay}" var="x">
                        <form:option value="${x.lotGiayId}">${x.ten}</form:option>
                    </c:forEach>
                </form:select>
            </div>

            <div class="mb-3">
                <label class="form-label">Mũi giày</label>
                <form:select path="muiGiay.muiGiayId" cssClass="form-select" aria-label="Default select example">
                    <c:forEach items="${lstMuiGiay}" var="x">
                        <form:option value="${x.muiGiayId}">${x.ten}</form:option>
                    </c:forEach>
                </form:select>
            </div>

            <div class="mb-3">
                <label class="form-label">Chất liệu</label>
                <form:select path="chatLieu.chatLieuId" cssClass="form-select" aria-label="Default select example">
                    <c:forEach items="${lstChatLieu}" var="x">
                        <form:option value="${x.chatLieuId}">${x.ten}</form:option>
                    </c:forEach>
                </form:select>
            </div>

            <div class="mb-3">
                <label class="form-label">Dây giày</label>
                <form:select path="dayGiay.dayGiayId" cssClass="form-select" aria-label="Default select example">
                    <c:forEach items="${lstDayGiay}" var="x">
                        <form:option value="${x.dayGiayId}">${x.ten}</form:option>
                    </c:forEach>
                </form:select>
            </div>

            <div class="mb-3">
                <label class="form-label">Tên</label>
                <form:input path="ten" cssClass="form-control"></form:input>
                <form:errors path="ten" cssClass="form-text" cssStyle="color: red"></form:errors>
                <p style="color: red">${error}</p>
            </div>

            <div class="mb-3">
                <label class="form-label">Năm sản xuất</label>
                <form:input path="namSX" cssClass="form-control"></form:input>
                <form:errors path="namSX" cssClass="form-text" cssStyle="color: red"></form:errors>
                <p style="color: red">${error1}</p>
            </div>

            <div class="mb-3">
                <label class="form-label">Mô tả</label>
                <form:input path="moTa" cssClass="form-control"></form:input>
                <form:errors path="moTa" cssClass="form-text" cssStyle="color: red"></form:errors>
            </div>

            <div class="mb-3">
                <label class="form-label">Trạng thái</label>
                <form:radiobutton cssClass="form-check-input" path="trangThai" value="true"
                                  label="Sử dụng"></form:radiobutton>
                <form:radiobutton cssClass="form-check-input" path="trangThai" value="false"
                                  label="Ngừng sử dụng"></form:radiobutton>
            </div>

            <form:button type="submit" class="btn btn-success">Thêm</form:button>
            <button type="submit" formaction="/san-pham/update/${id}" class="btn btn-warning">Update</button>

        </form:form>

        <table class="table">
            <tr>
                <td scope="col">#</td>
                <td scope="col">Tên</td>
                <td scope="col">Chất liệu</td>
                <td scope="col">Đế giày</td>
                <td scope="col">Cổ giày</td>
                <td scope="col">Lót giày</td>
                <td scope="col">Mũi giày</td>
                <td scope="col">Dây giày</td>
                <td scope="col">Thương hiệu</td>
                <td scope="col">Năm sản xuất</td>
                <td scope="col">Mô tả</td>
                <td scope="col">Trạng thái</td>
                <%--                <td scope="col">Ảnh</td>--%>
                <td scope="col">Chức năng</td>
            </tr>
            <c:forEach items="${lst.content}" var="x" varStatus="vTri">
                <tr>
                    <td scope="row">${vTri.index + 1}</td>
                    <td>${x.ten}</td>
                    <td>${x.chatLieu.ten}</td>
                    <td>${x.deGiay.ten}</td>
                    <td>${x.coGiay.ten}</td>
                    <td>${x.lotGiay.ten}</td>
                    <td>${x.muiGiay.ten}</td>
                    <td>${x.dayGiay.ten}</td>
                    <td>${x.thuongHieu.ten}</td>
                    <td>${x.namSX}</td>
                    <td>${x.moTa}</td>
                    <td>${x.trangThai == true ? "Đang sử dụng" : "Ngừng sử dụng"}</td>
                        <%--                    <td style="width: 150px"><img src="/img/${lst.anh}" alt="" style="width:50%"></td>--%>
                    <td>
                        <button class="btn btn-info"><a href="/san-pham/detail/${x.sanPhamId}"
                                                        style="text-decoration: none; color: white">Chi tiết</a>
                        </button>
                        <button class="btn btn-danger"><a href="/san-pham/remove/${x.sanPhamId}"
                                                          style="text-decoration: none; color: white">Xóa</a>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <nav aria-label="Page navigation example">
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
        </nav>
    </div>
</div>

</body>
</html>