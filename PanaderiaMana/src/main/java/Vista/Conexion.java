package Vista;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author BENJAMIN
 */
public class Conexion {
    
    Connection con = null;
    
    
    String usuario = "root";
    String contrasenia = "mysql";
    String bd = "panamanabd";
    String ip = "localhost";
    String puerto = "3306";
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;
    
    public Connection establecerConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cadena,usuario,contrasenia);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "La conexión de base de datos denegada");
            
        }
        return con;
                
    }        
}
