package com.lt.dao;

import cn.itcast.jdbc.TxQueryRunner;
import com.lt.domain.Customer;
import com.lt.domain.PageBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 持久层
 */
public class CustomerDao {
    private QueryRunner queryRunner=new TxQueryRunner();

    /**
     * 添加客户
     * @param customer
     */
    public void add(Customer customer){
        try {
            String sql="insert into t_customer values(?,?,?,?,?,?,?)";
            Object[] params={customer.getCid(),customer.getCname(),customer.getGender(),customer.getBirthday(),
                    customer.getCellphone(),customer.getEmail(),customer.getDescription()};
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    /**
//     * 查询所有
//     * @return
//     */
//    public List<Customer> findAll(){
//        try {
//            String sql="select * from t_customer";
//            return queryRunner.query(sql,new BeanListHandler<Customer>(Customer.class));
//        } catch (SQLException e) {
//           throw new RuntimeException(e);
//        }
//    }


    /**
     * 查询所有(分页)
     * @param pc
     * @param ps
     * @return
     */
    public PageBean<Customer> findAll(int pc, int ps){
        try {
            /**
             * 1、得到PageBean对象pb
             * 2、设置pb的pc和ps
             * 3、得到tr，设置给pb
             * 4、到beanList，设置给pb
             * 5、返回pb
             */
            PageBean<Customer> pageBean=new PageBean<Customer>();
            pageBean.setPc(pc);
            pageBean.setPs(ps);
            /**
             * 得到tr
             */
            String sql="select count(*) from t_customer";
            Number number=(Number)queryRunner.query(sql,new ScalarHandler());
            int tr= number.intValue();
            pageBean.setTr(tr);
            /**
             * 得到BeanList
             */
            sql="select * from t_customer order by cname limit ?,?";
            List<Customer> beanList=queryRunner.query(sql,new BeanListHandler<Customer>(Customer.class),(pc-1)*ps,ps);
            pageBean.setBeanList(beanList);
            return pageBean;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }


    /**
     * 加载用户
     * @param cid
     * @return
     */
    public Customer load(String cid) {
        try {
            String sql="select * from t_customer where cid=?";
            return queryRunner.query(sql,new BeanHandler<Customer>(Customer.class),cid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 编辑用户
     * @param customer
     */
    public void edit(Customer customer){
        try {
            String sql="update t_customer set cname=?,gender=?,birthday=?,cellphone=?,email=?,description=? where cid=?";
            Object[] params={customer.getCname(),customer.getGender(),customer.getBirthday(),
                    customer.getCellphone(),customer.getEmail(),customer.getDescription(),customer.getCid()};
            queryRunner.update(sql,params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除用户
     * @param cid
     */
    public void delete(String cid){
        String sql = "delete from t_customer where cid=?";
        Object[] params = {cid};
        try {
            queryRunner.update(sql, params);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//    /**
////     * 多条件组合查询
////     * @param criteria
////     * @return
////     */
////    public List<Customer> query(Customer criteria){
////        try {
////            /**
////             * 1、给出sql模板
////             * 2、给出参数
////             * 3、调用query方法，使用结果集处理器:BeanListHandler
////             */
////            /**
////             * 一、给出sql模板
////             * 二、给出参数！
////             */
////            /**
////             * 1、给出一个sql语句前半部
////             */
////            StringBuilder sql = new StringBuilder("select * from t_customer where 1=1");
////            /**
////             * 2、判断条件，完成向sql中追加where子句
////             */
////            /**
////             * 3、创建一个ArrayList，用来装载参数值
////             */
////            List<Object> params = new ArrayList<Object>();
////            String cname = criteria.getCname();
////            if (cname != null && !cname.trim().isEmpty()) {
////                sql.append(" and cname like ?");
////                params.add("%"+cname+"%");
////            }
////
////            String gender = criteria.getGender();
////            if (gender != null && !gender.trim().isEmpty()) {
////                sql.append(" and gender=?");
////                params.add(gender);
////            }
////
////            String cellphone = criteria.getCellphone();
////            if (cellphone != null && !cellphone.trim().isEmpty()) {
////                sql.append(" and cellphone like ?");
////                params.add("%"+cellphone+"%");
////            }
////
////            String email = criteria.getEmail();
////            if (email != null && !email.trim().isEmpty()) {
////                sql.append(" and email like ?");
////                params.add("%"+email+"%");
////            }
////
////            /**
////             * 三、执行query
////             */
////            return queryRunner.query(sql.toString(), new BeanListHandler<Customer>(Customer.class), params.toArray());
////        }catch (SQLException e){
////            throw new RuntimeException(e);
////        }
////
////
////    }


    /**
     * 多条件组合查询
     * @param criteria
     * @return
     */
    public  PageBean<Customer> query(Customer criteria,int pc,int ps){
        try {
            /**
             * 1、创建PageBean对象
             * 2、设置已有的属性
             * 3、得到tr
             * 4、得到beanList
             */
            /*
                创建pb，设置已有属性
             */
            PageBean<Customer> pageBean=new PageBean<Customer>();
            pageBean.setPc(pc);
            pageBean.setPs(ps);
            /*
              得到tr
             */
            /**
             * 1、给出一个sql语句前半部
             */
            StringBuilder cntsql = new StringBuilder("select count(*) from t_customer");
            StringBuilder wheresql = new StringBuilder(" where 1=1");
            /**
             * 2、判断条件，完成向sql中追加where子句
             */
            /**
             * 3、创建一个ArrayList，用来装载参数值
             */
            List<Object> params = new ArrayList<Object>();
            String cname = criteria.getCname();
            if (cname != null && !cname.trim().isEmpty()) {
                wheresql.append(" and cname like ?");
                params.add("%"+cname+"%");
            }

            String gender = criteria.getGender();
            if (gender != null && !gender.trim().isEmpty()) {
                wheresql.append(" and gender=?");
                params.add(gender);
            }

            String cellphone = criteria.getCellphone();
            if (cellphone != null && !cellphone.trim().isEmpty()) {
                wheresql.append(" and cellphone like ?");
                params.add("%"+cellphone+"%");
            }

            String email = criteria.getEmail();
            if (email != null && !email.trim().isEmpty()) {
                wheresql.append(" and email like ?");
                params.add("%"+email+"%");
            }

            /**
             * select count(*) ..+where子句
             */
            Number number= (Number) queryRunner.query(cntsql.append(wheresql).toString(),new ScalarHandler(),params.toArray());
            int tr=number.intValue();
            pageBean.setTr(tr);


            /**
             * 得到beanList
             */
            StringBuilder sql=new StringBuilder("select * from t_customer");
            //我们查询beanList这一步，还需要给出limit子句
            StringBuilder limitSql=new StringBuilder(" limit ?,?");
            //params中需要给出limit后两个问号对应的值
            params.add((pc-1)*ps);
            params.add(ps);
            //执行
            List<Customer> beanList=queryRunner.query(sql.append(wheresql).append(limitSql).toString(),
                    new BeanListHandler<Customer>(Customer.class),
                    params.toArray());
            pageBean.setBeanList(beanList);
            return pageBean;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }


    }
}
