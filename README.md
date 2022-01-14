# HighestTree
HighestTree ADS project made with special emphasis on software design patterns.

## Creating a jar executable
Some dependencies were added, so those dependencies need to be added to the jar.


That can be achieved with:
```bash
mvn clean compile assembly:single
```

After this command the jar executable is available at **target/**.

## Running javafx

Can be achieved with:
``` bash
mvn clean javafx:run
```
