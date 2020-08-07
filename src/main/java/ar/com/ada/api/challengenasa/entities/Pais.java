package ar.com.ada.api.challengenasa.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "pais")
public class Pais {
 
    @Id
    @Column(name="codigo_pais")
    private int codigoPais;
    private String nombre;
    
    @OneToMany(mappedBy = "pais", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Temperatura> temperaturas = new ArrayList<>();

    public int getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(int codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Temperatura> getTemperaturas() {
        return temperaturas;
    }

    public void setTemperaturas(List<Temperatura> temperaturas) {
        this.temperaturas = temperaturas;
    }
    public void agregarTemperatura(Temperatura temperatura){
        this.temperaturas.add(temperatura);
        temperatura.setPais(this);
    }
}