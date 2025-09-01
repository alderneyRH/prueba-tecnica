package com.example.loyaltyapi.repositorios;

import com.example.loyaltyapi.modelos.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IDepartamentoRepositorio  extends JpaRepository<Departamento, Integer> {

    

    //BUSCAR DEPARTAMENTOS POR PAÍS
    @Query("SELECT d FROM Departamento d WHERE d.pais.id_pais = :idPais ORDER BY d.nombre_departamento ASC")
    List<Departamento> findById_paisOrderByName(@Param("idPais") Integer id_pais);

    // * Buscar departamento por nombre exacto
    @Query("SELECT d FROM Departamento d WHERE UPPER(d.nombre_departamento) = UPPER(:nombre)")
    Optional<Departamento> findByNombre_departamentoIgnoreCase(@Param("nombre") String nombre_departamento);

    // * Verificar si existe un departamento con ese nombre en un país específico
    @Query("SELECT CASE WHEN COUNT(d) > 0 THEN true ELSE false END FROM Departamento d WHERE UPPER(d.nombre_departamento) = UPPER(:nombre) AND d.pais.id_pais = :idPais")
    boolean existsByNombre_departamentoAndPaisId(@Param("nombre") String nombre_departamento, @Param("idPais") Integer id_pais);

}
