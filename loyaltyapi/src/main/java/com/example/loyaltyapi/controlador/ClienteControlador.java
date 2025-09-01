package com.example.loyaltyapi.controlador;

import com.example.loyaltyapi.modelos.Cliente;
import com.example.loyaltyapi.servicios.ClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping ("/api/clientes")
public class ClienteControlador {

    @Autowired
    private ClienteServicio clienteServicio;

    // crear cliente
    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
        try {
            Cliente nuevoCliente = clienteServicio.crearCliente(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Datos inválidos",
                    "message", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "error", "Error interno del servidor",
                    "message", "No se pudo crear el cliente"
            ));
        }
    }

    //      * Obtener todos los clientes ordenados alfabéticamente
    //     * GET /api/clientes

    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        try {
            List<Cliente> clientes = clienteServicio.obtenerTodosLosClientes();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //* Buscar cliente por ID
     // GET /api/clientes/{id}

    @GetMapping("/{id_cliente}")
    public ResponseEntity<?> obtenerClientePorId(@PathVariable Integer id_cliente) {
        try {
            Optional<Cliente> cliente = clienteServicio.buscarPorId(id_cliente);
            if (cliente.isPresent()) {
                return ResponseEntity.ok(cliente.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    //      * Buscar cliente por número de identificación
    //     * GET /api/clientes/buscar-identificacion?numero={numero}
    @GetMapping("/buscar-identificacion")
    public ResponseEntity<?> buscarPorNumeroIdentificacion(@RequestParam String numero_identificacion) {
        try {
            Optional<Cliente> cliente = clienteServicio.buscarClientePorNumeroIdentificacion(numero_identificacion);
            if (cliente.isPresent()) {
                return ResponseEntity.ok(cliente.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    //          * Buscar clientes por nombre
    //     * GET /api/clientes/buscar-nombre?nombre={nombre}
    @GetMapping("/buscar-nombre")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre_cliente) {
        try {
            List<Cliente> clientes = clienteServicio.buscarPorNombre(nombre_cliente);
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    //          * Buscar clientes por apellidos
    //     * GET /api/clientes/buscar-apellidos?apellidos={apellidos}
    @GetMapping("/buscar-apellidos")
    public ResponseEntity<?> buscarPorApellidos(@RequestParam String apellidos_cliente) {
        try {
            List<Cliente> clientes = clienteServicio.buscarPorApellidos(apellidos_cliente);
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    //          * Buscar clientes por nombre completo (nombre + apellidos)
    //     * GET /api/clientes/buscar-completo?nombre={nombreCompleto}
    @GetMapping("/buscar-completo")
    public ResponseEntity<?> buscarPorNombreCompleto(@RequestParam String nombreCompleto) {
        try {
            List<Cliente> clientes = clienteServicio.buscarPorNombreCompleto(nombreCompleto);
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    //         * Buscar clientes por ciudad
    //     * GET /api/clientes/por-ciudad/{idCiudad}
    @GetMapping("/por-ciudad/{idCiudad}")
    public ResponseEntity<?> buscarPorCiudad(@PathVariable Integer id_ciudad) {
        try {
            List<Cliente> clientes = clienteServicio.buscarClientesPorCiudad(id_ciudad);
            return ResponseEntity.ok(clientes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

}
