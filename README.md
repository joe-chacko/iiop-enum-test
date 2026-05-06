# RMI-IIOP Test Project

This project demonstrates a Java 8 client connecting to a Yoko server (running on Semeru 17) using RMI-IIOP with an enum return type.

## Project Structure

- **Remote Interface**: `DayService` - Returns a `WeekDay` enum from `getDayOfWeek()` method
- **Enum**: `WeekDay` - Represents days of the week (MONDAY through SUNDAY)
- **Implementation**: `DayServiceImpl` - Checks the current day and returns the appropriate enum value
- **Server**: Runs on Semeru 17 with Yoko 1.6.1 libraries
- **Client**: Runs on Java 8 (Zulu 8.0.492)

## Prerequisites

- SDKMAN installed with:
  - Java 8 (Zulu 8.0.492) - `sdk install java 8.0.492-zulu`
  - Java 17 (Semeru 17.0.13) - `sdk install java 17.0.13-sem`
- Gradle

## Build

```bash
gradle build
```

## Generate RMI-IIOP Stubs

The stubs are generated using Java 8's rmic tool with the -iiop flag:

```bash
~/.sdkman/candidates/java/8.0.492-zulu/bin/rmic -iiop \
  -classpath build/classes/java/main:libs/* \
  -d build/classes/java/main \
  com.example.rmi.DayServiceImpl
```

This generates:
- `_DayService_Stub.class` - Client-side stub
- `_DayServiceImpl_Tie.class` - Server-side tie

## Running the Test

### Step 1: Start the Server

In one terminal:

```bash
./run-server.sh
```

The server will:
- Start with Semeru 17 and Yoko libraries
- Print the IOR (Interoperable Object Reference)
- Save the IOR to `server.ior` file
- Wait for client connections

### Step 2: Run the Client

In another terminal:

```bash
./run-client.sh
```

The client will:
- Start with Java 8 (Zulu 8.0.492)
- Read the IOR from `server.ior`
- Connect to the server
- Call `getDayOfWeek()` method
- Display the current day of the week

## Expected Output

**Server:**
```
Starting RMI-IIOP Server with Yoko...
========================================
Server is ready!
========================================
IOR:
IOR:000000000000003b49444c3a636f6d2f6578616d706c652f726d692f446179536572766963653a312e300000000000010000000000000068000102000000000a3132372e302e302e31000013880000001c3afabcaf0000000020c91f5500000008000000000000000000000001000000010000001c00000000000100010000000105010001000101090000000105010001
========================================

IOR saved to server.ior
Press Ctrl+C to stop the server
```

**Client:**
```
Starting RMI-IIOP Client...
Read IOR from server.ior
Connected to DayService

========================================
SUCCESS!
Current day of week: TUESDAY
========================================
```

## Key Components

### Yoko Libraries (v1.6.1)

Downloaded from: https://github.com/OpenLiberty/yoko/releases/tag/v1.6.1

- `yoko-core.jar` - Core CORBA functionality
- `yoko-spec-corba.jar` - CORBA specifications
- `yoko-rmi-impl.jar` - RMI-IIOP implementation
- `yoko-rmi-spec.jar` - RMI-IIOP specifications
- `yoko-util.jar` - Utility classes
- `yoko-osgi.jar` - OSGi support

### Java Language Level

The project is compiled with Java 8 compatibility:
- `sourceCompatibility = JavaVersion.VERSION_1_8`
- `targetCompatibility = JavaVersion.VERSION_1_8`

## Troubleshooting

1. **Server won't start**: Ensure Semeru 17 is installed via SDKMAN
2. **Client can't connect**: Make sure `server.ior` file exists and server is running
3. **Stub generation fails**: Verify Java 8 is installed and rmic is available
4. **ClassNotFoundException**: Rebuild the project with `gradle build`

## Testing the Problem

This setup recreates the scenario where:
- A Java 8 client uses RMI-IIOP stubs generated with Java 8's rmic
- The server runs with modern Yoko libraries on Java 17
- An enum is passed as a return value from a remote method

This can be used to test compatibility issues between different Java versions and CORBA implementations.