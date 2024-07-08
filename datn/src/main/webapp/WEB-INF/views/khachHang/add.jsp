<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<%@ page pageEncoding="utf-8" %>
<jsp:useBean id="pageTitle" scope="page" class="java.lang.String" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.7.2/font/bootstrap-icons.min.css" rel="stylesheet">

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
        <h5>Thêm Khách Hàng</h5>
            <form:form action="${pageContext.request.contextPath}/saveKhachHang" modelAttribute="khachHang" method="post">
                <div class="row">
                    <!-- Customer Details Column -->
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label for="hoTen">Họ Tên:</label>
                            <form:input path="hoTen" id="hoTen" class="form-control" required="true" />
                            <form:errors path="hoTen" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label>Giới Tính:</label><br>
                            <div class="form-check form-check-inline">
                                <form:radiobutton path="gioiTinh" id="nam" value="true" class="form-check-input" />
                                <label class="form-check-label" for="nam">Nam</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <form:radiobutton path="gioiTinh" id="nu" value="false" class="form-check-input" />
                                <label class="form-check-label" for="nu">Nữ</label>
                            </div>
                            <form:errors path="gioiTinh" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="ngaySinh">Ngày Sinh:</label>
                            <form:input path="ngaySinh" type="date" id="ngaySinh" class="form-control" required="true" />
                            <form:errors path="ngaySinh" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="email">Email:</label>
                            <form:input path="email" type="email" id="email" class="form-control" required="true" />
                            <form:errors path="email" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="sdt">Số Điện Thoại:</label>
                            <form:input path="sdt" id="sdt" oninput="this.value = this.value.replace(/[^0-9]/g, '')" class="form-control" required="true" />
                            <form:errors path="sdt" cssClass="text-danger" />
                        </div>
                    </div>

                    <!-- Address Details Column -->
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label for="diaChiNhan">Địa Chỉ Nhận:</label>
                            <form:input path="diaChiList[0].diaChiNhan" id="diaChiNhan" class="form-control" required="true" />
                            <form:errors path="diaChiList[0].diaChiNhan" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="xa">Xã/Phường:</label>
                            <form:input path="diaChiList[0].xa" id="xa" class="form-control" required="true" />
                            <form:errors path="diaChiList[0].xa" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="huyen">Quận/Huyện:</label>
                            <form:input path="diaChiList[0].huyen" id="huyen" class="form-control" required="true" />
                            <form:errors path="diaChiList[0].huyen" cssClass="text-danger" />
                        </div>
                        <div class="mb-3">
                            <label for="thanhPho">Thành Phố/Tỉnh:</label>
                            <form:input path="diaChiList[0].thanhPho" id="thanhPho" class="form-control" required="true" />
                            <form:errors path="diaChiList[0].thanhPho" cssClass="text-danger" />
                        </div>
                    </div>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Thêm</button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
