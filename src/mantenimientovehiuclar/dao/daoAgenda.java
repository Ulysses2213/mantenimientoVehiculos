/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientovehiuclar.dao;
import mantenimientovehiuclar.conexion.bdConnection;
import mantenimientovehiuclar.beans.beansAgenda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author gem2u
 */
public class daoAgenda extends bdConnection{
    public int buscarUsuario(beansAgenda bsAgenda){
        int codigo = 0;
        try{
            getConnection();
            if(connection != null){
                String query = "select * from mecanico where nombreUsuario=? and contrasena =?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, bsAgenda.getNombreUsuario());
                ps.setString(2, bsAgenda.getContrasena());
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    codigo = 1;
                }else{
                    codigo = -2;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return codigo;
    }
    
    public DefaultTableModel mostrarTablaAgenda(String nombreUsuario){
        String [] titulos = {"Num. Inventario", "Descripción", "Fecha Entrada", "Fecha Salida", "Procedimiento"};
        String [] registro = new String[5];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        try{
            getConnection();
            if(connection != null){
                String query = "select * from vehiculo where nombreUsuario=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, nombreUsuario);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    registro[0] = rs.getString("numInventario");
                    registro[1] = rs.getString("descripcion");
                    registro[2] = rs.getString("fechaEntrada");
                    registro[3] = rs.getString("fechaSalida");
                    registro[4] = rs.getString("procedimiento");
                    modelo.addRow(registro);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return modelo;
    }
    
    public int registrarVehiculo(beansAgenda bsAgenda){
        int codigo = 0;
        try{
            getConnection();
            if(connection != null){
                String query = "select * from vehiculo where numInventario=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, bsAgenda.getNumInventario());
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    codigo = -1;
                }else{
                    query = "insert into vehiculo(nombreUsuario, numInventario, descripcion, fechaEntrada, fechaSalida, procedimiento)"
                            + "values(?,?,?,?,?,?)";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, bsAgenda.getNombreUsuario());
                    ps.setString(2, bsAgenda.getNumInventario());
                    ps.setString(3, bsAgenda.getDescripcion());
                    ps.setString(4, bsAgenda.getFechaEntrada());
                    ps.setString(5, bsAgenda.getFechaSalida());
                    ps.setString(6, bsAgenda.getProcedimiento());
                    int res = ps.executeUpdate();
                    if(res >= 1){
                        codigo = 1;
                    }else{
                        codigo = -2;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return codigo;
    }
    
    public int modificarVehiculo(beansAgenda bsAgenda){
        int codigo = 0;
        try{
            getConnection();
            if(connection != null){
                String query = "select * from vehiculo where nombreUsuario=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, bsAgenda.getNombreUsuario());
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    query = "update vehiculo set numInventario=?, descripcion=?, fechaEntrada=?, fechaSalida=?, procedimiento=? where numInventario=?";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, bsAgenda.getNumInventario());
                    ps.setString(2, bsAgenda.getDescripcion());
                    ps.setString(3, bsAgenda.getFechaEntrada());
                    ps.setString(4, bsAgenda.getFechaSalida());
                    ps.setString(5, bsAgenda.getProcedimiento());
                    ps.setString(6, bsAgenda.getNumInventario());
                    int res = ps.executeUpdate();
                    if(res >= 1){
                        codigo = 1;
                    }else{
                        codigo = -2;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return codigo;
    }
    
    public int eliminarVehiculo(beansAgenda bsAgenda){
        int codigo = 0;
        try{
            getConnection();
            if(connection != null){
                String query = "select * from vehiculo where nombreUsuario =?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, bsAgenda.getNombreUsuario());
                ResultSet rs = ps.executeQuery();
                if(rs.next()){
                    query = "delete from vehiculo where numInventario=?";
                    ps = connection.prepareStatement(query);
                    ps.setString(1, bsAgenda.getNumInventario());
                    int res = ps.executeUpdate();
                    if(res >= 1){
                        codigo = 1;
                    }else{
                        codigo = -2;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return codigo;
    }
    
    public DefaultTableModel mostrarTablaVehiculo(String numInv){
        String [] titulos = {"Num. Inventario", "Descripción", "Fecha Entrada", "Fecha Salida", "Procedimiento"};
        String [] registro = new String[5];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
         try{
            getConnection();
            if(connection != null){
                String query = "select * from vehiculo where numInventario=?";
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, numInv);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    registro[0] = rs.getString("numInventario");
                    registro[1] = rs.getString("descripcion");
                    registro[2] = rs.getString("fechaEntrada");
                    registro[3] = rs.getString("fechaSalida");
                    registro[4] = rs.getString("procedimiento");
                    modelo.addRow(registro);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return modelo;
    }
}
