package com.example.loyaltyapi.repositorios;

import com.example.loyaltyapi.modelos.TipoIdentificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITipoIdentificacionRepositorio  extends JpaRepository <TipoIdentificacion,Integer > {

    // * Buscar tipo de identificación por nombre exacto
    @Query("SELECT t FROM TipoIdentificacion t WHERE t.tipo_identificacion = :tipo")
    Optional<TipoIdentificacion> findByTipo_identificacion(@Param("tipo") String tipo_identificacion);

    //  * Buscar tipos de identificación que contengan parte del texto
    @Query("SELECT t FROM TipoIdentificacion t WHERE UPPER(t.tipo_identificacion) LIKE UPPER(CONCAT('%', :textoParcial, '%'))")
    List<TipoIdentificacion> findByTipoIdentificacionContainingIgnoreCase(@Param("textoParcial") String textoParcial);

    //  * Verificar si existe un tipo de identificación con ese nombre
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TipoIdentificacion t WHERE t.tipo_identificacion = :tipo")
    boolean existsByTipo_identificacion(@Param("tipo") String tipo_identificacion);

    // * Obtener todos los tipos ordenados alfabéticamente
    @Query ("SELECT t FROM TipoIdentificacion t ORDER BY t.tipo_identificacion ASC")
    List<TipoIdentificacion> findALLOrderedByname();


}



