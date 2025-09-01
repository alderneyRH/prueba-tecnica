package com.example.loyaltyapi.repositorios;

import com.example.loyaltyapi.modelos.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICiudadRepositorio extends JpaRepository<Ciudad, Integer> {

    //  BUSCAR CIUDADES POR DEPARTAMENTO
    @Query ("SELECT c FROM Ciudad c WHERE c.departamento.id_departamento = :id_departamento ORDER BY c.nombre_ciudad ASC")
    List<Ciudad> findById_departamentoOrderByName(@Param("id_departamento") Integer id_departamento);

    // Buscar ciudad por nombre exacto
    @Query("SELECT c FROM Ciudad c WHERE UPPER(c.nombre_ciudad) = UPPER(:nombre)")
    Optional<Ciudad> findByNombreCiudadIgnoreCase(@Param("nombre") String nombre_ciudad);

    //  * Contar clientes por ciudad
    @Query ("SELECT COUNT(cl) FROM Cliente cl WHERE cl.ciudad.id_ciudad = :idCiudad")
    Long countClientesByCiudad(@Param("id_ciudad") Integer id_ciudad);

}
