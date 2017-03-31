# Changes #

## Board ##
All methods (excluding use_card) were moved to **Player**

Reason:  
"buy_from_deck"  
"discard_card"  
"attack_player"  
"equip_player"  
Who do this things? The **Player**, not the **Board**. The **Board** is just one way of comunication, so i will move every single action to the **Player**.

## Player ##
