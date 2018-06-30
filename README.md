# Block Editor (IJA project)
Bachelor study at FIT VUT Brno  
4th semester - summer 2018  
Subject: **Java Programming Language (IJA)**

Similar project: *[C++ Block Editor](https://github.com/Furdys/ICP-proj)*

## Authors
* Jiří Furda *(xfurda00) - Mostly backend*
* Peter Havan *(xhavan00) - Mostly GUI*

## Score
* Overall: **47/94**

### Details
*The [submitted version](https://github.com/Furdys/IJA-proj/tree/6ffc78c035daa53aae7650bfb90fe60652442570) had some missing features, such as loading from and saving to file (which was actually done but not yet accessible through GUI). This happend due to parallel developement with our [ICP project](https://github.com/Furdys/ICP-proj), which had earlier deadline by two days therefore had bigger priority for us.
We were given a second chance to complete missing features and obtain minimum points to pass the subject which we did. We had also reworked port connection to be more user-readable.*

## Description
Block editor allows you to create a block scheme. It supports three unique value types (float, complex number and boolean) which are used by blocks for arithmetic (both real and complex) and by blocks for logic.
Every block has its input and output ports which can be connected to a port of another block. Created scheme can be saved to file and also loaded from file. When your scheme is done, you can execute whole calculation at once or you can use stepping, which caclulates only one block at the time. Any value can be read by hovering over a connection or over a block. Editor also prevents loops and incompatibile connection

## Instalation
For compilation use `ant compile` in root folder.
This creates **ija-client.jar** in folder *dest-client* and also creates Doxygen in folder *doc*.  
If you also want to open this program, use `ant run` instead.

## Screenshots
![Block Editor screenshot](https://imgur.com/rs4zuP9.gif)
### Connecting blocks
![Connecting blocks example](https://imgur.com/vWQvCIm.gif)
### Reading values
![Value reading example](https://i.imgur.com/MXLbucW.gif)
### Moving block
![Moving block example](https://imgur.com/oYNECcU.gif)
