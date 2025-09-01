package com.example.loyaltyapi.controlador;

import com.example.loyaltyapi.modelos.Marca;
import com.example.loyaltyapi.servicios.MarcaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/marcas")
public class MarcaControlador {

    @Autowired
     private MarcaServicio marcaServicio;

      //      * Crear una nueva marca
    //     * POST /api/marcas

    @PostMapping
    public ResponseEntity<?> crearMarca(@RequestBody Marca marca) {
        try {
            Marca marcaCreada = marcaServicio.crearMarca(marca);
            return ResponseEntity.status(HttpStatus.CREATED).body(marcaCreada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

       //       * Obtener todas las marcas ordenadas alfabéticamente
    //     * GET /api/marcas

    @GetMapping
    public ResponseEntity<List<Marca>> obtenerTodasLasMarcas() {
        try {
            List<Marca> marcas = marcaServicio.obtenerTodasLasMarcas();
            return ResponseEntity.ok(marcas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

     //       * Buscar marca por ID
    //     * GET /api/marcas/{id}

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerMarcaPorId(@PathVariable Integer id_marca) {
        try {
            Optional<Marca> marca = marcaServicio.buscarPorId(id_marca);
            if (marca.isPresent()) {
                return ResponseEntity.ok(marca.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

   //          * Buscar marcas que contengan parte del nombre
    //     * GET /api/marcas/buscar-parcial?nombre={nombreParcial}

    @GetMapping("/buscar-parcial")
    public ResponseEntity<?> buscarPorNombreParcial(@RequestParam String nombreParcial) {
        try {
            List<Marca> marcas = marcaServicio.buscarPorNombreParcial(nombreParcial);
            return ResponseEntity.ok(marcas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

   //         * Contar fidelizaciones de una marca
    //     * GET /api/marcas/{id}/fidelizaciones-count

    @GetMapping("/{id}/fidelizaciones-count")
    public ResponseEntity<?> contarFidelizacionesPorMarca(@PathVariable Integer id_marca) {
        try {
            Long count = marcaServicio.contarFidelizacionesPorMarca(id_marca);
            return ResponseEntity.ok(new FidelizacionesCountResponse(count));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    // Clase interna para la respuesta de conteo de fidelizaciones
    public static class FidelizacionesCountResponse {
        private Long count;

        public FidelizacionesCountResponse(Long count) {
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

