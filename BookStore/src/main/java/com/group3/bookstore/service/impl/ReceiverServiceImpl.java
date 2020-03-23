package com.group3.bookstore.service.impl;

import com.group3.bookstore.mapper.ReceiverMapper;
import com.group3.bookstore.pojo.Receiver;
import com.group3.bookstore.service.IReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiverServiceImpl implements IReceiverService {

    @Autowired
    private ReceiverMapper receiverMapper;

    @Override
    public List<Receiver> getReceiverByUserId(String userId) {
        List<Receiver> receiverList = receiverMapper.getReceiverByUserId(userId);
        return receiverList;
    }

    @Override
    public Receiver getReceiverByReceiverId(String receiverId) {
        Receiver receiver = receiverMapper.getReceiverByReceiverId(receiverId);
        return receiver;
    }

    @Override
    public void addReceiver(Receiver receiver) {
        receiverMapper.insertReceiver(receiver);
    }

    @Override
    public void modifyReceiver(Receiver receiver) {
        receiverMapper.updateReceiver(receiver);
    }

    @Override
    public void removeReceiver(String receiverId) {
        receiverMapper.deleteReceiver(receiverId);
    }
}
