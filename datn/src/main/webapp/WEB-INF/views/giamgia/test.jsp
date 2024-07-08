<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="pageTitle" scope="page" class="java.lang.String"/>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <title>Phiếu Giảm Giá</title>
    <style>
        body {
            font-family: sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
            border: 5px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 1);
        }

        .container {
            width: 90%;
            margin: 0 auto;
            padding: 20px;
        }

        .header-container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            padding: 20px;
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

        .filter-container {
            height: 70px;
            display: flex;
            justify-content: space-between;
            margin-top: 20px;
            margin-bottom: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .filter-group {
            display: flex;
            align-items: center;
        }

        .filter-group label {
            margin-right: 5px;
        }

        .filter-group input {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .filter-group select {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-outline {
            position: relative;
        }

        .form-outline label {
            position: absolute;
            top: -10px;
            /* Điều chỉnh khoảng cách từ trên xuống */
            left: 10px;
            /* Điều chỉnh khoảng cách từ trái sang */
            padding: 0 5px;
            background-color: white;
            color: gray;
            font-size: 12px;
        }

        td {
            height: 70px;
            text-align: center;
        }

        .status-dang-dien-ra {
            color: #FF5733; /* Màu cho trạng thái Đang diễn ra */
        }

        .status-sap-dien-ra {
            color: #FFC300; /* Màu cho trạng thái Sắp diễn ra */
        }

        .status-ket-thuc {
            color: #27AE60; /* Màu cho trạng thái Kết thúc */
        }

        .alert {
            padding: 15px;
            /*margin-bottom: 20px;*/
            margin-top: 40px;
            margin-left: 50px;
            border: 1px solid transparent;
            border-radius: 4px;
        }

        .alert-success {
            color: #3c763d;
            background-color: #dff0d8;
            border-color: #d6e9c6;
        }

        .alert-fixed {
            position: fixed;
            left: 53%;
            transform: translate(-50%, -50%);
            z-index: 9999;
        }
    </style>
    <script>
        function submitForm() {
            document.getElementById("filterForm").submit();
        }

        function submitfrom() {
            document.getElementById("filter-Form").submit();
        }

        function submitformToiThieu() {
            document.getElementById("filterToiThieu").submit();
        }

        function updateTrangThai(phieuGiamGiaId, isChecked) {
            var url = "/giamgia/updateTrangThai/" + phieuGiamGiaId;

            $.ajax({
                type: "POST",
                url: url,
                data: {checked: isChecked},
                success: function (response) {
                    console.log("Trạng thái đã được cập nhật.");
                    // Có thể cập nhật giao diện tại đây nếu cần
                },
                error: function () {
                    alert("Đã xảy ra lỗi trong quá trình cập nhật trạng thái.");
                }
            });
        }

        function showSuccessMessage(message) {
            var successAlert = document.createElement("div");
            successAlert.className = "alert alert-success alert-fixed";
            successAlert.textContent = message;
            document.body.appendChild(successAlert);

            setTimeout(function () {
                successAlert.style.opacity = "0";
                setTimeout(function () {
                    document.body.removeChild(successAlert);
                }, 600);
            }, 3000);
        }

        window.onload = function () {
            if (document.getElementById("success-alert")) {
                setTimeout(function () {
                    var alert = document.getElementById("success-alert");
                    alert.style.opacity = "0";
                    setTimeout(function () {
                        alert.parentNode.removeChild(alert);
                    }, 600);
                }, 3000);
            }
        };

        function getStatusClass(trangThai) {
            switch (trangThai) {
                case 'Đang diễn ra':
                    return 'status-dang-dien-ra';
                case 'Sắp diễn ra':
                    return 'status-sap-dien-ra';
                case 'Kết thúc':
                    return 'status-ket-thuc';
                default:
                    return '';
            }
        }

        function checkAndSubmit() {
            var from = document.getElementById("from").value;
            var to = document.getElementById("to").value;

            if (from && to) {
                document.getElementById("filterNgay").submit();
            }
        }
    </script>
</head>
<body>
<div>
    <div class="header-container">
        <c:if test="${not empty successMessage}">
            <div id="success-alert" class="alert alert-success alert-fixed">
                    ${successMessage}
            </div>
        </c:if>
        <h1>
            <a href="/giamgia" style="color: black; text-decoration: none">
                Phiếu Giảm Giá
            </a>
        </h1>
        <a href="/giamgia/add">
            <button class="add-button"><i class="bi bi-plus-lg"></i> Thêm mới</button>
        </a>
    </div>
    <div class="filter-container">
        <div class="filter-group" style="margin-left: 30px">
            <form id="filterNgay" action="/giamgia/searchByDateRange" method="get"
                  style="display: flex; align-items: center; gap: 10px">
                <div class="form-outline datetimepicker">
                    <input
                            type="date"
                            class="form-control text-dark"
                            id="from"
                            name="from"
                            value="${param.from}"
                            onchange="checkAndSubmit()"
                            style="
                    height: 40px;
                    width: 160px;
                    font-size: 13px;
                    text-align: center;
                    padding-top: 15px;
                "
                    />
                    <label
                            for="from"
                            class="text-dark"
                            style="
                    font-size: 17px;
                    border-radius: 5px 5px 0px 0px;
                "
                    >Từ ngày</label>
                </div>
                <div class="form-outline datetimepicker" style="display: flex; align-items: center; gap: 10px;">
                    <div>
                        <input
                                type="date"
                                class="form-control text-dark"
                                id="to"
                                name="to"
                                value="${param.to}"
                                onchange="checkAndSubmit()"
                                style="height: 40px;
                                        width: 160px;
                                        font-size: 13px;
                                        text-align: center;
                                        padding-top: 15px;"/>
                        <label
                                for="to"
                                class="text-dark"
                                style="font-size: 17px; border-radius: 5px 5px 0px 0px;">Đến
                            ngày</label>
                    </div>
                </div>
            </form>
        </div>
        <div class="filter-group">
            <form id="filterForm" action="/giamgia/search" method="get"
                  style="display: flex; align-items: center; gap: 10px; margin-left: 5px">
                <label for="keyword">Tìm kiếm:</label>
                <input type="text" id="keyword" name="keyword" value="${param.keyword}"
                       placeholder="Nhập từ khóa tìm kiếm"/>
                <button type="submit" class="btn btn-primary" style="height: 40px" onclick="submitForm()">Tìm</button>
            </form>
        </div>
        <div class="filter-group">
            <form id="filter-Form" action="/giamgia/searchByStatus" method="get"
                  style="display: flex; align-items: center; gap: 10px;">
                <label for="trangThai">Trạng thái:</label>
                <select id="trangThai" name="trangThai" onchange="submitfrom()" style="height: 40px">
                    <option value="">Tất cả</option>
                    <option value="Đang diễn ra" <c:if test="${param.trangThai == 'Đang diễn ra'}">selected</c:if>>Đang diễn ra</option>
                    <option value="Sắp diễn ra" <c:if test="${param.trangThai == 'Sắp diễn ra'}">selected</c:if>>Sắp diễn ra</option>
                    <option value="Kết thúc" <c:if test="${param.trangThai == 'Kết thúc'}">selected</c:if>>Kết thúc</option>
                </select>
            </form>
        </div>
        <div class="filter-group">
            <form id="filterToiThieu" action="/giamgia/searchByToiThieu" method="get"
                  style="display: flex; align-items: center; gap: 10px;">
                <label for="toiThieu">Số lượng tối thiểu:</label>
                <input type="number" id="toiThieu" name="toiThieu" value="${param.toiThieu}" min="0" max="100"
                       placeholder="Nhập số lượng" style="height: 40px; width: 100px">
                <button type="submit" class="btn btn-primary" style="height: 40px" onclick="submitformToiThieu()">Lọc
                </button>
            </form>
        </div>
    </div>

    <div class="container">
        <table class="table table-bordered table-hover table-responsive">
            <thead>
            <tr>
                <th>STT</th>
                <th>Mã giảm giá</th>
                <th>Loại mã</th>
                <th>Số lượng</th>
                <th>Trạng thái</th>
                <th>Ngày tạo</th>
                <th>Ngày bắt đầu</th>
                <th>Ngày kết thúc</th>
                <th>Giá trị</th>
                <th>Hành động</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="voucher" items="${vouchers}" varStatus="status">
                <tr>
                    <td>${status.index + 1}</td>
                    <td>${voucher.maGiamGia}</td>
                    <td>${voucher.loaiMa}</td>
                    <td>${voucher.soLuong}</td>
                    <td class="${getStatusClass(voucher.trangThai)}">${voucher.trangThai}</td>
                    <td>
                        <fmt:formatDate value="${voucher.ngayTao}" pattern="dd/MM/yyyy"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${voucher.ngayBatDau}" pattern="dd/MM/yyyy"/>
                    </td>
                    <td>
                        <fmt:formatDate value="${voucher.ngayKetThuc}" pattern="dd/MM/yyyy"/>
                    </td>
                    <td>${voucher.giaTri}</td>
                    <td>
                        <div style="display: flex; align-items: center; justify-content: center; gap: 10px;">
                            <div class="form-check form-switch">
                                <input
                                        class="form-check-input"
                                        type="checkbox"
                                        id="trangThaiSwitch-${voucher.id}"
                                    ${voucher.trangThai == 'Đang diễn ra' ? 'checked' : ''}
                                        onchange="updateTrangThai(${voucher.id}, this.checked)"
                                />
                            </div>
                            <a href="/giamgia/edit/${voucher.id}" class="text-decoration-none">
                                <i class="bi bi-pencil-square"></i>
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"
        integrity="sha256-oP8xCET5kylHkdDdX+vtIKUlv68tMX9CkG1ann0qUWs=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-aK4m9v2VzGUS4/NOKGCtjvSZ5WJ4pw1TQAg3YYZ6Op3K3OOZgq5RS9M6n43WJgQF" crossorigin="anonymous"></script>
</body>
</html>
