# bricks
A basic Bricks clone to demonstrate libgdx game development.

This project includes the code for all platform targets, but only the desktop target has been tested.

## Building
Open the project in Eclipse, right-click the "bricks-desktop" project, and click "Export...". Under "Java", select "Runnable JAR file"
and click next. Select the destination of the jar, make sure "Extract required libraries into generated JAR" is ticked, and click finish.

## Running
You should be able to double-click the generated jar file and run the game, or via the command-line with `java -jar ./bricks.jar`.

## How to Play
By clicking on the flipper, you start the game. The ball will begin moving and bouncing off the walls and bricks laid out at the top of
the screen. Move the flipper by clicking it and dragging it from side to side. The goal is to prevent the ball from hitting the bottom
of the screen. If you break every brick, you win the game and are subsequently offered to play again. Click on the "Play Again?" prompt
to start the game over.

## Comments
This project doesn't demonstrate a lot of best practices or the best architecture. The point was to get something up and running quickly
and roughly with libgdx to familiarize myself with the technology and workflow. I considered coming back to this project later to
refactor it with best practices in mind - and I might still - but as of right now, I believe it's better to move on to new projects.
