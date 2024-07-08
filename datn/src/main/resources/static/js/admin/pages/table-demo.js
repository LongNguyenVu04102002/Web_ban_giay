$(document).ready(function () {
    $('#data-table-all, #data-table-cancel, #data-table-delivery, #data-table-confirm, #data-table-confirmed, #data-table-shipping, #data-table-delivered, #data-table-completed, #data-table-cart').DataTable({
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
        $('#data-table-all, #data-table-cancel, #data-table-delivery, #data-table-confirm, #data-table-confirmed, #data-table-shipping, #data-table-delivered, #data-table-completed, #data-table-cart').DataTable().search(keyword).draw();
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

});
