# Project Simulation

Modular simulator of routing networks.

## Overview

The program allows you to build network topology, set up connection between devices and turn up routing algorithms. Thanks to modular architecture Project Simulation is open to be expanded for many routing algorithms.

#### Building topology

[1.gif](https://raw.githubusercontent.com/thekristopl/Project-Simulation/master/gitresources/1.gif)

To add devices click on theirs icon an then click on blank space. To link devices left-click on them and choose interface. To break up connection left click on device and choose interface.

#### Setting address

[2.gif](https://raw.githubusercontent.com/thekristopl/Project-Simulation/master/gitresources/2.gif)

To configure device right click on it and show console. You can define name of device, interface address and mask or set up routing. Whole syntax is availible [here](https://github.com/thekristopl/Project-Simulation/raw/master/gitresources/syntax.pdf).

#### Check connections

[3.gif](https://raw.githubusercontent.com/thekristopl/Project-Simulation/master/gitresources/3.gif)

By typing `ping X.X.X.X` you can easily check if connections has been established and proof if routing is working. Currently there is staic routing supported only (but you can write more!).

## How to launch app

Firstly, you need to install Java Runtime Environment (version 15). Then run [JAR file](https://github.com/thekristopl/Project-Simulation/raw/master/gitresources/Project-Simulation.jar).

#### On Linux

Open terminal in folder witch `.jar` file and type:
`java -jar Project-Simulation.jar`

## Usage & License

Project Simulation is free to use and fork under [BEER-WARE LICENSE](LICENSE).
