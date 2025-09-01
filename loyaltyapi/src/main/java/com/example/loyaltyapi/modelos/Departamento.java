package com.example.loyaltyapi.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table (name = "departamentos")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Departamento {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_departamento")
    private Integer id_departamento;

    @Column (name = "nombre_departamento", nullable = false, length = 100)
    private String nombre_departamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "id_pais", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "departamentos"})
    private Pais pais;

    public Departamento() {
    }

    public Departamento(Pais pais, String nombre_departamento) {
        this.pais = pais;
        this.nombre_departamento = nombre_departamento;
    }

    public Integer getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(Integer id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
}
