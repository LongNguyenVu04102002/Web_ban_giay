document.addEventListener('DOMContentLoaded', function () {
    function handleToggleForm(toggleId, formSectionId, shippingId, paymentId, cashPaymentId) {
        const switcher = document.getElementById(toggleId);
        const formSection = document.getElementById(formSectionId);
        const shipping = document.getElementById(shippingId);
        const payment = document.getElementById(paymentId);
        const cashPayment = document.getElementById(cashPaymentId);

        if (switcher && formSection && shipping && payment && cashPayment) {
            switcher.addEventListener('change', function () {
                const isChecked = this.checked;
                formSection.classList.toggle('hidden-visibility', !isChecked);
                shipping.classList.toggle('hidden', !isChecked);
                payment.classList.toggle('hidden', !isChecked);

                if (!isChecked) {
                    cashPayment.checked = true;
                }
            });
        }
    }

    for (let i = 1; i <= 5; i++) {
        handleToggleForm(`toggleForm${i}`, `form-section-${i}`, `shipping${i}`, `payment${i}`, `cashPayment${i}`);
    }

    function loadCustomerData(tabId) {
        const customerNameSpan = document.querySelector(`#customer-name-${tabId} .font-semibold`);
        const customerEmailSpan = document.querySelector(`#customer-email-${tabId} .font-semibold`);
        const customerPhoneSpan = document.querySelector(`#customer-phone-${tabId} .font-semibold`);
        const customerIdInput = document.getElementById(`customer-id-${tabId}`);
        const khachLeButton = document.getElementById(`khach-le-${tabId}`);

        const savedCustomer = JSON.parse(localStorage.getItem(`selectedCustomer-${tabId}`));
        if (savedCustomer) {
            customerIdInput.value = savedCustomer.id;
            customerNameSpan.textContent = savedCustomer.name;
            customerEmailSpan.textContent = savedCustomer.email;
            customerPhoneSpan.textContent = savedCustomer.phone;

            document.querySelector(`#customer-name-${tabId}`).style.display = 'flex';
            document.querySelector(`#customer-email-${tabId}`).style.display = 'flex';
            document.querySelector(`#customer-phone-${tabId}`).style.display = 'flex';
            khachLeButton.style.display = 'none';
        } else {
            document.querySelector(`#customer-name-${tabId}`).style.display = 'none';
            document.querySelector(`#customer-email-${tabId}`).style.display = 'none';
            document.querySelector(`#customer-phone-${tabId}`).style.display = 'none';
            khachLeButton.style.display = 'inline-block';
        }
    }

    // Load customer data for all tabs
    for (let i = 1; i <= 5; i++) {
        loadCustomerData(i);
    }

    function activateTab(tabId) {
        const tabButtons = document.querySelectorAll('.tab-nav');
        const tabContents = document.querySelectorAll('.tab-pane');

        tabButtons.forEach(button => {
            if (button.getAttribute('data-bs-target') === `#${tabId}`) {
                button.classList.add('active');
                button.setAttribute('aria-selected', 'true');
            } else {
                button.classList.remove('active');
                button.setAttribute('aria-selected', 'false');
            }
        });

        tabContents.forEach(content => {
            if (content.id === tabId) {
                content.classList.add('active', 'show');
            } else {
                content.classList.remove('active', 'show');
            }
        });
    }

    // Event listener to save the active tab in localStorage
    document.querySelectorAll('.tab-nav').forEach(button => {
        button.addEventListener('click', function () {
            const tabId = this.getAttribute('data-bs-target').substring(1); // Remove the '#' from the ID
            localStorage.setItem('activeTab', tabId);
        });
    });

    // Load the active tab from localStorage if it exists
    const savedTab = localStorage.getItem('activeTab');
    if (savedTab) {
        activateTab(savedTab);
    } else {
        // Default to the first tab if no tab is saved
        activateTab('tab1');
    }
    calculateTotal();
});

function clearSelectedCustomer(tabId) {
    localStorage.removeItem(`selectedCustomer-${tabId}`);
    const customerInfoContainer = document.getElementById(`customer-info-${tabId}`);
    const khachLeButton = document.getElementById(`khach-le-${tabId}`);

    if (customerInfoContainer) {
        customerInfoContainer.querySelector(`#customer-id-${tabId}`).value = '';
        customerInfoContainer.querySelector(`#customer-name-${tabId} .font-semibold`).textContent = '';
        customerInfoContainer.querySelector(`#customer-email-${tabId} .font-semibold`).textContent = '';
        customerInfoContainer.querySelector(`#customer-phone-${tabId} .font-semibold`).textContent = '';

        customerInfoContainer.querySelector(`#customer-name-${tabId}`).style.display = 'none';
        customerInfoContainer.querySelector(`#customer-email-${tabId}`).style.display = 'none';
        customerInfoContainer.querySelector(`#customer-phone-${tabId}`).style.display = 'none';

        khachLeButton.style.display = 'inline-block';
    }
}

function selectCustomer(button, tabId) {
    const customerId = button.getAttribute('data-id');
    const hoTen = button.getAttribute('data-ho-ten');
    const email = button.getAttribute('data-email');
    const sdt = button.getAttribute('data-sdt');

    const customerInfoContainer = document.getElementById(`customer-info-${tabId}`);
    const khachLeButton = document.getElementById(`khach-le-${tabId}`);

    if (customerInfoContainer) {
        customerInfoContainer.querySelector(`#customer-id-${tabId}`).value = customerId;
        customerInfoContainer.querySelector(`#customer-name-${tabId} .font-semibold`).textContent = hoTen;
        customerInfoContainer.querySelector(`#customer-email-${tabId} .font-semibold`).textContent = email;
        customerInfoContainer.querySelector(`#customer-phone-${tabId} .font-semibold`).textContent = sdt;

        customerInfoContainer.querySelector(`#customer-name-${tabId}`).style.display = 'flex';
        customerInfoContainer.querySelector(`#customer-email-${tabId}`).style.display = 'flex';
        customerInfoContainer.querySelector(`#customer-phone-${tabId}`).style.display = 'flex';

        khachLeButton.style.display = 'none';

        localStorage.setItem(`selectedCustomer-${tabId}`, JSON.stringify({
            id: customerId,
            name: hoTen,
            email,
            phone: sdt
        }));

        const modal = document.getElementById(`dialogCustomer${tabId}`);
        if (modal) {
            const modalInstance = bootstrap.Modal.getInstance(modal);
            if (modalInstance) {
                modalInstance.hide();
            }
        }
        fetch('/api/diachi/khachhang/' + customerId)
            .then(response => response.json())
            .then(data => {
                displayAddressList(data, tabId);
            })
            .catch(error => {
                console.error('Lỗi khi lấy danh sách địa chỉ:', error);
            });
    }
}

function displayAddressList(data, tabId) {
    const addressListBody = document.getElementById('address-list-body' + tabId);

    addressListBody.innerHTML = '';

    // Nếu có dữ liệu
    if (data && data.length > 0) {
        data.forEach((address, index) => {
            const tr = document.createElement('tr');
            tr.innerHTML = `
                <td>${index + 1}</td>
                <td>${address.ten}</td>
                <td>${address.email}</td>
                <td>${address.sdt}</td>
                <td>${address.thanhPho}</td>
                <td>${address.huyen}</td>
                <td>${address.xa}</td>
                <td>${address.diaChi}</td>
                <td>
                    <button type="button" class="btn btn-sm bg-green-500 text-white" 
                        onclick="chooseAddress(${tabId},'${address.ten}', '${address.email}', '${address.sdt}', '${address.thanhPho}', '${address.huyen}', '${address.xa}', '${address.diaChi}')">
                        Chọn
                    </button>
                </td>
            `;
            addressListBody.appendChild(tr);
        });
    } else {
        const tr = document.createElement('tr');
        tr.innerHTML = `
            <td colspan="9" class="text-center">Không có địa chỉ</td>
        `;
        addressListBody.appendChild(tr);
    }
}

function chooseAddress(indexId, ten, email, sdt, thanhPho, huyen, xa, diaChi) {
    document.getElementById('tenNguoiNhan' + indexId).value = ten;
    document.getElementById('email' + indexId).value = email;
    document.getElementById('sdt' + indexId).value = sdt;
    document.getElementById('province' + indexId).value = thanhPho;
    document.getElementById('district' + indexId).value = huyen;
    document.getElementById('ward' + indexId).value = xa;
    document.getElementById('diaChi' + indexId).value = diaChi;

    const modal = document.getElementById(`dialogDiaChi${indexId}`);
    if (modal) {
        const modalInstance = bootstrap.Modal.getInstance(modal);
        if (modalInstance) {
            modalInstance.hide();
        }
    }
}

$(document).ready(function () {
    const token = 'c00660e2-2e4f-11ef-8f55-4ee3d82283af';
    const shopId = '5144948';

    // Hàm lấy danh sách tỉnh thành phố
    function getProvinces(id) {
        $.ajax({
            url: 'https://online-gateway.ghn.vn/shiip/public-api/master-data/province',
            type: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Token': token
            },
            success: function (response) {
                if (response.code === 200) {
                    $(`#province${id}`).html('<option value="">Chọn Thành Phố</option>');
                    $.each(response.data, function (key, value) {
                        $(`#province${id}`).append(`<option value="${value.ProvinceID}">${value.ProvinceName}</option>`);
                    });
                } else {
                    console.error('Error fetching provinces:', response.message);
                }
            },
            error: function (xhr, status, error) {
                console.error('AJAX error:', status, error);
                console.error('Response text:', xhr.responseText);
            }
        });
    }

    function getDistricts(provinceId, id) {
        $.ajax({
            url: `https://online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=${provinceId}`,
            type: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Token': token
            },
            success: function (response) {
                if (response.code === 200) {
                    $(`#district${id}`).html('<option value="">Chọn Huyện</option>');
                    $(`#ward${id}`).html('<option value="">Chọn Xã</option>');
                    $.each(response.data, function (key, value) {
                        $(`#district${id}`).append(`<option value="${value.DistrictID}">${value.DistrictName}</option>`);
                    });
                } else {
                    console.error('Error fetching districts:', response.message);
                }
            },
            error: function (xhr, status, error) {
                console.error('AJAX error:', status, error);
                console.error('Response text:', xhr.responseText);
            }
        });
    }

    // Hàm lấy danh sách xã theo huyện
    function getWards(districtId, id) {
        $.ajax({
            url: `https://online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=${districtId}`,
            type: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Token': token
            },
            success: function (response) {
                if (response.code === 200) {
                    $(`#ward${id}`).html('<option value="">Chọn Xã</option>');
                    $.each(response.data, function (key, value) {
                        $(`#ward${id}`).append(`<option value="${value.WardCode}">${value.WardName}</option>`);
                    });
                } else {
                    console.error('Error fetching wards:', response.message);
                }
            },
            error: function (xhr, status, error) {
                console.error('AJAX error:', status, error);
                console.error('Response text:', xhr.responseText);
            }
        });
    }

    function calculateShippingFee(id) {
        const selectedDistrict = parseInt($(`#district${id}`).val(), 10);
        const selectedWard = $(`#ward${id}`).val();

        if (selectedDistrict && selectedWard) {
            const data = {
                service_type_id: 2,
                from_district_id: 3440,
                to_district_id: selectedDistrict,
                to_ward_code: selectedWard.toString(),
                height: 20,
                length: 40,
                weight: 20,
                width: 20,
                insurance_value: 3000000
            };

            $.ajax({
                url: 'https://online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/fee',
                type: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Token': token,
                    'ShopId': shopId
                },
                data: JSON.stringify(data),
                success: function (response) {
                    if (response.code === 200) {
                        $(`#shippingFee${id}`).text(response.data.total.toLocaleString() + ' VNĐ');
                        $(`#hiddenShippingFee${id}`).val(response.data.total);
                        calculateTotal()
                    } else {
                        $(`#shippingFee${id}`).text('Error fetching shipping fee.');
                        console.error('Error fetching shipping fee:', response.message);
                    }
                },
                error: function (xhr, status, error) {
                    $(`#shippingFee${id}`).text('Error fetching shipping fee.');
                    console.error('AJAX error:', status, error);
                    console.error('Response text:', xhr.responseText);
                }
            });
        }
    }

    function initialize() {
        $('select[id^="province"]').each(function () {
            const id = $(this).attr('id').replace('province', '');
            getProvinces(id);
        });
    }

    initialize();

    // Xử lý thay đổi tỉnh thành phố
    $(document).on('change', 'select[id^="province"]', function () {
        const id = $(this).attr('id').replace('province', '');
        const provinceId = $(this).val();
        const provinceName = $(this).find('option:selected').text();
        if (provinceId) {
            getDistricts(provinceId, id);
            $(`#provinceName${id}`).val(provinceName);
        } else {
            $(`#district${id}`).html('<option value="">Chọn Huyện</option>');
            $(`#ward${id}`).html('<option value="">Chọn Xã</option>');
            $(`#provinceName${id}`).val('');
        }
    });

    // Xử lý thay đổi huyện
    $(document).on('change', 'select[id^="district"]', function () {
        const id = $(this).attr('id').replace('district', '');
        const districtId = $(this).val();
        const districtName = $(this).find('option:selected').text();
        if (districtId) {
            getWards(districtId, id);
            $(`#districtName${id}`).val(districtName);
        } else {
            $(`#ward${id}`).html('<option value="">Chọn Xã</option>');
            $(`#districtName${id}`).val('');
        }
    });

    // Xử lý thay đổi xã
    $(document).on('change', 'select[id^="ward"]', function () {
        const id = $(this).attr('id').replace('ward', '');
        const wardId = $(this).val();
        const wardName = $(this).find('option:selected').text();
        $(`#wardName${id}`).val(wardName);
        calculateShippingFee(id);

    });
});

function calculateTotal() {
    // Lấy tất cả các phần tử tổng tiền
    const subtotals = document.querySelectorAll('[id^="subtotal"]');
    const discounts = document.querySelectorAll('[id^="discount"]');
    const shippingFees = document.querySelectorAll('[id^="shippingFee"]');
    const totalLabels = document.querySelectorAll('[id^="total"]');
    const hiddenTotalAmounts = document.querySelectorAll('[id^="hiddenTotalAmount"]');

    subtotals.forEach((subtotal, index) => {
        // Lấy giá trị tạm tính, giảm giá và phí ship
        const subtotalValue = parseFloat(subtotal.textContent.replace(/[^0-9,-]+/g, '').replace(',', '.')) || 0;
        const discountValue = parseFloat(discounts[index]?.textContent.replace(/[^0-9,-]+/g, '').replace(',', '.')) || 0;
        const shippingFeeValue = parseFloat(shippingFees[index]?.textContent.replace(/[^0-9,-]+/g, '').replace(',', '.')) || 0;

        // Tính tổng tiền
        const totalValue = subtotalValue - discountValue + shippingFeeValue;

        totalLabels[index].textContent = totalValue.toLocaleString() + ' VNĐ';
        hiddenTotalAmounts[index].value = totalValue.toFixed(1);
    });
}

$(document).ready(function () {
    $('.toggle-form-checkbox').on('change', function () {
        const indexId = $(this).attr('id').replace('toggleForm', '');
        const formSectionId = '#form-section-' + indexId;
        const formId = '#form' + indexId;

        if ($(this).is(':checked')) {
            $(formSectionId).removeClass('hidden-visibility').show();
            validateForm(formId);
        } else {
            $(formSectionId).hide();
            $(formSectionId).addClass('hidden-visibility').show();
            $(formId).validate().resetForm();
        }
    });

    function validateForm(formId) {
        $(formId).validate({
            ignore: ':hidden:not(:checkbox)',
            errorElement: 'div',
            errorClass: 'input-invalid',
            validClass: 'input-valid',
            errorPlacement: function (error, element) {
                error.addClass('text-red-600 mt-1');
                if (element.prop('type') === 'checkbox') {
                    error.insertAfter(element.parent('label'));
                } else {
                    error.insertAfter(element);
                }
            },
            rules: {
                tenNguoiNhan: {required: true},
                sdt: {required: true, digits: true, minlength: 10, maxlength: 10},
                email: {required: true, email: true},
                thanhPho: {required: true},
                huyen: {required: true},
                xa: {required: true},
                diaChi: {required: true}
            },
            messages: {
                tenNguoiNhan: {
                    required: 'Vui lòng nhập tên khách hàng'
                },
                sdt: {
                    required: 'Vui lòng nhập số điện thoại',
                    digits: 'Số điện thoại phải là chữ số',
                    minlength: 'Số điện thoại phải có ít nhất 10 chữ số',
                    maxlength: 'Số điện thoại không được quá 10 chữ số'
                },
                email: {
                    required: 'Vui lòng nhập địa chỉ email',
                    email: 'Địa chỉ email không hợp lệ'
                },
                thanhPho: {
                    required: 'Vui lòng chọn tỉnh thành'
                },
                huyen: {
                    required: 'Vui lòng chọn huyện'
                },
                xa: {
                    required: 'Vui lòng chọn xã'
                },
                diaChi: {
                    required: 'Vui lòng nhập địa chỉ'
                }
            },
            submitHandler: function (form) {
                form.submit();
            }
        });
    }
});

