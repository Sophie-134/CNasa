package ar.com.ada.api.challengenasa.repos;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.challengenasa.entities.Temperatura;
@Repository
public interface TemperaturaRepository extends JpaRepository<Temperatura, Integer> {
   
    public List<Temperatura> findByAnioTemperatura(int anio);

    @Query("Select t FROM Temperatura t WHERE t.pais.codigoPais = :codigoPais")
    public List<Temperatura> findAllByCodigoPais(@Param("codigoPais")int codigoPais);
}