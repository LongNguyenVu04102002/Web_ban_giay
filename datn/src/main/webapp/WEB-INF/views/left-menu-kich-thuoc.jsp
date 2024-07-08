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
<nav class="sidebar close">
    <header>
        <div class="image-text">
        <span class="image">
          <img src="logo.png" alt="">
        </span>
            <div class="text logo-text">
                <span class="name">Codinglab</span>
                <span class="profession">Web developer</span>
            </div>
        </div>
        <i class='bx bx-chevron-right toggle'></i>
    </header>

    <div class="menu-bar">
        <div class="menu">
            <li class="nav-link">
                <a href="/diachi">
                    <i class='bx bx-user icon'></i>
                    <span class="text nav-text">Địa chỉ</span>
                </a>
            </li>

            <li class="nav-link">
                <a href="/khachhang">
                    <i class='bx bx-bar-chart-alt-2 icon'></i>
                    <span class="text nav-text">Khachs Hàng</span>
                </a>
            </li>

            <li class="nav-link">
                <a href="#">
                    <i class='bx bx-bell icon'></i>
                    <span class="text nav-text">Notifications</span>
                </a>
            </li>

            <li class="nav-link">
                <a href="#">
                    <i class='bx bx-pie-chart-alt icon'></i>
                    <span class="text nav-text">Analytics</span>
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
                <a href="#">
                    <i class='bx bx-wallet icon'></i>
                    <span class="text nav-text">Wallets</span>
                </a>
            </li>

            </ul>
        </div>

        <div class="bottom-content">
            <li class="">
                <a href="#">
                    <i class='bx bx-log-out icon'></i>
                    <span class="text nav-text">Logout</span>
                </a>
            </li>

            <li class="mode">
                <div class="sun-moon">
                    <i class='bx bx-moon icon moon'></i>
                    <i class='bx bx-sun icon sun'></i>
                </div>
                <span class="mode-text text">Dark mode</span>

                <div class="toggle-switch">
                    <span class="switch"></span>
                </div>
            </li>

        </div>
    </div>
</nav>

<section class="home">
    <div class="text">Kích thước</div>
    <jsp:include page="kich-thuoc.jsp"></jsp:include>
</section>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    const body = document.querySelector('body'),
        sidebar = body.querySelector('nav'),
        toggle = body.querySelector(".toggle"),
        modeSwitch = body.querySelector(".toggle-switch"),
        modeText = body.querySelector(".mode-text");

    toggle.addEventListener("click", () => {
        sidebar.classList.toggle("close");
    });

    modeSwitch.addEventListener("click", () => {
        body.classList.toggle("dark");
        if (body.classList.contains("dark")) {
            modeText.innerText = "Light mode";
        } else {
            modeText.innerText = "Dark mode";
        }
    });

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
        --body-color: #e4e9f7;
        --sidebar-color: #fff;
        --primary-color: #695cfe;
        --primary-color-light: #f6f5ff;
        --toggle-color: #ddd;
        --text-color: #707070;
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

    nav.sidebar {
        position: fixed;
        top: 0;
        left: 0;
        height: 100%;
        width: 260px;
        padding: 10px 14px;
        background: var(--sidebar-color);
        transition: var(--tran-05);
        z-index: 100;
    }

    nav.sidebar.close {
        width: 88px;
    }

    nav.sidebar li {
        height: 50px;
        list-style: none;
        display: flex;
        align-items: center;
        margin-top: 10px;
    }

    nav.sidebar header .image,
    nav.sidebar .icon {
        min-width: 60px;
        border-radius: 6px;
    }

    nav.sidebar .icon {
        min-width: 60px;
        border-radius: 6px;
        height: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 20px;
    }

    nav.sidebar .text,
    nav.sidebar .icon {
        color: var(--text-color);
        transition: var(--tran-03);
    }

    nav.sidebar .text {
        font-size: 17px;
        font-weight: 500;
        white-space: nowrap;
        opacity: 1;
    }

    nav.sidebar .dropdown-menu {
        display: none;
        flex-direction: column;
        position: absolute;
        top: 100%;
        left: 0;
        background-color: var(--sidebar-color);
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
        z-index: 100;
        padding: 10px 0;
        border-radius: 6px;
        min-width: 200px;
    }


    nav.sidebar .dropdown-menu.show {
        display: block;
    }

    nav.sidebar.close .text {
        opacity: 0;
    }

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
        transition: var(--tran-05);
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
        transition: var(--tran-05);
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
        transition: var(--tran-05);
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

    body.dark .sidebar li a:hover .icon,
    body.dark .sidebar li a:hover .text {
        color: var(--text-color);
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

    .mode .sun-moon i {
        position: absolute;
    }

    .mode .sun-moon i.sun {
        opacity: 0;
    }

    body.dark .mode .sun-moon i.sun {
        opacity: 1;
    }

    body.dark .mode .sun-moon i.moon {
        opacity: 0;
    }

    .menu-bar .bottom-content .toggle-switch {
        position: absolute;
        right: 0;
        height: 100%;
        min-width: 60px;
        display: flex;
        align-items: center;
        justify-content: center;
        border-radius: 6px;
        cursor: pointer;
    }

    .toggle-switch .switch {
        position: relative;
        height: 22px;
        width: 40px;
        border-radius: 25px;
        background-color: var(--toggle-color);
        transition: var(--tran-05);
    }

    .switch::before {
        content: "";
        position: absolute;
        height: 15px;
        width: 15px;
        border-radius: 50%;
        top: 50%;
        left: 5px;
        transform: translateY(-50%);
        background-color: var(--sidebar-color);
        transition: var(--tran-04);
    }

    body.dark .switch::before {
        left: 20px;
    }

    .home {
        position: absolute;
        top: 0;
        top: 0;
        left: 250px;
        height: 100vh;
        width: calc(100% - 250px);
        background-color: var(--body-color);
        transition: var(--tran-05);
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

</style>