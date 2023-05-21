# Catan

The project is to implement computer version of Catan board game.
This repository contains backend part of the project.

## Main rules

Further information: https://www.catan.com/sites/default/files/2021-06/catan_base_rules_2020_200707.pdf


- Game is suitable for 3-4 players
- The players take on the role of settlers establishing settlements in the Island of Catan, they build settlements, cities and roads to be connected as they settle the island.
- The game board is composed of hexagonal tiles (hexes), laid out randomly at the beginning of the game.
- Players build spending resources (wool, grain, lumber, brick and ore) decipted using resource cards;  hills produce brick, forests produce lumber, mountains produce ore, fields produce grain, and pastures produce wool.

### Rolling the dice
- Each player's turn starts with the rolling of two six-sized dice, this is used to determined which hexes produce resources.
- Those who have a settlement adjacent to a hex with the number visible in the dice, receive a card of the corresponding resource.
- Those who have a city adjacent to a hex with the number visible in the dice, receive two cards of the corresponding resource.
- A robber token is initially placed in the desert.
    - If any player rolls 7, then the robber is activated, that player must move the token then to another hex, which will no longer produce resources until the robber is moved again to another hex, essentially blocking a resource field.
    - That player may also steal a resource card from any player with a settlement or city adjacent to the robber's new position:
    - If anybody has more than 7 cards, they need to discard half of them (chosen ones).
    e.g. if you have 8 you have to discard 4, however if you have 9 you should discard also 4 cards.

### Trading

- As another possible action that can be taken in-game, players can trade with other players or with the bank
- Anybody then can make an offer to trade (but only with this player)
- As a default trading with bank is 4 similar resources for chosen one
    - e.g. 4 woods for 1 wheat
    - when you have a harbour, then trading with bank can be 3:1 or 2:1

### Building

- After trading, it’s time to build (if there are enough resources)
- From resources you can buy village, city, road or development card
    - ********************village -******************** gives 1 resources when number is on the dice
    - **************city -************** gives 2 resources, when number is on the dice, can be built only on the village (exchange)
    - ************road -************ connects villages/cities, because village can be built only if is connected to another village/city
    - **************************************develompment card -************************************** gives extra points, resources or knight, who can move the thief


## Technology used (expected)

- Java - for backend, game’s logic
- Typescript with frameworks - for frontend, interface
- SQL database - for storing game information
- Coding style: [https://google.github.io/styleguide/javaguide.html](https://google.github.io/styleguide/javaguide.html)

## Team:

- Norberto Farias Cassinello
- Antonio Maña
- Minerva Gomez
- Pablo Ferreiro
- Pablo Luis Molina Blanes
- Sergio Astorga Segovia
- Emilio Rodrigo Carreira Villalta
- Zuzanna Furtak
- Agnieszka Lasek
- Ivan Iroslavov Petkov

## Citations:

Wikipedia contributors. (2023, January 16). Catan. In Wikipedia, The Free Encyclopedia. Retrieved 12:18, March 8, 2023, from https://en.wikipedia.org/w/index.php?title=Catan&oldid=1133942167

## Javadoc Documentation
Open the mvnw in integrated terminal. Then, execute .\mvnw javadoc:javadoc  , and it should generate in a folder the html for the documentation.
