/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author arvip
 */
public class Partido {

    protected int id;
    protected int e1;
    protected int e2;
    protected int g1;
    protected int g2;

    public Partido(int e1, int e2, int g1, int g2) {
        super();
        this.id = id;
        this.e1 = e1;
        this.e2 = e2;
        this.g1 = g1;
        this.g2 = g2;
    }
    
     public Partido(int id, int e1, int e2, int g1, int g2) {
        super();
        this.id = id;
        this.e1 = e1;
        this.e2 = e2;
        this.g1 = g1;
        this.g2 = g2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getE1() {
        return e1;
    }

    public void setE1(int e1) {
        this.e1 = e1;
    }

    public int getE2() {
        return e2;
    }

    public void setE2(int e2) {
        this.e2 = e2;
    }

    public int getG1() {
        return g1;
    }

    public void setG1(int g1) {
        this.g1 = g1;
    }

    public int getG2() {
        return g2;
    }

    public void setG2(int g2) {
        this.g2 = g2;
    }
    
}
