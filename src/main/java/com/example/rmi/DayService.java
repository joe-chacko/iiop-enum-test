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

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for the day service.
 * This interface will be used for RMI-IIOP communication.
 */
public interface DayService extends Remote {
    /**
     * Gets the current day of the week.
     * @return the current WeekDay enum value
     * @throws RemoteException if a remote communication error occurs
     */
    WeekDay getDayOfWeek() throws RemoteException;
}
