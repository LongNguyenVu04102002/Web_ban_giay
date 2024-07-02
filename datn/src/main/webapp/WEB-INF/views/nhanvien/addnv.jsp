<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<%@ page pageEncoding="utf-8" %>
<jsp:useBean id="pageTitle" scope="page" class="java.lang.String" />
<%
    pageTitle = "Danh sách Nhân Viên";
%>

<style>
    input[type=checkbox]{
        height: 0;
        width: 0;
        visibility: hidden;
    }

    .label_checkbox {
        cursor: pointer;
        text-indent: -9999px;
        width: 70px;
        height: 36px;
        background: grey;
        display: block;
        border-radius: 100px;
        position: relative;
    }

    .label_checkbox:after {
        content: '';
        position: absolute;
        top: 5px;
        left: 5px;
        width: 25px;
        height: 25px;
        background: #fff;
        border-radius: 90px;
        transition: 0.3s;
    }

    input:checked + .label_checkbox {
        background: #bada55;
    }

    input:checked + label:after {
        left: calc(100% - 5px);
        transform: translateX(-100%);
    }

    .label_checkbox:active:after {
        width: 30px;
    }
</style>

<div class="container">
    <div class="row">

        <div class="col-md-9 mt-5">


            <div class="right-content">
                <h2>Thêm Nhân Viên</h2>
                <form:form method="post" action="/saveNhanVien" modelAttribute="nhanVien"
                           onsubmit="if(!confirm('thêm nhân viên?')){return false}">

                    <div class="mb-3">
                        <form:label path="hoTen">Họ Tên:</form:label>
                        <form:input path="hoTen" class="form-control" />
                        <form:errors path="hoTen" cssClass="text-danger" />
                    </div>
                    <div class="mb-3">
                        <form:label path="gioiTinh">Giới Tính:</form:label><br>
                        <form:radiobutton path="gioiTinh" value="true" class="form-check-input" />
                        <form:label path="gioiTinh" class="form-check-label">Nam</form:label><br>
                        <form:radiobutton path="gioiTinh" value="false" class="form-check-input" />
                        <form:label path="gioiTinh" class="form-check-label">Nữ</form:label>
                        <form:errors path="gioiTinh" cssClass="text-danger" />
                    </div>
                    <div class="mb-3">
                        <form:label path="ngaySinh">Ngày Sinh:</form:label>
                        <form:input path="ngaySinh" type="date" class="form-control" />
                        <form:errors path="ngaySinh" cssClass="text-danger" />
                    </div>
                    <div class="mb-3">
                        <form:label path="sdt">Số Điện Thoại:</form:label>
                        <form:input path="sdt" type="tel" pattern="[0-9]*" class="form-control" />
                        <form:errors path="sdt" cssClass="text-danger" />
                    </div>
                    <div class="mb-3">
                        <form:label path="email">Email:</form:label>
                        <form:input path="email" class="form-control" />
                        <form:errors path="email" cssClass="text-danger" />
                    </div>
                    <div class="mb-3">
                        <form:label path="matKhau">Mật Khẩu:</form:label>
                        <form:password path="matKhau" class="form-control" />
                        <form:errors path="matKhau" cssClass="text-danger" />
                    </div>
                    <div class="mb-3">
                        <form:label path="trangThai">Trạng Thái:</form:label>
                        <form:checkbox path="trangThai" class="form-check-input" />
                        <form:errors path="trangThai" cssClass="text-danger" />
                    </div>
                    <button type="submit" class="btn btn-primary">Thêm Nhân Viên</button>
                    <a href="/nhanvien" class="btn btn-secondary">Trở về danh sách nhân viên</a>

                </form:form>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
