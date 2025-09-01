package com.example.loyaltyapi.controlador;

import com.example.loyaltyapi.modelos.Departamento;
import com.example.loyaltyapi.servicios.DepartamentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoControlador {

    @Autowired
    private DepartamentoServicio departamentoServicio;

    // * Crear un nuevo departamento
    //     * POST /api/departamentos
    //     * Body debe incluir: nombre_departamento e id_pais

    @PostMapping
    public ResponseEntity<?> crearDepartamento(@RequestBody DepartamentoRequest request) {
        try {
            Departamento departamentoCreado = departamentoServicio.crearDepartamento(
                    request.getNombre_departamento(),
                    request.getId_pais()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(departamentoCreado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    //     * Obtener todos los departamentos
    //     * GET /api/departamentos

    @GetMapping
    public ResponseEntity<List<Departamento>> obtenerTodosLosDepartamentos() {
        try {
            List<Departamento> departamentos = departamentoServicio.obtenerTodosLosDepartamentos();
            return ResponseEntity.ok(departamentos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

        //         * Buscar departamento por ID
    //     * GET /api/departamentos/{id}

    @GetMapping("/{id_departamento}")
    public ResponseEntity<?> obtenerDepartamentoPorId(@PathVariable Integer id_departamento) {
        try {
            Optional<Departamento> departamento = departamentoServicio.buscarPorId(id_departamento);
            if (departamento.isPresent()) {
                return ResponseEntity.ok(departamento.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    //          * Buscar departamento por nombre exacto
    //     * GET /api/departamentos/buscar?nombre={nombre}

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre_departamento) {
        try {
            Optional<Departamento> departamento = departamentoServicio.buscarPorNombre(nombre_departamento);
            if (departamento.isPresent()) {
                return ResponseEntity.ok(departamento.get());
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


      //         * Buscar departamentos por país
      //     * GET /api/departamentos/por-pais/{idPais}
    @GetMapping("/por-pais/{id_pais}")
    public ResponseEntity<?> buscarPorPais(@PathVariable Integer id_pais) {
        if (id_pais == null || id_pais <= 0) {
            return ResponseEntity.badRequest().body("El id del país debe ser mayor que 0");
        }
        try {
            List<Departamento> departamentos = departamentoServicio.buscarPorPais(id_pais);
            return ResponseEntity.ok(departamentos);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error interno del servidor: " + e.getMessage());
        }
    }

    // Clases internas para las peticiones y respuestas
    public static class DepartamentoRequest {
        private String nombre_departamento;
        private Integer id_pais;

        public DepartamentoRequest() {
        }

        public String getNombre_departamento() {
            return nombre_departamento;
        }

        public void setNombre_departamento(String nombre_departamento) {
            this.nombre_departamento = nombre_departamento;
        }

        public Integer getId_pais() {
            return id_pais;
        }

        public void setId_pais(Integer id_pais) {
            this.id_pais = id_pais;
        }
    }

    public static class ExisteResponse {
        private boolean existe;

        public ExisteResponse(boolean existe) {
            this.existe = existe;
        }

        public boolean isExiste() {
            return existe;
        }

        public void setExiste(boolean existe) {
            this.existe = existe;
        }

    }
}