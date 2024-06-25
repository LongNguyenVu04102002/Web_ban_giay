<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<%@ page pageEncoding="utf-8" %>
<jsp:useBean id="pageTitle" scope="page" class="java.lang.String" />
<%
    pageTitle = "Danh sách Khách hàng";
%>

<div class="container">
    <div class="row">

        <div class="col-md-9 mt-5">
            <h2>Tìm kiếm Khách Hàng</h2>

                        <!-- Form Tìm kiếm -->
                        <form action="${pageContext.request.contextPath}/searchBySDT" method="post">
                            <div class="mb-3">
                                <label for="sdt">Số Điện Thoại:</label>
                                <input type="text" id="sdt" name="sdt" class="form-control" />
                            </div>
                            <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                        </form>
            <main>
                <h2>Danh sách Khách hàng</h2>
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tên</th>
                            <th>Giới Tính</th>
                            <th>Ngày Sinh</th>
                            <th>Email</th>
                            <th>Số Điện Thoại</th>
                            <th>Trạng Thái</th>
                            <th>Hành Động</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="khachHang" items="${khachHangs}">
                            <tr>
                                <td>${khachHang.khachHangId}</td>
                                <td>${khachHang.hoTen}</td>
                                <td>${khachHang.gioiTinh ? 'Nam' : 'Nữ'}</td>
                                <td>${khachHang.ngaySinh}</td>
                                <td>${khachHang.email}</td>
                                <td>${khachHang.sdt}</td>
                                <td>${khachHang.trangThai ? 'Hoạt Động' : 'Không Hoạt Động'}</td>
                                <td>
                                    <a href="/khachhang/${khachHang.khachHangId}/toggle" class="btn btn-warning">
                                        ${khachHang.trangThai ? 'Đổi Sang Không Hoạt Động' : 'Đổi Sang Hoạt Động'}
                                    </a>
                                    <a href="/edit/${khachHang.khachHangId}" class="btn btn-primary">Chỉnh Sửa</a>
                                    <a href="/detail/${khachHang.khachHangId}" class="btn btn-primary">Chi tiết</a>

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
        <div class="col-md-3">
            <div class="right-content">
                <h2><c:if test="${khachHang.khachHangId == null }">Chi tiết Khách Hàng</c:if></h2>

                <form:form action="${pageContext.request.contextPath}/saveKhachHang" method="post" modelAttribute="khachHang">
                    <!-- Mã khách hàng (ẩn) -->
                    <form:hidden path="khachHangId" />

                    <div class="mb-3">
                        <form:label path="hoTen">Họ Tên:</form:label>
                        <form:input path="hoTen" class="form-control"  />
                          <td><form:errors path="hoTen" cssClass="error"/></td>
                    </div>
                    <div class="mb-3">
                        <form:label path="gioiTinh">Giới Tính:</form:label><br>
                        <form:radiobutton path="gioiTinh" value="true" class="form-check-input"  />
                        <form:label path="gioiTinh" class="form-check-label">Nam</form:label><br>
                        <form:radiobutton path="gioiTinh" value="false" class="form-check-input"  />
                        <form:label path="gioiTinh" class="form-check-label">Nữ</form:label>
                    </div>
                    <div class="mb-3">
                        <form:label path="ngaySinh">Ngày Sinh:</form:label>
                        <form:input path="ngaySinh" type="date" class="form-control" />
                    </div>




                   <form:label path="sdt">Số Điện Thoại:</form:label>
                    <form:input path="sdt" class="form-control" />
                        <form:errors path="sdt" cssClass="error" />
                    <div class="mb-3">
                        <form:label path="email">Email:</form:label>
                        <form:input path="email" class="form-control"  />
                    </div>
                    <div class="mb-3">
                        <form:label path="matKhau">Mật Khẩu:</form:label>
                        <form:password path="matKhau" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <form:label path="trangThai">Trạng Thái:</form:label>
                        <form:checkbox path="trangThai" class="form-check-input"  />
                    </div>
                    <c:if test="${khachHang.khachHangId == null}">
                        <button type="submit" class="btn btn-primary">Lưu Khách Hàng</button>
                    </c:if>
                      <c:if test="${khachHang.khachHangId != null}">
                       <button type="submit" class="btn btn-primary">Lưu Khách Hàng</button>
                      </c:if>

                     <div class="mb-3 mt-3">
                         <a href="${pageContext.request.contextPath}/khachhang" class="btn btn-secondary">Quay lại trang gốc</a>
                      </div>
                </form:form>

                 <!-- Hiển thị thông báo lỗi nếu có -->
                    <spring:hasBindErrors name="khachHang">
                        <div style="color: red;">
                            ${errorMessage}
                        </div>
                    </spring:hasBindErrors>
            </div>
        </div>
    </div>
</div>




<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
