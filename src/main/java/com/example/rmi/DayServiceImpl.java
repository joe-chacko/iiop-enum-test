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
import java.rmi.RemoteException;
import java.util.Calendar;

/**
 * Implementation of the DayService remote interface.
 * Returns the current day of the week as a WeekDay enum.
 */
public class DayServiceImpl extends PortableRemoteObject implements DayService {
    
    public DayServiceImpl() throws RemoteException {
        super();
    }
    
    @Override
    public WeekDay getDayOfWeek() throws RemoteException {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        
        // Convert Calendar day to WeekDay enum
        // Calendar.SUNDAY = 1, Calendar.MONDAY = 2, etc.
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                return WeekDay.MONDAY;
            case Calendar.TUESDAY:
                return WeekDay.TUESDAY;
            case Calendar.WEDNESDAY:
                return WeekDay.WEDNESDAY;
            case Calendar.THURSDAY:
                return WeekDay.THURSDAY;
            case Calendar.FRIDAY:
                return WeekDay.FRIDAY;
            case Calendar.SATURDAY:
                return WeekDay.SATURDAY;
            case Calendar.SUNDAY:
                return WeekDay.SUNDAY;
            default:
                throw new RemoteException("Unknown day of week: " + dayOfWeek);
        }
    }
}

