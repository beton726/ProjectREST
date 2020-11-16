package dev.yusov.controller;

import dev.yusov.model.Client;
import dev.yusov.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Метод обрабатывает POST запросы на адрес /clients
//    @PostMapping(value = "/clients")
//    public ResponseEntity<?> create(@RequestBody Client client) {
//        clientService.create(client);
//        return new ResponseEntity<Object>(HttpStatus.CREATED);
//    }

    // Метод обрабатывает GET запросы на адрес /clients
//    @GetMapping(value = "/clients")
//    public ResponseEntity<List<Client>> read() {
//        final List<Client> clients = clientService.readAll();
//
//        return clients != null && !clients.isEmpty()
//                ? new ResponseEntity<List<Client>>(clients, HttpStatus.OK)
//                : new ResponseEntity<List<Client>>(HttpStatus.NOT_FOUND);
//    }

    // Получение клиента по его id
    @GetMapping(value = "/clients/{id}")
    public ResponseEntity<Client> read(@PathVariable(name = "id") int id) {
        final Client client = clientService.read(id);

        return client != null
                ? new ResponseEntity<Client>(client, HttpStatus.OK)
                : new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
    }

    // Обновление
    @PutMapping(value = "/clients/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Client client) {
        final boolean updated = clientService.update(client, id);

        return updated
                ? new ResponseEntity<Object>(HttpStatus.OK)
                : new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
    }

    //Удаление
    @DeleteMapping(value = "/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = clientService.delete(id);

        return deleted
                ? new ResponseEntity<Object>(HttpStatus.OK)
                : new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
    }

    @PostMapping(value = "/getBody")
    public ResponseEntity<String> create(@RequestBody String body) {

        String client = clientService.getXML(body);

        return client != null
                ? new ResponseEntity<String>(client, HttpStatus.OK)
                : new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

    //Тестовый GET
    @GetMapping(value = "/client")
    public ResponseEntity<String> read() {
        String client = "";

        return client != null
                ? new ResponseEntity<String>(client, HttpStatus.OK)
                : new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }



    // @RequestParam - отвечает за параметры запроса, они находятся после знака ?
    @PostMapping(value = "/sbpo-module/v1/subsidiary/clients")
    public ResponseEntity<String> getBody(@RequestParam String RqUID, @RequestBody String body) {

        if(RqUID == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        String client = body;
        System.out.println("Параметры запроса: " + RqUID);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"result\":\"OK\",\"rquid\":\""  +  RqUID + "\"}");
        System.out.println(stringBuilder);



        return stringBuilder != null
                ? new ResponseEntity<String>(stringBuilder.toString(), HttpStatus.OK)
                : new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }

    // @RequestParam - отвечает за параметры запроса, они находятся после знака ?
    @PostMapping(value = "/sbpo-module/v1/subsidiary/clients/products")
    public ResponseEntity<String> getBodys(@RequestParam String RqUID, @RequestBody String body) {

        if(RqUID == null) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        String client = body;
        System.out.println("Параметры запроса: " + RqUID);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"result\":\"OK\"}");
        System.out.println(stringBuilder);

        return stringBuilder != null
                ? new ResponseEntity<String>(stringBuilder.toString(), HttpStatus.OK)
                : new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }



}