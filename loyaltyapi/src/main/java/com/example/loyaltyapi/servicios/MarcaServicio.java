package com.example.loyaltyapi.servicios;

import com.example.loyaltyapi.modelos.Marca;
import com.example.loyaltyapi.repositorios.IMarcaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaServicio {

    @Autowired
    private IMarcaRepositorio marcaRepositorio;

     // Crear una nueva marca
    public Marca crearMarca(Marca marca) {
        // Validar que el nombre no esté vacío
        if (marca.getMarca() == null || marca.getMarca().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la marca no puede estar vacío");
        }

        // Verificar que no exista una marca con el mismo nombre
        if (marcaRepositorio.existsByMarca(marca.getMarca().trim())) {
            throw new IllegalArgumentException("Ya existe una marca con el nombre: " + marca.getMarca());
        }

        // Limpiar y guardar
        marca.setMarca(marca.getMarca().trim());
        return marcaRepositorio.save(marca);
    }

    // * Obtener todas las marcas
    public List<Marca> obtenerTodasLasMarcas() {
        return marcaRepositorio.findAllOrderedByName();
    }

    // * Buscar marcas que contengan parte del nombre
    public List<Marca> buscarPorNombreParcial(String nombreParcial) {
        if (nombreParcial == null || nombreParcial.trim().isEmpty()) {
            return obtenerTodasLasMarcas();
        }
        return marcaRepositorio.findByMarcaContainingIgnoreCase(nombreParcial.trim());
    }

    // Contar fidelizaciones por marca
    public Long contarFidelizacionesPorMarca(Integer id_marca) {
        if (id_marca == null || id_marca <= 0) {
            throw new IllegalArgumentException("El ID de la marca debe ser un número positivo");
        }

        if (!marcaRepositorio.existsById(id_marca)) {
            throw new IllegalArgumentException("No se encontró la marca con ID: " + id_marca);
        }

        return marcaRepositorio.countFidelizacionesByMarca(id_marca);
    }

    // Buscar marca por ID
    public Optional<Marca> buscarPorId(Integer id_marca) {
        if (id_marca == null || id_marca <= 0) {
            throw new IllegalArgumentException("El ID de la marca debe ser un número positivo");
        }
        return marcaRepositorio.findById(id_marca);
    }
}
