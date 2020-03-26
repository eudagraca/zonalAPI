package mz.co.zonal.service;

import mz.co.zonal.models.Message;
import mz.co.zonal.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MessagesService implements MessagesImpl {

    @Autowired
    private MessageRepository repository;


    @Override
    public ArrayList<Message> allMessageByProduct(Long product) {
        return null;
    }

    @Override
    public Message sendMessage(Message message) {
        return repository.save(message);
    }
}
