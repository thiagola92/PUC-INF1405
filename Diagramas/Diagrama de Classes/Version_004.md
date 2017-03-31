# Changes #

## Status ##
Deleted

Reason: It seems that most of the status are not necessary, you can calculate they at time during actions.  
For example, how much damage will you do with one weapon that have 2 of damage?  
Go through every equipment and check if they increase your damage.  
There is no reason to have one variable inside **Status** that will hold the total.

## Card (interface) ##
Add variable name so each card have a title (one way to identify).

## Player ##
Receive two variables from **Status**.  
Deleted one variable that the class didn't even existed.  
And there is no reason for the diagram tell variable that you can already suspect by seeing the conection. For example,  
You can delete variable team: Color becauseYou can see by the arrow that this class have a enumeration of color.

## Board ##
Board received some methods that will do the basic things of the cards, like attack/equip. Still not sure if this should be there or inside class **Weapon** and **Equipement**.

## Game ##
This class will be responsabile for reciving one command and translate to the game. Also the other way.
