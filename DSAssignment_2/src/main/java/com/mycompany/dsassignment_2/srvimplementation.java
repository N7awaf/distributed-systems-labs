/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dsassignment_2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class srvimplementation extends UnicastRemoteObject implements srvinterface
{

    public srvimplementation() throws RemoteException
    {
        super();
    }

    public String getGrade(double mark) throws RemoteException
    {
        if (mark < 60)
            return "F";

        else if (mark < 65)
            return "D";

        else if (mark < 70)
            return "D+";

        else if (mark < 75)
            return "C";

        else if (mark < 80)
            return "C+";

        else if (mark < 85)
            return "B";

        else if (mark < 90)
            return "B+";

        else if (mark < 95)
            return "A";

        else
            return "A+";
    }
}