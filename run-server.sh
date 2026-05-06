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

echo "Running server with Java version:"
java -version

# Build the classpath with all Yoko jars
YOKO_JARS="libs/yoko-core.jar:libs/yoko-spec-corba.jar:libs/yoko-rmi-impl.jar:libs/yoko-rmi-spec.jar:libs/yoko-util.jar:libs/yoko-osgi.jar"
CLASSPATH="build/classes/java/main:$YOKO_JARS"

echo ""
echo "Starting RMI-IIOP Server with Yoko..."
echo ""

java -cp "$CLASSPATH" com.example.rmi.Server
