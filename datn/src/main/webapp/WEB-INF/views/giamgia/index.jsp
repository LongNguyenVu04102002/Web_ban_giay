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
            box-shadow: 0 4px 8px rgba( 0, 0, 0, 1);
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
            margin-bottom: 20px;
            margin-top: 30px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
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
            align-items: center;
            margin-bottom: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
        }

        .filter-group {
            display: flex;
            align-items: center;
        }

        .filter-group label {
            margin-right: 10px;
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

        td {
            height: 70px;
            text-align: center;
        }

        .status-button {
            padding: 5px 10px;
            background-color: #f5a742;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 5px;
        }

        .edit-button {
            padding: 5px 10px;
            background-color: #198754;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .trangThai-dangdienra {
            color: #42cd60; /* Màu chữ */
        }

        /* Màu vàng cho Sắp diễn ra */
        .trangThai-sapdienra {
            color: #cbab4f; /* Màu chữ */
        }

        /* Màu đỏ cho Kết thúc */
        .trangThai-ketthuc {
            color: #cf4857; /* Màu chữ */

        }

        .alert {
            padding: 15px;
            margin-bottom: 20px;
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
            })
        };
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

        window.onload = function() {
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


    </script>
</head>
<body>
<div>
    <c:if test="${not empty successMessage}">
        <div id="success-alert" class="alert alert-success alert-fixed">
                ${successMessage}
        </div>
    </c:if>
    <div class="header-container">
        <h1 style="margin-left: 30px;">
            Phiếu Giảm Giá
        </h1>
        <div class="filter-group" style="margin-left: 300px">
            <label for="search">Tìm kiếm:</label>
            <input type="text" id="search" placeholder="Tìm kiếm..."/>
        </div>
        <a href="/giamgia/add" style="color: white; font-weight: bold; text-decoration: none; margin-right: 30px">
            <button class="add-button">Thêm mới</button>
        </a>

    </div>
    <div class="filter-container">

        <div class="filter-group" style="margin-left: 30px">
            <form id="filterToiThieu" action="/giamgia/searchGiaTriDonToiThieu" method="get">
                <label for="toiThieu">Giá trị đơn tối thiểu:</label>
                <select id="toiThieu" name="toiThieu" onchange="submitfrom()">
                    <option value="all" ${status == 'all' ? 'selected' : ''}>Tất Cả</option>
                    <option value="Đang diễn ra" ${status == 'Đang diễn ra' ? 'selected' : ''}>0 - 500.000</option>
                    <option value="Sắp diễn ra" ${status == 'Sắp diễn ra' ? 'selected' : ''}>500.000 - 5.000.000</option>
                    <option value="Kết thúc" ${status == 'Kết thúcKết thúc' ? 'selected' : ''}> > 5.000.000  </option>
                </select>
            </form>
        </div>
        <div class="filter-group">
            <form id="filter-Form" action="/giamgia/searchTrangThai" method="get">
                <label for="status">Trạng Thái:</label>
                <select id="status" name="status" onchange="submitfrom()">
                    <option value="all" ${status == 'all' ? 'selected' : ''}>Tất Cả</option>
                    <option value="Đang diễn ra" ${status == 'Đang diễn ra' ? 'selected' : ''}>Đang diễn ra</option>
                    <option value="Sắp diễn ra" ${status == 'Sắp diễn ra' ? 'selected' : ''}>Sắp diễn ra</option>
                    <option value="Kết thúc" ${status == 'Kết thúcKết thúc' ? 'selected' : ''}>Kết thúc</option>
                </select>
            </form>
        </div>
        <div class="filter-group" style="margin-right: 30px">
            <form id="filterForm" action="/giamgia/searchLoaiPhieu" method="get">
                <label for="type">Loại phiếu:</label>
                <select id="type" name="type" onchange="submitForm()">
                    <option value="0" ${type == '0' ? 'selected' : ''}>Tất Cả</option>
                    <option value="1" ${type == '1' ? 'selected' : ''}>Tiền mặt</option>
                    <option value="2" ${type == '2' ? 'selected' : ''}>%</option>
                </select>
            </form>
        </div>
    </div>
    <table class="table" style="margin-top: 60px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);">
        <thead class="table-light">
        <tr>
            <th>#</th>
            <th>Mã giảm giá</th>
            <th>Loại phiếu</th>
            <th>Giá trị giảm</th>
            <th>Số lượng</th>
            <th>Giá trị đơn tối thiểu</th>
            <th>Giá trị giảm tối đa</th>
            <th>Ngày bắt đầu</th>
            <th>Ngày kết thúc</th>
            <th>Trạng thái</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="listPhieu" items="${page.content}" varStatus="loop">
            <tr>
                <td>${loop.index + 1}</td>
                <td>${listPhieu.maGiamGia}</td>
                <td>
                    <c:choose>
                        <c:when test="${listPhieu.loaiPhieu == 1}">Tiền mặt</c:when>
                        <c:when test="${listPhieu.loaiPhieu == 2}">%</c:when>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${listPhieu.loaiPhieu == 1}">
                            <fmt:formatNumber value="${listPhieu.tienGiam}" type="currency"/>
                        </c:when>
                        <c:when test="${listPhieu.loaiPhieu == 2}">
                            ${listPhieu.phanTramGiam}%
                        </c:when>
                    </c:choose>
                </td>
                <td>${listPhieu.soLuongPhieu}</td>
                <td><fmt:formatNumber value="${listPhieu.giaTriDonToiThieu}" type="currency"/></td>
                <td><fmt:formatNumber value="${listPhieu.giaTriGiamToiDa}" type="currency"/></td>
                <td>${listPhieu.ngayBatDau}</td>
                <td>${listPhieu.ngayKetThuc}</td>
                <td class="
                    ${listPhieu.trangThai == 'Đang diễn ra' ? 'trangThai-dangdienra' :
                    (listPhieu.trangThai == 'Sắp diễn ra' ? 'trangThai-sapdienra' :
                    (listPhieu.trangThai == 'Kết thúc' ? 'trangThai-ketthuc' : ''))}
                ">${listPhieu.trangThai}</td>

                <td>
                    <a href="/giamgia/detail/${listPhieu.phieuGiamGiaId}">
                        <i class="bi bi-pencil-square"></i>
                    </a>
                    <c:if test="${listPhieu.trangThai == 'Đang diễn ra'}">
                        <div class="form-check form-switch">
                            <input class="form-check-input" type="checkbox" role="switch"
                                   id="flexSwitchCheckDefault${listPhieu.phieuGiamGiaId}"
                                   onchange="updateTrangThai(${listPhieu.phieuGiamGiaId}, this.checked)"
                                   <c:if test="${listPhieu.trangThai == 'Đang diễn ra'}">checked</c:if>
                            >
                        </div>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="text-left mt-3" style="text-align: left"> <!-- Sử dụng class 'text-left' thay vì 'text-center' -->
        <c:if test="${page.number > 0}">
            <a href="?page=${page.number}&type=${type}&status=${status}" class="btn btn-primary btn-sm" style="font-weight: bold"><<</a>
        </c:if>
        <span class="btn btn-primary btn-sm">${page.number + 1} / ${page.totalPages}</span>
        <c:if test="${page.number + 1 < page.totalPages}">
            <a href="?page=${page.number + 2}&type=${type}&status=${status}" class="btn btn-primary btn-sm" style="font-weight: bold">>></a>
        </c:if>
    </div>



</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>
