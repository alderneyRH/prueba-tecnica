package com.example.loyaltyapi.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table (name = "marcas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Marca {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_marca")
    private Integer id_marca;

    @Column (name = "marca", nullable = false, length = 100)
    private String marca;

    public Marca() {
    }

    public Marca(String marca) {
        this.marca = marca;
    }

    public Integer getId_marca() {
        return id_marca;
    }

    public void setId_marca(Integer id_marca) {
        this.id_marca = id_marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
