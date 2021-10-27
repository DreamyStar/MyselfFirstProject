package com.oracle.costomer.view;

import java.net.DatagramPacket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CustomerDao2 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stu?serverTimezone=UTC","root","123456");
            String sql = "select * from customer where name = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,"zlq");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int t = rs.getInt(1);
                System.out.println("编号"+t);
                String name = rs.getString(2);
                System.out.println("姓名"+name);
            }
            con.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
