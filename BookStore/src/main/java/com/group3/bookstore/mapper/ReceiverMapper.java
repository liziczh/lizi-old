package com.group3.bookstore.mapper;

import com.group3.bookstore.pojo.Receiver;

import java.util.List;

public interface ReceiverMapper {
    // 查看收货信息列表（getAllReceiver）：
    public List<Receiver> getReceiverByUserId(String userId);
    // 查看收获信息项（getReceiverByReceiverId）：
    public Receiver getReceiverByReceiverId(String receiverId);
    // 添加收货信息项（addReceiver）：
    public void insertReceiver(Receiver receiver);
    // 更新收货信息项（modifyReceiver）：
    public void updateReceiver(Receiver receiver);
    // 删除收货信息项（removeReceiver）：
    public void deleteReceiver(String receiverId);

}
