<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách Địa Chỉ</title>
</head>
<body>
    <h1>Danh sách Địa Chỉ</h1>
    <a href="/create">Tạo Địa Chỉ mới</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Địa Chỉ Nhận</th>
            <th>Ngày Tạo</th>
            <th>SĐT</th>
            <th>Họ Tên</th>
            <th>Trạng Thái</th>
            <th>Xã</th>
            <th>Huyện</th>
            <th>Thành Phố</th>
            <th>Khách Hàng</th>
            <th>Hành Động</th>
        </tr>
        <c:forEach var="diaChi" items="${diaChiList}">
            <tr>
                <td>${diaChi.diaChiId}</td>
                <td>${diaChi.diaChiNhan}</td>
                <td>${diaChi.ngayTao}</td>
                <td>${diaChi.sdt}</td>
                <td>${diaChi.hoTen}</td>
                <td>${diaChi.trangThai}</td>
                <td>${diaChi.xa}</td>
                <td>${diaChi.huyen}</td>
                <td>${diaChi.thanhPho}</td>
                <td>${diaChi.khachHang.hoTen}</td>
                <td>
                    <a href="/diaChi/edit/${diaChi.diaChiId}">Sửa</a>
                    <a href="/diaChi/delete/${diaChi.diaChiId}">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
