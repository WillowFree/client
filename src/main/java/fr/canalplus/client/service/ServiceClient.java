package fr.canalplus.client.service;

import fr.canalplus.client.model.ClientDto;
import fr.canalplus.client.model.ClientEntity;

public interface ServiceClient {
    ClientEntity mettreAJour(ClientDto clientDto);
}
