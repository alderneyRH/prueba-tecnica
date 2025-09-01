package com.example.loyaltyapi.repositorios;

import com.example.loyaltyapi.modelos.Cliente;
import com.example.loyaltyapi.modelos.Fidelizacion;
import com.example.loyaltyapi.modelos.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;

@Repository
public interface IFidelizacionRepositorio extends JpaRepository<Fidelizacion, Integer> {

    // * Buscar fidelización específica por cliente y marca
    Optional<Fidelizacion> findByClienteAndMarca(Cliente cliente, Marca marca);

    // * Buscar todas las fidelizaciones de un cliente
    List<Fidelizacion> findByCliente(Cliente cliente);

    // * Buscar todas las fidelizaciones de una marca
    List<Fidelizacion> findByMarca(Marca marca);

    // * Verificar si existe fidelización entre cliente y marca
    boolean existsByClienteAndMarca(Cliente cliente, Marca marca);

    // * Obtener todas las fidelizaciones ordenadas por fecha (más recientes primero)
    @Query ("SELECT f FROM Fidelizacion f ORDER BY f.fecha_fidelizacion DESC")
    List<Fidelizacion> findAllOrderedByDateDesc();

    // * Contar fidelizaciones por marca
    @Query ("SELECT f.marca, COUNT(f) FROM Fidelizacion f GROUP BY f.marca ORDER BY COUNT(f) DESC")
    List<Object[]> countFidelizacionesByMarca();

    // * Contar fidelizaciones por cliente
    @Query ("SELECT f.cliente, COUNT(f) FROM Fidelizacion f GROUP BY f.cliente ORDER BY COUNT(f) DESC")
    List<Object[]> countFidelizacionesByCliente();

}
