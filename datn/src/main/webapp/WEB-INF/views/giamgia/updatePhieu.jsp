<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm Phiếu Giảm Giá</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        body {
            font-family: sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        .container {
            width: 90%;
            margin: 50px auto;
            background-color: #fff;
            padding: 30px;
            border-radius: 5px;
            /*box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);*/
        }

        .header-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .header-container h1 {
            margin-right: 20px;
        }

        .add-button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .form-group {
            margin-bottom: 20px;
            position: relative;
        }

        .label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        .text-danger{
            font-weight: lighter;
        }

        .input-field {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
        }

        .select-field {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            box-sizing: border-box;
            appearance: none;
            background-color: #fff;
        }

        .input-with-unit {
            position: relative;
        }

        .input-with-unit input {
            padding-right: 40px; /* Adjusted for the unit */
        }

        .input-with-unit::after {
            content: attr(data-unit);
            position: absolute;
            right: 10px;
            top: 50%;
            transform: translateY(-50%);
            color: #777;
        }

        .button {
            display: block;
            width: 150px;
            padding: 10px;
            background-color: #4caf50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin: 20px auto;
        }

        .button:hover {
            background-color: #45a049;
        }
    </style>
    <script>
        function toggleFields() {
            var loaiPhieu = document.getElementById("loaiPhieu").value;
            var tienGiamField = document.getElementById("tienMat");
            var phanTramGiamField = document.getElementById("phanTram");

            if (loaiPhieu == "1") {
                tienGiamField.style.display = "block";
                phanTramGiamField.style.display = "none";
            } else if (loaiPhieu == "2") {
                tienGiamField.style.display = "none";
                phanTramGiamField.style.display = "block";
            }
        }

        window.onload = toggleFields;
    </script>
    <script type="text/javascript">
        function confirmUpdate() {
            return confirm("Bạn có chắc chắn muốn cập nhật thông tin phiếu giảm giá này?");
        }
    </script>
</head>
<body>
<div class="container">
    <div class="header-container">
        <h1>Thêm phiếu Giảm Giá</h1>
        <a href="/giamgia" style="color: white; font-weight: bold; text-decoration: none">
            <button class="add-button"> <i class="bi bi-arrow-left"></i> Trở về</button>
        </a>
    </div>

    <form:form method="post" action="/giamgia/update/${phieuGiamGia.phieuGiamGiaId}" modelAttribute="phieuGiamGia" onsubmit="return confirmUpdate()">
        <div class="row">
            <!-- Cột thứ nhất -->
            <div class="col-md-6 form-column">
                <div class="form-group">
                    <label class="label" for="maGiamGia">Mã giảm giá</label>
                    <form:input path="maGiamGia" id="maGiamGia" class="input-field" placeholder="Mã giảm giá..."/>
                    <form:errors path="maGiamGia" cssClass="text-danger"/>
                </div>
                <div class="form-group">
                    <label class="label" for="loaiPhieu">Loại Phiếu</label>
                    <form:select path="loaiPhieu" id="loaiPhieu" class="select-field" onchange="toggleFields()">
                        <form:option value="1">Tiền mặt</form:option>
                        <form:option value="2">Phần trăm</form:option>
                    </form:select>
                    <form:errors path="loaiPhieu" cssClass="text-danger"/>
                </div>
                <div class="form-group" id="phanTram">
                    <label class="label" for="phanTramGiam">
                        Giá Trị Giảm: <form:errors path="phanTramGiam" cssClass="text-danger"/>
                    </label>
                    <div class="input-with-unit" data-unit="%">
                        <form:input path="phanTramGiam" id="phanTramGiam" class="input-field" placeholder="Phần trăm giảm giá..." oninput="this.value = this.value.replace(/[^0-9]/g, '')" />
                    </div>

                </div>
                <div class="form-group" id="tienMat">
                    <label class="label" for="tienGiam">
                        Giá Trị Giảm: <form:errors path="tienGiam" cssClass="text-danger"/>
                    </label>
                    <div class="input-with-unit" data-unit="VND">
                        <form:input path="tienGiam" id="tienGiam" class="input-field" placeholder="Số tiền giảm..." oninput="this.value = this.value.replace(/[^0-9]/g, '')" />
                    </div>

                </div>
                <div class="form-group">
                    <label class="label" for="soLuongPhieu">Số lượng: <form:errors path="soLuongPhieu" cssClass="text-danger"/></label>
                    <form:input path="soLuongPhieu" id="soLuongPhieu" class="input-field" placeholder="Số lượng phiếu..." oninput="this.value = this.value.replace(/[^0-9]/g, '')" />

                </div>
            </div>

            <!-- Cột thứ hai -->
            <div class="col-md-6 form-column">
                <div class="form-group">
                    <label class="label" for="giaTriDonToiThieu">
                        Giá trị đơn tối thiểu: <form:errors path="giaTriDonToiThieu" cssClass="text-danger"/>
                    </label>
                    <div class="input-with-unit" data-unit="VND">
                        <form:input path="giaTriDonToiThieu" id="giaTriDonToiThieu" class="input-field" placeholder="Giá trị đơn tối thiểu..." oninput="this.value = this.value.replace(/[^0-9]/g, '')" />
                    </div>

                </div>
                <div class="form-group">
                    <label class="label" for="giaTriGiamToiDa">
                        Giảm Tối Đa: <form:errors path="giaTriGiamToiDa" cssClass="text-danger"/>
                    </label>
                    <div class="input-with-unit" data-unit="VND">
                        <form:input path="giaTriGiamToiDa" id="giaTriGiamToiDa" class="input-field" placeholder="Giảm tối đa..." oninput="this.value = this.value.replace(/[^0-9]/g, '')" />
                    </div>

                </div>
                <div class="form-group">
                    <label class="label" for="ngayBatDau">
                        Ngày Bắt Đầu: <form:errors path="ngayBatDau" cssClass="text-danger"/>
                    </label>
                    <form:input path="ngayBatDau" id="ngayBatDau" class="input-field" type="date"/>

                </div>
                <div class="form-group">
                    <label class="label" for="ngayKetThuc">
                        Ngày Kết Thúc: <form:errors path="ngayKetThuc" cssClass="text-danger"/></label>
                    <form:input path="ngayKetThuc" id="ngayKetThuc" class="input-field" type="date"/>

                </div>
            </div>
        </div>
        <button type="submit" class="button">Lưu</button>
    </form:form>

</div>
</body>
</html>
