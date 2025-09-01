package com.example.loyaltyapi.servicios;


import com.example.loyaltyapi.modelos.Ciudad;
import com.example.loyaltyapi.modelos.Cliente;
import com.example.loyaltyapi.modelos.TipoIdentificacion;
import com.example.loyaltyapi.repositorios.ICiudadRepositorio;
import com.example.loyaltyapi.repositorios.IClienteRepositorio;
import com.example.loyaltyapi.repositorios.ITipoIdentificacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio {

    @Autowired
    private IClienteRepositorio clienteRepositorio;

    @Autowired
    private ICiudadRepositorio ciudadRepositorio;

    @Autowired
    private ITipoIdentificacionRepositorio tipoIdentificacionRepositorio;

    public Cliente crearCliente(Cliente cliente) {
// Validaciones básicas
        if (cliente == null) {
            throw new IllegalArgumentException("Los datos del cliente no pueden ser nulos");
        }

        if (cliente.getNumero_identificacion() == null || cliente.getNumero_identificacion().trim().isEmpty()) {
            throw new IllegalArgumentException("El número de identificación es obligatorio");
        }

        if (cliente.getNombre_cliente() == null || cliente.getNombre_cliente().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio");
        }

        if (cliente.getApellidos_cliente() == null || cliente.getApellidos_cliente().trim().isEmpty()) {
            throw new IllegalArgumentException("Los apellidos del cliente son obligatorios");
        }

        // Verificar que no exista un cliente con el mismo número de identificación
        Optional<Cliente> clienteExistente = clienteRepositorio.findByNumero_identificacion(
                cliente.getNumero_identificacion().trim()
        );
        if (clienteExistente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un cliente con el número de identificación: " +
                    cliente.getNumero_identificacion());
        }

        // Validar y obtener el tipo de identificación
        if (cliente.getTipoIdentificacion() == null || cliente.getTipoIdentificacion().getId_tipo_identificacion() == null) {
            throw new IllegalArgumentException("El tipo de identificación es obligatorio");
        }

        Optional<TipoIdentificacion> tipoIdentificacion = tipoIdentificacionRepositorio.findById(
                cliente.getTipoIdentificacion().getId_tipo_identificacion()
        );
        if (!tipoIdentificacion.isPresent()) {
            throw new IllegalArgumentException("Tipo de identificación no válido");
        }

        // Validar y obtener la ciudad
        if (cliente.getCiudad() == null || cliente.getCiudad().getId_ciudad() == null) {
            throw new IllegalArgumentException("La ciudad es obligatoria");
        }

        Optional<Ciudad> ciudad = ciudadRepositorio.findById(cliente.getCiudad().getId_ciudad());
        if (!ciudad.isPresent()) {
            throw new IllegalArgumentException("Ciudad no válida");
        }

        // Establecer las entidades completas
        cliente.setTipoIdentificacion(tipoIdentificacion.get());
        cliente.setCiudad(ciudad.get());

        // Limpiar datos de entrada
        cliente.setNumero_identificacion(cliente.getNumero_identificacion().trim());
        cliente.setNombre_cliente(cliente.getNombre_cliente().trim());
        cliente.setApellidos_cliente(cliente.getApellidos_cliente().trim());
        if (cliente.getDireccion() != null) {
            cliente.setDireccion(cliente.getDireccion().trim());
        }

        // Guardar y retornar el cliente
        return clienteRepositorio.save(cliente);
    }

    // * Obtener todos los clientes
    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepositorio.findAllOrderedByName();
    }

    // Buscar cliente por ID
    public Optional<Cliente> buscarPorId(Integer id_cliente) {
        if (id_cliente == null || id_cliente <= 0) {
            throw new IllegalArgumentException("El ID del cliente debe ser un número positivo");
        }
        return clienteRepositorio.findById(id_cliente);
    }

    //  * Buscar clientes por nombre
    public List<Cliente> buscarPorNombre(String nombre_cliente) {
        if (nombre_cliente == null || nombre_cliente.trim().isEmpty()) {
            return obtenerTodosLosClientes();
        }
        return clienteRepositorio.findByNombre_clienteContainingIgnoreCase(nombre_cliente.trim());
    }

    // * Buscar clientes por apellidos
    public List<Cliente> buscarPorApellidos(String apellidos_cliente) {
        if (apellidos_cliente == null || apellidos_cliente.trim().isEmpty()) {
            return obtenerTodosLosClientes();
        }
        return clienteRepositorio.findByApellidos_clienteContainingIgnoreCase(apellidos_cliente.trim());
    }

    // * Buscar clientes por ciudad (usando ID de ciudad)
    public List<Cliente> buscarClientesPorCiudad(Integer id_ciudad) {
        if (id_ciudad == null || id_ciudad <= 0) {
            throw new IllegalArgumentException("El ID de la ciudad debe ser un número positivo");
        }

        if (!ciudadRepositorio.existsById(id_ciudad)) {
            throw new IllegalArgumentException("No se encontró la ciudad con ID: " + id_ciudad);
        }

        return clienteRepositorio.findByCiudadId(id_ciudad);
    }

    //  * Buscar clientes por nombre completo (nombre + apellidos)

    public List<Cliente> buscarPorNombreCompleto(String nombreCompleto) {
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            return obtenerTodosLosClientes();
        }
        return clienteRepositorio.findByNombreCompletoContaining(nombreCompleto.trim());
    }

    //  * Buscar cliente por número de identificación exacto

    public Optional<Cliente> buscarClientePorNumeroIdentificacion(String numero_identificacion) {
        if (numero_identificacion == null || numero_identificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("El número de identificación no puede estar vacío");
        }
        return clienteRepositorio.findByNumero_identificacion(numero_identificacion.trim());
    }

}

