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

    public void crearPais(Pais pais){
        paisRepo.save(pais);
    }

    public List<Pais> listarPaises(){
        return paisRepo.findAll();
    }
    public Pais buscarPaisPorId(int id){
        Optional<Pais> paisId = paisRepo.findById(id);
        
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