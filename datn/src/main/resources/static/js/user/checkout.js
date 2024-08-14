const notificationTypeHtml = {
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
                                    Giỏ hàng của bạn đang trống.
                                </div>
                                <div class="notification-description">
                                    Vui lòng mua thêm sản phẩm để tiến hành thanh toán.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`,
};
$(document).ready(function () {
    function getProvinces() {
        $.ajax({
            url: 'https://online-gateway.ghn.vn/shiip/public-api/master-data/province',
            type: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Token': 'c00660e2-2e4f-11ef-8f55-4ee3d82283af'
            },
            success: function (response) {
                if (response.code === 200) {
                    $("#province").append('<option value="">Chọn Thành Phố</option>');
                    $.each(response.data, function (key, value) {
                        $("#province").append('<option value="' + value.ProvinceID + '">' + value.ProvinceName + '</option>');
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

    function getDistricts(provinceId) {
        $.ajax({
            url: 'https://online-gateway.ghn.vn/shiip/public-api/master-data/district?province_id=' + provinceId,
            type: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Token': 'c00660e2-2e4f-11ef-8f55-4ee3d82283af'
            },
            success: function (response) {
                if (response.code === 200) {
                    $("#district").html('<option value="">Chọn Huyện</option>');
                    $("#ward").html('<option value="">Chọn Xã</option>');
                    $.each(response.data, function (key, value) {
                        $("#district").append('<option value="' + value.DistrictID + '">' + value.DistrictName + '</option>');
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

    function getWards(districtId) {
        $.ajax({
            url: 'https://online-gateway.ghn.vn/shiip/public-api/master-data/ward?district_id=' + districtId,
            type: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Token': 'c00660e2-2e4f-11ef-8f55-4ee3d82283af'
            },
            success: function (response) {
                if (response.code === 200) {
                    $("#ward").html('<option value="">Chọn Xã</option>');
                    $.each(response.data, function (key, value) {
                        $("#ward").append('<option value="' + value.WardCode + '">' + value.WardName + '</option>');
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

    function calculateShippingFee() {
        const selectedDistrict = parseInt($("#district").val(), 10);
        const selectedWard = $("#ward").val();

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
                    'Token': 'c00660e2-2e4f-11ef-8f55-4ee3d82283af',
                    'ShopId': '5144948'
                },
                data: JSON.stringify(data),
                success: function (response) {
                    if (response.code === 200) {
                        $("#shippingFee").text(response.data.total.toLocaleString() + ' VNĐ');
                        document.getElementById('hiddenShippingFee').value = response.data.total;
                        updateTotal()
                    } else {
                        $("#shippingFee").text('Error fetching shipping fee.');
                        console.error('Error fetching shipping fee:', response.message);
                    }
                },
                error: function (xhr, status, error) {
                    $("#shippingFee").text('Error fetching shipping fee.');
                    console.error('AJAX error:', status, error);
                    console.error('Response text:', xhr.responseText);
                }
            });
        }
    }

    getProvinces();

    $("#province").on('change', function () {
        const provinceId = $(this).val();
        const provinceName = $("#province option:selected").text();
        if (provinceId) {
            getDistricts(provinceId);
            $("#provinceName").val(provinceName);
        } else {
            $("#district").html('<option value="">Chọn Huyện</option>');
            $("#ward").html('<option value="">Chọn Xã</option>');
            $("#provinceName").val('');
        }
    });

    $("#district").on('change', function () {
        const districtId = $(this).val();
        const districtName = $("#district option:selected").text();
        if (districtId) {
            getWards(districtId);
            $("#districtName").val(districtName);
        } else {
            $("#ward").html('<option value="">Chọn Xã</option>');
            $("#districtName").val('');
        }
    });

    $("#ward").on('change', function () {
        const wardId = $(this).val();
        const wardName = $("#ward option:selected").text();
        $("#wardName").val(wardName);
        calculateShippingFee();
    });

    $('#form-validation').validate({
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
        }
    });
});

function handleNotificationType(type) {
    $('#notification-toast').append(notificationTypeHtml[type])
    $('#notification-toast .toast:last-child').toast('show');
    setTimeout(function () {
        $('#notification-toast .toast:first-child').remove();
    }, 5000);
}

function checkCartItems() {
    const cartItems = document.querySelectorAll('.cart-item').length;
    if (cartItems === 0) {
        handleNotificationType('Error');
        return false;
    }
    return true;
}

function updateTotal() {
    const discountElement = document.getElementById('discount');
    const subtotalElement = document.getElementById('subtotal');
    const shippingFeeElement = document.getElementById('shippingFee');
    const totalElement = document.getElementById('total');

    function parseCurrency(text) {
        const cleanedText = text.replace(/[^\d.,]/g, '');

        const parts = cleanedText.split(',');
        let integerPart = parts[0].replace(/\./g, '');
        let decimalPart = parts.length > 1 ? parts[1] : '0';

        // Kết hợp phần nguyên và phần thập phân
        const numericValue = parseFloat(`${integerPart}.${decimalPart}`);

        return isNaN(numericValue) ? 0 : numericValue;
    }

    const discount = discountElement ? parseCurrency(discountElement.innerText) : 0;
    const subtotal = subtotalElement ? parseCurrency(subtotalElement.innerText) : 0;
    const shippingFee = shippingFeeElement ? parseCurrency(shippingFeeElement.innerText) : 0;

    const total = subtotal - discount + shippingFee;

    if (totalElement) {
        totalElement.innerText = total.toLocaleString() + ' VNĐ';
    } else {
        console.error('Total element is missing.');
    }
}

document.addEventListener('DOMContentLoaded', function () {
    updateTotal();
});
