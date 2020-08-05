package ar.com.ada.api.challengenasa.entities;

import javax.persistence.*;

@Entity
@Table(name = "temperatura")
public class Temperatura {

    @Id
    @Column(name = "temperatura_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int temperaturaId;
    @Column(name = "pais_iso")
    private int paisIso;
    @Column(name = "anio_temperatura")
    private int anioTemperatura;
    private Double grados;
	

    @ManyToOne
    @JoinColumn(name = "codigo_pais", referencedColumnName = "codigo_pais")
    private Pais pais;

    public int getTemperaturaId() {
        return temperaturaId;
    }

    public void setTemperaturaId(int temperaturaId) {
        this.temperaturaId = temperaturaId;
    }

    public int getPaisIso() {
        return paisIso;
    }

    public void setPaisIso(int paisIso) {
        this.paisIso = paisIso;
    }

    public int getAnioTemperatura() {
        return anioTemperatura;
    }

    public void setAnioTemperatura(int anioTemperatura) {
        this.anioTemperatura = anioTemperatura;
    }

    public Double getGrados() {
        return grados;
    }

    public void setGrados(Double grados) {
        this.grados = grados;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
        this.pais.getTemperaturas().add(this);
    }
 public enum ResultadoTransaccionEnum{
     ANIO_EXISTENTE;
 }
}