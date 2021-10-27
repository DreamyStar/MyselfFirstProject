package com.oracle.costomer.view;

import com.oracle.costomer.domain.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CustomerDao {
    private static Connection con;
    private static CustomerDao cd;
    public String url = "jdbc:mysql://localhost:3306/stu?serverTimezone=UTC";
    public String user = "root";
    public String passWord = "123456";

    public CustomerDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加
     */
    public boolean insert(Customer cu) {
        boolean flag = true;
        String sql = "insert into customer set name=?,gender=?,age=?,phone=?,email=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cu.getName());
            ps.setString(2, cu.getGender());
            ps.setInt(3, cu.getAge());
            ps.setInt(4, cu.getPhone());
            ps.setString(5, cu.getEmail());
            int i = ps.executeUpdate();
            if (i != 1) {
                System.out.println("添加失败");
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (flag);
    }

    /**
     * 删除
     */
    public boolean delete(int id) {
        boolean flag = true;
        String sql = "delete from customer where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            if (i != 1) {
                System.out.println("删除失败");
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (flag);
    }

    /**
     * 更新
     */
    public boolean update(Customer cu, int id) {
        boolean flag = true;
        String sql = "update customer set name=?,gender=?,age=?,phone=?,email=? where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cu.getName());
            ps.setString(2, cu.getGender());
            ps.setInt(3, cu.getAge());
            ps.setInt(4, cu.getPhone());
            ps.setString(5, cu.getEmail());
            ps.setInt(6, id);
            int i = ps.executeUpdate();
            if (i != 1) {
                System.out.println("更新失败!!!");
                flag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询
     */
    public List<Customer> selectAll() {
        String sql = "select * from customer";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            List<Customer> list = new LinkedList<>();
            while (rs.next()) {
                list.add(new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("gender"),
                        rs.getInt("age"), rs.getInt("phone"), rs.getString("email")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用ID查询
     */
    public Customer selectID(int id) {
        Customer cu = null;
        String sql = "select * from customer where id=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cu = new Customer(rs.getString("name"), rs.getString("gender"),
                        rs.getInt("age"), rs.getInt("phone"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cu;
    }

    /**
     * 名字查询
     */
    public List<Customer> selectName(String name) {
        Customer cu = null;
        String sql = "select * from customer where name=?";
        List<Customer> list = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            list = new LinkedList<>();
            if (rs.next()) {
                cu = new Customer(rs.getInt("id"),rs.getString("name"), rs.getString("gender"),
                        rs.getInt("age"), rs.getInt("phone"), rs.getString("email"));
            }
            list.add(cu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public  void close() {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Statement con = null;
        if (rs != null) {
            try {
               rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
