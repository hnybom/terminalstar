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

## Database
The database is located in the user's home directory 
and is called `~/.terminalstar/terminalstar.db`. It is a simple sqlite database with a table called `stars` 
with the following columns: `id`, `shorthand`, `description`, `path`.

## License
MIT
```
Copyright (c) 2024 Henri Nybom

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```