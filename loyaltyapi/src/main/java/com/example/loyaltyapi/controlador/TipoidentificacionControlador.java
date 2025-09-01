package com.example.loyaltyapi.controlador;

import com.example.loyaltyapi.modelos.TipoIdentificacion;
import com.example.loyaltyapi.servicios.TipoidentificacionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/tipos-identificaciones")
public class TipoidentificacionControlador {

    @Autowired
     private TipoidentificacionServicio tipoidentificacionServicio;

     //     * Crear un nuevo tipo de identificación

    @PostMapping
    public ResponseEntity<?> crearTipoIdentificacion(@RequestBody TipoIdentificacion tipo_identificacion) {
        try {
            TipoIdentificacion tipoCreado = tipoidentificacionServicio.crearTipoIdentificacion(tipo_identificacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(tipoCreado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    //    * Obtener todos los tipos de identificación ordenados alfabéticamente

    @GetMapping
    public ResponseEntity<List<TipoIdentificacion>> obtenerTodosLosTipos() {
        try {
            List<TipoIdentificacion> tipos = tipoidentificacionServicio.obtenerTodosLosTipos();
            return ResponseEntity.ok(tipos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //     * Buscar tipos de identificación que contengan parte del texto

    @GetMapping("/buscar-parcial")
    public ResponseEntity<?> buscarPorTipoParcial(@RequestParam String textoParcial) {
        try {
            List<TipoIdentificacion> tipos = tipoidentificacionServicio.buscarPorTipoParcial(textoParcial);
            return ResponseEntity.ok(tipos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    //      * Buscar tipo de identificación por tipo exacto

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorTipo(@RequestParam String tipo_identificacion) {
        try {
            Optional<TipoIdentificacion> tipoIdentificacion = tipoidentificacionServicio.buscarPorTipo(tipo_identificacion);
            if (tipoIdentificacion.isPresent()) {
                return ResponseEntity.ok(tipoIdentificacion.get());
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
}
