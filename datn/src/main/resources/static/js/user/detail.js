document.addEventListener('DOMContentLoaded', function () {
    let sanPhamId = document.getElementById('sanPhamId').value;
    let selectedSizeId = null;
    let selectedColorId = null;

    function selectSize(sizeId) {
        selectedSizeId = sizeId;
        updateButtonStyles();
        fetchPrice();
    }

    function selectColor(colorId) {
        selectedColorId = colorId;
        updateColorStyles();
        fetchPrice();
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
        if (selectedSizeId && selectedColorId && sanPhamId) {
            fetch(`/sanphamchitiet/price?sanPhamId=${sanPhamId}&sizeId=${selectedSizeId}&colorId=${selectedColorId}`)
                .then(response => response.json())
                .then(price => {
                    document.getElementById('product-price').textContent = `${formatPrice(price)} VNĐ`;
                })
                .catch(error => {
                    console.error('Error fetching price:', error);
                    document.getElementById('product-price').textContent = 'Price Unavailable';
                });
        } else {
            document.getElementById('product-price').textContent = '';
        }
    }

    function formatPrice(price) {
        return new Intl.NumberFormat('vi-VN', { style: 'decimal' }).format(price);
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
                </div>`
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
        }, 5000);
    }

    document.querySelector('button[type="submit"]').addEventListener('click', function (e) {
        e.preventDefault();

        const tenSanPham = document.querySelector('h5.text-2xl.font-semibold').innerText;
        const kichThuoc = document.querySelector('button.size-size.bg-orange-500').innerText;
        const mauSac = document.querySelector('button.size-color.bg-orange-500').innerText;
        const gia = document.getElementById('product-price').innerText.replace(/[^0-9-]+/g, "");
        const soLuong = document.querySelector('input[name="soLuong"]').value;
        const cartItem = {
            id: null,
            tenSanPham: tenSanPham,
            kichThuoc: kichThuoc,
            mauSac: mauSac,
            gia: gia,
            soLuong: parseInt(soLuong)
        };
        fetch('/cart/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cartItem)
        })
            .then(response => response.json())
            .then(data => {
                if (data.status === 'success') {
                    location.reload();
                    handleNotificationType('Success');
                } else {
                    console.error('Error:', data);
                }
            })
            .catch(error => console.error('Error:', error));
    });
});
