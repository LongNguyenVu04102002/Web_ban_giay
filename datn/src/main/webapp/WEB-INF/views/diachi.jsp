<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<%@ page pageEncoding="utf-8" %>
<jsp:useBean id="pageTitle" scope="page" class="java.lang.String" />
<%
    pageTitle = "Danh sách Khách hàng";
%>
<html>
<div class="container">
    <div class="row">

        <div class="col-md-9 mt-5">
            <main>
                <h2>Danh sách Địa Chỉ</h2>
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>dia chi nhan</th>
                            <th>ngay tao</th>
                            <th>sdt </th>
                            <th>ho ten </th>
                            <th>Số Điện Thoại</th>
                              <th>xa </th>
                              <th>huyen </th>
                               <th>thanhpho</th>
                            <th>Trạng Thái</th>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="diaChi" items="${diaChis}">
                            <tr>
                                <td>${diaChi.diaChiId}</td>
                                <td>${diaChi.diaChiNhan}</td>
                                <td>${diaChi.ngayTao}</td>
                                <td>${diaChi.sdt}</td>
                                <td>${diaChi.hoTen}</td>
                                <td>${diaChi.xa}</td>
                                <td>${diaChi.huyen}</td>
                                <td>${diaChi.thanhPho}</td>
                                <td>${khachHang.trangThai ? 'Hoạt Động' : 'Không Hoạt Động'}</td>
                                <td>
                                    <a href="/diachi/${diaChi.diaChiId}/toggle" class="btn btn-warning">
                                        ${diaChi.trangThai ? 'Đổi Sang Không Hoạt Động' : 'Đổi Sang Hoạt Động'}
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Phân trang -->
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <c:if test="${currentPage > 1}">
                            <li class="page-item"><a class="page-link" href="?page=1&size=${size}">First</a></li>
                            <li class="page-item"><a class="page-link" href="?page=${currentPage - 1}&size=${size}">Previous</a></li>
                        </c:if>
                        <c:forEach begin="1" end="${totalPages}" varStatus="page">
                            <li class="page-item ${currentPage == page.index + 1 ? 'active' : ''}">
                                <a class="page-link" href="?page=${page.index + 1}&size=${size}">${page.index + 1}</a>
                            </li>
                        </c:forEach>
                        <c:if test="${currentPage < totalPages}">
                            <li class="page-item"><a class="page-link" href="?page=${currentPage + 1}&size=${size}">Next</a></li>
                            <li class="page-item"><a class="page-link" href="?page=${totalPages}&size=${size}">Last</a></li>
                        </c:if>
                    </ul>
                </nav>
            </main>
        </div>

        <!-- Right Side (Form) -->

    </div>

</div>
</html>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

