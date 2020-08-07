package ar.com.ada.api.challengenasa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import ar.com.ada.api.challengenasa.entities.Pais;
import ar.com.ada.api.challengenasa.repos.PaisRepository;

@Service
public class PaisService {
   
    @Autowired
    PaisRepository paisRepo;

    public boolean crearPais(Pais pais){
        if (existe(pais.getCodigoPais()))
            return false;

        grabar(pais);
        return true;
    }

    public boolean existe(int CodigoPais) {
        Pais pais = buscarPaisPorId(CodigoPais);

        return pais != null;

    }
    public void grabar(Pais pais) {

        paisRepo.save(pais);
    }

    

    public List<Pais> listarPaises(){
        return paisRepo.findAll();
    }
    public Pais buscarPaisPorId(int codigoPais){
        Optional<Pais> paisId = paisRepo.findById(codigoPais);
        
        if (paisId.isPresent()) {
            return paisId.get();
        }
        return null;
    }
    public void actualizarPais(Pais pais, String nombreAct){
        pais.setNombre(nombreAct);
        paisRepo.save(pais);
    }
}