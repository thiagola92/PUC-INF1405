# Changes #

## Board ##
All methods (excluding use_card) were moved to **Player**.
All variables that are already represented in connection were removed;

Reason:  
"buy_from_deck"  
"discard_card"  
"attack_player"  
"equip_player"  
Who do this things? The **Player**, not the **Board**. The **Board** is just one way of comunication, so i will move every single action to the **Player**.  
I will probably change the name of the method use_card;

## Player ##
All variables that are already represented in connection were removed;
Received many methods from **Board**.

Reason: Read the **Board** reason.

## Historic ##
Name changed to **HistoricEntry**.
It will hold one ArrayList with every **Play**.

Reason: I could let save as string and if needed to see the historic, i could translate. But is better to let something easy to translate or to record... (i could explain better but i am lazy, sorry).

## Play ##
New class.

Reason: Instead that create a string saying "THIAGO, ATTACK, LEO", i would save on one class with all the info. Easy to change if needed.
