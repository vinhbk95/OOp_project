/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop812;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class connectDatabase {
    
    private final String className = "com.mysql.jdbc.Driver";
    private Connection connection;
    private String table = "quanly"; //Khai báo rồi chút kết nối đến bảng bank
    private final String url = "jdbc:mysql://localhost:3306/oop812"; // Đường dẫ kết nối đến database bank
    private String user = "root"; //tài khoản và pass đăng nhập database
    private String pass = "123456";
    
    public void connect()
    {
        try {
            Class.forName(className);
            connection = (Connection)DriverManager.getConnection(url, user, pass);
            System.out.println("Connect success !");
        } catch (ClassNotFoundException ex) {
            System.out.println("Class Not Fount !");
        } catch (SQLException ex) {
            System.out.println("Connect Error !");
        }
    }
    
    
    
    
    public ResultSet getDataMucct(){
        ResultSet rs = null;
        //String sqlCommand = "select * from " + table;
        Statement st;
        try {                      
            st = connection.createStatement();
            rs = st.executeQuery("select * from mucct");
        } catch (SQLException ex) {
            System.out.println("Select Error !");
        }
        return rs;
    }
    
    public ResultSet getData(String sqlCommand)
    {
        ResultSet rs = null;
        //String sqlCommand = "select * from " + table;
        Statement st;
        try {                      
            st = connection.createStatement();
            rs = st.executeQuery(sqlCommand);
        } catch (SQLException ex) {
            System.out.println("Select Error !");
        }
        return rs;
    }
    
    public ResultSet getMucct(String name)
    {
        ResultSet rs = null;
        String sqlCommand = "select * from quanly where mucct = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, name);//1 là vị trí của dấu hỏi chấm trong hàm, thay vào số ? là id
            rs = pst.executeQuery();//Phương thức này thực thi câu select SQL, trả về 1 đối tượng ResultSet để chứa 1 danh sách các records thỏa mãn câu select.
        } catch (SQLException ex) {
            System.out.println("Select Error!");
        }
        return rs;
    }
    
    public ResultSet getChitiet(String name)
    {
        ResultSet rs = null;
        String sqlCommand = "select * from quanly where daymonth = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, name);//1 là vị trí của dấu hỏi chấm trong hàm, thay vào số ? là id
            rs = pst.executeQuery();//Phương thức này thực thi câu select SQL, trả về 1 đối tượng ResultSet để chứa 1 danh sách các records thỏa mãn câu select.
        } catch (SQLException ex) {
            System.out.println("Select Error!");
        }
        return rs;
    }
    
    public ResultSet getChitietthang(String name)
    {
        ResultSet rs = null;
        String sqlCommand = "select mucct, sum(tien) from quanly where daymonth = ? group by mucct";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sqlCommand);
            pst.setString(1, name);//1 là vị trí của dấu hỏi chấm trong hàm, thay vào số ? là id
            rs = pst.executeQuery();//Phương thức này thực thi câu select SQL, trả về 1 đối tượng ResultSet để chứa 1 danh sách các records thỏa mãn câu select.
        } catch (SQLException ex) {
            System.out.println("Select Error!");
        }
        return rs;
    }
    
    public ResultSet gettk(String name)
    {
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(name);
            rs = pst.executeQuery();//Phương thức này thực thi câu select SQL, trả về 1 đối tượng ResultSet để chứa 1 danh sách các records thỏa mãn câu select.
        } catch (SQLException ex) {
            System.out.println("Select Error!");
        }
        return rs;
    }
    
    
    
    public int insertmucct(String name)
    {
        String sqlCommand = "insert into mucct value(?)";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareCall(sqlCommand);
            pst.setString(1, name);
            if(pst.executeUpdate() > 0)
            {
                System.out.println("Insert Success !");
                return 1;
            }
            else
            {
                System.out.println("Insert Error !");
                return 0;
            }
        } catch (SQLException ex) {
            System.out.println("Inset Error !");
        }
        return 0;
    }
    
    public void deleteId(String id)
    {
        String sqlCommand = "delete from quanly where id = ?";
        PreparedStatement pst = null;
        try {
            //pst = connection.prepareStatement(sqlCommand);
            pst = connection.prepareCall(sqlCommand);
            pst.setString(1, id);
            //Phương thức này dùng để thực thi các câu sql insert, delete, update,… ngoại trừ câu select. Phương thức này trả về số mẫu tin bị ảnh hưởng bởi câu SQL.
            if(pst.executeUpdate()>0)//executeUpdate trả về 2 giá trị = 0 hoặc > 0
            {
                System.out.println("Delete success !");
            }
            else
            {
                System.out.println("Delete Error !");
            }
        } catch (SQLException ex) {
            System.out.println("Delete Error !" + ex.toString());
        }
    }
    
    public void insert(quanly ql)
    {
        String sqlCommand = "INSERT INTO `oop812`.`quanly` (`time`, `lydo`, `mucct`, `tien`, `tag`, `daymonth` ) VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareCall(sqlCommand);
            pst.setString(1, ql.getTime());
            pst.setString(2, ql.getLydo());
            pst.setString(3, ql.getMucct());
            pst.setDouble(4, ql.getTien());
            pst.setInt(5, ql.getTag());
            pst.setString(6, ql.getMyear());
            if(pst.executeUpdate() > 0)
            {
                System.out.println("Insert Success !");
            }
            else
            {
                System.out.println("Insert Error 1 !");
            }
        } catch (SQLException ex) {
            System.out.println("Inset Error 2 !");
        }
    }
    
    public void update(quanly ql)
    {
        String sqlCommand = "update quanly set time = ?, lydo = ?, mucct = ?, tien = ?, tag = ?, daymonth = ? where id = ?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareCall(sqlCommand);
            pst.setString(1, ql.getTime());
            pst.setString(2, ql.getLydo());
            pst.setString(3, ql.getMucct());
            pst.setDouble(4, ql.getTien());
            pst.setInt(5, ql.getTag());
            pst.setString(6, ql.getMyear());
            pst.setInt(7, ql.getId());
            if(pst.executeUpdate() > 0)
            {
                JOptionPane.showMessageDialog(null, "Update Success !");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Update Error !");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Update Error, Không được sửa ID");
        }
        
    }
    
    
}
