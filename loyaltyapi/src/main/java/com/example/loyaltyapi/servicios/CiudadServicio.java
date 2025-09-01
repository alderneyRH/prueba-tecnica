package com.example.loyaltyapi.servicios;

import com.example.loyaltyapi.modelos.Ciudad;
import com.example.loyaltyapi.repositorios.ICiudadRepositorio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiudadServicio {

    @Autowired
    private ICiudadRepositorio ciudadRepositorio;

    // Buscar ciudad por nombre exacto

    public Optional<Ciudad> buscarPorNombre(String nombre_ciudad) {
        if (nombre_ciudad == null || nombre_ciudad.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la ciudad no puede estar vacío");
        }
        return ciudadRepositorio.findByNombreCiudadIgnoreCase(nombre_ciudad.trim());
    }

    // Buscar ciudades por departamento

    public List<Ciudad> buscarPorDepartamento(Integer id_departamento) {
        if (id_departamento == null || id_departamento <= 0) {
            throw new IllegalArgumentException("El ID del departamento debe ser un número positivo");
        }

        return ciudadRepositorio.findById_departamentoOrderByName(id_departamento);
    }

    // Contar clientes por ciudad
    public long contarClientesPorCiudad(Integer id_ciudad) {
        if (id_ciudad == null || id_ciudad <= 0) {
            throw new IllegalArgumentException("El ID de la ciudad debe ser un número positivo");
        }
        return ciudadRepositorio.countClientesByCiudad(id_ciudad);
    }

    // Obtener todas las ciudades
    public List<Ciudad> obtenerTodasLasCiudades() {
        return ciudadRepositorio.findAll();
    }

    // Buscar ciudad por ID
    public Optional<Ciudad> buscarPorId(Integer id_ciudad) {
        if (id_ciudad == null || id_ciudad <= 0) {
            throw new IllegalArgumentException("El ID de la ciudad debe ser un número positivo");
        }
        return ciudadRepositorio.findById(id_ciudad);
    }
}
