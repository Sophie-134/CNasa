package ar.com.ada.api.challengenasa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.challengenasa.entities.Temperatura;
import ar.com.ada.api.challengenasa.entities.Temperatura.ResultadoTransaccionEnum;
import ar.com.ada.api.challengenasa.repos.TemperaturaRepository;

@Service
public class TemperaturaService {
    @Autowired
    TemperaturaRepository tempRepo;
    
    public Temperatura buscarTemperaturaPorId(int temperaturaId){
        Optional<Temperatura> t = tempRepo.findById(temperaturaId);

        if (t.isPresent())
            return t.get();
        return null;
    }
    public void crearTemperatura(Temperatura temperatura){
        tempRepo.save(temperatura);
    }

    public ResultadoTransaccionEnum buscarTemperaturaPorAnio(int anio){
        //List<Temperatura> t =new ArrayList();
        //List<Pais> pais =PaisService.ListarPaises();
        Temperatura t = tempRepo.findByAnio(anio);
        if (t == null) {
            return this.buscarTemperaturaPorAnio(anio);
        } else {
            return ResultadoTransaccionEnum.ANIO_EXISTENTE;
        }
    }
}