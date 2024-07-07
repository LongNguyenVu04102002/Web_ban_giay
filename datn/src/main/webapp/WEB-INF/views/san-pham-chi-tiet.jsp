<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>SP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <style>
        .content {
            padding: 20px;
        }

        .form-card {
            margin-bottom: 20px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
            background-color: #f8f9fa;
        }
    </style>
    <script>
        function confirmAction(message) {
            return confirm(message);
        }
    </script>
</head>
<body>
<div class="content">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <table class="table">
                    <thead>
                    <tr>
                        <td scope="col">#</td>
                        <td scope="col">Mã</td>
                        <td scope="col">Sản phẩm</td>
                        <td scope="col">Kích thước</td>
                        <td scope="col">Màu sắc</td>
                        <td scope="col">Đợt giảm</td>
                        <td scope="col">Số lượng</td>
                        <td scope="col">Giá bán</td>
                        <td scope="col">Trạng thái</td>
                        <td scope="col">Chức năng</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${lst.content}" var="x" varStatus="vTri">
                        <tr>
                            <td scope="row">${vTri.index + 1}</td>
                            <td>${x.barCode}</td>
                            <td>${x.sanPham.ten}</td>
                            <td>${x.kichThuoc.ten}</td>
                            <td>${x.mauSac.ten}</td>
                            <td>${x.dotGiamGia.ten}</td>
                            <td>${x.soLuong}</td>
                            <td><fmt:formatNumber value="${x.giaBan}" type="number"
                                                  maxFractionDigits="0"></fmt:formatNumber></td>
                            <td>${x.trangThai == true ? "Đang sử dụng" : "Ngừng sử dụng"}</td>
                            <td>
                                <a href="/san-pham-chi-tiet/detail/${x.sanPhamChiTietId}"
                                   style="text-decoration: none; color: white">
                                    <button class="btn btn-info"><i
                                            class="bi bi-search"></i>
                                    </button>
                                </a>
                                <a href="/san-pham-chi-tiet/remove/${x.sanPhamChiTietId}"
                                   style="text-decoration: none; color: white" onclick="return confirmAction('Bạn có chắc chắn muốn xóa?')">
                                    <button class="btn btn-danger"><i
                                            class="bi bi-trash"></i>
                                    </button>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:if test="${lst.number != 0}">
                            <li class="page-item">
                                <a class="page-link" href="?p=${lst.number - 1}">←</a>
                            </li>
                        </c:if>

                        <c:forEach begin="0" end="${lst.totalPages > 1 ? lst.totalPages - 1 : 0}" var="i">
                            <li class="page-item <c:if test='${i == lst.number}'>active</c:if>">
                                <a class="page-link" href="?p=${i}">${i + 1}</a>
                            </li>
                        </c:forEach>

                        <c:if test="${lst.number != lst.totalPages - 1}">
                            <li class="page-item">
                                <a class="page-link" href="?p=${lst.number + 1}">→</a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </div>
            <div class="col-md-3">
                <div class="form-card">
                    <form:form action="/san-pham-chi-tiet/add" method="post" modelAttribute="spct">
                        <form:input path="sanPhamChiTietId" cssStyle="display: none"></form:input>

                        <div class="mb-3">
                            <label class="form-label">Mã</label>
                            <form:input path="barCode" cssClass="form-control"></form:input>
                            <form:errors path="barCode" cssClass="form-text" cssStyle="color: red"></form:errors>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Sản phẩm</label>
                            <form:select path="sanPham.sanPhamId" cssClass="form-select"
                                         aria-label="Default select example">
                                <c:forEach items="${lstSanPham}" var="x">
                                    <form:option value="${x.sanPhamId}">${x.ten}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Kích thước</label>
                            <form:select path="kichThuoc.kichThuocId" cssClass="form-select"
                                         aria-label="Default select example">
                                <c:forEach items="${lstKichThuoc}" var="x">
                                    <form:option value="${x.kichThuocId}">${x.ten}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Màu sắc</label>
                            <form:select path="mauSac.mauSacId" cssClass="form-select"
                                         aria-label="Default select example">
                                <c:forEach items="${lstMauSac}" var="x">
                                    <form:option value="${x.mauSacId}">${x.ten}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Đợt giảm giá</label>
                            <form:select path="dotGiamGia.dotGiamGiaId" cssClass="form-select"
                                         aria-label="Default select example">
                                <c:forEach items="${lstDotGiamGia}" var="x">
                                    <form:option value="${x.dotGiamGiaId}">${x.ten}</form:option>
                                </c:forEach>
                            </form:select>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Số lượng</label>
                            <form:input path="soLuong" cssClass="form-control"></form:input>
                            <form:errors path="soLuong" cssClass="form-text" cssStyle="color: red"></form:errors>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Giá bán</label>
                            <form:input path="giaBan" cssClass="form-control"></form:input>
                            <form:errors path="giaBan" cssClass="form-text" cssStyle="color: red"></form:errors>
                        </div>

                        <div class="mb-3">
                            <label class="form-label">Trạng thái</label>
                            <br>
                            <form:radiobutton cssClass="form-check-input" path="trangThai" value="true"
                                              label="Sử dụng"></form:radiobutton>
                            <br>
                            <form:radiobutton cssClass="form-check-input" path="trangThai" value="false"
                                              label="Ngừng sử dụng"></form:radiobutton>
                        </div>

                        <form:button type="submit" class="btn btn-success" onclick="return confirmAction('Bạn có chắc chắn muốn thêm mới?')"><i
                                class="bi bi-plus-circle"></i></form:button>
                        <button type="submit" formaction="/san-pham-chi-tiet/update/${id}" class="btn btn-warning" onclick="return confirmAction('Bạn có chắc chắn muốn sửa?')">
                            <i
                                    class="bi bi-wrench"></i>
                        </button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
