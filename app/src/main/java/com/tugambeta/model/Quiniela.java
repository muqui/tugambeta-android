package com.tugambeta.model;
// Generated 5/11/2016 06:03:56 PM by Hibernate Tools 4.3.1


import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


/**
 * Quiniela generated by hbm2java
 */

public class Quiniela implements java.io.Serializable {

    private Integer idquiniela;
    private String paginaTipo;
    private String nombre;
    private Timestamp fechaLimite;
    private List<Partidos> partidoses = new LinkedList<Partidos>();
    private List<Jugador> jugadors = new LinkedList<Jugador>();
    private List<Jugadorresultados> jugadorresultadoses = new LinkedList<Jugadorresultados>();

    private String fechaTemporal;

    public Quiniela() {
    }

    public Quiniela(List<Partidos> partidos) {
        this.partidoses = partidos;

    }

    public Quiniela( String nombre) {
        
        this.nombre = nombre;
    }

    public Quiniela( String nombre, Timestamp fechaLimite, List<Partidos> partidoses, List<Jugador> jugadors, List<Jugadorresultados> jugadorresultadoses, String paginaTipo) {
        this.paginaTipo = paginaTipo;
        this.nombre = nombre;
        this.fechaLimite = fechaLimite;
        this.partidoses = partidoses;
        this.jugadors = jugadors;
        this.jugadorresultadoses = jugadorresultadoses;
    }

    public Integer getIdquiniela() {
        return this.idquiniela;
    }

    public void setIdquiniela(Integer idquiniela) {
        this.idquiniela = idquiniela;
    }

   

 

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public Timestamp getFechaLimite() {
        return this.fechaLimite;
    }

    public void setFechaLimite(Timestamp fechaLimite) {
        this.fechaLimite = fechaLimite;
    }


    public List<Partidos> getPartidoses() {
        return this.partidoses;
    }

    public void setPartidoses(List<Partidos> partidoses) {
        this.partidoses = partidoses;
    }


    public List<Jugador> getJugadors() {
        return this.jugadors;
    }

    public void setJugadors(List<Jugador> jugadors) {
        this.jugadors = jugadors;
    }

    public List<Jugadorresultados> getJugadorresultadoses() {
        return this.jugadorresultadoses;
    }

    public void setJugadorresultadoses(List<Jugadorresultados> jugadorresultadoses) {
        this.jugadorresultadoses = jugadorresultadoses;
    }


    public String getFechaTemporal() {
        return fechaTemporal;
    }

    /**
     * @param fechaTemporal the fechaTemporal to set
     */
    public void setFechaTemporal(String fechaTemporal) {
        this.fechaTemporal = fechaTemporal;
    }


    public String getPaginaTipo() {
        return paginaTipo;
    }

    /**
     * @param paginaTipo the paginaTipo to set
     */
    public void setPaginaTipo(String paginaTipo) {
        this.paginaTipo = paginaTipo;
    }

}
