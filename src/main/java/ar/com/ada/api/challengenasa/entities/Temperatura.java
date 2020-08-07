package ar.com.ada.api.challengenasa.entities;

import javax.persistence.*;

@Entity
@Table(name = "temperatura")
public class Temperatura {

    @Id
    @Column(name = "temperatura_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int temperaturaId;
    //@Column(name = "pais_iso")
    //private int paisIso; Aca se estaria duplicando la columna, ya esta declarada abajo con pais
    @Column(name = "anio_temperatura")
    private int anioTemperatura;
    private double grados;
	

    @ManyToOne
    @JoinColumn(name ="pais_iso", referencedColumnName = "codigo_pais")
    private Pais pais;

    public int getTemperaturaId() {
        return temperaturaId;
    }

    public void setTemperaturaId(int temperaturaId) {
        this.temperaturaId = temperaturaId;
    }


    public int getAnioTemperatura() {
        return anioTemperatura;
    }

    public void setAnioTemperatura(int anioTemperatura) {
        this.anioTemperatura = anioTemperatura;
    }

    public double getGrados() {
        return grados;
    }

    public void setGrados(double grados) {
        this.grados = grados;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
        this.pais.getTemperaturas().add(this);
    }
 
}