package com.oracle.costomer.view;

import com.oracle.costomer.domain.Customer;
import com.oracle.costomer.until.CMUtility;

import java.util.List;

public class CustomerView {
    CustomerDao cd = new CustomerDao();

    /**
     * 主页面
     */
    public void enterMainMenu() {
        do {
            System.out.println("-------***--欢迎来客户管理主页面--***--------");
            System.out.println("\t\t1、登录1");
            System.out.println("\t\t2、退出2");
            System.out.println("\t\t请输入您的选择:");
            char c = CMUtility.readMenuSelection();
            switch (c) {
                case '1':
                    System.out.println("请输入用户名:");
                    String name = CMUtility.readString(10);
                    System.out.println("请输入用户密码:");
                    String passWord = CMUtility.readString(10);
                    if (name.equals(cd.user) && passWord.equals(cd.passWord)) {
                        enterMenu();
                    } else {
                        System.out.println("-*-*-*-*-*用户名或密码错误-*-*-*-*-*\n");
                    }
                    break;
                case '2':
                    System.out.println("您确定要退出吗？确定输入y，否则输入n");
                    System.out.println("请输入您的选择:");
                    char cn = CMUtility.readConfirmSelection();
                    if ('Y' == cn) {
                        System.exit(0);
                    }
                    break;
            }
        } while (true);
    }

    /**
     * 管理页面
     */
    public void enterMenu(){
        boolean flag = true;
        do {
            System.out.println("------**----欢迎来到客户管理系统-----**-----");
            System.out.println("\t\t1、添加新客户请输入1");
            System.out.println("\t\t2、删除客户请输入2");
            System.out.println("\t\t3、更新客户请输入3");
            System.out.println("\t\t4、查询客户请输入4");
            System.out.println("\t\t5、姓名查询5");
            System.out.println("\t\t6、退出系统请输入6");
            System.out.println("\t\t请输入您的选择:");
            char r = CMUtility.readMenuSelection();
            switch (r){
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    deleteCustomer();
                    break;
                case '3':
                    updateCustomer();
                    break;
                case '4':
                    selectAllCustomer();
                    break;
                case '5':
                    selectNameCustomer();
                    break;
                case '6':
                    System.out.println("确定要退出吗？确定输入y，否则输入n");
                    System.out.println("请输入您的选择");
                    char ro = CMUtility.readConfirmSelection();
                    if ('Y' == ro){
                        cd.close();
                        System.out.println("退出成功");
                        System.exit(0);
                    }
                    break;
            }
        }while (flag);
    }

    /**
     *添加新客户
     */
    private void addNewCustomer(){
        System.out.println("—*****----添加新客户----*****-");
        System.out.println("请输入姓名:");
        String name = CMUtility.readString(10);
        System.out.println("请输入性别:");
        String gender = CMUtility.readString(3);
        System.out.println("请输入年龄:");
        int age = CMUtility.readInt();
        System.out.println("请输入电话:");
        int phone = CMUtility.readInt();
        System.out.println("请输入邮箱:");
        String email = CMUtility.readString(15);
        Customer cu = new Customer(name,gender,age,phone,email);
        if (cd.insert(cu)){
            System.out.println("--------添加成功-------");
        }else {
            System.out.println("--------添加失败-------");
        }
    }

    /**
     *删除客户
     */
    private void deleteCustomer(){
        int id = 0;
        System.out.println("---------删除客户------");
        System.out.println("如果退出删除请输入-1，请输入您要选择删除客户的ID:");
        for (;;){
            id = CMUtility.readInt();
            if (id == -1)
                return;
            Customer cu = cd.selectID(id);
            if (cu == null){
                System.out.println("=======没有此用户=======");
            }else
                break;
        }
        System.out.println("您确定要删除吗？确定请输入y，否则输入n");
        System.out.println("\t\t请输入您的选择");
        char ar = CMUtility.readConfirmSelection();
        if ('Y' ==ar){
            if (cd.delete(id)){
                System.out.println("======*****删除成功*****=======");
            }else {
                System.out.println("======*****删除失败*****=======");
            }
        }
    }

    /**
     *更新客户
     */
    private void updateCustomer(){
        Customer cu = null;
        System.out.println("-****-****-****-更新客户-****-****-****-");
        System.out.println("如果退出更新请输入-1，否则输入您要选择更新的客户的ID:");
        for (;;){
            int id = CMUtility.readInt();
            if (id == -1){
                return;
            }
            cu = cd.selectID(id);
            if (cu == null){
                System.out.println("*******没有此用户******");
                System.out.println("如果退出更新请输入-1，否则输入您要选择更新的客户的ID:");
                continue;
            }
            System.out.println("您确定要退出更新吗？确定请输入y，否则输入n");
            System.out.println("\t\t请输入您的选择:");
            char r = CMUtility.readConfirmSelection();
            if ('N' == r){
                return;
            }
            System.out.println("姓名"+cu.getName()+"请输入更新后的姓名");
            String name = CMUtility.readString(10,cu.getName());
            System.out.println("性别"+cu.getGender()+"请输入更新后的性别");
            String gender = CMUtility.readString(2,cu.getGender());
            System.out.println("年龄"+cu.getAge()+"请输入更新后的年龄");
            int age = CMUtility.readInt(cu.getAge());
            System.out.println("电话"+cu.getPhone()+"请输入更新后的电话");
            int phone = CMUtility.readInt(cu.getPhone());
            System.out.println("邮箱"+cu.getEmail()+"请输入更新后的邮箱");
            String email = CMUtility.readString(15,cu.getEmail());
            cu = new Customer(name,gender,age,phone,email);
            if (cd.update(cu,id)){
                System.out.println("修改成功");
                System.out.println("如果退出更新请输入-1，否则输入您要选择更新的客户的ID:");
            }else {
                System.out.println("修改失败");
                System.out.println("如果退出更新请输入-1，否则输入您要选择更新的客户的ID:");
            }
        }
    }

    /**
     *查询所有客户
     */
    private void selectAllCustomer(){
        System.out.println("=====列出所有客户=====");
        List<Customer> list = cd.selectAll();
        if (list.size() == 0){
            System.out.println("没有找到bbb");
        }else {
            for(Customer c:list){
                System.out.println(c);
            }
        }
        System.out.println("======所有客户在头上=======");
    }

    /**
     *查找姓名
     */
    private void selectNameCustomer(){
        Customer cu = null;
//        int id = 0;
        System.out.println("列出所有客户");
        System.out.println("请输入需要查找的姓名:");
        String name = CMUtility.readString(10);
        List<Customer> list = cd.selectName(name);
        if (list.size() != 0){
            for (Customer c : list){
                System.out.println(c);
            }
        }else{
            System.out.println("没有此用户");
        }
    }
    public static void main(String[] args) {
        CustomerView v = new CustomerView();
        v.enterMainMenu();
    }
}
