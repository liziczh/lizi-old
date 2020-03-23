package com.group3.bookstore.controller;

import com.group3.bookstore.pojo.Receiver;
import com.group3.bookstore.pojo.User;
import com.group3.bookstore.service.IReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/receiver")
public class ReceiverController {

    @Autowired
    private IReceiverService receiverService;
    // 查看收货信息列表（getAllReceiver）：
    @RequestMapping(value = "/getReceiverByUserId")
    public ModelAndView getReceiverByUserId(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Receiver> receiverList = receiverService.getReceiverByUserId(user.getUserId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("receiverList",receiverList);
        modelAndView.setViewName("receiver");
        return modelAndView;
    }
    // 查看收货信息项（getReceiverByReceiverId）
    @RequestMapping(value = "/getReceiverByReceiverId")
    public ModelAndView getReceiverByReceiverId(HttpSession session){
        User user = (User) session.getAttribute("user");
        Receiver receiver = receiverService.getReceiverByReceiverId(user.getUserId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("receiver",receiver);
        modelAndView.setViewName("receiverDetail");
        return modelAndView;
    }

    // 添加收货信息项（addReceiver）
    @RequestMapping(value = "/addReceiver")
    public ModelAndView addReceiver(String receiverName,String receiverPhoneNumber,String province,String city,String county,String street,HttpSession session){
        User user = (User) session.getAttribute("user");
        String receiverId = String.valueOf(UUID.randomUUID());
        Receiver receiver = new Receiver(receiverId,user.getUserId(),receiverName,receiverPhoneNumber,province,city,county,street);
        receiverService.addReceiver(receiver);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("receiver",receiver);
        modelAndView.setViewName("receiver");
        return modelAndView;
    }

    // 编辑收货信息项（modifyReceiver）
    @RequestMapping(value = "/modifyReceiver")
    public ModelAndView modifyReceiver(String receiverName,String receiverPhoneNumber,String province,String city,String county,String street,HttpSession session){
        User user = (User) session.getAttribute("user");
        String receiverId = String.valueOf(UUID.randomUUID());
        Receiver receiver = new Receiver(receiverId,user.getUserId(),receiverName,receiverPhoneNumber,province,city,county,street);
        receiver.setReceiverName(receiverName);
        receiver.setReceiverPhoneNumber(receiverPhoneNumber);
        receiver.setProvince(province);
        receiver.setCity(city);
        receiver.setCounty(county);
        receiver.setStreet(street);
        receiverService.addReceiver(receiver);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("receiver",receiver);
        modelAndView.setViewName("receiver");
        return modelAndView;
    }
    // 删除收货信息项（removeReceiver）
    @RequestMapping(value = "/removeReceiver")
    public String removeReceiver(String receiverId,HttpSession session){
        User user = (User) session.getAttribute("user");
        receiverService.removeReceiver(receiverId);
        return "receiver";
    }

}
