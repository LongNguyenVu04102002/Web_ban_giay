<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.1.1/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Sản phẩm</title>
</head>

<body>
<nav class="sidebar">
    <header>
        <div class="image-text">
            <div class="text">
                <span class="name">XBOY</span>
                <span class="profession">Web developer</span>
            </div>
        </div>
    </header>

    <div class="menu-bar">
        <div class="menu">

            <li class="nav-link">
                <a href="/khachhang">
                    <i class='bx bx-user icon'></i>
                    <span class="text nav-text">Hóa đơn</span>
                </a>
            </li>
            <li class="nav-link dropdown">
                <a href="#" class="dropdown-toggle">
                    <i class='bx bx-heart icon'></i>
                    <span class="text nav-text">Quản lý sản phẩm</span>
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="/san-pham-chi-tiet">Sản phẩm chi tiết</a>
                    <a class="dropdown-item" href="/san-pham">Sản phẩm</a>
                    <a class="dropdown-item" href="/chat-lieu">Chất liệu</a>
                    <a class="dropdown-item" href="/co-giay">Cổ giày</a>
                    <a class="dropdown-item" href="/day-giay">Dây giày</a>
                    <a class="dropdown-item" href="/de-giay">Đế giày</a>
                    <a class="dropdown-item" href="/lot-giay">Lót giày</a>
                    <a class="dropdown-item" href="/mui-giay">Mũi giày</a>
                    <a class="dropdown-item" href="/kich-thuoc">Kích thước</a>
                    <a class="dropdown-item" href="/mau-sac">Màu sắc</a>
                    <a class="dropdown-item" href="/thuong-hieu">Thương hiệu</a>
                </div>
            </li>
            <li class="nav-link">
                <a href="/nhanvien">
                    <i class='bx bx-pie-chart-alt icon'></i>
                    <span class="text nav-text">Nhân Viên</span>
                </a>
            </li>
            <li class="nav-link">
                <a href="/khachhang">
                    <i class='bx bx-heart icon'></i>
                    <span class="text nav-text">Khách hàng</span>
                </a>
            </li>
            <li class="nav-link">
                <a href="/giamgia">
                    <i class='bx bx-wallet icon'></i>
                    <span class="text nav-text">Phiếu giảm giá</span>
                </a>
            </li>
        </div>

        </ul>
    </div>

    <div class="bottom-content">
        <li class="">
            <a href="#">
                <i class='bx bx-log-out icon'></i>
                <span class="text nav-text">Logout</span>
            </a>
        </li>
    </div>
    </div>
</nav>

<div class="home">
    <div class="includee">
        <jsp:include page="khachhang.jsp"></jsp:include>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    document.querySelectorAll('.dropdown-toggle').forEach(item => {
        item.addEventListener('click', event => {
            event.preventDefault();
            const dropdown = item.nextElementSibling;
            dropdown.classList.toggle('show');
        });
    });
</script>

</body>
</html>
<style>
    @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap");

    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family: "Poppins", sans-serif;
    }

    :root {
        /* ===== Colors ===== */
        --body-color: #d3d3d3;
        --sidebar-color: #fff;
        --primary-color: #695cfe;
        --primary-color-light: #f6f5ff;
        --toggle-color: #ddd;
        --text-color: #707070;

        /* ====== Transition ====== */
        --tran-03: all 0.2s ease;
        --tran-03: all 0.3s ease;
        --tran-04: all 0.3s ease;
        --tran-05: all 0.3s ease;
    }

    body {
        min-height: 100vh;
        background-color: var(--body-color);
        transition: var(--tran-05);
    }

    ::selection {
        background-color: var(--primary-color);
        color: #fff;
    }

    body.dark {
        --body-color: #18191a;
        --sidebar-color: #242526;
        --primary-color: #3a3b3c;
        --primary-color-light: #3a3b3c;
        --toggle-color: #fff;
        --text-color: #ccc;
    }

    /* ===== Sidebar ===== */
    .sidebar {
        position: fixed;
        top: 0;
        left: 0;
        height: 100%;
        width: 250px;
        padding: 10px 14px;
        background: var(--sidebar-color);
        transition: var(--tran-05);
        z-index: 100;
    }

    .sidebar.close {
        width: 88px;
    }

    /* ===== Reusable code - Here ===== */
    .sidebar li {
        height: 50px;
        list-style: none;
        display: flex;
        align-items: center;
        margin-top: 10px;
    }

    .sidebar header .image,
    .sidebar .icon {
        min-width: 60px;
        border-radius: 6px;
    }

    .sidebar .icon {
        min-width: 60px;
        border-radius: 6px;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 20px;
    }

    .sidebar .text,
    .sidebar .icon {
        color: var(--text-color);
        transition: var(--tran-03);
    }

    .sidebar .text {
        font-size: 17px;
        font-weight: 500;
        white-space: nowrap;
        opacity: 1;
    }

    .sidebar.close .text {
        opacity: 0;
    }

    /* =========================== */

    .sidebar header {
        position: relative;
    }

    .sidebar header .image-text {
        display: flex;
        align-items: center;
    }

    .sidebar header .logo-text {
        display: flex;
        flex-direction: column;
    }

    header .image-text .name {
        margin-top: 2px;
        font-size: 18px;
        font-weight: 600;
    }

    header .image-text .profession {
        font-size: 16px;
        margin-top: -2px;
        display: block;
    }

    .sidebar header .image {
        display: flex;
        align-items: center;
        justify-content: center;
    }

    .sidebar header .image img {
        width: 40px;
        border-radius: 6px;
    }

    .sidebar header .toggle {
        position: absolute;
        top: 50%;
        right: -25px;
        transform: translateY(-50%) rotate(180deg);
        height: 25px;
        width: 25px;
        background-color: var(--primary-color);
        color: var(--sidebar-color);
        border-radius: 50%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 22px;
        cursor: pointer;
        /*transition: var(--tran-05);*/
    }

    body.dark .sidebar header .toggle {
        color: var(--text-color);
    }

    .sidebar.close .toggle {
        transform: translateY(-50%) rotate(0deg);
    }

    .sidebar .menu {
        margin-top: 40px;
    }

    .sidebar li.search-box {
        border-radius: 6px;
        background-color: var(--primary-color-light);
        cursor: pointer;
        /*transition: var(--tran-05);*/
    }

    .sidebar li.search-box input {
        height: 100%;
        width: 100%;
        outline: none;
        border: none;
        background-color: var(--primary-color-light);
        color: var(--text-color);
        border-radius: 6px;
        font-size: 17px;
        font-weight: 500;
        /*transition: var(--tran-05);*/
    }

    .sidebar li a {
        list-style: none;
        height: 100%;
        background-color: transparent;
        display: flex;
        align-items: center;
        height: 100%;
        width: 100%;
        border-radius: 6px;
        text-decoration: none;
        transition: var(--tran-03);
    }

    .sidebar li a:hover {
        background-color: var(--primary-color);
    }

    .sidebar li a:hover .icon,
    .sidebar li a:hover .text {
        color: var(--sidebar-color);
    }

    .sidebar .menu-bar {
        height: calc(100% - 55px);
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        overflow-y: scroll;
    }

    .menu-bar::-webkit-scrollbar {
        display: none;
    }

    .sidebar .menu-bar .mode {
        border-radius: 6px;
        background-color: var(--primary-color-light);
        position: relative;
        transition: var(--tran-05);
    }

    .menu-bar .mode .sun-moon {
        height: 50px;
        width: 60px;
    }

    .home {
        position: absolute;
        top: 0;
        left: 250px;
        height: 100vh;
        width: calc(100% - 250px);
        background-color: var(--body-color);

    }

    .home .text {
        font-size: 30px;
        font-weight: 500;
        color: var(--text-color);
        padding: 12px 60px;
    }

    .sidebar.close ~ .home {
        left: 78px;
        height: 100vh;
        width: calc(100% - 78px);
    }

    body.dark .home .text {
        color: var(--text-color);
    }

    .includee {
        position: relative;
        width: calc(97%);
        height: 97vh;
        border: 1px solid white;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.4);
        background-color: white;
        margin-left: 30px;
        margin-top: 10px;
        font-family: "Poppins", sans-serif;
    }
</style>