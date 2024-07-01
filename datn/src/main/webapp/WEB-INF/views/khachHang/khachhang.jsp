<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<%@ page pageEncoding="utf-8" %>
<jsp:useBean id="pageTitle" scope="page" class="java.lang.String" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<%
    pageTitle = "Danh sách Khách hàng";
%>



<div>
    <div class="row">
        <div class="col-md-12 mt-5">
       <h1 style="margin-left: 30px;">
                 Khách Hàng
              </h1>

               <a href="/khachhang" style="color: white; font-weight: bold; text-decoration: none">
                          <button class="add-button"> <i class="bi bi-arrow-left"></i> Trở về</button>
                      </a>

               <form action="${pageContext.request.contextPath}/searchBySDT" method="post" class="d-flex align-items-center justify-content-end mb-3">


           <!-- Ô tìm kiếm theo số điện thoại -->
           <input type="text" id="sdt" name="sdt" class="form-control form-control-sm me-2" style="max-width: 200px;" placeholder="Tìm theo số điện thoại"/>
           <button type="submit" class="btn btn-primary">Tìm kiếm</button>
       </form>


<div class="d-flex justify-content-center align-items-center mb-3">
    <!-- Form tìm kiếm theo ngày sinh -->
    <form action="${pageContext.request.contextPath}/searchByNgaySinh" method="get" class="d-flex align-items-center mb-3 me-2">
        <div class="mb-3 me-2" style="max-width: 200px;">
            <label for="fromDate">From Date:</label>
            <input type="date" id="fromDate" name="fromDate" class="form-control" required />
        </div>
        <div class="mb-3 me-2" style="max-width: 200px;">
            <label for="toDate">To Date:</label>
            <input type="date" id="toDate" name="toDate" class="form-control" required />
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>

    <!-- Form lọc theo giới tính -->
    <form id="filterFormGender" class="d-flex align-items-center mb-3 me-2" action="${pageContext.request.contextPath}/filterByGender" method="get">
        <div class="mb-3 me-2">
            <label for="gender" class="form-label">Lọc theo Giới Tính:</label>
            <select id="gender" name="gender" class="form-control" onchange="filterKhachHang()">
                <option value="" ${selectedGender == '' ? 'selected' : ''}>Tất cả</option>
                <option value="true" ${selectedGender == 'true' ? 'selected' : ''}>Nam</option>
                <option value="false" ${selectedGender == 'false' ? 'selected' : ''}>Nữ</option>
            </select>
        </div>
    </form>

    <!-- Form lọc theo trạng thái -->
    <form id="filterFormStatus" class="d-flex align-items-center mb-3" action="${pageContext.request.contextPath}/filterByStatus" method="get">
        <div class="mb-3 me-2">
            <label for="status" class="form-label">Lọc theo Trạng Thái:</label>
            <select id="status" name="status" class="form-control" onchange="filterByStatus()">
                <option value="true" ${selectedStatus == 'true' ? 'selected' : ''}>Hoạt động</option>
                <option value="false" ${selectedStatus == 'false' ? 'selected' : ''}>Không hoạt động</option>
            </select>
        </div>
    </form>
</div>


            <main>
                <!-- Nút Thêm mới -->
                <div class="d-flex justify-content-end">
                    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#addCustomerModal">
                        Thêm mới
                    </button>
                </div>

                <!-- Modal Thêm mới Khách hàng -->
                <div class="modal fade" id="addCustomerModal" tabindex="-1" aria-labelledby="addCustomerModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="addCustomerModalLabel">Thêm Khách Hàng Mới</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form action="${pageContext.request.contextPath}/saveKhachHang" method="post">
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
                                        <input type="text" id="sdt" name="sdt" class="form-control" required />
                                    </div>

                                    <button type="submit" class="btn btn-primary">Thêm</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>


             <table class="table table-bordered table-hover">
                     <thead class="thead-light">
                         <tr>
                             <th>#</th>
                             <th>Tên</th>
                             <th>Giới Tính</th>
                             <th>Ngày Sinh</th>
                             <th>Email</th>
                             <th>Số Điện Thoại</th>
                             <th>Trạng Thái</th>
                             <th>Hành Động</th>
                         </tr>
                     </thead>
                     <tbody>
                         <tr class="table-secondary">
                             <td colspan="8"></td>
                         </tr>
                         <c:forEach var="khachHang" items="${khachHangs}" varStatus="loop">
                             <tr>
                                 <td>${loop.index + 1}</td> <!-- Số thứ tự bắt đầu từ 1 -->
                                 <td>${khachHang.hoTen}</td>
                                 <td>${khachHang.gioiTinh ? 'Nam' : 'Nữ'}</td>
                                 <td>${khachHang.ngaySinh}</td>
                                 <td>${khachHang.email}</td>
                                 <td>${khachHang.sdt}</td>
                                 <td>
                                     <form action="${pageContext.request.contextPath}/khachhang/${khachHang.khachHangId}/toggle" method="get">
                                         <div class="form-check form-switch">
                                             <input class="form-check-input" type="checkbox" id="trangThai${khachHang.khachHangId}" name="trangThai" ${khachHang.trangThai ? 'checked' : ''} onchange="this.form.submit()">
                                             <label class="form-check-label" for="trangThai${khachHang.khachHangId}">${khachHang.trangThai ? 'Hoạt Động' : 'Không Hoạt Động'}</label>
                                         </div>
                                     </form>
                                 </td>
                                 <td>
                                     <a data-bs-toggle="modal" data-bs-target="#updateCustomerModal${khachHang.khachHangId}">
                                         <i class="fas fa-edit text-primary"></i>
                                     </a>
                                     <a data-bs-toggle="modal" data-bs-target="#showAddressesModal${khachHang.khachHangId}">
                                         <i class="fas fa-map-marked-alt text-info"></i>
                                     </a>
                                 </td>
                             </tr>



                                            <!-- Modal Hiển thị Địa Chỉ -->
                                                      <div class="modal fade" id="showAddressesModal${khachHang.khachHangId}" tabindex="-1" aria-labelledby="showAddressesModalLabel${khachHang.khachHangId}" aria-hidden="true">
                                                          <div class="modal-dialog">
                                                              <div class="modal-content">
                                                                  <div class="modal-header">
                                                                      <h5 class="modal-title" id="showAddressesModalLabel${khachHang.khachHangId}">Địa chỉ của ${khachHang.hoTen}</h5>
                                                                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                  </div>
                                                                  <div class="modal-body">
                                                                      <!-- Danh sách địa chỉ của khách hàng -->
                                                                      <ul>
                                                                          <c:forEach var="diaChi" items="${khachHang.diaChiList}">
                                                                              <li>${diaChi.diaChiNhan}, ${diaChi.xa}, ${diaChi.huyen}, ${diaChi.thanhPho}   <a data-bs-toggle="modal" data-bs-target="#updateAddressModal${diaChi.diaChiId}">
                                                                                 <i class="fas fa-edit"></i> </a>
                                                                                   </li>

                                                                          </c:forEach>
                                                                      </ul>
                                                                  </div>
                                                                  <div class="modal-footer">
                                                                      <!-- Nút thêm địa chỉ -->
                                                                      <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addAddressModal${khachHang.khachHangId}">
                                                                          Thêm Địa Chỉ
                                                                      </button>


                                                                      <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                                                                  </div>
                                                              </div>
                                                          </div>
                                                      </div>


                                                         <!-- Modal Thêm Địa Chỉ -->
                                                                  <div class="modal fade" id="addAddressModal${khachHang.khachHangId}" tabindex="-1" aria-labelledby="addAddressModalLabel${khachHang.khachHangId}" aria-hidden="true">
                                                                      <div class="modal-dialog">
                                                                          <div class="modal-content">
                                                                              <div class="modal-header">
                                                                                  <h5 class="modal-title" id="addAddressModalLabel${khachHang.khachHangId}">Thêm Địa Chỉ cho ${khachHang.hoTen}</h5>
                                                                                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                              </div>
                                                                              <div class="modal-body">
                                                                                  <!-- Form thêm địa chỉ mới -->
                                                                                  <form action="${pageContext.request.contextPath}/addAddress" method="post">
                                                                                      <input type="hidden" name="khachHangId" value="${khachHang.khachHangId}" />
                                                                                      <div class="mb-3">
                                                                                          <label for="diaChiNhan">Địa Chỉ Nhận:</label>
                                                                                          <input type="text" id="diaChiNhan" name="diaChiNhan" class="form-control" required />
                                                                                      </div>
                                                                                      <div class="mb-3">
                                                                                          <label for="xa">Xã/Phường:</label>
                                                                                          <input type="text" id="xa" name="xa" class="form-control" required />
                                                                                      </div>
                                                                                      <div class="mb-3">
                                                                                          <label for="huyen">Quận/Huyện:</label>
                                                                                          <input type="text" id="huyen" name="huyen" class="form-control" required />
                                                                                      </div>
                                                                                      <div class="mb-3">
                                                                                          <label for="thanhPho">Thành Phố/Tỉnh:</label>
                                                                                          <input type="text" id="thanhPho" name="thanhPho" class="form-control" required />
                                                                                      </div>
                                                                                      <button type="submit" class="btn btn-primary">Thêm Địa Chỉ</button>
                                                                                  </form>
                                                                              </div>
                                                                          </div>
                                                                      </div>
                                                                  </div>

                                                                  <!-- Modal Cập Nhật Địa Chỉ -->
                                                                  <c:forEach var="diaChi" items="${khachHang.diaChiList}">
                                                                      <div class="modal fade" id="updateAddressModal${diaChi.diaChiId}" tabindex="-1" aria-labelledby="updateAddressModalLabel${diaChi.diaChiId}" aria-hidden="true">
                                                                          <div class="modal-dialog">
                                                                              <div class="modal-content">
                                                                                  <div class="modal-header">
                                                                                      <h5 class="modal-title" id="updateAddressModalLabel${diaChi.diaChiId}">Cập Nhật Địa Chỉ</h5>
                                                                                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                                  </div>
                                                                                  <div class="modal-body">
                                                                                      <form action="${pageContext.request.contextPath}/updateAddress" method="post">
                                                                                          <input type="hidden" name="diaChiId" value="${diaChi.diaChiId}" />
                                                                                          <div class="mb-3">
                                                                                              <label for="diaChiNhan${diaChi.diaChiId}">Địa Chỉ Nhận:</label>
                                                                                              <input type="text" id="diaChiNhan${diaChi.diaChiId}" name="diaChiNhan" class="form-control" value="${diaChi.diaChiNhan}" required />
                                                                                          </div>
                                                                                          <div class="mb-3">
                                                                                              <label for="xa${diaChi.diaChiId}">Xã/Phường:</label>
                                                                                              <input type="text" id="xa${diaChi.diaChiId}" name="xa" class="form-control" value="${diaChi.xa}" required />
                                                                                          </div>
                                                                                          <div class="mb-3">
                                                                                              <label for="huyen${diaChi.diaChiId}">Quận/Huyện:</label>
                                                                                              <input type="text" id="huyen${diaChi.diaChiId}" name="huyen" class="form-control" value="${diaChi.huyen}" required />
                                                                                          </div>
                                                                                          <div class="mb-3">
                                                                                              <label for="thanhPho${diaChi.diaChiId}">Thành Phố/Tỉnh:</label>
                                                                                              <input type="text" id="thanhPho${diaChi.diaChiId}" name="thanhPho" class="form-control" value="${diaChi.thanhPho}" required />
                                                                                          </div>
                                                                                          <button type="submit" class="btn btn-primary">Cập Nhật</button>
                                                                                      </form>
                                                                                  </div>
                                                                              </div>
                                                                          </div>
                                                                      </div>
                                                                  </c:forEach>







                    <!-- Modal Cập nhật Khách hàng -->
                                    <div class="modal fade" id="updateCustomerModal${khachHang.khachHangId}" tabindex="-1" aria-labelledby="updateCustomerModalLabel${khachHang.khachHangId}" aria-hidden="true">
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="updateCustomerModalLabel${khachHang.khachHangId}">Cập Nhật Khách Hàng</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <form action="${pageContext.request.contextPath}/saveKhachHang" method="post">
                                                        <input type="hidden" name="khachHangId" value="${khachHang.khachHangId}" />
                                                        <div class="mb-3">
                                                            <label for="hoTen${khachHang.khachHangId}">Họ Tên:</label>
                                                            <input type="text" id="hoTen${khachHang.khachHangId}" name="hoTen" class="form-control" value="${khachHang.hoTen}" required />
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="gioiTinh${khachHang.khachHangId}">Giới Tính:</label>
                                                            <select id="gioiTinh${khachHang.khachHangId}" name="gioiTinh" class="form-control" required>
                                                                <option value="true" ${khachHang.gioiTinh ? 'selected' : ''}>Nam</option>
                                                                <option value="false" ${khachHang.gioiTinh ? '' : 'selected'}>Nữ</option>
                                                            </select>
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="ngaySinh${khachHang.khachHangId}">Ngày Sinh:</label>
                                                            <input type="date" id="ngaySinh${khachHang.khachHangId}" name="ngaySinh" class="form-control" value="${khachHang.ngaySinh}" required />
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="email${khachHang.khachHangId}">Email:</label>
                                                            <input type="email" id="email${khachHang.khachHangId}" name="email" class="form-control" value="${khachHang.email}" required />
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="sdt${khachHang.khachHangId}">Số Điện Thoại:</label>
                                                            <input type="text" id="sdt${khachHang.khachHangId}" name="sdt" class="form-control" value="${khachHang.sdt}" required />
                                                        </div>
                                                        <div class="mb-3">
                                                            <label for="trangThai${khachHang.khachHangId}">Trạng Thái:</label>
                                                            <select id="trangThai${khachHang.khachHangId}" name="trangThai" class="form-control" required>
                                                                <option value="true" ${khachHang.trangThai ? 'selected' : ''}>Hoạt Động</option>
                                                                <option value="false" ${khachHang.trangThai ? '' : 'selected'}>Không Hoạt Động</option>
                                                            </select>
                                                        </div>
                                                        <button type="submit" class="btn btn-primary">Cập nhật</button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Phân trang -->
           <nav aria-label="Page navigation">
               <ul class="pagination">
                   <c:if test="${currentPage > 1}">
                       <li class="page-item"><a class="page-link" href="?status=${selectedStatus}&gender=${selectedGender}&page=1&size=${size}">First</a></li>
                       <li class="page-item"><a class="page-link" href="?status=${selectedStatus}&gender=${selectedGender}&page=${currentPage - 1}&size=${size}">Previous</a></li>
                   </c:if>
                   <c:forEach begin="1" end="${totalPages}" varStatus="page">
                       <li class="page-item ${currentPage == page.index + 1 ? 'active' : ''}">
                           <a class="page-link" href="?status=${selectedStatus}&gender=${selectedGender}&page=${page.index + 1}&size=${size}">${page.index + 1}</a>
                       </li>
                   </c:forEach>
                   <c:if test="${currentPage < totalPages}">
                       <li class="page-item"><a class="page-link" href="?status=${selectedStatus}&gender=${selectedGender}&page=${currentPage + 1}&size=${size}">Next</a></li>
                       <li class="page-item"><a class="page-link" href="?status=${selectedStatus}&gender=${selectedGender}&page=${totalPages}&size=${size}">Last</a></li>
                   </c:if>
               </ul>
           </nav>


            </main>
        </div>
    </div>
</div>






<script>
function filterKhachHang() {
    var gender = document.getElementById("gender").value;
    window.location.href = "${pageContext.request.contextPath}/filterByGender?gender=" + gender;
}

function filterByStatus() {
    var status = document.getElementById("status").value;
    window.location.href = "${pageContext.request.contextPath}/filterByStatus?status=" + status;
}

</script>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
