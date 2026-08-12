/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dslab6;

/**
 *
 * @author nawaf
 */
import java.rmi.*;

public interface SumInterface extends Remote {

    public int sum(int a, int b) throws RemoteException;
}
