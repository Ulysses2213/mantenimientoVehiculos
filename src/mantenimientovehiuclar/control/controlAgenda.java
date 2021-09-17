/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientovehiuclar.control;
import javax.swing.table.DefaultTableModel;
import mantenimientovehiuclar.beans.beansAgenda;
import mantenimientovehiuclar.dao.daoAgenda;

/**
 *
 * @author gem2u
 */
public class controlAgenda {
    
    beansAgenda bsAgenda = new beansAgenda();
    daoAgenda dAgenda = new daoAgenda();
    String usuario = "";
    
   public int validarCredenciales(String nombreUsuario, String contrasena){
       int codigo = 0;
       if(nombreUsuario == null || nombreUsuario.isEmpty() || contrasena == null || contrasena.isEmpty()){
           codigo = -1;
       }else{
           usuario = nombreUsuario;
           bsAgenda.setNombreUsuario(nombreUsuario);
           bsAgenda.setContrasena(contrasena);
           codigo = dAgenda.buscarUsuario(bsAgenda);
       }
       return codigo;
   }
   
   public DefaultTableModel mostrarTablaAgenda(String nombreUsuario){
       DefaultTableModel modelo = dAgenda.mostrarTablaAgenda(nombreUsuario);
       return modelo;
   }
   
   public int validarRegistro(String nombreUsuario, String numInv, String descripcion, String fechaEntrada, String fechaSalida, 
           String procedimiento, int bandera){
       int codigo = 0;
       if(numInv == null || numInv.isEmpty() || descripcion == null || descripcion.isEmpty()
        || fechaEntrada == "00/00/00 00:00" || fechaSalida == "00/00/00 00:00"
        || procedimiento == null || procedimiento.isEmpty()){
           codigo = -1;
       }else{
            bsAgenda.setNombreUsuario(nombreUsuario);
            bsAgenda.setNumInventario(numInv);
            bsAgenda.setDescripcion(descripcion);
            bsAgenda.setFechaEntrada(fechaEntrada);
            bsAgenda.setFechaSalida(fechaSalida);
            bsAgenda.setProcedimiento(procedimiento);
            if(bandera == 0){
                codigo = dAgenda.registrarVehiculo(bsAgenda);
            }else{
                codigo = dAgenda.modificarVehiculo(bsAgenda);
            }
            
       }
       return codigo;
   }
   
   public int validarNumInv(String numInv, String usuario){
       int codigo = 0;
       if(numInv == null || numInv.isEmpty() || usuario == null || usuario.isEmpty()){
           codigo = -1;
       }else{
           bsAgenda.setNombreUsuario(usuario);
           bsAgenda.setNumInventario(numInv);
           codigo = dAgenda.eliminarVehiculo(bsAgenda);
       }
       return codigo;
   }
   
   public DefaultTableModel mostrarVehiculo(String numInv){
       DefaultTableModel modelo = dAgenda.mostrarTablaVehiculo(numInv);
       return modelo;
   }
   
}
