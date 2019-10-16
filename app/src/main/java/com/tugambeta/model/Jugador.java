package com.tugambeta.model;
// Generated 5/11/2016 06:03:56 PM by Hibernate Tools 4.3.1




import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashSet;
import java.util.Set;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class Jugador  implements java.io.Serializable {


    private Integer idjugar;
    private Quiniela quiniela;
   // private Users users;
    private String jugador;
    private String codigo;
    private Integer aciertos;
    @JsonInclude(JsonInclude.Include.NON_NULL) //ignore null field on this property only
    private Set<Jugadorresultados> jugadorresultadoses = new HashSet<Jugadorresultados>(0);

    public Jugador() {
    }







    public Integer getIdjugar() {
        return this.idjugar;
    }

    public void setIdjugar(Integer idjugar) {
        this.idjugar = idjugar;
    }


    public Quiniela getQuiniela() {
        return this.quiniela;
    }

    public void setQuiniela(Quiniela quiniela) {
        this.quiniela = quiniela;
    }

/*
    public Users getUsers() {
        return this.users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
*/

    public String getJugador() {
        return this.jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }



    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }



    public Integer getAciertos() {
        return this.aciertos;
    }

    public void setAciertos(Integer aciertos) {
        this.aciertos = aciertos;
    }



    public Set<Jugadorresultados> getJugadorresultadoses() {
        return this.jugadorresultadoses;
    }

    public void setJugadorresultadoses(Set<Jugadorresultados> jugadorresultadoses) {
        this.jugadorresultadoses = jugadorresultadoses;
    }




}
