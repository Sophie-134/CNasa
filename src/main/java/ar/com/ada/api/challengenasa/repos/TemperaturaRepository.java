package ar.com.ada.api.challengenasa.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.ada.api.challengenasa.entities.Temperatura;

public interface TemperaturaRepository extends JpaRepository<Temperatura, Integer> {
   
    public Temperatura findByAnio(int anio);
    public List<Temperatura> findAllByCodigoPais(int codigoPais);
}