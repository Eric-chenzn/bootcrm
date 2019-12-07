package com.itchenzn.bootcrm.controller;


import com.itchenzn.bootcrm.dao.CustomerDao;
import com.itchenzn.bootcrm.pojo.BaseDict;
import com.itchenzn.bootcrm.pojo.Customer;
import com.itchenzn.bootcrm.pojo.User;
import com.itchenzn.bootcrm.service.BaseDictService;
import com.itchenzn.bootcrm.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class CustomerController {
    //依赖注入
    @Autowired
    private BaseDictService baseDictService;

    @Autowired
    private CustomerService customerService;

    //客户来源
    @Value("${customer.from.type}")
    private String FROM_TYPE;

    //客户所属行业
    @Value("${customer.industry.type}")
    private String INDUSTRY_TYPE;

    //客户级别
    @Value("${customer.level.type}")
    private String LEVEL_TYPE;

    /**
     * 客户列表
     */
    @RequestMapping("/customer/list.action")
    public String list(Model model,
                       @RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10")Integer rows,
                       String custName,
                       String custSource,
                       String custIndustry,
                       String custLevel
                       ){

        //添加客户来源查询的条件
        Customer customer=new Customer();
        customer.setRows(rows);
        customer.setStart((page-1)*rows);
         //条件查询客户信息

        //判断客户名称是否存在
        if (StringUtils.isNotBlank(custName)){
            customer.setCust_name(custName);
        }
        //判断客户信息来源
        if (StringUtils.isNotBlank(custSource)){
            customer.setCust_source(custSource);
        }

        //判断客户所属行业
        if (StringUtils.isNotBlank(custIndustry)){
            customer.setCust_industry(custIndustry);
        }

        //判断客户所属行业
        if (StringUtils.isNotBlank(custLevel)){
            customer.setCust_level(custLevel);
        }


       //查询客户列表信息
        List<Customer> customerList= customerService.selectCustomerList(customer);
        model.addAttribute("customerList",customerList);

        //查询客户总条目数(用于分页显示数据)
        Integer customerCount=customerService.selectCustomerListCount(customer);
        //将分页的数据传递到前端
        model.addAttribute("rows",rows);
        model.addAttribute("page",page);
        model.addAttribute("customerCount",customerCount);

        //将条件查询的信息发送到前端页面
        model.addAttribute("custName",custName);
        model.addAttribute("custSource",custSource);
        model.addAttribute("custIndustry",custIndustry);
        model.addAttribute("custLevel",custLevel);



        //查询客户信息列表
        List<BaseDict> fromType=baseDictService.findBaseDictByTypeCode(FROM_TYPE);

        //添加客户所属行业的查询条件
        List<BaseDict> industryType=baseDictService.findBaseDictByTypeCode(INDUSTRY_TYPE);

        //添加客户级别的查询条件
        List<BaseDict> levelType=baseDictService.findBaseDictByTypeCode(LEVEL_TYPE);



        //将参数封装到model容器中
        model.addAttribute("fromType",fromType);

        model.addAttribute("industryType",industryType);

        model.addAttribute("levelType",levelType);

        return "customer";
    }

    /**
     * 创建客户
     */
    @RequestMapping("/customer/create.action")
    @ResponseBody
    public String customerCreate(Customer customer, HttpSession session) {
        //获取session中的当前用户信息
        User user = (User) session.getAttribute("user");

        //将当前用户的id存储在客户对象中
        customer.setCust_create_id(user.getUser_id());

        //将责任人的id 存储在客户对象中
        customer.setCust_user_id(user.getUser_id());

        //创建data对象
        Date date = new Date();

        //得到一个TimeStamp格式的时间，存入mysql中的时间格式为“yyyy/MM/dd HH:mm:ss”
        Timestamp timestamp = new Timestamp(date.getTime());

        customer.setCust_createtime(timestamp);

        //执行Service中创建的方法
        int rows = customerService.createCustomer(customer);
        if (rows > 0) {
            return "ok";
        } else {
            return "false";
        }

    }

        /**
         * 修改客户信息
         */

        //1.根据id查询客户信息
        @RequestMapping("/customer/getCustomerById.action")
        @ResponseBody
        public Customer getCustomerById(Integer id){
            Customer customer = customerService.getCustomerById(id);
            return customer;
        }

        //2.更新客户信息
        @RequestMapping("/customer/updateCustomer.action")
        @ResponseBody
        public String customerUpdate(Customer customer){
            int rows = customerService.updateCustomer(customer);
            if(rows>0){
                return "OK";
            }else{
                return "FAIL";
            }

        }

    /**
     *删除客户信息
     */

    @RequestMapping("/customer/deleteCustomerById.action")
    @ResponseBody
    public String deleteCustomerById(Integer id){
        int rows=customerService.deleteCustomerById(id);
        if (rows>0)
            return "OK";
        else
            return "FALSE";
    }

}




