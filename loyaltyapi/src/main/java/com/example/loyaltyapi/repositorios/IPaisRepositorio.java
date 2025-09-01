package com.example.loyaltyapi.repositorios;

import com.example.loyaltyapi.modelos.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IPaisRepositorio extends JpaRepository<Pais, Integer> {

    //* Buscar país por nombre exacto
    @Query("SELECT p FROM Pais p WHERE UPPER(p.nombre_pais) = UPPER(:nombre)")
    Optional<Pais> findByNombre_paisIgnoreCase(@Param("nombre") String nombre_pais);

    // * Buscar países que contengan parte del nombre (búsqueda parcial)
    @Query("SELECT p FROM Pais p WHERE UPPER(p.nombre_pais) LIKE UPPER(CONCAT('%', :nombreParcial, '%'))")
    List<Pais> findByNombrePaisContainingIgnoreCase(@Param("nombreParcial") String nombreParcial);

    //  * Verificar si existe un país con ese nombre
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Pais p WHERE UPPER(p.nombre_pais) = UPPER(:nombre)")
    boolean existsByNombre_pais(@Param("nombre") String nombre_pais);

    // * Obtener todos los países ordenados alfabéticamente
    @Query("SELECT p FROM Pais p ORDER BY p.nombre_pais ASC")
    List<Pais> findAllOrderedByName();

}
