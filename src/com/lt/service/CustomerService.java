package com.lt.service;

import com.lt.dao.CustomerDao;
import com.lt.domain.Customer;
import com.lt.domain.PageBean;

import java.util.List;

/**
 * 业务层
 */
public class CustomerService {
    private CustomerDao customerDao=new CustomerDao();

    /**
     * 添加客户
     * @param customer
     */
    public void add(Customer customer){
        customerDao.add(customer);
    }

//    /**
//     * 查询所有
//     * @return
//     */
//    public List<Customer> findAll(){
//        return customerDao.findAll();
//    }

    /**
     * 查询所有(分页)
     * @param pc
     * @param ps
     * @return
     */
    public PageBean<Customer> findAll(int pc,int ps){
        return customerDao.findAll(pc,ps);
    }

    /**
     * 加载用户
     * @param cid
     * @return
     */
    public Customer load(String cid){
        return customerDao.load(cid);
    }


    /**
     * 编辑用户
     * @param customer
     */
    public void edit(Customer customer){
       customerDao.edit(customer);
    }


    /**
     * 删除用户
     * @param cid
     */
    public void delete(String cid){
        customerDao.delete(cid);
    }

//    /**
//     * 多条件组合查询
//     * @param customer
//     */
//    public  List<Customer> query(Customer customer){
//        return customerDao.query(customer);
//    }

    public   PageBean<Customer> query(Customer customer,int pc,int ps){
        return customerDao.query(customer,pc,ps);
    }
}
