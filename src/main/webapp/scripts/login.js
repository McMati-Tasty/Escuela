
$(document).ready(function() {
    console.log("Script de login inicializado");
    
    $('#formulario-login').on('submit', function(e) {
        e.preventDefault();
        console.log("Formulario interceptado");
        
        
        $('#mensaje-ajax')
            .html('<span class="spinner">⌛</span> Validando credenciales...')
            .removeClass('error exito')
            .addClass('cargando')
            .show();
        
       
        $.ajax({
            type: 'POST',
            url: '/login',  
            data: $(this).serialize(),
            dataType: 'json',
            success: function(respuesta) {
                if(respuesta.exito) {
                    $('#mensaje-ajax')
                        .html('✅ ' + respuesta.mensaje)
                        .removeClass('cargando error')
                        .addClass('exito');
                    
                    setTimeout(function() {
                        window.location.href = respuesta.redireccion;
                    }, 1500);
                } else {
                    $('#mensaje-ajax')
                        .html('❌ ' + respuesta.error)
                        .removeClass('cargando exito')
                        .addClass('error');
                }
            },
            error: function(xhr) {
                console.error("Error en AJAX:", xhr.status, xhr.responseText);
                $('#mensaje-ajax')
                    .html('⚠️ Error de conexión: ' + xhr.status)
                    .removeClass('cargando exito')
                    .addClass('error');
            }
        });
    });
});