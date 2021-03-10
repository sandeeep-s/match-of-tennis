# Tennis Match Scorer

This is an application to help referee manage score of a tennis match.

## Prerequisites

Java 11
Gradle6+

## Build

Open terminal and change working directory to project root

Execute command `gradle clean shadowJar`

Executable `match-of-tennis.jar` file is generated in `bootstrap/build/lib` folder

## Execute
Currently execution is possible only through tests. Plan to introduce command line interface shortly.

## Test
Open terminal and change working directory to project root

Execute command `gradle clean test`

## Architecture

At high level the project consists of `application`, `adapters` and `bootstrap` modules. It uses **`Hexagnal Architecture`** with *Ports and Adapters* housed in `adapters` module while *Application Layer* and *Domain Model* reside in `application` module and `bootstrap` module wires up the dependencies.
```
