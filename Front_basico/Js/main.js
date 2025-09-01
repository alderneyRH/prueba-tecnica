document.addEventListener('DOMContentLoaded', () => {
    console.log('🚀 Iniciando aplicación...');
    
    cargarMarcas();
    cargarPaises();
    cargarTiposIdentificacion();
    
    
    // Event listeners para selects dependientes
document.getElementById('pais').addEventListener('change', (e) => {
    const idPais = e.target.value;
    
    if (idPais) {
        cargarDepartamentosPorPais(idPais);
    } else {
        // Resetear departamentos y ciudades
        document.getElementById('departamento').innerHTML = '<option value="">Seleccione un país primero</option>';
        document.getElementById('departamento').disabled = true;
        document.getElementById('ciudad').innerHTML = '<option value="">Seleccione un departamento primero</option>';
        document.getElementById('ciudad').disabled = true;
    }
});

    // Event listener para select de departamento
document.getElementById('departamento').addEventListener('change', (e) => {
    const idDepartamento = e.target.value;
    
    if (idDepartamento) {
        cargarCiudadesPorDepartamento(idDepartamento);
    } else {
        document.getElementById('ciudad').innerHTML = '<option value="">Seleccione un departamento primero</option>';
        document.getElementById('ciudad').disabled = true;
    }
});

    // Manejar envío del formulario
    document.getElementById("registroForm").addEventListener("submit", async function(e) {
        e.preventDefault();
        
        const submitBtn = document.getElementById('submitBtn');
        submitBtn.disabled = true;
        submitBtn.textContent = 'Registrando...';
        
        try {
            const formData = new FormData(this);
            
            // Preparar datos del cliente
            const clienteData = {
                tipoIdentificacion: {
                    id_tipo_identificacion: parseInt(formData.get('tipoId'))
                },
                numero_identificacion: formData.get('numeroId'),
                nombre_cliente: formData.get('nombres'),
                apellidos_cliente: formData.get('apellidos'),
                fecha_nacimiento: formData.get('fechaNacimiento'),
                direccion: formData.get('direccion'),
                ciudad: {
                    id_ciudad: parseInt(formData.get('ciudad'))
                }
            };
            
            // 1. Crear cliente
            const cliente = await crearCliente(clienteData);
            
            // 2. Crear fidelización
            const marcaId = parseInt(formData.get('marca'));
            await crearFidelizacion(cliente.id_cliente, marcaId);
            
            showMessage('Cliente registrado y fidelización creada exitosamente!', 'success');
            this.reset();
            
            // Resetear selects dependientes
            document.getElementById('departamento').disabled = true;
            document.getElementById('ciudad').disabled = true;
            
        } catch (error) {
            console.error('Error en el registro:', error);
            showMessage(`Error: ${error.message}`, 'error');
        } finally {
            submitBtn.disabled = false;
            submitBtn.textContent = 'Registrarse';
        }
    });
});

