/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dslab7;

/**
 *
 * @author nawaf
 */
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.text.SimpleDateFormat;

public class TimeImp extends UnicastRemoteObject implements TimeInterface {

    private static final long serialVersionUID = 7L;

    public TimeImp() throws RemoteException {
        super();
    }

    public long getTime() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return System.currentTimeMillis();
    }

    public void putString(String message) {
        System.out.println(message);
    }

    public String getTimeFormatted(long time) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        return format.format(date);
    }
}
