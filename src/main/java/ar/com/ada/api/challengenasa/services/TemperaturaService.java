package ar.com.ada.api.challengenasa.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.challengenasa.entities.Pais;
import ar.com.ada.api.challengenasa.entities.Temperatura;
import ar.com.ada.api.challengenasa.models.response.TemperaturaMax;
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
        //paisService.grabar(temperatura.getPais());
        tempRepo.save(temperatura);
    }

    public List<Temperatura> buscarTemperaturaPorAnio(int anio) {
        // List<Temperatura> t =new ArrayList();
        // List<Pais> pais =PaisService.ListarPaises();
        return tempRepo.findByAnioTemperatura(anio);
        
        }
    

    public List<Temperatura> buscarPorPais(int codigoPais){

        return tempRepo.findAllByCodigoPais(codigoPais);

    }

    public void borrarTemperatura(Temperatura temperatura){
        temperatura.setAnioTemperatura(0);
        tempRepo.save(temperatura);
    }

    public TemperaturaMax buscarTempMax(int codigoPais) {

        TemperaturaMax tempMax = new TemperaturaMax();

        tempMax.temperaturaMaxima = 0.00;

        List<Temperatura> temperaturas = new ArrayList<>();
        temperaturas = buscarPorPais(codigoPais);

        for (int i = 0; i < temperaturas.size(); i++) {

            if (temperaturas.get(i).getGrados() > tempMax.temperaturaMaxima) {

                tempMax.nombrePais = temperaturas.get(i).getPais().getNombre();
                tempMax.temperaturaMaxima = temperaturas.get(i).getGrados();
                tempMax.anio = temperaturas.get(i).getAnioTemperatura();
                
            }
        }

        return tempMax;

    }
    
}