package com.example.loyaltyapi.modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table (name = "ciudades")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class  Ciudad {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_ciudad")
    private Integer id_ciudad;

    @Column (name = "nombre_ciudad", nullable = false, length = 100)
    private String nombre_ciudad;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "id_departamento", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "ciudades"})
    private Departamento departamento;

    public Ciudad() {
    }

    public Ciudad(String nombre_ciudad, Departamento departamento) {
        this.nombre_ciudad = nombre_ciudad;
        this.departamento = departamento;
    }


    public Integer getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(Integer id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getNombre_ciudad() {
        return nombre_ciudad;
    }

    public void setNombre_ciudad(String nombre_ciudad) {
        this.nombre_ciudad = nombre_ciudad;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
