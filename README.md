# ads
ADS project using patterns

## Creating a jar executable
Some dependencies were added, so those dependencies need to be added to the jar.


That can be achieved with:
```bash
mvn clean compile assembly:single
```

After this command the jar executable is available at **target/**.