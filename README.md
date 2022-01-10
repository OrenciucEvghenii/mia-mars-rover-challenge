# mia-mars-rover-challenge

## Setup
To install all dependencies, compile and start application call next command from project root folder:
```shell
mvn install && java -cp target/mia-mars-rover-challenge-1.0-SNAPSHOT.jar com.mia.MarsRover
```

## Operating
First input line is used to set plateau size:
```shell
5 5
```

After that you can enter two lines for init rovers. First line to set rover's position and orientation. Second to set navigation sequence. 
Example:
```shell
1 2 N
LMLMLMLMM
```

When you are done initializing your rovers, press Ctrl+D to start rovers navigation through Mars plateau.
As a result you will be able to see final position of each rover.

## Unit Tests
To run unit tests run next command from project root folder
```shell
mvn test
```