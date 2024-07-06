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
            <form action="${pageContext.request.contextPath}/saveKhachHang" method="post">
                <div class="row">
                    <!-- Customer Details Column -->
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label for="hoTen">Họ Tên:</label>
                            <input type="text" id="hoTen" name="hoTen" class="form-control" required />
                        </div>
                        <div class="mb-3">
                            <label>Giới Tính:</label><br>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" id="nam" name="gioiTinh" value="true" required>
                                <label class="form-check-label" for="nam">Nam</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" id="nu" name="gioiTinh" value="false" required>
                                <label class="form-check-label" for="nu">Nữ</label>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="ngaySinh">Ngày Sinh:</label>
                            <input type="date" id="ngaySinh" name="ngaySinh" class="form-control" required />
                        </div>
                        <div class="mb-3">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" class="form-control" required />
                        </div>
                        <div class="mb-3">
                            <label for="sdt">Số Điện Thoại:</label>
                            <input type="text" id="sdt" oninput="this.value = this.value.replace(/[^0-9]/g, '')" name="sdt" class="form-control" required />
                        </div>
                    </div>

                    <!-- Address Details Column -->
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label for="diaChiNhan">Địa Chỉ Nhận:</label>
                            <input type="text" id="diaChiNhan" name="diaChiList[0].diaChiNhan" class="form-control" required />
                        </div>
                        <div class="mb-3">
                            <label for="xa">Xã/Phường:</label>
                            <input type="text" id="xa" name="diaChiList[0].xa" class="form-control" required />
                        </div>
                        <div class="mb-3">
                            <label for="huyen">Quận/Huyện:</label>
                            <input type="text" id="huyen" name="diaChiList[0].huyen" class="form-control" required />
                        </div>
                        <div class="mb-3">
                            <label for="thanhPho">Thành Phố/Tỉnh:</label>
                            <select id="thanhPho" name="diaChiList[0].thanhPho" class="form-control" required>
                                <option value="">Chọn thành phố/tỉnh</option>
                                <!-- Options will be dynamically added here -->
                            </select>
                        </div>
                    </div>
                </div>

                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Thêm</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        fetch('https://online-gateway.ghn.vn/shiip/public-api/master-data/province')
            .then(response => response.json())
            .then(data => {
                const selectElement = document.getElementById('thanhPho');
                data.data.forEach(province => {
                    const option = document.createElement('option');
                    option.value = province.ProvinceID;
                    option.textContent = province.ProvinceName;
                    selectElement.appendChild(option);
                });
            })
            .catch(error => console.error('Error fetching provinces:', error));
    });
</script>
