package com.example.loyaltyapi.servicios;

import com.example.loyaltyapi.modelos.Cliente;
import com.example.loyaltyapi.modelos.Fidelizacion;
import com.example.loyaltyapi.modelos.Marca;
import com.example.loyaltyapi.repositorios.IClienteRepositorio;
import com.example.loyaltyapi.repositorios.IFidelizacionRepositorio;
import com.example.loyaltyapi.repositorios.IMarcaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FidelizacionServicio {

    @Autowired
    private IFidelizacionRepositorio fidelizacionRepositorio;

    @Autowired
    private IClienteRepositorio clienteRepositorio;

    @Autowired
    private IMarcaRepositorio marcaRepositorio;

    public Fidelizacion crearFidelizacion(Integer clienteId, Integer marcaId) {
        // Validaciones básicas
        if (clienteId == null || clienteId <= 0) {
            throw new IllegalArgumentException("El ID del cliente debe ser un número positivo");
        }

        if (marcaId == null || marcaId <= 0) {
            throw new IllegalArgumentException("El ID de la marca debe ser un número positivo");
        }

        // Verificar que el cliente existe
        Optional<Cliente> clienteOpt = clienteRepositorio.findById(clienteId);
        if (!clienteOpt.isPresent()) {
            throw new IllegalArgumentException("No se encontró el cliente con ID: " + clienteId);
        }

        // Verificar que la marca existe
        Optional<Marca> marcaOpt = marcaRepositorio.findById(marcaId);
        if (!marcaOpt.isPresent()) {
            throw new IllegalArgumentException("No se encontró la marca con ID: " + marcaId);
        }

        Cliente cliente = clienteOpt.get();
        Marca marca = marcaOpt.get();

        // Verificar si ya existe una fidelización entre este cliente y marca

        // Verificar si ya existe una fidelización entre este cliente y marca
        if (fidelizacionRepositorio.existsByClienteAndMarca(cliente, marca)) {
            throw new IllegalArgumentException("Ya existe una fidelización entre el cliente " +
                    cliente.getNombre_cliente() + " " + cliente.getApellidos_cliente() +
                    " y la marca " + marca.getMarca());
        }

        // Crear la nueva fidelización
        Fidelizacion nuevaFidelizacion = new Fidelizacion(cliente, marca);

        // Guardar y retornar
        return fidelizacionRepositorio.save(nuevaFidelizacion);
    }

     //    * Buscar fidelización específica por cliente y marca

    public Optional<Fidelizacion> buscarFidelizacion(Cliente cliente, Marca marca) {
        return fidelizacionRepositorio.findByClienteAndMarca(cliente, marca);
    }

    //      * Verificar si existe fidelización entre cliente y marca

        public boolean existeFidelizacion(Cliente cliente, Marca marca) {
            return fidelizacionRepositorio.existsByClienteAndMarca(cliente, marca);
        }

    //     * Buscar todas las fidelizaciones de una marca

        public List<Fidelizacion> buscarFidelizacionesPorMarca(Marca marca) {
            if (marca == null) {
                throw new IllegalArgumentException("La marca no puede ser nula");
            }
            return fidelizacionRepositorio.findByMarca(marca);
        }

        //      * Buscar todas las fidelizaciones de un cliente

        public List<Fidelizacion> buscarFidelizacionesPorCliente(Cliente cliente) {
            if (cliente == null) {
                throw new IllegalArgumentException("El cliente no puede ser nulo");
            }
            return fidelizacionRepositorio.findByCliente(cliente);
        }

    //      * Obtener todas las fidelizaciones ordenadas por fecha descendente

    public List<Fidelizacion> obtenerTodasLasFidelizacionesOrdenadas() {
        return fidelizacionRepositorio.findAllOrderedByDateDesc();
    }

    //       * Obtener estadísticas de fidelizaciones por marca

        public Map<Marca, Long> obtenerEstadisticasPorMarca() {
            List<Object[]> resultados = fidelizacionRepositorio.countFidelizacionesByMarca();
            return resultados.stream()
                .collect(Collectors.toMap(
                    result -> (Marca) result[0],
                    result -> (Long) result[1]
                ));
        }
    //       * Obtener estadísticas de fidelizaciones por cliente

        public Map<Cliente, Long> obtenerEstadisticasPorCliente() {
            List<Object[]> resultados = fidelizacionRepositorio.countFidelizacionesByCliente();
            return resultados.stream()
                .collect(Collectors.toMap(
                    result -> (Cliente) result[0],
                    result -> (Long) result[1]
                ));
        }
}
