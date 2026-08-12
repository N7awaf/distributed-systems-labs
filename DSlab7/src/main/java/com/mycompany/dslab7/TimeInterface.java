/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dslab7;

/**
 *
 * @author nawaf
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TimeInterface extends Remote {

    long getTime() throws RemoteException;

    void putString(String message) throws RemoteException;

    String getTimeFormatted(long time) throws RemoteException;
}
