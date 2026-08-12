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
import java.rmi.server.*;

public class SumImpl extends UnicastRemoteObject implements SumInterface {

    public SumImpl() throws RemoteException {
//No action needed here.
    }
//override sum defined in SumInterface

    public int sum(int a, int b) throws RemoteException {
        return a + b;
    }
}
