package com.example.loyaltyapi.controlador;

import com.example.loyaltyapi.modelos.Cliente;
import com.example.loyaltyapi.modelos.Fidelizacion;
import com.example.loyaltyapi.modelos.Marca;
import com.example.loyaltyapi.servicios.ClienteServicio;
import com.example.loyaltyapi.servicios.FidelizacionServicio;
import com.example.loyaltyapi.servicios.MarcaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/fidelizaciones")
public class FidelizacionControlador {

    @Autowired
    private FidelizacionServicio fidelizacionServicio;

    @Autowired
    private ClienteServicio clienteServicio;

    @Autowired
    private MarcaServicio marcaServicio;

    // crear fidelizacion
    @PostMapping
    public ResponseEntity<?> crearFidelizacion(@RequestBody Map<String, Integer> datos) {
        try {
            Integer clienteId = datos.get("clienteId");
            Integer marcaId = datos.get("marcaId");

            if (clienteId == null || marcaId == null) {
                return ResponseEntity.badRequest().body(Map.of(
                        "error", "Datos incompletos",
                        "message", "Se requieren clienteId y marcaId"
                ));
            }

            Fidelizacion nuevaFidelizacion = fidelizacionServicio.crearFidelizacion(clienteId, marcaId);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaFidelizacion);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Datos inválidos",
                    "message", e.getMessage()
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "error", "Error interno del servidor",
                    "message", "No se pudo crear la fidelización"
            ));
        }
    }

    //     * Buscar fidelización específica por cliente y marca
    //     * GET /api/fidelizacion/buscar?clienteId=1&marcaId=1

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarFidelizacion(
            @RequestParam Integer id_cliente,
            @RequestParam Integer id_marca) {
        try {
            Optional<Cliente> cliente = clienteServicio.buscarPorId(id_cliente);
            Optional<Marca> marca = marcaServicio.buscarPorId(id_marca);

            if (cliente.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("Cliente con ID " + id_cliente + " no encontrado");
            }

            if (marca.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("Marca con ID " + id_marca + " no encontrada");
            }

            Optional<Fidelizacion> fidelizacion = fidelizacionServicio
                    .buscarFidelizacion(cliente.get(), marca.get());

            if (fidelizacion.isPresent()) {
                return ResponseEntity.ok(fidelizacion.get());
            } else {
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar la fidelización: " + e.getMessage());
        }
    }

    //     * Verificar si existe fidelización entre cliente y marca
    //     * GET /api/fidelizacion/existe?clienteId=1&marcaId=1

    @GetMapping("/existe")
    public ResponseEntity<?> existeFidelizacion(
            @RequestParam Integer id_cliente,
            @RequestParam Integer id_marca) {
        try {
            Optional<Cliente> cliente = clienteServicio.buscarPorId(id_cliente);
            Optional<Marca> marca = marcaServicio.buscarPorId(id_marca);

            if (cliente.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("Cliente con ID " + id_cliente + " no encontrado");
            }

            if (marca.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("Marca con ID " + id_marca + " no encontrada");
            }

            boolean existe = fidelizacionServicio
                    .existeFidelizacion(cliente.get(), marca.get());

            return ResponseEntity.ok(Map.of(
                    "existe", existe,
                    "clienteId", id_cliente,
                    "marcaId", id_marca
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al verificar la fidelización: " + e.getMessage());
        }
    }

    //     * Buscar todas las fidelizaciones de una marca
    //     * GET /api/fidelizacion/marca/1
    @GetMapping("/marca/{marcaId}")
    public ResponseEntity<?> buscarFidelizacionesPorMarca(@PathVariable Integer id_marca) {
        try {
            Optional<Marca> marca = marcaServicio.buscarPorId(id_marca);

            if (marca.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("Marca con ID " + id_marca + " no encontrada");
            }

            List<Fidelizacion> fidelizaciones = fidelizacionServicio
                    .buscarFidelizacionesPorMarca(marca.get());

            return ResponseEntity.ok(fidelizaciones);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar fidelizaciones por marca: " + e.getMessage());
        }
    }

    //        * Buscar todas las fidelizaciones de un cliente
    //     * GET /api/fidelizacion/cliente/
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<?> buscarFidelizacionesPorCliente(@PathVariable Integer id_cliente) {
        try {
            Optional<Cliente> cliente = clienteServicio.buscarPorId(id_cliente);

            if (cliente.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("Cliente con ID " + id_cliente + " no encontrado");
            }

            List<Fidelizacion> fidelizaciones = fidelizacionServicio
                    .buscarFidelizacionesPorCliente(cliente.get());

            return ResponseEntity.ok(fidelizaciones);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar fidelizaciones por cliente: " + e.getMessage());
        }
    }

    //     * Obtener todas las fidelizaciones ordenadas por fecha (más recientes primero)
    //     * GET /api/fidelizacion/todas
    @GetMapping("/todas")
    public ResponseEntity<?> obtenerTodasLasFidelizaciones() {
        try {
            List<Fidelizacion> fidelizaciones = fidelizacionServicio
                    .obtenerTodasLasFidelizacionesOrdenadas();
            return ResponseEntity.ok(fidelizaciones);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener todas las fidelizaciones: " + e.getMessage());
        }
    }

    //         * Obtener estadísticas de fidelizaciones por marca
    //     * GET /api/fidelizacion/estadisticas/marca
    @GetMapping("/estadisticas/marca")
    public ResponseEntity<?> obtenerEstadisticasPorMarca() {
        try {
            Map<Marca, Long> estadisticas = fidelizacionServicio.obtenerEstadisticasPorMarca();
            return ResponseEntity.ok(estadisticas);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener estadísticas por marca: " + e.getMessage());
        }
    }

    //       * Obtener estadísticas de fidelizaciones por cliente
    //     * GET /api/fidelizacion/estadisticas/cliente
    @GetMapping("/estadisticas/cliente")
    public ResponseEntity<?> obtenerEstadisticasPorCliente() {
        try {
            Map<Cliente, Long> estadisticas = fidelizacionServicio.obtenerEstadisticasPorCliente();
            return ResponseEntity.ok(estadisticas);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al obtener estadísticas por cliente: " + e.getMessage());
        }
    }
}
