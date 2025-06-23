
$(document).ready(function () {
    $('.eliminar-btn').on('click', function () {
        const idAlumno = $(this).data('id');

        if (confirm("¿Querés eliminar al alumno y sus notas?")) {
            $.ajax({
                url: 'EliminarAlumno',
                type: 'POST',
                data: { id: idAlumno }, 
                success: function (response) {
                    if(response.status === "success"){
                        alert("Alumno eliminado correctamente.");
                        location.reload(); 
                    } else {
                        alert("No se pudo eliminar al alumno.");
                    }
                },
                error: function (xhr, status, error) {
                    console.error(error);
                    alert("Hubo un error al eliminar al alumno.");
                }
            });
        }
    });
});