/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop812;

import java.sql.Date;



/**
 *
 * @author DELL
 */
public class quanly {
    private int id;
    private String time;
    private String lydo;
    private String mucct;
    private double tien;
    private int tag;
    private String myear;

    public quanly(int id, String time, String lydo, String mucct, double tien, int tag, String myear) {
        this.id = id;
        this.time = time;
        this.lydo = lydo;
        this.mucct = mucct;
        this.tien = tien;
        this.tag = tag;
        this.myear = myear;
    }

    public String getMyear() {
        return myear;
    }

    public void setMyear(String myear) {
        this.myear = myear;
    }

    public quanly() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    public String getMucct() {
        return mucct;
    }

    public void setMucct(String mucct) {
        this.mucct = mucct;
    }

    public double getTien() {
        return tien;
    }

    public void setTien(double tien) {
        this.tien = tien;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }


    
    
    public Object[] toArray()
    {
        return new Object[]{id, time, lydo, mucct, tien, tag};
    }


    
    
}
