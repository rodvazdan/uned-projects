# Microblogging System

A basic Twitter-like microblogging system implemented using Java RMI.

## Overview

This project implements a distributed microblogging system, part of "Sistemas Distribuidos" in UNED for the year 2022-2023.

There are three main components:

1. **Servidor**: Manages user authentication and message handling.
2. **Base de datos**: Stores all system data.
3. **Cliente**: End-user interfaces for interacting with the system.

The system components must be started in a specific order using command-line scripts:

1. `basededatos`: Starts the Database program and its service.
2. `servidor`: Starts the Server program and its services.
3. `cliente`: Starts a user client program and its service.

To facilitate this, batch files (`.bat` for Windows and `.sh` for Unix-based systems) are provided to launch each application.
