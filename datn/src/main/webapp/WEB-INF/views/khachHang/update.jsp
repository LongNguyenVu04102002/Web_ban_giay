<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<%@ page pageEncoding="utf-8" %>
<jsp:useBean id="pageTitle" scope="page" class="java.lang.String" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.7.2/font/bootstrap-icons.min.css" rel="stylesheet">

<style>
    .address-table {
        margin-top: 20px;
    }

    .form-container {
        background-color: #f8f9fa;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
    }

    .form-container h5 {
        margin-bottom: 20px;
    }
</style>

<div class="container">

<div class="d-flex justify-content-end" style="margin-right: 10px;">
    <a href="/khachhang" class="btn btn-outline-success">
        <i class="bi bi-arrow-left"></i> Quay Lại
    </a>
</div>


    <div class="row justify-content-center">
        <div class="col-md-8">
          <div class="container">
              <div class="row justify-content-center">
                  <div class="col-md-8">
                      <div class="form-container">
                          <h5 class="mb-4">Cập Nhật Khách Hàng</h5>
                          <form action="${pageContext.request.contextPath}/updateKhachHang/${khachHang.khachHangId}" method="post">
                              <input type="hidden" name="khachHangId" value="${khachHang.khachHangId}" />
                              <input type="hidden" name="currentPage" value="${currentPage}" />
                              <input type="hidden" name="pageSize" value="${pageSize}" />
                              <!-- Các trường thông tin khách hàng -->
                              <div class="mb-3">
                                  <label for="hoTen">Họ Tên:</label>
                                  <input type="text" id="hoTen" name="hoTen" class="form-control" value="${khachHang.hoTen}" required />
                              </div>
                           <div class="mb-3">
                               <label for="gioiTinh">Giới Tính:</label><br>
                               <div class="form-check form-check-inline">
                                   <input class="form-check-input" type="radio" id="nam" name="gioiTinh" value="true" ${khachHang.gioiTinh ? 'checked' : ''}>
                                   <label class="form-check-label" for="nam">Nam</label>
                               </div>
                               <div class="form-check form-check-inline">
                                   <input class="form-check-input" type="radio" id="nu" name="gioiTinh" value="false" ${!khachHang.gioiTinh ? 'checked' : ''}>
                                   <label class="form-check-label" for="nu">Nữ</label>
                               </div>
                           </div>

                              <div class="mb-3">
                                  <label for="ngaySinh">Ngày Sinh:</label>
                                  <input type="date" id="ngaySinh" name="ngaySinh" class="form-control" value="${khachHang.ngaySinh}" required />
                              </div>
                              <div class="mb-3">
                                  <label for="email">Email:</label>
                                  <input type="email" id="email" name="email" class="form-control" value="${khachHang.email}" required />
                              </div>
                              <div class="mb-3">
                                  <label for="sdt">Số Điện Thoại:</label>
                                  <input type="text" id="sdt" name="sdt" class="form-control" value="${khachHang.sdt}" required />
                              </div>
                              <div class="mb-3">
                                  <label for="trangThai">Trạng Thái:</label>
                                  <select id="trangThai" name="trangThai" class="form-control" required>
                                      <option value="true" ${khachHang.trangThai ? 'selected' : ''}>Hoạt Động</option>
                                      <option value="false" ${khachHang.trangThai ? '' : 'selected'}>Không Hoạt Động</option>
                                  </select>
                              </div>

                              <button type="submit" class="btn btn-primary">Cập nhật</button>
                          </form>
                      </div>
                  </div>
                  <div class="col-md-4">
                      <!-- Bảng địa chỉ -->
                      <div class="form-container address-table">
                          <h5>Địa Chỉ</h5>
                          <table class="table">
                              <thead>
                                  <tr>
                                      <th>Địa Chỉ Nhận</th>
                                      <th>Xã/Phường</th>
                                      <th>Quận/Huyện</th>
                                      <th>Thành Phố/Tỉnh</th>
                                      <th>Hành Động</th>
                                  </tr>
                              </thead>
                              <tbody>
                                  <c:if test="${empty khachHang.diaChiList}">
                                      <tr>
                                          <td>
                                              <input type="text" name="diaChiList[0].diaChiNhan" class="form-control" />
                                          </td>
                                          <td>
                                              <input type="text" name="diaChiList[0].xa" class="form-control" />
                                          </td>
                                          <td>
                                              <input type="text" name="diaChiList[0].huyen" class="form-control" />
                                          </td>
                                          <td>
                                              <input type="text" name="diaChiList[0].thanhPho" class="form-control" />
                                          </td>
                                          <td>
                                              <button type="button" class="btn btn-danger btn-sm" onclick="removeAddress(this)">Xóa</button>
                                          </td>
                                      </tr>
                                  </c:if>
                                  <c:forEach var="diaChi" items="${khachHang.diaChiList}" varStatus="status">
                                      <tr>
                                          <td>
                                              <input type="text" name="diaChiList[${status.index}].diaChiNhan" class="form-control" value="${diaChi.diaChiNhan}" />
                                          </td>
                                          <td>
                                              <input type="text" name="diaChiList[${status.index}].xa" class="form-control" value="${diaChi.xa}" />
                                          </td>
                                          <td>
                                              <input type="text" name="diaChiList[${status.index}].huyen" class="form-control" value="${diaChi.huyen}" />
                                          </td>
                                          <td>
                                              <input type="text" name="diaChiList[${status.index}].thanhPho" class="form-control" value="${diaChi.thanhPho}" />
                                          </td>
                                          <td>
                                              <input type="hidden" name="diaChiList[${status.index}].diaChiId" value="${diaChi.diaChiId}" />
                                              <button type="button" class="btn btn-danger btn-sm" onclick="removeAddress(this)">Xóa</button>
                                          </td>
                                      </tr>
                                  </c:forEach>
                              </tbody>
                          </table>
                      </div>
                  </div>
              </div>
          </div>

          <script>
          function removeAddress(button) {
              var row = button.closest('tr');
              row.remove();
          }
          </script>




<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-QDfl8x7ClI6/T0P43+hih2kC4VfYm+bi5uQHYOybz8b0EXsO1pbkTq3p7J7l/BP3" crossorigin="anonymous"></script>
