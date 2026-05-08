#!/bin/bash
###############################################################################
# Copyright (c) 2026 IBM Corporation and others.
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License 2.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-2.0/
#
# SPDX-License-Identifier: EPL-2.0
###############################################################################

echo "Running client with Java version:"
java -version

# Build the classpath - client needs the stubs and interface classes
find libs/ -maxdepth 1 -name "*.jar" |paste -sd :
exit
YOKO_JARS="$(find lib/ -maxdepth 1 -name "*.jar" |paste -sd :)"
CLASSPATH="build/classes/java/main:$YOKO_JARS"


echo ""
echo "Starting RMI-IIOP Client with Yoko..."
echo "Make sure server is running and server.ior file exists"
echo ""

java -cp "$CLASSPATH" \
    com.example.rmi.Client
