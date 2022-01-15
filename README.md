<h1 align="center">
Highest Tree
</h1>

<p align="center">HighestTree ADS project made with special emphasis on software design patterns.</p>

<hr>

## Contributors

| [<img src="https://avatars.githubusercontent.com/u/39097691?v=4" width="75px;"/>](https://github.com/guergeiro) | [[<img src="https://avatars3.githubusercontent.com/u/39312512?s=400&v=4" width="75px;"/>](https://github.com/diogofalken) | [<img src="https://avatars.githubusercontent.com/u/44342122?v=4" width="75px;"/>](https://github.com/hugofpaiva) | [<img src="https://avatars.githubusercontent.com/u/60890452?v=4" width="75px;"/>](https://github.com/Orlando-pt/) |
| :----------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------------------------------------------------: | 
|[Breno Salles](https://github.com/guergeiro) | [Diogo M. Costa](https://github.com/diogofalken)| [Hugo Almeida](https://github.com/hugofpaiva) | [Orlando Macedo](https://github.com/Orlando-pt/)

## Report

A [report](./docs/report.md) with the project architecture, functionalities, design patterns used some problems surpassed while developing the system. Report is here.

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