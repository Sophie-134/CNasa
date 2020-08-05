package ar.com.ada.api.challengenasa.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.ada.api.challengenasa.entities.Pais;
import ar.com.ada.api.challengenasa.entities.Temperatura;
import ar.com.ada.api.challengenasa.models.request.ActualizarPaisRequest;
import ar.com.ada.api.challengenasa.models.request.PaisRequest;
import ar.com.ada.api.challengenasa.models.response.GenericResponse;
import ar.com.ada.api.challengenasa.services.PaisService;
import ar.com.ada.api.challengenasa.services.TemperaturaService;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class PaisController {

    @Autowired
    PaisService paisService;
    @Autowired
    TemperaturaService tempService;

    @PostMapping("/paises")
    public ResponseEntity<?> crearPais(@RequestBody PaisRequest request) {
        Pais pais = new Pais();

        pais.setCodigoPais(request.codigoPais);
        pais.setNombre(request.nombre);

        paisService.crearPais(pais);

        GenericResponse resp = new GenericResponse();
        resp.isOk = true;
        resp.id = pais.getCodigoPais();
        resp.message = "Pais creado con exito";
        return ResponseEntity.ok(pais);

    }

    @GetMapping("/paises")
    public ResponseEntity<List<Pais>> listarPaises() {
        return ResponseEntity.ok(paisService.listarPaises());
    }

    @GetMapping("/paises/{id}")
    public ResponseEntity<Temperatura> listarPaisesPorId(@PathVariable int temperaturaId) {

        Temperatura temperatura = tempService.buscarTemperaturaPorId(temperaturaId);

        if (temperatura == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(temperatura);
    }

    /**
     * @GetMapping("/paises/{codigoPais}") public ResponseEntity<?>
     * buscarPorId(@PathVariable int codigoPais){
     * 
     * Pais pais = paisService.buscarPorId(codigoPais);
     * 
     * if (pais != null) { return ResponseEntity.ok(pais); }
     * 
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * 
     * }
     */
    @PutMapping(value = "paises/{id}")
    public ResponseEntity<GenericResponse> ActualizarPais(@PathVariable int id,
            @RequestBody ActualizarPaisRequest request) {

        Pais pais = paisService.buscarPaisPorId(id);

        if (pais != null) {
            paisService.actualizarPais(pais, request.nombre);
            GenericResponse res = new GenericResponse();
            res.isOk = true;
            res.id = pais.getCodigoPais();
            res.message = "Se ha cambiado el nombre a " + pais.getNombre() + ".";

            return ResponseEntity.ok(res);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}