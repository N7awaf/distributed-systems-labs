/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dslab6;

/**
 *
 * @author nawaf
 */
import java.rmi.registry.*;

public class SumServer {

    public static void main(String[] args) throws Exception {
//Create a reference to an implementation object…
        SumImpl temp = new SumImpl();
//Create the string URL holding the remote object's name…
        Registry reg = LocateRegistry.
                createRegistry(1900);
//'Bind' the object reference to the remote object’s name…
        reg.rebind("myapp", temp);
//Display a message so that we know the process has been completed
        System.out.println("Server is running, binding is complete ...\n");
    }
}
//rmiObjectName is the name of the remote object, temp is the reference to
//the remote object
