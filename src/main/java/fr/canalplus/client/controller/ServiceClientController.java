package fr.canalplus.client.controller;

import fr.canalplus.client.model.ClientDto;
import fr.canalplus.client.service.ServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
public class ServiceClientController {

    private final ServiceClient serviceClient;

    @Autowired
    public ServiceClientController(ServiceClient serviceClient) {
        this.serviceClient = serviceClient;
    }

    @PostMapping("/update")
    public ResponseEntity<ClientDto> post(@RequestBody @Valid ClientDto clientDto) {
        serviceClient.mettreAJour(clientDto);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }
}
