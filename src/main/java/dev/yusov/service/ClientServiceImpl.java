package dev.yusov.service;

import dev.yusov.model.Client;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ClientServiceImpl implements ClientService {

    // Хранилище клиентов
    private static final Map<Integer, Client> CLIENT_REPOSITORY_MAP = new HashMap<Integer, Client>();

    // Переменная для генерации ID клиента
    private static final AtomicInteger CLIENT_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Client client) {
        final int clientId = CLIENT_ID_HOLDER.incrementAndGet();
        client.setId(clientId);
        CLIENT_REPOSITORY_MAP.put(clientId,client);
    }

    @Override
    public List<Client> readAll() {
        return new ArrayList<Client>(CLIENT_REPOSITORY_MAP.values());
    }

    @Override
    public Client read(int id) {
        return CLIENT_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Client client, int id) {
        if(CLIENT_REPOSITORY_MAP.containsKey(id)) {
            client.setId(id);
            CLIENT_REPOSITORY_MAP.put(id, client);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return CLIENT_REPOSITORY_MAP.remove(id) != null;
    }

    @Override
    public String getXML(String xml) {
//        String fullPath = ClientServiceImpl.class.getResource("/example.xml").toString();
//        fullPath.substring(fullPath.indexOf('/')+1);
//        System.out.println("Выводим путь: " + fullPath);
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        try {
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document document = builder.parse(new File(fullPath));
//            document.getDocumentElement().normalize();
//        } catch (IOException | ParserConfigurationException | SAXException e) {}



        System.out.println("Изменяем: " + xml);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Что то изменили в XML." + xml);
        return stringBuffer.toString();
    }

    // Положительный ответ

}