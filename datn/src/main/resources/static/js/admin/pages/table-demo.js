$(document).ready(function () {
    $('#data-table-all, #data-table-cancel, #data-table-delivery, #data-table-confirm, #data-table-confirmed, #data-table-shipping, #data-table-delivered, #data-table-completed, #data-table-cart, #data-table-sp, #data-table-pgg, #data-table-account').DataTable({
        paging: true,
        searching: true,
        ordering: true,
        info: true,
        lengthChange: false,
        language: {
            url: '//cdn.datatables.net/plug-ins/1.10.25/i18n/Vietnamese.json'
        }
    });

    $('#search').on('input', function () {
        const keyword = $(this).val().toLowerCase();
        $('#data-table-all, #data-table-cancel, #data-table-delivery, #data-table-confirm, #data-table-confirmed, #data-table-shipping, #data-table-delivered, #data-table-completed, #data-table-cart, #data-table-sp, #data-table-pgg').DataTable().search(keyword).draw();
    });

    $('#trangThaiFilter').on('change', function () {
        const selectedTrangThai = $(this).val();
        $('#data-table-all, #data-table-cancel, #data-table-delivery, #data-table-confirm, #data-table-confirmed, #data-table-shipping, #data-table-delivered, #data-table-completed, #data-table-cart').DataTable().column(7).search(selectedTrangThai).draw();
    });

    $('#loaiHoaDonFilter').on('change', function () {
        const selectedLoaiHoaDon = $(this).val();
        $('#data-table-all, #data-table-cancel, #data-table-delivery, #data-table-confirm, #data-table-confirmed, #data-table-shipping, #data-table-delivered, #data-table-completed, #data-table-cart').DataTable().column(5).search(selectedLoaiHoaDon).draw();
    });

    $('#maNhanVienFilter').on('change', function () {
        const selectedMaNhanVien = $(this).val();
        $('#data-table-all, #data-table-cancel, #data-table-delivery, #data-table-confirm, #data-table-confirmed, #data-table-shipping, #data-table-delivered, #data-table-completed, #data-table-cart').DataTable().column(3).search(selectedMaNhanVien).draw();
    });

    $('#sanPhamFilter').on('change', function () {
        const selectedTrangThai = $(this).val();
        $('#data-table-sp').DataTable().column(3).search(selectedTrangThai).draw();
    });

<<<<<<< HEAD
    filterColumn('#trangThaiFilter', 7);
    filterColumn('#loaiHoaDonFilter', 5);
    filterColumn('#maNhanVienFilter', 3);
    filterColumn('#sanPhamFilter', 3, ['#data-table-sp']);
    filterColumn('#loaiPhieuFilter', 2, ['#data-table-pgg']);
    filterColumn('#trangThaiPggFilter', 9, ['#data-table-pgg']);
    filterColumn('#gioiTinhAccFilter', 5, ['#data-table-account']);
    filterColumn('#trangThaiAccFilter', 7, ['#data-table-account']);
<<<<<<< HEAD
=======
    filterColumn('#trangThaiSpFilter', 10, ['#data-table-sp']);
>>>>>>> hoa-don
=======
    $('#loaiPhieuFilter').on('change', function () {
        const selectedLoaiPhieu = $(this).val();
        $('#data-table-pgg').DataTable().column(2).search(selectedLoaiPhieu).draw();
    });
>>>>>>> parent of ab5fb6c (table-demo)

    $('#trangThaiPggFilter').on('change', function () {
        const selectedTrangThaiPgg = $(this).val();
        $('#data-table-pgg').DataTable().column(9).search(selectedTrangThaiPgg).draw();
    });

    $('#gioiTinhAccFilter').on('change', function () {
        const selectedGioiTinh = $(this).val();
        $('#data-table-account').DataTable().column(5).search(selectedGioiTinh).draw();
    });

    $('#trangThaiAccFilter').on('change', function () {
        const selectedTrangThai = $(this).val();
        $('#data-table-account').DataTable().column(7).search(selectedTrangThai).draw();
    });

    $('#fromDate, #toDate').on('change', function() {
        const fromDate = $('#fromDate').val();
        const toDate = $('#toDate').val();

        if (fromDate && toDate) {
            $('#data-table-pgg').DataTable().draw();
        }
    });

    $.fn.dataTable.ext.search.push(
        function(settings, data, dataIndex) {
            const fromDate = $('#fromDate').val();
            const toDate = $('#toDate').val();
            const startDate = data[7];
            const endDate = data[8];

<<<<<<< HEAD
                if (!fromDate && !toDate) {
                    return true;
                }
                if (!fromDate && endDate <= toDate) {
                    return true;
                }
                if (fromDate <= startDate && !toDate) {
                    return true;
                }
                if (fromDate <= startDate && endDate <= toDate) {
                    return true;
                }
                return false;
<<<<<<< HEAD
} else if (settings.nTable.id === 'data-table-account') {
=======
            } else if (settings.nTable.id === 'data-table-account') {
>>>>>>> hoa-don
                const fromDate = $('#fromDate').val();
                const toDate = $('#toDate').val();
                const dateStr = data[4];

                if (!fromDate && !toDate) {
                    return true;
                }

                if (fromDate && toDate) {
                    return dateStr >= fromDate && dateStr <= toDate;
                } else if (fromDate) {
                    return dateStr >= fromDate;
                } else if (toDate) {
                    return dateStr <= toDate;
                }

                return true;
            }
            return true;
        }
    );
<<<<<<< HEAD
});
=======
});
>>>>>>> hoa-don
=======
            return (fromDate === '' && toDate === '') ||
                (fromDate === '' && endDate <= toDate) ||
                (fromDate <= startDate && toDate === '') ||
                (fromDate <= startDate && endDate <= toDate);
            
        }
    );

    $('#data-table-pgg').DataTable().on('draw', function() {
        $('#data-table-pgg').DataTable().columns([7, 8]).search('').draw();
    });

});
>>>>>>> parent of ab5fb6c (table-demo)
