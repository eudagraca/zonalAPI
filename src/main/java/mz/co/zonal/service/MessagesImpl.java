package mz.co.zonal.service;

import mz.co.zonal.models.Brand;
import mz.co.zonal.models.Message;

import java.util.ArrayList;

public interface MessagesImpl {

    ArrayList<Message> allMessageByProduct(Long product);
//    Brand findBrand(Long id);
    Message sendMessage(Message message);
}
