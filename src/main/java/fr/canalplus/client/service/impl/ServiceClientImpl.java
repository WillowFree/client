package fr.canalplus.client.service.impl;


import fr.canalplus.client.model.AdresseEntity;
import fr.canalplus.client.model.ClientDto;
import fr.canalplus.client.model.ClientEntity;
import fr.canalplus.client.repositories.ClientRepository;
import fr.canalplus.client.service.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceClientImpl implements ServiceClient {

    private final ClientRepository clientRepository;

    @Autowired
    public ServiceClientImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientEntity mettreAJour(ClientDto clientDto) {

        ClientEntity clientEntity = new ClientEntity(clientDto.getNom(), clientDto.getPrenom(), clientDto.getDateDeNaissance());
        clientEntity.setId(clientDto.getId());
        AdresseEntity adresseEntity = new AdresseEntity(clientDto.getLibelleAdresse(), clientDto.getCodePostaleAdresse(),
                clientDto.getVilleAdresse(), clientDto.getPaysAdresse(), clientDto.getTypeAdresse());
        adresseEntity.setId(clientDto.getAdresseId());
        clientEntity.addAdresse(adresseEntity);

        return clientRepository.saveAndFlush(clientEntity);
    }
}
