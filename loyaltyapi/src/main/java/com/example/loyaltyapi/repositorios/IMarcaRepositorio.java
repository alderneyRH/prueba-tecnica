package com.example.loyaltyapi.repositorios;

import com.example.loyaltyapi.modelos.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMarcaRepositorio extends JpaRepository<Marca, Integer> {


    // * Buscar marcas que contengan parte del nombre
    List<Marca> findByMarcaContainingIgnoreCase(String nombreParcial);

    //  * Verificar si existe una marca con ese nombre
    boolean existsByMarca(String marca);

    //     * Obtener todas las marcas ordenadas alfabéticamente
    //     *  DROPDOWN FINAL DEL FORMULARIO
    @Query ("SELECT m FROM Marca m ORDER BY m.marca ASC")
    List<Marca> findAllOrderedByName();

    //   * Contar cuántos clientes están fidelizados a cada marca
    @Query("SELECT COUNT(f) FROM Fidelizacion f WHERE f.marca.id_marca = :idMarca")
    Long countFidelizacionesByMarca(@Param("idMarca") Integer id_marca);

}
