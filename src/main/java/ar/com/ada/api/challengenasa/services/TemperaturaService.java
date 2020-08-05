package ar.com.ada.api.challengenasa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.challengenasa.entities.Pais;
import ar.com.ada.api.challengenasa.entities.Temperatura;
import ar.com.ada.api.challengenasa.entities.Temperatura.ResultadoTransaccionEnum;
import ar.com.ada.api.challengenasa.repos.TemperaturaRepository;

@Service
public class TemperaturaService {
    @Autowired
    TemperaturaRepository tempRepo;
    @Autowired
    PaisService paisService;

    public Temperatura buscarTemperaturaPorId(int temperaturaId) {
        Optional<Temperatura> t = tempRepo.findById(temperaturaId);

        if (t.isPresent())
            return t.get();
        return null;
    }

    public void crearTemperatura(Temperatura temperatura) {
        tempRepo.save(temperatura);
    }

    public ResultadoTransaccionEnum buscarTemperaturaPorAnio(int anio) {
        // List<Temperatura> t =new ArrayList();
        // List<Pais> pais =PaisService.ListarPaises();
        Temperatura t = tempRepo.findByAnio(anio);
        if (t == null) {
            return this.buscarTemperaturaPorAnio(anio);
        } else {
            return ResultadoTransaccionEnum.ANIO_EXISTENTE;
        }
    }

    public List<Temperatura> buscarPorPais(int codigoPais){

        return tempRepo.findAllByCodigoPais(codigoPais);

    }

    public void borrarTemperatura(Temperatura temperatura){
        temperatura.setAnioTemperatura(0);
        tempRepo.save(temperatura);
    }

    /*public List<Temperatura> listarPaisPorCodigo(int codigoPais){
        List<Temperatura> temps = new ArrayList();
        List<Pais> paises = paisService.listarPaises();

        for (Pais p : paises) {

            for (Temperatura t : p.getTemperaturas()) {

                if (t.getAnioTemperatura().equals(codigoPais)) {

                    tempsAnio.add(t);
                }
            }
        }

        return tempsAnio;
}return null;

    }*/
}