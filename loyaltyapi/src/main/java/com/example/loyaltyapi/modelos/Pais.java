package com.example.loyaltyapi.modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;


@Entity
@Table  (name = "paises")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Pais {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column ( name = "id_pais")
   private Integer id_pais;

    @Column (name = "nombre_pais", nullable = false, length = 100)
   private String nombre_pais;

    public Pais() {
    }

    public Pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
    }

    public Integer getId_pais() {
        return id_pais;
    }

    public void setId_pais(Integer id_pais) {
        this.id_pais = id_pais;
    }

    public String getNombre_pais() {
        return nombre_pais;
    }

    public void setNombre_pais(String nombre_pais) {
        this.nombre_pais = nombre_pais;
    }
}
