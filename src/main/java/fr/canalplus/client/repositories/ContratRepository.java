package fr.canalplus.client.repositories;

import fr.canalplus.client.model.ContratEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ContratRepository extends CrudRepository<ContratEntity, Long> {

    Set<ContratEntity> findAllByClientEntity_NomAndClientEntity_Prenom(@Param("nom") String nom, @Param("prenom") String prenom);

}
