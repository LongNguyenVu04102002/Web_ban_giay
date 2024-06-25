<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:useBean id="pageTitle" scope="page" class="java.lang.String" />
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <title>Phiếu Giảm Giá</title>
    <style>
        body {
            font-family: sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
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
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
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
            color:  #28a745; /* Màu chữ trắng */
        }
        .trangThai-sapdienra {
            color: #007bff; /* Màu chữ trắng */
        }
        .trangThai-ketthuc {
            color: #ffc107; /* Màu chữ đen */
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header-container">
        <h1>Phiếu Giảm Giá</h1>
        <a href="/giamgia/add" style="color: white; font-weight: bold; text-decoration: none">
            <button class="add-button">Thêm mới</button>
        </a>
    </div>
    <div class="filter-container">
        <div class="filter-group">
            <label for="search">Tìm kiếm:</label>
            <input type="text" id="search" placeholder="Tìm kiếm..." oninput="filterResults()" value="${search}" />
        </div>
        <div class="filter-group">
            <label for="type">Thể Loại:</label>
            <select id="type" onchange="filterResults()">
                <option value="all" ${type == 'all' ? 'selected' : ''}>Tất Cả</option>
                <option value="1" ${type == '1' ? 'selected' : ''}>Tiền mặt</option>
                <option value="2" ${type == '2' ? 'selected' : ''}>%</option>
            </select>
        </div>
    </div>
    <table class="table">
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
            <th>Hành Động</th>
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
                <td class="${listPhieu.trangThai == 'Đang diễn ra' ? 'trangThai-dangdienra' : (listPhieu.trangThai == 'Sắp diễn ra' ? 'trangThai-sapdienra' : 'trangThai-ketthuc')}">${listPhieu.trangThai}</td>
                <td>
                    <a href="/giamgia/detail/${listPhieu.phieuGiamGiaId}">
                        <i class="bi bi-pencil-square"></i>
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="text-center mt-3">
        <c:if test="${page.number > 0}">
            <a href="?page=${page.number}&search=${search}&type=${type}" class="btn btn-primary btn-sm" style="font-weight: bold"><<</a>
        </c:if>
        <span class="btn btn-primary btn-sm">${page.number + 1} / ${page.totalPages}</span>
        <c:if test="${page.number + 1 < page.totalPages}">
            <a href="?page=${page.number + 2}&search=${search}&type=${type}" class="btn btn-primary btn-sm" style="font-weight: bold">>></a>
        </c:if>
    </div>
</div>
<script>
    function filterResults() {
        const search = document.getElementById('search').value;
        const type = document.getElementById('type').value;
        window.location.href = `/giamgia?search=${search}&type=${type}&page=1`;
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
