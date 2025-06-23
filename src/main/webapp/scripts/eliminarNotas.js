$(document).ready(function () {
    $('.eliminar-btn').on('click', function () {
        const idAlumno = $(this).data('id');

        if (confirm("¿queres eliminar las notas de este alumno?")) {
            $.ajax({
                url: 'EliminarNotaServlet',
                type: 'POST',
                data: { idAlumno: idAlumno },
                success: function (response) {
                    alert("Notas eliminadas correctamente.");
                    location.reload(); 
                },
                error: function (xhr, status, error) {
                    console.error(error);
                    alert("Hubo un error al eliminar las notas.");
                }
            });
        }
    });
});