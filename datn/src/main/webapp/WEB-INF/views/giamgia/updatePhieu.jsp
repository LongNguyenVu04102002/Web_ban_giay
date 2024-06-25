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
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
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
</head>
<body>
<div class="container">
    <div class="header-container">
        <h1>Thêm phiếu Giảm Giá</h1>
        <a href="/giamgia" style="color: white; font-weight: bold; text-decoration: none">
            <button class="add-button">Danh sách phiếu giảm giá</button>
        </a>
    </div>

    <form:form method="post" action="/giamgia/update" modelAttribute="phieuGiamGia">
        <div class="row">
            <!-- Cột thứ nhất -->
            <div class="col-md-6 form-column">
                <div class="form-group">
                    <label class="label" for="maGiamGia">Mã giảm giá</label>
                    <input
                            type="text"
                            class="input-field"
                            id="maGiamGia"
                            placeholder="Mã giảm giá..."
                            value="${phieuGiamGia.maGiamGia}"
                    />
                </div>
                <div class="form-group">
                    <label class="label" for="loaiPhieu">Loại Phiếu</label>
                    <select class="select-field" id="loaiPhieu" onchange="toggleFields()">
                        <option value="1">Tiền mặt</option>
                        <option value="2">Phần trăm</option>
                    </select>
                </div>
                <div class="form-group" id="phanTram">
                    <label class="label" for="phanTramGiam">Giá Trị Giảm</label>
                    <div class="input-with-unit" data-unit="%">
                        <input
                                type="text"
                                class="input-field"
                                id="phanTramGiam"
                                placeholder="Phần trăm giảm giá..."
                                value="${phieuGiamGia.phanTramGiam}"
                        />
                    </div>
                </div>
                <div class="form-group" id="tienMat">
                    <label class="label" for="tienGiam">Giá Trị Giảm</label>
                    <div class="input-with-unit" data-unit="VND">
                        <input
                                type="text"
                                class="input-field"
                                id="tienGiam"
                                placeholder="Số tiền giảm..."
                                value="${phieuGiamGia.tienGiam}"
                        />
                    </div>
                </div>
                <div class="form-group">
                    <label class="label" for="soLuongPhieu">Số lượng</label>
                    <input
                            type="text"
                            class="input-field"
                            id="soLuongPhieu"
                            placeholder="Số lượng phiếu..."
                            value="${phieuGiamGia.soLuongPhieu}"
                    />
                </div>
            </div>

            <!-- Cột thứ hai -->
            <div class="col-md-6 form-column">
                <div class="form-group">
                    <label class="label" for="giaTriDonToiThieu">Giá trị đơn tối thiểu</label>
                    <div class="input-with-unit" data-unit="VND">
                        <input
                                type="text"
                                class="input-field"
                                id="giaTriDonToiThieu"
                                placeholder="Giá trị đơn tối thiểu..."
                                value="${phieuGiamGia.giaTriDonToiThieu}"
                        />
                    </div>
                </div>
                <div class="form-group">
                    <label class="label" for="giaTriGiamToiDa">Giảm Tối Đa</label>
                    <div class="input-with-unit" data-unit="VND">
                        <input
                                type="text"
                                class="input-field"
                                id="giaTriGiamToiDa"
                                placeholder="Giảm tối đa..."
                                value="${phieuGiamGia.giaTriGiamToiDa}"
                        />
                    </div>
                </div>
                <div class="form-group">
                    <label class="label" for="ngayBatDau">Ngày Bắt Đầu</label>
                    <input type="date" class="input-field" id="ngayBatDau" value="${phieuGiamGia.ngayBatDau}"/>
                </div>
                <div class="form-group">
                    <label class="label" for="ngayKetThuc">Ngày Kết Thúc</label>
                    <input type="date" class="input-field" id="ngayKetThuc" value="${phieuGiamGia.ngayKetThuc}"/>
                </div>
            </div>
        </div>
        <button type="submit" class="button">Lưu</button>
    </form:form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
