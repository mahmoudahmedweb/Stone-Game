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
based on **MVC architecture**.

**stonegame.controller**: Manages the flow of the game, including starting a new game and handling player turns.

**stonegame.model**: Contains the core game logic, data models, and storage mechanisms.

## Game Features
#### Game Initialization: 
- Prompts users to enter their names.
- Initializes the game model with player information.

#### Game Play:
- Players take turns placing stones on the grid.
- The game state is updated after each move.
- Input is validated to ensure correct format and valid moves.

#### High Score Tracking:
- Maintains a high-score table with player statistics.
- Loads and saves player statistics to a file.

#### Unit testing:
JUnit 5 is used to test:
- The StoneGameModel (core logic of the application).
- Game data storage.
- The high score tracking mechanism.

#### Documentation and Code Quality:
- Public classes and methods are documented

## Libraries and Frameworks
The project utilizes the following key libraries and frameworks:

JavaFX: For creating the game UI.
JUnit 5: For unit testing.
Jackson: For JSON processing.
Log4j: For logging.
Javadoc: For documentation.
JaCoCo: For code coverage analysis.
Checkstyle: For ensuring code quality and style compliance.

## Author
- The project was developed by Mahmoud Ahmed Ramadan.
