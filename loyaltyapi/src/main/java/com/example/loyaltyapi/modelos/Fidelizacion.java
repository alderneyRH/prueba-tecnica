package com.example.loyaltyapi.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "fidelizacion",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_cliente_marca",
                columnNames = {"cliente", "marca"}
        ))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Fidelizacion {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_fidelizacion")
    private Integer id_fidelizacion;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "cliente", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fidelizaciones"})
    private Cliente cliente;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "marca", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "fidelizaciones"})
    private Marca marca;

    @Column (name = "fecha_fidelizacion")
    private LocalDate fecha_fidelizacion;

    public Fidelizacion() {
        this.fecha_fidelizacion = LocalDate.now();
    }

    public Fidelizacion(Cliente cliente, Marca marca) {
        this.cliente = cliente;
        this.marca = marca;
        this.fecha_fidelizacion = LocalDate.now();
    }

    public Fidelizacion(Integer id_fidelizacion, Cliente cliente, Marca marca, LocalDate fecha_fidelizacion) {
        this.id_fidelizacion = id_fidelizacion;
        this.cliente = cliente;
        this.marca = marca;
        this.fecha_fidelizacion = fecha_fidelizacion;
    }

    public Integer getId_fidelizacion() {
        return id_fidelizacion;
    }

    public void setId_fidelizacion(Integer id_fidelizacion) {
        this.id_fidelizacion = id_fidelizacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public LocalDate getFecha_fidelizacion() {
        return fecha_fidelizacion;
    }

    public void setFecha_fidelizacion(LocalDate fecha_fidelizacion) {
        this.fecha_fidelizacion = fecha_fidelizacion;
    }
}
