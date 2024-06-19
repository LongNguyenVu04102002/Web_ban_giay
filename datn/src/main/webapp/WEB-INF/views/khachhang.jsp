<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../layout/header.jsp" %>
<%@ page pageEncoding="utf-8" %>
<jsp:useBean id="pageTitle" scope="page" class="java.lang.String" />
<%
    pageTitle = "Danh sách Khách hàng";
%>

<div class="container">
    <div class="row">
        <!-- Main Content (Left Side) -->
        <div class="col-md-9 mt-5">
            <main>
                <h2>Danh sách Khách hàng</h2>
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tên</th>
                            <th>giới tính</th>
                             <th>ngày sinh</th>
                            <th>Email</th>
                            <th>Số điện thoại</th>
                            <th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="khachHang" items="${khachHangs}">
                            <tr>
                                <td>${khachHang.khachHangId}</td>
                                <td>${khachHang.hoTen}</td>
                                <td>${khachHang.gioiTinh ? 'nam' : 'nữ' }</td>
                                 <td>${khachHang.ngaySinh}</td>
                                <td>${khachHang.email}</td>
                                <td>${khachHang.sdt}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </main>
        </div>

        <!-- Right Side (Form) -->
        <div class="col-md-3">
            <div class="right-content">
                <form>


                    <div class="mb-3 mt-5">
                        <label for="exampleInputEmail1" class="form-label">Email address</label>
                        <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                        <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                    </div>


                    <div class="mb-3">
                        <label for="exampleInputPassword1" class="form-label">Password</label>
                        <input type="password" class="form-control" id="exampleInputPassword1">
                    </div>
                    <div class="mb-3">
                                            <label for="exampleInputPassword1" class="form-label">Password</label>
                                            <input type="password" class="form-control" id="exampleInputPassword1">
                                        </div>
                                        <div class="mb-3">
                                                                <label for="exampleInputPassword1" class="form-label">Password</label>
                                                                <input type="password" class="form-control" id="exampleInputPassword1">
                                                            </div>
                                                            <div class="mb-3">
                                                                                    <label for="exampleInputPassword1" class="form-label">Password</label>
                                                                                    <input type="password" class="form-control" id="exampleInputPassword1">
                                                                                </div>
                                                                                <div class="mb-3">
                                                                                                        <label for="exampleInputPassword1" class="form-label">Password</label>
                                                                                                        <input type="password" class="form-control" id="exampleInputPassword1">
                                                                                                    </div>

                    <button type="submit" class="btn btn-primary">Submit</button>
                </form>
            </div>
        </div>
    </div>
</div>


<%@ include file="../layout/footer.jsp" %>
