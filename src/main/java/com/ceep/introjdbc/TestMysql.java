/*
 * Programa de testeo conexión base de datos MySQL
 */
package com.ceep.introjdbc;

import datos.PersonaDao;
import datos.UsuarioDao;
import dominio.Persona;
import dominio.usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Juan Labandeira
 */
public class TestMysql {
    
    public static void main(String[] args) {
        
        //Agregando personas
        PersonaDao personaDao = new PersonaDao();
        Persona persona1 = new Persona("Roman","López","rlopez@gmail.com","668665664");
        Persona persona2=new Persona(1,"Juan","Labandeira Ros", "juanfernan@gmail.com",
        "666666666");
        
        
        //Agregando usuarios
        usuario user1 = new usuario("Jonon","1234");
        
        
        
        
        
        //personaDao.insertar(persona1);
        personaDao.actualizar(persona2);
        try {
            List<Persona> personas = personaDao.seleccionar();
            personas.forEach(persona -> {
                    System.out.println("persona = " + persona);
            }
            );
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        
        
    }
    
}
