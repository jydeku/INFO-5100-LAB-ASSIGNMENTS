/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Product;

/**
 *
 * @author User
 */
public class DatabaseUtils {
    
    String connStr = "jdbc:mysql://localhost:8000/Products";
    String user = "root";
    String pass = "admin";
    static Connection conn;
    
    public DatabaseUtils() {
        try {
            conn = DriverManager.getConnection(connStr, user, pass);
            System.out.println("Connection Successful... ");
        } catch (SQLException ex) {
            System.out.println("Connection Failed... "+ ex.getMessage());
        }
    }
    
    public static ArrayList<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        String QUERY = "SELECT * FROM Product";
        
        try{ 
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
            
            while(rs.next()){
                Product product = new Product(rs.getInt("id"), 
                        rs.getString("name"), rs.getDouble("price"));
                products.add(product);
            }
            
            stmt.close();
        }catch(SQLException ex){
            System.out.println("Error while fetching data... "+ ex.getMessage());
        }
        
        return (ArrayList<Product>) products;
    }
    
    
    public static boolean insertProduct(Product product){
        String QUERY = "INSERT INTO Product(name, price) VALUES (?, ?)";
        
        try{
            PreparedStatement ps = conn.prepareStatement(QUERY);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            int row = ps.executeUpdate();
            ps.close();
            if(row > 0){
                return true;
            }else{
                return false;
            }
            
        }catch(SQLException ex){
            System.out.println("Error while inserting data... "+ex.getMessage());
            return false;
        }
    }
    
    public static boolean updateProduct(Product product){
        String QUERY = "UPDATE Product SET name=?, price=? WHERE id=?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(QUERY);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getId());
            
            int row = ps.executeUpdate();
            ps.close();
            if(row > 0){
                return true;
            }else{
                return false;
            }
        }catch(SQLException ex){
            System.out.println("Error while updating data... "+ex.getMessage());
            return false;
        }
    }
    
    public static boolean deleteProduct(int id){
        String QUERY = "DELETE FROM Product WHERE id = ?";
        
        try{
            PreparedStatement ps = conn.prepareStatement(QUERY);
            ps.setInt(1, id);
            
            int row = ps.executeUpdate();
            
            ps.close();
            if(row > 0){
                return true;
            }else{
                return false;
            }
        }catch(SQLException ex){
            System.out.println("Error while deleting data... "+ ex.getMessage());
            return false;
        }
    }
    
    public void close(){
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error while closing: "+ ex.getMessage());
        }
    }
    
    
}
