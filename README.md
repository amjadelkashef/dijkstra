dijkstra
========
ABOUT

Given an input file representing the communication cost between every adjacent nodes, this program uses Dijkstra's algorithm to identify the shortest path between two nodes, as well as the overall communication cost between them.



COMPILING THE PROGRAM

The project was developed using Maven 2. In order to compile it, you need to have Maven installed and do this:
- open a terminal
- move to the project's root directory
- enter: mvn package
The jar file will be generated in: .../target



EXECUTING THE PROGRAM

- open a terminal
- move to the target directory of the project
- enter: java -jar dijksta.jar

Optional: before running the program, copy the input file in the same folder as the jar file in order not to have to specify the absolute path to the file when the program is running.



USING THE PROGRAM

- When asked to specify the path to the input file, the user can either
  - Enter only the name of the file if the file is in the same folder as the jar.
	- Enter the absolute path to the file.
- To exit the program, enter: "exit"
- Whenever an instruction is completed, the user is redirected to the menu. He can there enter 1, 2 or 3 to selection an action.
- When an identified error occurs (input file not found for example), the error is logged and the user is redirected to the menu. The program crashes only when it's an non-identified error.
