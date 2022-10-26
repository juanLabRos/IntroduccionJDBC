/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import dominio.usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan Labandeira
 */
public class UsuarioDao {

    private static final String SQL_SELECT = "SELECT * FROM usuario";
    private static final String SQL_INSERT = "INSERT INTO usuario (nombreUsuario, "
            + "password ) VALUES (?, ?)";
    private static final String SQL_UPDATE = ("UPDATE usuario SET"
            + "nombreUsuario = ?, apellido = ?"
            + "WHERE idusuario = ?");
    private static final String SQL_DELETE = ("idUsuario = ?");
    
     // Método que nos lista todas las personas de nuestro sistema
    public List<usuario> select() throws SQLException {
        
        // Inicializo mis variables
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        usuario usuario = null;
        List<usuario> usuarios = new ArrayList<>();
        
        conn = getConnection();
        stmt = conn.prepareStatement(SQL_SELECT);
        rs = stmt.executeQuery();
        
        while (rs.next()){
            int idusuario = rs.getInt("idusuario");
            String nombreUsuario = rs.getString("nombreUsuario");
            String password = rs.getString("password");
            
            // Instacio un nuevo objeto
            usuarios.add(new usuario(idusuario,nombreUsuario,password));
        }

        close(rs);
        close(stmt);
        close(conn);
        
        return usuarios;
    }
    
    // Método que inserta una persona en mi sistema
    public int insert(usuario usuario){
        
        // Declaro e inicializo mis variables
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            // 1. Establecemos la conecxión
            conn = getConnection();
            
            // 2. Preparo mi instrucción para ejecutarla contra la base de datos
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, usuario.getIdusuario());
            stmt.setString(2, usuario.getNombreUsuario());
                                            
            
            // 3. Ejecuto la query
            registros = stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return registros;
    }
    public int update(usuario usuario){
        
        //Inicializando variables
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getNombreUsuario());
            stmt.setString(2, usuario.getpassword());
            stmt.setInt(5, usuario.getIdusuario());
            
            registros=stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    public int borrar(usuario usuario){
        
        //Inicializando variables
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getIdusuario());
            
            registros=stmt.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }finally{
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
   
}
