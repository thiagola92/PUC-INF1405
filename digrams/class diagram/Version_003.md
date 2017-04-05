# Changes #

## Character ##
Renamed to class **Status**.  
Moved variables that represent status from player to the class **Status**.

Reason: I wanted to compress all information that is a int and could be changed every time in one game into one single class.  
Also, right now have one "character" that have one unique ability will make the game too complex.  
I may add after the basic things end.

## State ##
One enumeration that say the state of player.

Reason: Now you only have to check this variable to see if the player can do some action.

## Player (enumeration) ##
Changed Arrays to ArrayList<Card>.

## Card ##
Now **Card** is an interface.

Reason: Before you would create one object **Card** that would implement the interface **Card** and that would have an enum **Function** just so you could know what type of thing that card would do in the game.  
And you would have to write the function for each card, when most of them would be the same action "attack X" or  "equip this".
Now you don't depend of one enum **Function** to check what this card will do, there will be 3 class that will implement from **Card** and two of them will what they should be doing.

## Card (interface) ##
Deleted

Reason: Read above

## Deck ##
Deleted

Reason: **Deck** is just an Array, doesn't need to be an entire class.

## Discard ##
Deleted

Reason: **Discard** is just an Array, doesn't need to be an entire class.

## Board ##
Created two variables that represent the old class **Card** and **Deck**.

## Weapon ##
Class that will do the basic thing when attack:
* Ask who you want to attack.
* Check the distance from the enemy.
* Ask if the enemy want to block.
 * If yes, they lose the block and end.
 * Else, reduce enemy health.
 * If enemy is dead, you receive his "reset".
* If you attack more than one time, repeat everything above.

## Equipement ##
Class that will do the basic thing when equiping:
* Who you want to equip.
* Equip the item to the player.

## Event ##
I don't have idea.

## Game ##
Still not sure what this class represent.

## Player_connection ##
Every information about the conection with this player, things like socket will be here.
