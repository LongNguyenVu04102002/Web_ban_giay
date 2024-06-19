<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="../layout/header.jsp" %>
<%@ page pageEncoding="utf-8" %>
<jsp:useBean id="pageTitle" scope="page" class="java.lang.String" />
<%
    pageTitle = "Danh sách Khách hàng";
%>

<div class="container">
    <div class="row">

        <div class="col-md-9 mt-5">
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
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </main>
        </div>

        <!-- Right Side (Form) -->
        <div class="col-md-3">
            <div class="right-content">
                <h2>Thêm Khách Hàng</h2>
                <form:form method="post" action="/khachhang" modelAttribute="khachHang">
                    <div class="mb-3">
                        <form:label path="hoTen">Họ Tên:</form:label>
                        <form:input path="hoTen" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <form:label path="gioiTinh">Giới Tính:</form:label><br>
                        <form:radiobutton path="gioiTinh" value="true" class="form-check-input" />
                        <form:label path="gioiTinh" class="form-check-label">Nam</form:label><br>
                        <form:radiobutton path="gioiTinh" value="false" class="form-check-input" />
                        <form:label path="gioiTinh" class="form-check-label">Nữ</form:label>
                    </div>
                    <div class="mb-3">
                        <form:label path="ngaySinh">Ngày Sinh:</form:label>
                        <form:input path="ngaySinh" type="date" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <form:label path="sdt">Số Điện Thoại:</form:label>
                        <form:input path="sdt" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <form:label path="email">Email:</form:label>
                        <form:input path="email" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <form:label path="matKhau">Mật Khẩu:</form:label>
                        <form:password path="matKhau" class="form-control" />
                    </div>
                    <div class="mb-3">
                        <form:label path="trangThai">Trạng Thái:</form:label>
                        <form:checkbox path="trangThai" class="form-check-input" />
                    </div>
                    <button type="submit" class="btn btn-primary">Thêm Khách Hàng</button>
                </form:form>
            </div>
        </
