/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Juan Labandeira
 */
public class usuario implements Serializable {
    
    private int idusuario;
    private String nombreUsuario;
    private String password;
    
    //Constructores

    public usuario(int idusuario, String nombreUsuario, String password) {
        this.idusuario = idusuario;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public usuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public usuario(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public usuario() {
    }
    
    
    
    
    //getter and setter

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getpassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.idusuario;
        hash = 37 * hash + Objects.hashCode(this.nombreUsuario);
        hash = 37 * hash + Objects.hashCode(this.password);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final usuario other = (usuario) obj;
        if (this.idusuario != other.idusuario) {
            return false;
        }
        if (!Objects.equals(this.nombreUsuario, other.nombreUsuario)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("usuario{idusuario=").append(idusuario);
        sb.append(", nombreUsuario=").append(nombreUsuario);
        sb.append(", password=").append(password);
        sb.append('}');
        return sb.toString();
    }
    
    
}
