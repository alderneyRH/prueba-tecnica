package com.example.loyaltyapi.modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table (name = "tipos_identificacion")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoIdentificacion {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_tipo_identificacion")
    private Integer id_tipo_identificacion;

    @Column (name = "tipo_identificacion", nullable = false, length = 100)
    private String tipo_identificacion;

    public TipoIdentificacion() {
    }

    public TipoIdentificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public Integer getId_tipo_identificacion() {
        return id_tipo_identificacion;
    }

    public void setId_tipo_identificacion(Integer id_tipo_identificacion) {
        this.id_tipo_identificacion = id_tipo_identificacion;
    }

    public String getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }
}
