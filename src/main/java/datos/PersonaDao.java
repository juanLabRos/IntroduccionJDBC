/*
 * Conjunto de operaciones que yo voy a poder realizar sobre una Persona
 */
package datos;

import dominio.Persona;
import static datos.Conexion.close;
import static datos.Conexion.getConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Labandeira
 */
public class PersonaDao {
    
    private static final String SQL_SELECT = "SELECT * FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona (nombre, "
            + "apellidos, email, telefono) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = ("UPDATE persona SET"
            + "nombre = ?, apellido = ?,email = ?,telefono = ?"
            + "WHERE idPersona = ?");
    private static final String SQL_DELETE = ("idPersona = ?");
    
    
    // Método que nos lista todas las personas de nuestro sistema
    public List<Persona> seleccionar() throws SQLException {
        
        // Inicializo mis variables
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();
        
        conn = getConnection();
        stmt = conn.prepareStatement(SQL_SELECT);
        rs = stmt.executeQuery();
        
        while (rs.next()){
            int idPersona = rs.getInt("idPersona");
            String nombre = rs.getString("nombre");
            String apellidos = rs.getString("apellidos");
            String telefono = rs.getString("telefono");
            String email = rs.getString("email");
            
            // Instacio un nuevo objeto
            personas.add(new Persona(idPersona, nombre, apellidos, email, telefono));
        }

        close(rs);
        close(stmt);
        close(conn);
        
        return personas;
    }
    
    // Método que inserta una persona en mi sistema
    public int insertar(Persona persona){
        
        // Declaro e inicializo mis variables
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            // 1. Establecemos la conecxión
            conn = getConnection();
            
            // 2. Preparo mi instrucción para ejecutarla contra la base de datos
            stmt = conn.prepareStatement(SQL_INSERT);
            // Asignar los valores a los ? de la consulta
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellidos());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());                                 
            
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
    
    public int actualizar(Persona persona){
        
        //Inicializando variables
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellidos());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona());
            
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
    public int borrar(Persona persona){
        
        //Inicializando variables
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getIdPersona());
            
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
