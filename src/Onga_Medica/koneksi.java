/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Onga_Medica;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author t
 */
public class koneksi {
     public static String serverIP="localhost";
    public static String serverPort="3306";
    public static String serverUser="root";
    public static String serverPass="";
    public static String serverDB="db_sparepart";
    private static Connection conn;
    private class_fungsi cls = new class_fungsi();
    
   public boolean getConnection() {
        if (conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn =DriverManager.getConnection("jdbc:mysql://"+ serverIP +":"+ 
                    serverPort +"/" + serverDB, serverUser, serverPass);
            } catch (ClassNotFoundException | SQLException ex) {
                ex.getMessage();
            }
        }
        return (conn!=null);
    }
    // Send data TO Database
    public void setData(String sql) throws SQLException {
        try {
            if (getConnection()) {
                conn.createStatement().executeUpdate(sql);
            }
        } catch (SQLException ex) {
            cls.showMsg(ex.getMessage(), "setData", 0);;
        }
        finally {
            disconnect();
        }
    }
    
    // Get Data From Database
    public ResultSet getData(String sql){
        ResultSet rs=null;
        try {
            if (getConnection()) {
                rs=conn.createStatement().executeQuery(sql);   
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return rs;
    }
    
    public void disconnect() throws SQLException {
        if (conn != null) {
            conn.close();
            conn=null;
        }
    }

   
    

}