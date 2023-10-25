/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author BENJAMIN
 */
public class Empleado {
    int id;
    String nombre;
    String rol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public void InsertarEmpleado(JTextField paramNombre, JTextField paramRol){
        setNombre(paramNombre.getText());
        setRol(paramRol.getText());
        
        Conexion objetoConexion = new Conexion();
        
        String consulta = "insert into empleado(nombreEmpleado,rol) values (?,?);";
        
        
        try{
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
            
            cs.setString(1,getNombre());
            cs.setString(2,getRol());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Datos Registrados Correctamente!");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudo registar datos" + e.toString());
        }       
    }
    
    public void MostrarEmpleados(JTable paramTablaTotalEmpleado){
        
        Conexion objetoConexion = new Conexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        
        paramTablaTotalEmpleado.setRowSorter(OrdenarTabla);
        
        String sql = "";
        
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        modelo.addColumn("Rol");
        
        paramTablaTotalEmpleado.setModel(modelo);
        
        sql = "select * from empleado";
        
        String[] datos = new String [3];
        
        Statement st;
        
        try{
            st = objetoConexion.establecerConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                modelo.addRow(datos);
            }
            
            paramTablaTotalEmpleado.setModel(modelo);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "No se pudeo mostrar los registros" + toString());
        }
        
    }
}
