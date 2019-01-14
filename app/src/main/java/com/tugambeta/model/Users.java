package com.tugambeta.model;
// Generated 5/11/2016 06:03:56 PM by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;


/**
 * Users generated by hbm2java
 */
public class Users implements java.io.Serializable {

    private String username;

    private String password;

    private String email;

    private boolean enabled;
    private Set<UserRoles> userRoleses = new HashSet<UserRoles>(0);
    private Set<Pagina> paginas = new HashSet<Pagina>(0);
    private Set<Jugador> jugadors = new HashSet<Jugador>(0);

    private String passwordConfirm;
    private String pagina;

    public Users() {
    }

    public Users(String username, boolean enabled) {
        this.username = username;
        this.enabled = enabled;
    }

    public Users(String username, String password, boolean enabled, Set<UserRoles> userRoleses, Set<Pagina> paginas, Set<Jugador> jugadors) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.userRoleses = userRoleses;
        this.paginas = paginas;
        this.jugadors = jugadors;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public Set<UserRoles> getUserRoleses() {
        return this.userRoleses;
    }

    public void setUserRoleses(Set<UserRoles> userRoleses) {
        this.userRoleses = userRoleses;
    }


    public Set<Pagina> getPaginas() {
        return this.paginas;
    }

    public void setPaginas(Set<Pagina> paginas) {
        this.paginas = paginas;
    }


    public Set<Jugador> getJugadors() {
        return this.jugadors;
    }

    public void setJugadors(Set<Jugador> jugadors) {
        this.jugadors = jugadors;
    }


    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * @param passwordConfirm the passwordConfirm to set
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }


    public String getPagina() {
        return pagina;
    }

    /**
     * @param pagina the pagina to set
     */
    public void setPagina(String pagina) {
        this.pagina = pagina;
    }
}
