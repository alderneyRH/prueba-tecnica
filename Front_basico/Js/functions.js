const API_BASE = "http://localhost:8080/api";

// Función para mostar mensajes

function showMessage(mensaje, type = "success") {
    const messageDiv = document.getElementById("message");
    const messageClass = type === "error" ? "error" : "success";
    messageDiv.innerHTML = `<div class="${messageClass}">${mensaje}</div>`;
    setTimeout(() => (messageDiv.innerHTML = ""), 5000);
}

// Función genérica para hacer peticiones GET
async function fetchData(endpoint) {
  try {
    const response = await fetch(`${API_BASE}${endpoint}`);
    if (!response.ok) {
      throw new Error(`HTTP ${response.status}: ${response.statusText}`);
    }
    return await response.json();
  } catch (error) {
    console.error(`❌ Error fetching ${endpoint}:`, error);
    throw error;
  }
}

// Función para cargar marcas
async function cargarMarcas() {
  try {
    console.log(" Cargando marcas desde API...");
    const marcas = await fetchData("/marcas");
    console.log(" Marcas recibidas:", marcas);

    const marcaSelect = document.getElementById("marca");
    marcaSelect.innerHTML = '<option value="">Seleccione...</option>';

    marcas.forEach((marca) => {
      const option = document.createElement("option");
      option.value = marca.id_marca;
      option.textContent = marca.marca;
      marcaSelect.appendChild(option);
    });

    console.log(` ${marcas.length} marcas cargadas exitosamente`);
  } catch (error) {
    console.error("❌ Error cargando marcas:", error);
    document.getElementById("marca").innerHTML =
      '<option value="">Error cargando marcas</option>';
    showMessage(`Error cargando marcas: ${error.message}`, "error");
  }
}

// Función para cargar países
async function cargarPaises() {
  try {
    console.log(" Cargando países desde API...");
    const paises = await fetchData("/paises");
    console.log(" Países recibidos:", paises);

    const paisSelect = document.getElementById("pais");
    paisSelect.innerHTML = '<option value="">Seleccione...</option>';

    paises.forEach((pais) => {
      const option = document.createElement("option");
      option.value = pais.id_pais;
      option.textContent = pais.nombre_pais;
      paisSelect.appendChild(option);
    });

    console.log(` ${paises.length} países cargados exitosamente`);
  } catch (error) {
    console.error("❌ Error cargando países:", error);
    document.getElementById("pais").innerHTML =
      '<option value="">Error cargando países</option>';
    showMessage(`Error cargando países: ${error.message}`, "error");
  }
}  

// Función para cargar tipos de identificación
async function cargarTiposIdentificacion() {
    try {
      console.log(" Cargando tipos de identificación desde API...");
      const tipos = await fetchData("/tipos-identificaciones");
      console.log(" Tipos de ID recibidos:", tipos);

      const tipoSelect = document.getElementById("tipoId");
      tipoSelect.innerHTML = '<option value="">Seleccione...</option>';

      tipos.forEach((tipo) => {
        const option = document.createElement("option");
        option.value = tipo.id_tipo_identificacion;
        option.textContent = tipo.tipo_identificacion;
        tipoSelect.appendChild(option);
      });

      console.log(
        ` ${tipos.length} tipos de identificación cargados exitosamente`
      );
    } catch (error) {
      console.error("❌ Error cargando tipos ID:", error);
      document.getElementById("tipoId").innerHTML =
        '<option value="">Error cargando tipos</option>';
     showMessage(`Error cargando tipos de identificación: ${error.message}`, 'error');
    }
}

// Función para cargar departamentos por país
async function cargarDepartamentosPorPais(id_pais) {
    try {
        console.log(`🔄 Cargando departamentos para país ID: ${id_pais}`);
        const departamentos = await fetchData(`/departamentos/por-pais/${id_pais}`);
        console.log('✅ Departamentos recibidos:', departamentos);
        
        const departamentoSelect = document.getElementById('departamento');
        departamentoSelect.innerHTML = '<option value="">Seleccione...</option>';
        
        departamentos.forEach(dept => {
            const option = document.createElement('option');
            option.value = dept.id_departamento;
            option.textContent = dept.nombre_departamento;
            departamentoSelect.appendChild(option);
        });
        
        departamentoSelect.disabled = false;
        console.log(`✅ ${departamentos.length} departamentos cargados`);
        
    } catch (error) {
        console.error('❌ Error cargando departamentos:', error);
        showMessage(`Error cargando departamentos: ${error.message}`, 'error');
    }
}

// Función para cargar ciudades por departamento  
async function cargarCiudadesPorDepartamento(id_departamento) {
    try {
        console.log(`🔄 Cargando ciudades para departamento ID: ${id_departamento}`);
        const ciudades = await fetchData(`/ciudades/por-departamento/${id_departamento}`);
        console.log('✅ Ciudades recibidas:', ciudades);
        
        const ciudadSelect = document.getElementById('ciudad');
        ciudadSelect.innerHTML = '<option value="">Seleccione...</option>';
        
        ciudades.forEach(ciudad => {
            const option = document.createElement('option');
            option.value = ciudad.id_ciudad;
            option.textContent = ciudad.nombre_ciudad;
            ciudadSelect.appendChild(option);
        });
        
        ciudadSelect.disabled = false;
        console.log(`✅ ${ciudades.length} ciudades cargadas`);
        
    } catch (error) {
        console.error('❌ Error cargando ciudades:', error);
        showMessage(`Error cargando ciudades: ${error.message}`, 'error');
    }
}

// Función para crear cliente
async function crearCliente(datosCliente) {
    try {
        console.log('Creando cliente:', datosCliente);
        
        const response = await fetch(`${API_BASE}/clientes`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(datosCliente)
        });
        
        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }
        
        const cliente = await response.json();
        console.log('Cliente creado:', cliente);
        return cliente;
        
    } catch (error) {
        console.error('Error creando cliente:', error);
        throw error;
    }
}

// Función para crear fidelización
async function crearFidelizacion(clienteId, marcaId) {
    try {
        const fidelizacionData = {
            clienteId: clienteId,
            marcaId: marcaId
        };
        
        console.log('Creando fidelización:', fidelizacionData);
        
        const response = await fetch(`${API_BASE}/fidelizaciones`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(fidelizacionData)
        });
        
        if (!response.ok) {
            throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }
        
        const fidelizacion = await response.json();
        console.log('Fidelización creada:', fidelizacion);
        return fidelizacion;
        
    } catch (error) {
        console.error('Error creando fidelización:', error);
        throw error;
    }
}
