package com.example.loyaltyapi.servicios;

import com.example.loyaltyapi.modelos.Pais;
import com.example.loyaltyapi.repositorios.IPaisRepositorio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisServicio {

    @Autowired
    private IPaisRepositorio paisRepositorio;

    // crear un nuevo Pais
    public Pais crearPais(Pais pais) {
        // Validar que el nombre no esté vacío
        if (pais.getNombre_pais() == null || pais.getNombre_pais().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del país no puede estar vacío");
        }

        // Verificar que no exista un país con el mismo nombre
        if (paisRepositorio.existsByNombre_pais(pais.getNombre_pais().trim())) {
            throw new IllegalArgumentException("Ya existe un país con el nombre: " + pais.getNombre_pais());
        }

        // Limpiar y guardar
        pais.setNombre_pais(pais.getNombre_pais().trim());
        return paisRepositorio.save(pais);
    }

    // Obtener todos los paises
    public List<Pais> obtenerTodosLosPaises() {
        return paisRepositorio.findAllOrderedByName();
    }

     //    * Buscar país por nombre exacto
    public Optional<Pais> buscarPorNombre(String nombre_pais) {
        if (nombre_pais == null || nombre_pais.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del país no puede estar vacío");
        }
        return paisRepositorio.findByNombre_paisIgnoreCase(nombre_pais.trim());
    }

     // Buscar países que contengan parte del nombre
    public List<Pais> buscarPorNombreParcial(String nombreParcial) {
        if (nombreParcial == null || nombreParcial.trim().isEmpty()) {
            return obtenerTodosLosPaises();
        }
        return paisRepositorio.findByNombrePaisContainingIgnoreCase(nombreParcial.trim());
    }
}
