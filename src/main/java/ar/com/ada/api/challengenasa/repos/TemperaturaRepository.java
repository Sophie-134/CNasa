package ar.com.ada.api.challengenasa.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.ada.api.challengenasa.entities.Temperatura;

public interface TemperaturaRepository extends JpaRepository<Temperatura, Integer> {
   
    public Temperatura findByAnio(int anio);
}