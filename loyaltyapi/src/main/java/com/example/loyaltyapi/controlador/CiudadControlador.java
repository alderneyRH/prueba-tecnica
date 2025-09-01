package com.example.loyaltyapi.controlador;

import com.example.loyaltyapi.modelos.Ciudad;
import com.example.loyaltyapi.servicios.CiudadServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ciudades")

public class CiudadControlador {

    @Autowired
    private CiudadServicio ciudadServicio;

    //          * Obtener todas las ciudades
    //         GET /api/ciudades
    @GetMapping
    public ResponseEntity<List<Ciudad>> obtenerTodasLasCiudades() {
        try {
            List<Ciudad> ciudades = ciudadServicio.obtenerTodasLasCiudades();
            return ResponseEntity.ok(ciudades);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //          * Buscar ciudad por ID
    //         * GET /api/ciudades/{id}

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerCiudadPorId(@PathVariable Integer id_ciudad) {
        try {
            Optional<Ciudad> ciudad = ciudadServicio.buscarPorId(id_ciudad);
            if (ciudad.isPresent()) {
                return ResponseEntity.ok(ciudad.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    //      * Buscar ciudad por nombre exacto
    //      * GET /api/ciudades/buscar?nombre={nombre}
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre_ciudad) {
        try {
            Optional<Ciudad> ciudad = ciudadServicio.buscarPorNombre(nombre_ciudad);
            if (ciudad.isPresent()) {
                return ResponseEntity.ok(ciudad.get());
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

    //       * Buscar ciudades por departamento
    //     * GET /api/ciudades/por-departamento/{id_Departamento}
    @GetMapping("/por-departamento/{id_departamento}")
    public ResponseEntity<?> buscarPorDepartamento(@PathVariable Integer id_departamento) {
        try {
            List<Ciudad> ciudades = ciudadServicio.buscarPorDepartamento(id_departamento);
            return ResponseEntity.ok(ciudades);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    //         * Contar clientes por ciudad
    //     * GET /api/ciudades/{id}/clientes-count
    @GetMapping("/{id}/clientes-count")
    public ResponseEntity<?> contarClientesPorCiudad(@PathVariable Integer id_ciudad) {
        try {
            Long count = ciudadServicio.contarClientesPorCiudad(id_ciudad);
            return ResponseEntity.ok(new ClientesCountResponse(count));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    public static class ClientesCountResponse {
        private Long count;

        public ClientesCountResponse(Long count) {
            this.count = count;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }
    }
}

