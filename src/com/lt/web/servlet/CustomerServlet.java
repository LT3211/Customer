package com.lt.web.servlet;

import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;
import com.lt.domain.Customer;
import com.lt.domain.PageBean;
import com.lt.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * WEB层
 */
public class CustomerServlet extends BaseServlet {
    private CustomerService customerService=new CustomerService();

    /**
     * 添加客户
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public String add(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /**
         * 1、封装表单数据到Customer对象
         * 2、补全：cid，使用uuid
         * 3、使用service方法完成添加工作
         * 4、向request域中保存成功信息
         * 5、转发到msg.jsp
         */
        Customer customer= CommonUtils.toBean(req.getParameterMap(),Customer.class);
        customer.setCid(CommonUtils.uuid());
        customerService.add(customer);
        req.setAttribute("msg","恭喜,添加客户成功！");
        return "f:/msg.jsp"; //转发
    }

//    /**
//     * 查询所有
//     * @param req
//     * @param res
//     * @return
//     * @throws ServletException
//     * @throws IOException
//     */
//    public String findAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        /**
//         * 1、调用Service得到所有客户
//         * 2、保存到request域
//         * 3、转发到list.jsp
//         */
//        req.setAttribute("cstmList",customerService.findAll());
//        return "f:/list.jsp";
//    }


    public String findAll(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /**
         * 1、获取页面传递的pc
         * 2、给定ps的值
         * 3、使用pc和ps调用service方法，得到PageBean，保存到request域
         * 4、转发到list.jsp
         */
        int pc=getPac(req);//得到pc
        int ps=10;//给定ps的值，每页10行记录
        PageBean<Customer> pageBean=customerService.findAll(pc,ps);//传递pc，ps给Service得到PageBean。

        //设置url
        pageBean.setUrl(getUrl(req));

        req.setAttribute("pb",pageBean);//保存到request域中

        return "f:/list.jsp";//转发到list.jsp
    }

    /**
     * 获取当前页码pc
     * @param request
     * @return
     */
    public int getPac(HttpServletRequest request){
        /**
         * 1、得到pc
         *      如果参数不存在，说明pc=1；
         *      如果pc参数存在，需要转换成int类型即可
         */
        String value=request.getParameter("pc");
        if (value==null||value.trim().isEmpty()){
            return 1;
        }
        return Integer.parseInt(value);
    }

    /**
     * 编辑之前的加载工作
     * @param req
     * @param res
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String preEdit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /**
         * 1、获取cid
         * 2、使用cid来调用srvice方法，得到Customer对象
         * 3、把Customer保存到request域中
         * 4、转发到edit.jsp显示在表单中
         */
        String cid=req.getParameter("cid");
        Customer customer=customerService.load(cid);
        req.setAttribute("cstm",customer);
        return "f:/edit.jsp";
    }


    /**
     * 编辑方法
     * @param req
     * @param res
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String edit(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /**
         * 1、封装表单数据到Customer对象中
         * 2、调用Service方法完成修改
         * 3、保存成功信息到request域
         * 4、转发到msg.jsp显示成功信息
         */

        //已经封装了cid到Customer对象中
        Customer customer=CommonUtils.toBean(req.getParameterMap(),Customer.class);
       customerService.edit(customer);
       req.setAttribute("msg","恭喜，编辑客户成功！");
        return "f:/msg.jsp";
    }


    /**
     * 删除记录
     * @param req
     * @param res
     * @return
     * @throws ServletException
     * @throws IOException
     */
    public String delete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        /**
         * 1、获取参数cid
         * 2、传递cid给service的方法完成删除
         * 3、保存成功信息到request域
         * 4、转发到msg.jsp显示成功信息
         */
        String cid=req.getParameter("cid");
        customerService.delete(cid);
        req.setAttribute("msg","恭喜，删除客户成功！");
        return "f:/msg.jsp";
    }


//    /**
//     * 高级查询
//     * @param req
//     * @param res
//     * @return
//     * @throws ServletException
//     * @throws IOException
//     */
//    public String query(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        /**
//         * 1、封装表单数据到Customer对象中，它只有四个属性(cname,gender,cllphone、email)
//         *   它就是一个条件
//         * 2、传使用Customer调用service方法，得到List<Customer>
//         * 3、保存到request域
//         * 4、转发list.jsp
//         */
//       Customer criteria=CommonUtils.toBean(req.getParameterMap(),Customer.class);
//        List<Customer> cstmList=customerService.query(criteria);
//        req.setAttribute("cstmList",cstmList);
//        return "f:/list.jsp";
//    }

    public String query(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println(getUrl(req));
        /**
         * 0、把条件封装到Customer对象中
         * 1、得到pc
         * 2、给定ps
         * 3、使用pc和ps，以及条件对象调用service方法得到PageBean
         * 4、把PageBean保存到request域
         * 5、转发到list.jsp
         */
        //获取查询条件
        Customer criteria=CommonUtils.toBean(req.getParameterMap(),Customer.class);

//        /**
//         * 处理GET请求方式的编码问题(本项目部署在Tomcat8.0中不需要处理请求参数问题)
//         */
//        criteria=encoding(criteria);
        int pc=getPac(req);//得到pc
        int ps=10;//给定ps的值，每页10行记录
        PageBean<Customer> pageBean=customerService.query(criteria,pc,ps);
       //得到url，保存到pb中
        pageBean.setUrl(getUrl(req));
        req.setAttribute("pb",pageBean);
        return "f:/list.jsp";
    }

//    /**
//     * 处理4样（不用处理）
//     * @param customer
//     * @return
//     */
//    private Customer encoding(Customer customer) throws UnsupportedEncodingException {
//        String cname = customer.getCname();
//        String gender = customer.getGender();
//        String cellPhone = customer.getCellphone();
//        String email = customer.getEmail();
//
//        if (cname != null && !cname.trim().isEmpty()) {
//            cname = new String(cname.getBytes("ISO-8859-1"), "UTF-8");
//            System.out.println(cname);
//            customer.setCname(cname);
//        }
//        if (gender != null && !gender.trim().isEmpty()) {
//            gender = new String(gender.getBytes("ISO-8859-1"), "UTF-8");
//            System.out.println(gender);
//            customer.setGender(gender);
//        }
//        if (cellPhone != null && !cellPhone.trim().isEmpty()) {
//            cellPhone = new String(cellPhone.getBytes("ISO-8859-1"), "UTF-8");
//            customer.setCellphone(cellPhone);
//        }
//        if (email != null && !email.trim().isEmpty()) {
//            email = new String(email.getBytes("ISO-8859-1"), "UTF-8");
//            customer.setEmail(email);
//        }
//        return customer;
//    }


    /**
     * 截取url
     *  /项目名/servlet路径？参数字符串
     * @return
     */
    private String getUrl(HttpServletRequest request){
        String contextPath=request.getContextPath();//获取项目名
        String servletPath=request.getServletPath();//获取servletPath，即/CustomerServlet
        String queryString=request.getQueryString();//获取问号之后的参数部分

        //判断参数部分中农是否包含pc这个参数，如果包含，需要截取下去，不要这一部分
        if (queryString.contains("&pc=")){
            int index=queryString.lastIndexOf("&pc=");
            queryString=queryString.substring(0,index);
        }

        return contextPath+servletPath+"?"+queryString;
    }

}
