document.addEventListener('DOMContentLoaded', function () {
    function handleToggleForm(toggleId, formSectionId, shippingId) {
        const switcher = document.getElementById(toggleId);
        const formSection = document.getElementById(formSectionId);
        const shipping = document.getElementById(shippingId);

        if (switcher && formSection && shipping) {
            switcher.addEventListener('change', function () {
                formSection.classList.toggle('hidden-visibility', !this.checked);
                shipping.classList.toggle('hidden', !this.checked);
            });
        }
    }

    // Initialize form toggle handlers
    for (let i = 1; i <= 5; i++) {
        handleToggleForm(`toggleForm${i}`, `form-section-${i}`, `shipping${i}`);
    }

    // Load saved customer data for each tab
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

    // Hàm lấy danh sách huyện theo tỉnh
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

    // Hàm tính phí vận chuyển
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

    // Khởi tạo dữ liệu tỉnh thành phố cho tất cả các phần tử
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




