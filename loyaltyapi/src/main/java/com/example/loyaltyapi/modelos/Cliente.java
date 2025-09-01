package com.example.loyaltyapi.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "clientes")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_cliente")
    private Integer id_cliente;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "tipo_identificacion", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "clientes"})
    private TipoIdentificacion tipoIdentificacion;

    @Column (name = "numero_identificacion", nullable = false, unique = true, length = 20)
    private String numero_identificacion;

    @Column (name = "nombre_cliente", nullable = false, length = 50)
    private String nombre_cliente;

    @Column (name = "apellidos_cliente", nullable = false, length = 50)
    private String apellidos_cliente;

    @Column (name = "fecha_nacimiento", nullable = false)
    private LocalDate fecha_nacimiento;

    @Column (name = "direccion", nullable = false, length = 200)
    private String direccion;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "ciudad", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "clientes"})
    private Ciudad ciudad;

    public Cliente() {
    }

    public Cliente(TipoIdentificacion tipoIdentificacion, String numero_identificacion, String nombre_cliente, String apellidos_cliente, LocalDate fecha_nacimiento, String direccion, Ciudad ciudad) {
        this.tipoIdentificacion = tipoIdentificacion;
        this.numero_identificacion = numero_identificacion;
        this.nombre_cliente = nombre_cliente;
        this.apellidos_cliente = apellidos_cliente;
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public TipoIdentificacion getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(TipoIdentificacion tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getNumero_identificacion() {
        return numero_identificacion;
    }

    public void setNumero_identificacion(String numero_identificacion) {
        this.numero_identificacion = numero_identificacion;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellidos_cliente() {
        return apellidos_cliente;
    }

    public void setApellidos_cliente(String apellidos_cliente) {
        this.apellidos_cliente = apellidos_cliente;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }
}
