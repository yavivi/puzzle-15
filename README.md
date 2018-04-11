# 15 Puzzle Game
Implementation of the 15-puzzle game

## Prerequisite
JDK 8 should be installed

## Running the game
The game can be run in 2 modes:
1. Textual UI
2. Graphical UI

First, clone the repo and change to its directory:
```
$ git clone <this-repo>
$ cd puzzle-15
```

### Textual UI

Run the following script to get a 4X4 board in textual UI:
```
$ ./run.sh text 4
```

The project will be built using gradle so it might take a while only at the first time.
The following will be displayed once the build is done. In order to move a tile, just enter its number:

```
  1  2  3  4
  5  6  7  8
  9 10 11 12
 13 14 15 __

Let's starts...
Number of misplaced tiles : 0
Possible commands:
1. <tile-to-move> - Type the tile number you wish to move (e.g. 5)
2. shuffle <n>    - Shuffle the board by running n random moves (defaults to 100 if n not provided)
3. bye            - leave the game
```

### Graphical UI
Run the following script to get a GUI board of 7X7 (or 4X4 if it is run with no parameters):
```
$ ./run.sh gui 7
```
