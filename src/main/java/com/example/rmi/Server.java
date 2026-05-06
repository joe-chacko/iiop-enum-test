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
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Server that publishes the DayService using RMI-IIOP.
 * This should be run with Semeru 17 and Yoko libraries.
 * Prints the IOR for the client to use.
 */
public class Server {
    public static void main(String[] args) {
        try {
            System.out.println("Starting RMI-IIOP Server with Yoko...");
            
            // Initialize the ORB
            ORB orb = ORB.init(args, null);
            
            // Get the root POA
            POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPOA.the_POAManager().activate();
            
            // Create and export the remote object
            DayServiceImpl dayService = new DayServiceImpl();
            
            // Get the object reference and convert to IOR string
            org.omg.CORBA.Object objRef = rootPOA.servant_to_reference(
                (org.omg.PortableServer.Servant) javax.rmi.CORBA.Util.getTie(dayService)
            );
            String ior = orb.object_to_string(objRef);
            
            // Print the IOR
            System.out.println("\n========================================");
            System.out.println("Server is ready!");
            System.out.println("========================================");
            System.out.println("IOR:");
            System.out.println(ior);
            System.out.println("========================================\n");
            
            // Save IOR to file for client
            try (PrintWriter out = new PrintWriter(new FileWriter("server.ior"))) {
                out.println(ior);
            }
            System.out.println("IOR saved to server.ior");
            System.out.println("Press Ctrl+C to stop the server\n");
            
            // Keep the server running
            orb.run();
            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
