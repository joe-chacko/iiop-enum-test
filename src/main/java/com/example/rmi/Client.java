/*******************************************************************************
 * Copyright (c) 2026 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package com.example.rmi;

import javax.rmi.PortableRemoteObject;
import org.omg.CORBA.ORB;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Client that connects to the DayService using RMI-IIOP.
 * This should be run with Java 8 (Zulu 8.0.492).
 * Reads the IOR from server.ior file.
 */
public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Starting RMI-IIOP Client...");
            
            // Initialize the ORB
            ORB orb = ORB.init(args, null);
            
            // Read the IOR from file
            String ior;
            try (BufferedReader reader = new BufferedReader(new FileReader("server.ior"))) {
                ior = reader.readLine();
            }
            
            System.out.println("Read IOR from server.ior");
            
            // Convert IOR string to object reference
            org.omg.CORBA.Object objRef = orb.string_to_object(ior);
            
            // Narrow to the DayService interface
            DayService dayService = (DayService) PortableRemoteObject.narrow(objRef, DayService.class);
            
            System.out.println("Connected to DayService");
            
            // Call the remote method
            WeekDay day = dayService.getDayOfWeek();
            
            System.out.println("\n========================================");
            System.out.println("SUCCESS!");
            System.out.println("Current day of week: " + day);
            System.out.println("========================================\n");
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
