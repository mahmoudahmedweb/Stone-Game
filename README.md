# Stone Game Project

## Game Description
The Stone Game Project is an implementation of a strategic two-player game,
where players take turns placing stones on a 5*5 board. The players are asked 
to write their names before starting a new game.

- The first player gets a red stone.
- The second player gets a blue stone.

The winning condition can be achieved if a player gets three of their colored
stones in a raw, or column, or diagonal.

## Packages
The project includes several key components organized into different packages
based on MVC architecture.

**stonegame.console**: Contains classes related to the console-based user interface, including input parsing and game initialization.
**stonegame.controller**: Manages the flow of the game, including starting a new game and handling player turns.
**stonegame.model**: Contains the core game logic, data models, and storage mechanisms.

## Game Features
#### Game Initialization: 
- Prompts users to enter their names.
- Initializes the game model with player information.

#### Game Play:
- Allows players to take turns placing stones on the grid.
- Updates the game state after each move.
- Validates input to ensure correct format and valid moves.

#### High Score Tracking:
- Maintains a high-score table with player statistics.
- Loads and saves player statistics to a file.

#### Unit testing:
- Tests the StoneGameModel (The core logic of the application).
- Tests the storage of the game data.
- Tests the mechanism of High Score Tracking.

#### Documentation and Code Quality:
- Uses Javadoc to document public classes and methods.
- Ensures code quality and style compliance using Checkstyle.

## Sequence of Moves
To play the Stone Game:
1. Enter the names of the two players.
2. The first player places a red stone on the board.
3. The second player places a blue stone on the board.
4. Players take turns until one achieves the winning condition or the board is filled.
## Author
- The project was developed by Mahmoud Ahmed Ramadan.
