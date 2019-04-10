package com.lt.dao;

import cn.itcast.utils.CommonUtils;
import com.lt.domain.Customer;
import org.junit.Test;

public class CustomerTest {
    CustomerDao customerDao=new CustomerDao();

    @Test
    public void fun1(){
        CustomerDao customerDao=new CustomerDao();
        for(int i=1;i<=300;i++){
            Customer customer=new Customer();

            customer.setCid(CommonUtils.uuid());
            customer.setCname("东方不败_"+i);
            customer.setBirthday("1999-11-11");
            customer.setGender(i%2==0?"男":"女");
            customer.setCellphone("139"+i);
            customer.setEmail("cstm_"+i+"@163.com");
            customer.setDescription("我是日月神教的！");

            customerDao.add(customer);
        }
    }
}
