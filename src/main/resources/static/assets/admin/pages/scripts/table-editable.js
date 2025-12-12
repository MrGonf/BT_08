var TableEditable = (function () {
    var oTable;
    var nEditing = null;
    var nNew = false;

    function handleTable() {
        var table = $("#sample_editable_1");

        // Destroy DataTable nếu đã khởi tạo
        if ($.fn.DataTable.isDataTable(table)) {
            table.DataTable().destroy();
        }

        oTable = table.DataTable({
            ajax: {
                url: '/admin/category/api',
                dataSrc: ''
            },
            columns: [
                { data: 'categoryId' },
                { data: 'categoryName' },
                { data: 'categoryCode' },
                {
                    data: 'status',
                    render: function(data) {
                        return data
                            ? '<span class="badge bg-green">Active</span>'
                            : '<span class="badge bg-gray">Deactive</span>';
                    }
                },
                { data: null, defaultContent: '<a class="edit" href="#">Edit</a>' },
                { data: null, defaultContent: '<a class="delete" href="#">Delete</a>' }
            ]
        });

        // ---------------- Edit row ----------------
        function editRow(nRow) {
            var data = oTable.row(nRow).data() || {};
            var jqTds = $(">td", nRow);

            jqTds.eq(1).html(`<input type="text" class="form-control" value="${data.categoryName || ''}">`);
            jqTds.eq(2).html(`<input type="text" class="form-control" value="${data.categoryCode || ''}">`);
            jqTds.eq(3).html(`
                <select class="form-control">
                    <option value="true" ${data.status ? 'selected' : ''}>Active</option>
                    <option value="false" ${!data.status ? 'selected' : ''}>Deactive</option>
                </select>
            `);

            jqTds.eq(4).html('<a class="save" href="#">Save</a>');
            jqTds.eq(5).html('<a class="cancel" href="#">Cancel</a>');
        }

        // ---------------- Save row ----------------
        function saveRow(nRow) {
            var $inputs = $("input, select", nRow);
            var data = oTable.row(nRow).data() || {};

            var categoryId = data.categoryId ?? null;

            var payload = {
                categoryName: $inputs.eq(0).val(),
                categoryCode: $inputs.eq(1).val(),
                status: $inputs.eq(2).val() === "true"
            };

            $.ajax({
                url: categoryId ? "/admin/category/api/" + categoryId : "/admin/category/api",
                type: categoryId ? "PUT" : "POST",
                contentType: "application/json",
                data: JSON.stringify(payload),
                success: function(res) {
                    if (!categoryId) {
                        // Row mới, add vào DataTable
                        oTable.row(nRow).data(res).draw(false);
                    } else {
                        // Row cũ, update
                        oTable.row(nRow).data(res).draw(false);
                    }
                    nEditing = null;
                    nNew = false;
                    alert("Saved successfully!");
                },
                error: function() {
                    alert("Error saving row!");
                }
            });
        }

        // ---------------- Cancel edit ----------------
        function cancelEdit(nRow) {
            if (nNew) {
                oTable.row(nRow).remove().draw();
                nNew = false;
            } else {
                oTable.row(nRow).invalidate().draw(false);
            }
            nEditing = null;
        }

        // ---------------- Add new ----------------
        $("#sample_editable_1_new").off().on("click", function(e) {
            e.preventDefault();
            if (nEditing) cancelEdit(nEditing);

            var aiNew = oTable.row.add({
                categoryId: null,
                categoryName: '',
                categoryCode: '',
                status: true
            }).draw().node();

            nEditing = aiNew;
            nNew = true;
            editRow(aiNew);
        });

        // ---------------- Delete row ----------------
        table.off("click", ".delete").on("click", ".delete", function(e) {
            e.preventDefault();
            if (!confirm("Are you sure to delete this row?")) return;

            var nRow = $(this).closest("tr");
            var data = oTable.row(nRow).data();

            $.ajax({
                url: "/admin/category/api/" + data.categoryId,
                type: "DELETE",
                success: function() {
                    oTable.row(nRow).remove().draw(false);
                    alert("Deleted successfully!");
                },
                error: function() {
                    alert("Error deleting row!");
                }
            });
        });

        // ---------------- Cancel ----------------
        table.off("click", ".cancel").on("click", ".cancel", function(e) {
            e.preventDefault();
            cancelEdit($(this).closest("tr")[0]);
        });

        // ---------------- Edit / Save ----------------
        table.off("click", ".edit, .save").on("click", ".edit, .save", function(e) {
            e.preventDefault();
            var nRow = $(this).closest("tr")[0];

            if (nEditing && nEditing !== nRow) cancelEdit(nEditing);

            if ($(this).hasClass("edit")) {
                editRow(nRow);
                nEditing = nRow;
            } else if ($(this).hasClass("save")) {
                saveRow(nRow);
            }
        });
    }

    return {
        init: function() {
            handleTable();
        }
    };
})();

$(document).ready(function() {
    TableEditable.init();
});
