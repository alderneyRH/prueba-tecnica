package com.example.loyaltyapi.servicios;

import com.example.loyaltyapi.modelos.Departamento;
import com.example.loyaltyapi.modelos.Pais;
import com.example.loyaltyapi.repositorios.IDepartamentoRepositorio;
import com.example.loyaltyapi.repositorios.IPaisRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoServicio {

    @Autowired
    private IDepartamentoRepositorio departamentoRepositorio;

    @Autowired
    private IPaisRepositorio paisRepositorio;

    // Crear un nuevo departamento
    public Departamento crearDepartamento(String nombre_departamento, Integer id_pais) {
        // Validar que el nombre no esté vacío
        if (nombre_departamento == null || nombre_departamento.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del departamento no puede estar vacío");
        }

        // Validar que el ID del país sea válido
        if (id_pais == null || id_pais <= 0) {
            throw new IllegalArgumentException("El ID del país debe ser un número positivo");
        }

        // Verificar que el país exista
        Optional<Pais> pais = paisRepositorio.findById(id_pais);
        if (pais.isEmpty()) {
            throw new IllegalArgumentException("No se encontró el país con ID: " + id_pais);
        }

        String nombreLimpio = nombre_departamento.trim();

        // Verificar que no exista un departamento con el mismo nombre en el mismo país
        if (departamentoRepositorio.existsByNombre_departamentoAndPaisId(nombreLimpio, id_pais)) {
            throw new IllegalArgumentException("Ya existe un departamento con el nombre '" + nombreLimpio + "' en este país");
        }

        // Crear y guardar el departamento
        Departamento departamento = new Departamento(pais.get(), nombreLimpio);
        return departamentoRepositorio.save(departamento);
    }


    // * Buscar departamento por nombre exacto
    public Optional<Departamento> buscarPorNombre(String nombre_departamento) {
        if (nombre_departamento == null || nombre_departamento.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del departamento no puede estar vacío");
        }
        return departamentoRepositorio.findByNombre_departamentoIgnoreCase(nombre_departamento.trim());
    }

    //  * Buscar departamentos por país
    public List<Departamento> buscarPorPais(Integer id_pais) {
        if (id_pais == null || id_pais <= 0) {
            throw new IllegalArgumentException("El ID del país debe ser un número positivo");
        }
        return departamentoRepositorio.findById_paisOrderByName(id_pais);
    }


    // Obtener todos los departamentos
    public List<Departamento> obtenerTodosLosDepartamentos() {
        return departamentoRepositorio.findAll();
    }


    // Buscar departamento por ID
    public Optional<Departamento> buscarPorId(Integer id_departamento) {
        if (id_departamento == null || id_departamento <= 0) {
            throw new IllegalArgumentException("El ID del departamento debe ser un número positivo");
        }
        return departamentoRepositorio.findById(id_departamento);
    }

}
