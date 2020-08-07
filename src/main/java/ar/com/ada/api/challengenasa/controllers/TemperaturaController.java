package ar.com.ada.api.challengenasa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.reactive.GenericReactiveTransaction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.challengenasa.entities.Pais;
import ar.com.ada.api.challengenasa.entities.Temperatura;
import ar.com.ada.api.challengenasa.models.request.TemperaturaRequest;
import ar.com.ada.api.challengenasa.models.response.GenericResponse;
import ar.com.ada.api.challengenasa.models.response.TemperaturaMax;
import ar.com.ada.api.challengenasa.models.response.TemperaturaResponse;
import ar.com.ada.api.challengenasa.services.PaisService;
import ar.com.ada.api.challengenasa.services.TemperaturaService;

@RestController
public class TemperaturaController {

    @Autowired
    TemperaturaService tService;
    @Autowired
    PaisService paisService;

    @PostMapping("/temperaturas")
    public ResponseEntity<GenericResponse> crearTemperatura(@RequestBody TemperaturaRequest request) {

        Temperatura temperatura = new Temperatura();
        //temperatura.setPais(paisService.buscarPaisPorId(request.codigoPais));
        temperatura.setAnioTemperatura(request.anio);
        temperatura.setGrados(request.grados);

        Pais pais =paisService.buscarPaisPorId(request.codigoPais);
        pais.setCodigoPais(request.codigoPais);
        pais.agregarTemperatura(temperatura);
        tService.crearTemperatura(temperatura);

        GenericResponse resp = new GenericResponse();
        resp.isOk= true;
        resp.id = temperatura.getTemperaturaId();
        resp.message = "Temperatura añadida con exito!";

        return ResponseEntity.ok(resp);

    }

    @GetMapping("/temperaturas/paises/{codigoPais}")
        public ResponseEntity<?> listarTemperatura(@PathVariable int codigoPais) {

        Pais pais = paisService.buscarPaisPorId(codigoPais);
        
       if (pais != null) {

            List<Temperatura> temperaturasPorPais = pais.getTemperaturas();

            return ResponseEntity.ok(temperaturasPorPais);

        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    } 
    

    @DeleteMapping("/temperaturas/{id}")
    public ResponseEntity<?> borrarTemperatura(@PathVariable int id) {

        Temperatura temp = tService.buscarTemperaturaPorId(id);

        if (temp != null) {

            tService.borrarTemperatura(temp);

            GenericResponse resp = new GenericResponse();
            resp.isOk = true;
            resp.id = temp.getTemperaturaId();
            resp.message = "La temperatura fue eliminada con éxito";

            return ResponseEntity.ok(resp);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/temperaturas/anios/{anio}")
    public ResponseEntity<?> listarTempPorAnio(@PathVariable int anio){

        List<Temperatura> temp = tService.buscarTemperaturaPorAnio(anio);

        List<TemperaturaResponse> tempResponse = new ArrayList();

        for (int i = 0; i < temp.size(); i++) {

            TemperaturaResponse t = new TemperaturaResponse();
            t.nombrePais = temp.get(i).getPais().getNombre();
            t.grados = temp.get(i).getGrados();

            tempResponse.add(t);

        }
              return ResponseEntity.ok(tempResponse); 

    }

    @GetMapping("/temperaturas/maximas/{codigoPais}")
    public ResponseEntity<?> listarTemperaturasMaximas(@PathVariable int codigoPais){

        TemperaturaMax  tempMax = tService.buscarTempMax(codigoPais);
        
        return ResponseEntity.ok(tempMax); 

    }

}
