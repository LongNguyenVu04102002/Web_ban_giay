$(document).ready(function () {
    const tables = [
        '#data-table-all', '#data-table-cancel', '#data-table-delivery',
        '#data-table-confirm', '#data-table-confirmed', '#data-table-shipping',
        '#data-table-delivered', '#data-table-completed', '#data-table-cart',
        '#data-table-sp', '#data-table-pgg', '#data-table-account'
    ];

    const initializeDataTable = (selector) => {
        $(selector).DataTable({
            paging: true,
            searching: true,
            ordering: true,
            info: true,
            lengthChange: false,
            language: {
                url: '//cdn.datatables.net/plug-ins/1.10.25/i18n/Vietnamese.json'
            }
        });
    };

    tables.forEach(initializeDataTable);

    const applyGlobalSearch = (event) => {
        const keyword = $(event.target).val().toLowerCase();
        tables.forEach(selector => {
            $(selector).DataTable().search(keyword).draw();
        });
    };

    $('#search').on('input', applyGlobalSearch);

    const filterColumn = (filterSelector, columnIndex, tableSelector = tables) => {
        $(filterSelector).on('change', function () {
            const value = $(this).val();
            tableSelector.forEach(selector => {
                $(selector).DataTable().column(columnIndex).search(value).draw();
            });
        });
    };

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

    const filterDateRange = () => {
        const fromDate = $('#fromDate').val();
        const toDate = $('#toDate').val();

        if (fromDate && toDate) {
            $('#data-table-pgg').DataTable().draw();
            $('#data-table-account').DataTable().draw();
        }
    };

    $('#fromDate, #toDate').on('change', filterDateRange);

    $.fn.dataTable.ext.search.push(
        function (settings, data, dataIndex) {
            if (settings.nTable.id === 'data-table-pgg') {
                const fromDate = $('#fromDate').val();
                const toDate = $('#toDate').val();
                const startDate = data[7];
                const endDate = data[8];

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
