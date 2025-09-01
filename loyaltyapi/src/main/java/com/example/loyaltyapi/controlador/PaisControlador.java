package com.example.loyaltyapi.controlador;

import com.example.loyaltyapi.modelos.Pais;
import com.example.loyaltyapi.servicios.PaisServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/paises")
public class PaisControlador {

    @Autowired
    private PaisServicio paisServicio;

    // * Crear un nuevo país

    @PostMapping
    public ResponseEntity<?> crearPais(@RequestBody Pais pais) {
        try {
            Pais paisCreado = paisServicio.crearPais(pais);
            return ResponseEntity.status(HttpStatus.CREATED).body(paisCreado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    // * Obtener todos los países ordenados alfabéticamente

    @GetMapping
    public ResponseEntity<List<Pais>> obtenerTodosLosPaises() {
        try {
            List<Pais> paises = paisServicio.obtenerTodosLosPaises();
            return ResponseEntity.ok(paises);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // * Buscar país por nombre exacto

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre_pais) {
        try {
            Optional<Pais> pais = paisServicio.buscarPorNombre(nombre_pais);
            if (pais.isPresent()) {
                return ResponseEntity.ok(pais.get());
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

    // * buscar paises que contengan parte del nombre

    @GetMapping("/buscar-parcial")
    public ResponseEntity<?> buscarPorNombreParcial(@RequestParam String nombreParcial) {
        try {
            List<Pais> paises = paisServicio.buscarPorNombreParcial(nombreParcial);
            return ResponseEntity.ok(paises);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }
}
