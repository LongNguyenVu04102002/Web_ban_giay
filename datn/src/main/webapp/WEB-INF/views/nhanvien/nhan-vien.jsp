<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <%@ page pageEncoding="utf-8" %>

    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>

    input[type=checkbox] {
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

        input:checked + .label_checkbox:after {
            left: calc(100% - 5px);
            transform: translateX(-100%);
        }

        .label_checkbox:active:after {
            width: 30px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="row">
        <main>
            <h2>Danh sách Nhân Viên</h2>
            <form method="get" action="/searchNhanVien">
                <div class="row mb-3">
                    <div class="col-md-4">
                        <input type="text" name="sdt" class="form-control" placeholder="Tìm kiếm theo số điện thoại" value="${param.sdt}">
                    </div>
                    <div class="col-md-3">
                        <select name="gender" class="form-select">
                            <option value="">Chọn giới tính</option>
                            <option value="true" ${param.gender == 'true' ? 'selected' : ''}>Nam</option>
                            <option value="false" ${param.gender == 'false' ? 'selected' : ''}>Nữ</option>
                        </select>
                    </div>
                    <div class="col-md-3">
                        <button type="submit" class="btn btn-primary">Tìm kiếm</button>
                    </div>
                </div>
            </form>


            <table class="table table-hover table-bordered">
                <thead>
                <tr>
                    <th class="text-center fw-medium">STT</th>
                    <th class="text-center fw-medium">Tên</th>
                    <th class="text-center fw-medium">Giới Tính</th>
                    <th class="text-center fw-medium">Ngày Sinh</th>
                    <th class="text-center fw-medium">Email</th>
                    <th class="text-center fw-medium">Số Điện Thoại</th>
                    <th class="text-center fw-medium">Trạng Thái</th>
                    <th class="text-center fw-medium">Hành Động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="nhanVien" items="${nhanviens}" varStatus="status">
                    <tr>
                        <td>${status.index + 1}</td>
                        <td>${nhanVien.hoTen}</td>
                        <td>${nhanVien.gioiTinh ? 'Nam' : 'Nữ'}</td>
                        <td>${nhanVien.ngaySinh}</td>
                        <td>${nhanVien.email}</td>
                        <td>${nhanVien.sdt}</td>
<%--                        <td>--%>
<%--                            <input type="checkbox" class="toggle-switch" id="switch${status.index}" data-id="${nhanVien.nhanVienId}" ${nhanVien.trangThai ? 'checked' : ''} />--%>
<%--                            <label class="label_checkbox" for="switch${status.index}">Toggle</label>--%>
<%--                        </td>--%>
                        <td>
                            <a href="/updateStatus?nhanVienId=${nhanVien.nhanVienId}&trangThai=${!nhanVien.trangThai}" class="btn ${nhanVien.trangThai ? 'btn-success' : 'btn-secondary'} btn-sm"  onclick="return confirm('Bạn có chắc chắn muốn đổi không?')">
                                    ${nhanVien.trangThai ? 'Kích hoạt' : 'Tắt'}
                            </a>
                        </td>



                        <td>
                            <a href="/editNhanVien/${nhanVien.nhanVienId}" class="btn btn-warning btn-sm">Sửa</a>
                            <a href="/deleteNhanVien/${nhanVien.nhanVienId}" class="btn btn-danger btn-sm" onclick="return confirm('Bạn có chắc chắn muốn xóa không?')">Xóa</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <a href="/addNhanVien" class="btn btn-danger btn-sm">Thêm nhân viên mới</a>

            <!-- Phân trang -->
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <c:if test="${currentPage > 1}">
                        <li class="page-item"><a class="page-link" href="?page=1&size=${size}">First</a></li>
                        <li class="page-item"><a class="page-link" href="?page=${currentPage - 1}&size=${size}">Previous</a></li>
                    </c:if>
                    <c:forEach begin="1" end="${totalPages}" varStatus="page">
                        <li class="page-item ${currentPage == page.index + 1 ? 'active' : ''}">
                            <a class="page-link" href="?page=${page.index + 1}&size=${size}">${page.index + 1}</a>
                        </li>
                    </c:forEach>
                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item"><a class="page-link" href="?page=${currentPage + 1}&size=${size}">Next</a></li>
                        <li class="page-item"><a class="page-link" href="?page=${totalPages}&size=${size}">Last</a></li>
                    </c:if>
                </ul>
            </nav>
        </main>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script>
    function updateStatus(nhanVienId, trangThai) {
        fetch(`/updateStatus?nhanVienId=${nhanVienId}&trangThai=${trangThai}`, {
            method: 'GET'
        }).then(response => {
            if (response.ok) {
                // Nếu phản hồi thành công, thông báo trạng thái đã được cập nhật
                console.log('Trạng thái đã được cập nhật');
            } else {
                alert('Cập nhật trạng thái thất bại!');
            }
        }).catch(error => {
            console.error('Lỗi:', error);
            alert('Có lỗi xảy ra!');
        });
    }
</script>


</body>
</html>
