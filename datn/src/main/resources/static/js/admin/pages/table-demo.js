$(document).ready(function () {
    $('#data-table-all, #data-table-cancel, #data-table-delivery, #data-table-confirm, #data-table-confirmed, #data-table-shipping, #data-table-delivered, #data-table-completed, #data-table-cart, #data-table-sp, #data-table-pgg, #data-table-account, #data-table-cl').DataTable({
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

    $('#loaiPhieuFilter').on('change', function () {
        const selectedLoaiPhieu = $(this).val();
        $('#data-table-pgg').DataTable().column(2).search(selectedLoaiPhieu).draw();
    });

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
