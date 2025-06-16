$(document).ready(function () {
    console.log("guardarNotas.js cargado"); // para verificar que el archivo se carga

    $(".guardarBtn").click(function () {
        const row = $(this).closest("tr");
        const idNota = $(this).data("id");
        const nota1 = row.find(".nota1").val();
        const nota2 = row.find(".nota2").val();
        const nota3 = row.find(".nota3").val();

        $.ajax({
            url: "guardar-nota",
            method: "POST",
            data: {
                idNota: idNota,
                nota1: nota1,
                nota2: nota2,
                nota3: nota3
            },
            success: function (response) {
                alert("Notas guardadas correctamente");
                location.reload(); // Refresca para mostrar el nuevo promedio
            },
            error: function () {
                alert("Error al guardar la nota");
            }
        });
    });
});