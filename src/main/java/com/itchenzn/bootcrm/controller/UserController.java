package com.itchenzn.bootcrm.controller;

import com.itchenzn.bootcrm.dao.CustomerDao;
import com.itchenzn.bootcrm.pojo.BaseDict;
import com.itchenzn.bootcrm.pojo.Customer;
import com.itchenzn.bootcrm.pojo.User;
import com.itchenzn.bootcrm.service.BaseDictService;
import com.itchenzn.bootcrm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BaseDictService baseDictService;


    @Autowired
    private CustomerDao customerDao;
    /**
     * 用户登录
     */
    //跳转到登录页面
    @RequestMapping("/login.action")
    public String toLogin(){
        //测试根据类别代码查询数据字典
       /* List<BaseDict> baseDicts= baseDictService.findBaseDictByTypeCode("001");
        for (BaseDict baseDict : baseDicts) {
            System.out.println(baseDict);
        }*/


        //测试添加客户信息的功能
       /* Customer customer=new Customer();
        customer.setCust_name("王五");
        customer.setCust_user_id(1);
        customer.setCust_create_id(1);
        customer.setCust_source("2");
        customer.setCust_industry("7");
        customer.setCust_level("9");
        customer.setCust_linkman("张三");
        customer.setCust_phone("027-8859991");
        customer.setCust_mobile("027-8859663");
        customer.setCust_zipcode("430005");
        customer.setCust_address("湖北武汉");

        Date d=new Date();
        Timestamp timestamp=new Timestamp(d.getTime());

        customer.setCust_createtime(timestamp);

        customerDao.createCustomer(customer);
        System.out.println("插入客户信息成功！");
        */

        return "login";
    }

    //实现用户登录功能
    @RequestMapping(value = "/login.action",method = RequestMethod.POST)
    public String login(String usercode, String password, Model model, HttpSession httpSession){
        //通过账号和密码查询用户
        User user=userService.findUser(usercode,password);
        if (user!=null){
            //将用户对象添加到Session中
            httpSession.setAttribute("user",user);
            //跳转到主页面
            return "redirect:/customer/list.action";
        }
        model.addAttribute("msg","账号或密码错误，请重新输入！！");
        //返回到登录页面
        return "login";
    }


    //跳转到客户页面
    @RequestMapping("/toCustomer.action")
    public String toCustomer(){
        return "customer";
    }

    //实现退出功能
    @RequestMapping("/logout.action")
    public String toLogout(HttpSession session){
        session.invalidate();
        return "redirect:login.action";
    }
}
