package ar.com.ada.api.challengenasa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.reactive.GenericReactiveTransaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.challengenasa.entities.Temperatura;
import ar.com.ada.api.challengenasa.models.request.TemperaturaRequest;
import ar.com.ada.api.challengenasa.models.response.GenericResponse;
import ar.com.ada.api.challengenasa.services.TemperaturaService;

@RestController
public class TemperaturaController {

    @Autowired
    TemperaturaService tService;

    @PostMapping("/temperaturas")
    public ResponseEntity<GenericResponse> crearTemperatura(@RequestBody TemperaturaRequest request) {
        
        Temperatura temperatura = new Temperatura();
        temperatura.setAnioTemperatura(request.anio);
        temperatura.setPaisIso(request.codigoPais);
        temperatura.setGrados(request.grados);

        tService.crearTemperatura(temperatura);
        
        GenericResponse resp = new GenericResponse();
        resp.isOk = true;
        resp.id = temperatura.getTemperaturaId();
        resp.message = "Temperatura añadida con exito!";
        
        return ResponseEntity.ok(resp);

    }

}