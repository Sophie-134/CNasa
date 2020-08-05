package ar.com.ada.api.challengenasa.repos;

import org.springframework.stereotype.Repository;

import ar.com.ada.api.challengenasa.entities.Pais;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer>{
}
