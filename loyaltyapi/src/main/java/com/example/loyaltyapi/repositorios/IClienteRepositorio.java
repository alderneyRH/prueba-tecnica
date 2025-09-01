package com.example.loyaltyapi.repositorios;

import com.example.loyaltyapi.modelos.Cliente;
import com.example.loyaltyapi.modelos.TipoIdentificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IClienteRepositorio extends JpaRepository<Cliente, Integer> {


    // * Buscar cliente por número de identificación exacto
    @Query("SELECT c FROM Cliente c WHERE c.numero_identificacion = :numeroId")
    Optional<Cliente> findByNumero_identificacion(@Param("numeroId") String numero_identificacion);

    // * Buscar clientes por nombre que contengan parte del texto
    @Query("SELECT c FROM Cliente c WHERE UPPER(c.nombre_cliente) LIKE UPPER(CONCAT('%', :nombre, '%'))")
    List<Cliente> findByNombre_clienteContainingIgnoreCase(@Param("nombre") String nombre_cliente);

    // * Buscar clientes por apellidos que contengan parte del texto
    @Query("SELECT c FROM Cliente c WHERE UPPER(c.apellidos_cliente) LIKE UPPER(CONCAT('%', :apellidos, '%'))")
    List<Cliente> findByApellidos_clienteContainingIgnoreCase(@Param("apellidos") String apellidos_cliente);

    // * buscar por nombre y apellido
    @Query ("SELECT c FROM Cliente c WHERE " +
            "CONCAT(c.nombre_cliente, ' ', c.apellidos_cliente) LIKE %?1% OR " +
            "CONCAT(c.apellidos_cliente, ' ', c.nombre_cliente) LIKE %?1%")
    List<Cliente> findByNombreCompletoContaining(String nombreCompleto);

    // * Obtener todos los clientes ordenados alfabéticamente por nombre
    @Query ("SELECT c FROM Cliente c ORDER BY c.nombre_cliente ASC, c.apellidos_cliente ASC")
    List<Cliente> findAllOrderedByName();

    // * Buscar clientes por ciudad (usando ID de ciudad)
    @Query("SELECT c FROM Cliente c WHERE c.ciudad.id_ciudad = :idCiudad")
    List<Cliente> findByCiudadId(@Param("idCiudad") Integer id_ciudad);

}
