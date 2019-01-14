/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tugambeta.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author mq12
 */
public class Estadisticas implements Serializable {


   
   

    private String local;
    private String visita;
    private List<EstadisticaPartido> localvsvisita = new LinkedList<EstadisticaPartido>();
     private int localvsvisitalocal;
    private int localvsvisitavisita;
    private int localvsvisitaempate;
    private List<EstadisticaPartido> localvsvisital = new LinkedList<EstadisticaPartido>();
    private int localvsvisitallocal;
    private int localvsvisitalvisita;
    private int localvsvisitalempate;
    private List<EstadisticaPartido> localvstodos = new LinkedList<EstadisticaPartido>();
     private int localvstodoslocal;
    private int localvstodosvisita;
    private int localvstodosempate;
    private List<EstadisticaPartido> localvstodosl = new LinkedList<EstadisticaPartido>();
    private int localvstodosllocal;
    private int localvstodoslvisita;
    private int localvstodoslempate;
     private List<EstadisticaPartido> visitavstodos = new LinkedList<EstadisticaPartido>();
      private int visitavstodoslocal;
    private int visitavstodosvisita;
    private int visitavstodosempate;
     private List<EstadisticaPartido> visitavstodosv = new LinkedList<EstadisticaPartido>();
      private int visitavstodosvlocal;
    private int visitavstodosvvisita;
    private int visitavstodosvempate;
    /**
     * @return the localvsvisitalocal
     */
    public int getLocalvsvisitalocal() {
        return localvsvisitalocal;
    }

    /**
     * @param localvsvisitalocal the localvsvisitalocal to set
     */
    public void setLocalvsvisitalocal(int localvsvisitalocal) {
        this.localvsvisitalocal = localvsvisitalocal;
    }

    /**
     * @return the localvsvisitavisita
     */
    public int getLocalvsvisitavisita() {
        return localvsvisitavisita;
    }

    /**
     * @param localvsvisitavisita the localvsvisitavisita to set
     */
    public void setLocalvsvisitavisita(int localvsvisitavisita) {
        this.localvsvisitavisita = localvsvisitavisita;
    }

    /**
     * @return the localvsvisitaempate
     */
    public int getLocalvsvisitaempate() {
        return localvsvisitaempate;
    }

    /**
     * @param localvsvisitaempate the localvsvisitaempate to set
     */
    public void setLocalvsvisitaempate(int localvsvisitaempate) {
        this.localvsvisitaempate = localvsvisitaempate;
    }

    /**
     * @return the local
     */
    public String getLocal() {
        return local;
    }

    /**
     * @param local the local to set
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * @return the visita
     */
    public String getVisita() {
        return visita;
    }

    /**
     * @param visita the visita to set
     */
    public void setVisita(String visita) {
        this.visita = visita;
    }

    /**
     * @return the localvsvisita
     */
    public List<EstadisticaPartido> getLocalvsvisita() {
        return localvsvisita;
    }

    /**
     * @param localvsvisita the localvsvisita to set
     */
    public void setLocalvsvisita(List<EstadisticaPartido> localvsvisita) {
        this.localvsvisita = localvsvisita;
    }
     /**
     * @return the localvsvisital
     */
    public List<EstadisticaPartido> getLocalvsvisital() {
        return localvsvisital;
    }

    /**
     * @param localvsvisital the localvsvisital to set
     */
    public void setLocalvsvisital(List<EstadisticaPartido> localvsvisital) {
        this.localvsvisital = localvsvisital;
    }
     /**
     * @return the localvstodos
     */
    public List<EstadisticaPartido> getLocalvstodos() {
        return localvstodos;
    }

    /**
     * @param localvstodos the localvstodos to set
     */
    public void setLocalvstodos(List<EstadisticaPartido> localvstodos) {
        this.localvstodos = localvstodos;
    }

    /**
     * @return the localvstodosl
     */
    public List<EstadisticaPartido> getLocalvstodosl() {
        return localvstodosl;
    }

    /**
     * @param localvstodosl the localvstodosl to set
     */
    public void setLocalvstodosl(List<EstadisticaPartido> localvstodosl) {
        this.localvstodosl = localvstodosl;
    }
 /**
     * @return the localvsvisitallocal
     */
    public int getLocalvsvisitallocal() {
        return localvsvisitallocal;
    }

    /**
     * @param localvsvisitallocal the localvsvisitallocal to set
     */
    public void setLocalvsvisitallocal(int localvsvisitallocal) {
        this.localvsvisitallocal = localvsvisitallocal;
    }

    /**
     * @return the localvsvisitalvisita
     */
    public int getLocalvsvisitalvisita() {
        return localvsvisitalvisita;
    }

    /**
     * @param localvsvisitalvisita the localvsvisitalvisita to set
     */
    public void setLocalvsvisitalvisita(int localvsvisitalvisita) {
        this.localvsvisitalvisita = localvsvisitalvisita;
    }

    /**
     * @return the localvsvisitalempate
     */
    public int getLocalvsvisitalempate() {
        return localvsvisitalempate;
    }

    /**
     * @param localvsvisitalempate the localvsvisitalempate to set
     */
    public void setLocalvsvisitalempate(int localvsvisitalempate) {
        this.localvsvisitalempate = localvsvisitalempate;
    }

    /**
     * @return the localvstodoslocal
     */
    public int getLocalvstodoslocal() {
        return localvstodoslocal;
    }

    /**
     * @param localvstodoslocal the localvstodoslocal to set
     */
    public void setLocalvstodoslocal(int localvstodoslocal) {
        this.localvstodoslocal = localvstodoslocal;
    }

    /**
     * @return the localvstodosvisita
     */
    public int getLocalvstodosvisita() {
        return localvstodosvisita;
    }

    /**
     * @param localvstodosvisita the localvstodosvisita to set
     */
    public void setLocalvstodosvisita(int localvstodosvisita) {
        this.localvstodosvisita = localvstodosvisita;
    }

    /**
     * @return the localvstodosempate
     */
    public int getLocalvstodosempate() {
        return localvstodosempate;
    }

    /**
     * @param localvstodosempate the localvstodosempate to set
     */
    public void setLocalvstodosempate(int localvstodosempate) {
        this.localvstodosempate = localvstodosempate;
    }

    /**
     * @return the localvstodosllocal
     */
    public int getLocalvstodosllocal() {
        return localvstodosllocal;
    }

    /**
     * @param localvstodosllocal the localvstodosllocal to set
     */
    public void setLocalvstodosllocal(int localvstodosllocal) {
        this.localvstodosllocal = localvstodosllocal;
    }

    /**
     * @return the localvstodoslvisita
     */
    public int getLocalvstodoslvisita() {
        return localvstodoslvisita;
    }

    /**
     * @param localvstodoslvisita the localvstodoslvisita to set
     */
    public void setLocalvstodoslvisita(int localvstodoslvisita) {
        this.localvstodoslvisita = localvstodoslvisita;
    }

    /**
     * @return the localvstodoslempate
     */
    public int getLocalvstodoslempate() {
        return localvstodoslempate;
    }

    /**
     * @param localvstodoslempate the localvstodoslempate to set
     */
    public void setLocalvstodoslempate(int localvstodoslempate) {
        this.localvstodoslempate = localvstodoslempate;
    }

        /**
     * @return the visitavstodos
     */
    public List<EstadisticaPartido> getVisitavstodos() {
        return visitavstodos;
    }

    /**
     * @param visitavstodos the visitavstodos to set
     */
    public void setVisitavstodos(List<EstadisticaPartido> visitavstodos) {
        this.visitavstodos = visitavstodos;
    }

    /**
     * @return the visitavstodoslocal
     */
    public int getVisitavstodoslocal() {
        return visitavstodoslocal;
    }

    /**
     * @param visitavstodoslocal the visitavstodoslocal to set
     */
    public void setVisitavstodoslocal(int visitavstodoslocal) {
        this.visitavstodoslocal = visitavstodoslocal;
    }

    /**
     * @return the visitavstodosvisita
     */
    public int getVisitavstodosvisita() {
        return visitavstodosvisita;
    }

    /**
     * @param visitavstodosvisita the visitavstodosvisita to set
     */
    public void setVisitavstodosvisita(int visitavstodosvisita) {
        this.visitavstodosvisita = visitavstodosvisita;
    }

    /**
     * @return the visitavstodosempate
     */
    public int getVisitavstodosempate() {
        return visitavstodosempate;
    }

    /**
     * @param visitavstodosempate the visitavstodosempate to set
     */
    public void setVisitavstodosempate(int visitavstodosempate) {
        this.visitavstodosempate = visitavstodosempate;
    }

    /**
     * @return the visitavstodosv
     */
    public List<EstadisticaPartido> getVisitavstodosv() {
        return visitavstodosv;
    }

    /**
     * @param visitavstodosv the visitavstodosv to set
     */
    public void setVisitavstodosv(List<EstadisticaPartido> visitavstodosv) {
        this.visitavstodosv = visitavstodosv;
    }

    /**
     * @return the visitavstodosvlocal
     */
    public int getVisitavstodosvlocal() {
        return visitavstodosvlocal;
    }

    /**
     * @param visitavstodosvlocal the visitavstodosvlocal to set
     */
    public void setVisitavstodosvlocal(int visitavstodosvlocal) {
        this.visitavstodosvlocal = visitavstodosvlocal;
    }

    /**
     * @return the visitavstodosvvisita
     */
    public int getVisitavstodosvvisita() {
        return visitavstodosvvisita;
    }

    /**
     * @param visitavstodosvvisita the visitavstodosvvisita to set
     */
    public void setVisitavstodosvvisita(int visitavstodosvvisita) {
        this.visitavstodosvvisita = visitavstodosvvisita;
    }

    /**
     * @return the visitavstodosvempate
     */
    public int getVisitavstodosvempate() {
        return visitavstodosvempate;
    }

    /**
     * @param visitavstodosvempate the visitavstodosvempate to set
     */
    public void setVisitavstodosvempate(int visitavstodosvempate) {
        this.visitavstodosvempate = visitavstodosvempate;
    }

   

}
