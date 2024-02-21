# Terminal star
Terminal star is a cli tool to save important paths to a local sqlite database.

## Installation
Just compile with graalvm and you are good to go.
`./gradlew nativeCompile`

## Usage
```
Usage: star <COMMAND> -->
    ls                              List all saved paths
    rm                              Remove current path from the database
    rm <shorthand>                  Remove the path with the given shorthand
    <shorthand>                     Print the path with the given shorthand
    add                             Add a new path to the database
    add <shorthand> <description>   Add a new path to the database with a shorthand and a description
    ```
