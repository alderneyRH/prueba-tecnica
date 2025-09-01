package com.example.loyaltyapi.servicios;

import com.example.loyaltyapi.modelos.TipoIdentificacion;
import com.example.loyaltyapi.repositorios.ITipoIdentificacionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoidentificacionServicio {

    @Autowired
    private ITipoIdentificacionRepositorio tipoIdentificacionRepositorio;

    // Crear un nuevo tipo de identificación
    public TipoIdentificacion crearTipoIdentificacion(TipoIdentificacion tipo_identificacion) {
        // Validar que el tipo no esté vacío
        if (tipo_identificacion.getTipo_identificacion() == null || tipo_identificacion.getTipo_identificacion().trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de identificación no puede estar vacío");
        }

        // Verificar que no exista un tipo con el mismo nombre
        if (tipoIdentificacionRepositorio.existsByTipo_identificacion(tipo_identificacion.getTipo_identificacion().trim())) {
            throw new IllegalArgumentException("Ya existe un tipo de identificación: " + tipo_identificacion.getTipo_identificacion());
        }

        // Limpiar y guardar
        tipo_identificacion.setTipo_identificacion(tipo_identificacion.getTipo_identificacion().trim());
        return tipoIdentificacionRepositorio.save(tipo_identificacion);
    }

    // * Buscar tipo de identificación por nombre exacto

    public Optional<TipoIdentificacion> buscarPorTipo(String tipo_identificacion) {
        if (tipo_identificacion == null || tipo_identificacion.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de identificación no puede estar vacío");
        }
        return tipoIdentificacionRepositorio.findByTipo_identificacion(tipo_identificacion.trim());
    }

     // * Buscar tipos que contengan parte del nombre

    public List<TipoIdentificacion> buscarPorTipoParcial(String textoParcial) {
        if (textoParcial == null || textoParcial.trim().isEmpty()) {
            return obtenerTodosLosTipos();
        }
        return tipoIdentificacionRepositorio.findByTipoIdentificacionContainingIgnoreCase(textoParcial.trim());
    }
    // * Obtener todos los tipos de identificación
    public List<TipoIdentificacion> obtenerTodosLosTipos() {
        return tipoIdentificacionRepositorio.findALLOrderedByname();
    }
}
