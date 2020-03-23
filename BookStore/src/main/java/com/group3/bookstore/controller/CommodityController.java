package com.group3.bookstore.controller;

import com.group3.bookstore.pojo.Commodity;
import com.group3.bookstore.service.ICategoryService;
import com.group3.bookstore.service.ICommodityService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
@Controller
@RequestMapping("/book")
@SessionAttributes("user")
public class CommodityController {
    @Autowired
    private ICommodityService commodityService;
    //页数
    private int currentPage = 0;
    //书名模糊查询内容
    private String name = null;
    //分类查询内容
    private String category = "all";
    /*排序规则
    * hot 热度（销量）
    * price 价格
    * time 最新
    **/
    private String order = "name";

    @RequestMapping(value = "/getAllCommodity")
    public ModelAndView  getAllCommodity(){
        this.currentPage = 0;
        return commoditiesList();
    }
    //查找一个
    @RequestMapping(value = "/getOne",method = RequestMethod.GET)
    public ModelAndView one(HttpServletRequest request){
        String commodityId = request.getParameter("id");
        Commodity book = commodityService.selectById(commodityId);
        System.out.println(commodityId);
        System.out.println(book);
        ModelAndView modelAndView = new ModelAndView("commodity/commodityinfo","book",book);
        return modelAndView;
    }
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView search(String name ,String category ,String order) {
        this.name = name;
        this.category = category;
        this.order = order;
        this.currentPage = 0;
        return commoditiesList();
    }
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView search(String category ,String order) {
        this.category = category;
        this.order = order;
        this.currentPage = 0;
        return commoditiesList();
    }

    @RequestMapping(value = "/addCommodity" , method = RequestMethod.GET)
    public String add(Commodity commodity){
        commodityService.addOne(commodity);
        return "controller";
    }

    @RequestMapping(value = "/deleteCommodity",method = RequestMethod.GET)
    public String delete(String bookId)
    {
        commodityService.deleteById(bookId);
        return "controller";
    }
    @RequestMapping(value = "/updateCommodity",method = RequestMethod.GET)
    public String update(String bookId,Commodity commodity)
    {
        commodityService.updateById(bookId,commodity);
        return "controller";
    }
    @RequestMapping(value = "/selectnew",method = RequestMethod.GET)
    public ModelAndView selectnew(){
        List<Commodity> commodities = commodityService.selectByTime();
        ModelAndView modelAndView = new ModelAndView("ads","commodities",commodities);
        return modelAndView;
    }

    @RequestMapping(value = "/selectOnsale",method = RequestMethod.GET)
    public ModelAndView selectOnsale(){
        List<Commodity> commodities = commodityService.selectBySaleCount();
        ModelAndView modelAndView = new ModelAndView("ads","commodities",commodities);
        return modelAndView;
    }

    @RequestMapping(value = "/next",method = RequestMethod.GET)
    public ModelAndView next(){
        currentPage++;
        return commoditiesList();
    }
    @RequestMapping(value = "/prev",method = RequestMethod.GET)
    public ModelAndView prev(){
        currentPage--;
        return commoditiesList();
    }
    //得到商品列表
    public ModelAndView commoditiesList() {
        List<Commodity> commodities = commodityService.selectAll(currentPage,name,category,order);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("commodities",commodities);
        modelAndView.setViewName("commodity/sortcommodity");
        System.out.println(commodities);
        return modelAndView;
    }

}
