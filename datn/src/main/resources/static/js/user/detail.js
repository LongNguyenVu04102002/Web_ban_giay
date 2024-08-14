document.addEventListener('DOMContentLoaded', function () {
    let sanPhamId = document.getElementById('sanPhamId').value;
    let selectedSizeId = null;
    let selectedColorId = null;
    let isFetchingPrice = false; // Trạng thái để tránh yêu cầu giá lặp lại

    function selectSize(sizeId) {
        selectedSizeId = sizeId;
        updateButtonStyles();
        debounceFetchPrice(); // Gọi hàm debounce thay vì fetchPrice trực tiếp
    }

    function selectColor(colorId) {
        selectedColorId = colorId;
        updateColorStyles();
        debounceFetchPrice(); // Gọi hàm debounce thay vì fetchPrice trực tiếp
    }

    function updateButtonStyles() {
        document.querySelectorAll('[id^="size-"]').forEach(button => {
            const sizeId = parseInt(button.id.split('-')[1], 10);
            if (sizeId === selectedSizeId) {
                button.classList.add('bg-orange-500', 'text-white');
                button.classList.remove('text-orange-500', 'hover:bg-orange-500', 'hover:text-white');
            } else {
                button.classList.remove('bg-orange-500', 'text-white');
                button.classList.add('text-orange-500', 'hover:bg-orange-500', 'hover:text-white');
            }
        });
    }

    function updateColorStyles() {
        document.querySelectorAll('[id^="color-"]').forEach(button => {
            const colorId = parseInt(button.id.split('-')[1], 10);
            if (colorId === selectedColorId) {
                button.classList.add('bg-orange-500', 'text-white');
                button.classList.remove('text-orange-500', 'hover:bg-orange-500', 'hover:text-white');
            } else {
                button.classList.remove('bg-orange-500', 'text-white');
                button.classList.add('text-orange-500', 'hover:bg-orange-500', 'hover:text-white');
            }
        });
    }

    function fetchPrice() {
        if (selectedSizeId && selectedColorId && sanPhamId && !isFetchingPrice) {
            isFetchingPrice = true;
            fetch(`/sanphamchitiet/price?sanPhamId=${sanPhamId}&sizeId=${selectedSizeId}&colorId=${selectedColorId}`)
                .then(response => response.json())
                .then(price => {
                    document.getElementById('product-price').textContent = `${formatPrice(price)} VNĐ`;
                })
                .catch(error => {
                    console.error('Lỗi khi lấy giá:', error);
                    document.getElementById('product-price').textContent = 'Giá không khả dụng';
                })
                .finally(() => {
                    isFetchingPrice = false;
                });
        } else {
            document.getElementById('product-price').textContent = '';
        }
    }

    function debounce(func, wait = 300) {
        let timeout;
        return function (...args) {
            clearTimeout(timeout);
            timeout = setTimeout(() => func.apply(this, args), wait);
        };
    }

    const debounceFetchPrice = debounce(fetchPrice);

    function formatPrice(price) {
        return new Intl.NumberFormat('vi-VN', {style: 'decimal'}).format(price);
    }

    // Expose functions to global scope
    window.selectSize = selectSize;
    window.selectColor = selectColor;

    const notificationTypeHtml = {
        Success: `<div class="toast fade show" id="notificationToastSuccess">
                    <div class="notification">
                        <div class="notification-content">
                            <div class="mr-3">
                                <span class="text-2xl text-emerald-400">
                                    <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 20 20" aria-hidden="true" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                                    </svg>
                                </span>
                            </div>
                            <div class="mr-4">
                                <div class="notification-title">Success</div>
                                <div class="notification-description">
                                    Thêm sản phẩm vào giỏ hàng thành công.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`,
        Size: `<div class="toast fade show" id="notificationToastInfo">
                    <div class="notification">
                        <div class="notification-content">
                            <div class="mr-3">
                                <span class="text-2xl text-blue-400">
                                    <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 20 20" aria-hidden="true" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z" clip-rule="evenodd"></path>
                                    </svg>
                                </span>
                            </div>
                            <div class="mr-4">
                                <div class="notification-title">Info</div>
                                <div class="notification-description">
                                    Bạn chưa chọn kích thước.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`,
        Color: `<div class="toast fade show" id="notificationToastInfo">
                    <div class="notification">
                        <div class="notification-content">
                            <div class="mr-3">
                                <span class="text-2xl text-blue-400">
                                    <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 20 20" aria-hidden="true" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z" clip-rule="evenodd"></path>
                                    </svg>
                                </span>
                            </div>
                            <div class="mr-4">
                                <div class="notification-title">Info</div>
                                <div class="notification-description">
                                    Bạn chưa chọn màu sắc.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`,
        Quantity: `<div class="toast fade show" id="notificationToastInfo">
                    <div class="notification">
                        <div class="notification-content">
                            <div class="mr-3">
                                <span class="text-2xl text-blue-400">
                                    <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 20 20" aria-hidden="true" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z" clip-rule="evenodd"></path>
                                    </svg>
                                </span>
                            </div>
                            <div class="mr-4">
                                <div class="notification-title">Info</div>
                                <div class="notification-description">
                                    Số lượng sản phẩm trong kho không đủ.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`,
        Error: `<div class="toast fade show" id="notificationToastInfo">
                    <div class="notification">
                        <div class="notification-content">
                            <div class="mr-3">
                                <span class="text-2xl text-blue-400">
                                    <svg stroke="currentColor" fill="currentColor" stroke-width="0" viewBox="0 0 20 20" aria-hidden="true" height="1em" width="1em" xmlns="http://www.w3.org/2000/svg">
                                        <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-7-4a1 1 0 11-2 0 1 1 0 012 0zM9 9a1 1 0 000 2v3a1 1 0 001 1h1a1 1 0 100-2v-3a1 1 0 00-1-1H9z" clip-rule="evenodd"></path>
                                    </svg>
                                </span>
                            </div>
                            <div class="mr-4">
                                <div class="notification-title">Info</div>
                                <div class="notification-description">
                                    Số lượng sản phẩm phải lớn hơn 0.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`,
    };

    function handleNotificationType(type) {
        const notificationContainer = document.getElementById('notification-toast');
        const notificationToast = document.createElement('div');
        notificationToast.innerHTML = notificationTypeHtml[type];
        notificationContainer.appendChild(notificationToast);

        setTimeout(function () {
            if (notificationToast.parentElement) {
                notificationToast.parentElement.removeChild(notificationToast);
            }
        }, 3000);
    }

    document.querySelector('button[type="submit"]').addEventListener('click', async function (e) {
        e.preventDefault();

        const tenSanPham = document.querySelector('h5.text-2xl.font-semibold').innerText;
        const kichThuoc = document.querySelector('button[id^="size-"].bg-orange-500');
        const mauSac = document.querySelector('button[id^="color-"].bg-orange-500');
        const gia = document.getElementById('product-price').innerText.replace(/[^0-9-]+/g, "");
        const soLuong = parseInt(document.querySelector('input[name="soLuong"]').value);

        if (!kichThuoc) {
            handleNotificationType('Size');
            return;
        }

        if (!mauSac) {
            handleNotificationType('Color');
            return;
        }

        if (soLuong < 1) {
            handleNotificationType('Error');
            return;
        }

        const cartItem = {
            id: null,
            tenSanPham: tenSanPham,
            kichThuoc: kichThuoc.innerText,
            mauSac: mauSac.innerText,
            gia: gia,
            soLuong: soLuong
        };

        try {
            const quantityResponse = await fetch(`/sanphamchitiet/check-quantity?sanPhamId=${sanPhamId}&sizeId=${selectedSizeId}&colorId=${selectedColorId}&soLuong=${soLuong}`);
            const quantityData = await quantityResponse.json();

            if (quantityData.status === 'available') {
                const addResponse = await fetch('/cart/add', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(cartItem)
                });

                const addData = await addResponse.json();

                if (addData.status === 'success') {
                    localStorage.setItem('notification', 'Success');
                    location.reload();
                } else if (addData.status === 'error') {
                    handleNotificationType('Quantity');
                }
            } else {
                handleNotificationType('Quantity');
            }
        } catch (error) {
            console.error('Lỗi:', error);
        }
    });

    window.addEventListener('load', function () {
        const notification = localStorage.getItem('notification');
        if (notification) {
            handleNotificationType(notification);
            localStorage.removeItem('notification');
        }
    });
});
