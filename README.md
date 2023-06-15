# Coursera_Algorithms_1

Projects provide boilerplate code and test material.
Use Git Bash rather than powershell.
Use "javac -cp .\;algs4.jar javafilename.java" and "java -cp .\;algs4.jar javafilename".
The "-cp" is the classpath option.
The ".\;algs4.jar" tells java to look in the current directory (.) and the library (algs4.jar). These are seperated by a semicolon for windows machines (colon for linux). The semicolon has special meaning in Git Bash, so it is escaped with a backslash (\).

To-Do:
Percolation:
- Implement error checking (ex: don't allow access out of bounds)
- Connect site to adjacent sites when opening
- Implement isFull() (basically done)
- Implement percolates()