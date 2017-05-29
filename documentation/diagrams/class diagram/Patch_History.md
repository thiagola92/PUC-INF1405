# Index #
* [Version 002](#version-002)
* [Version 003](#version-003)
* [Version 004](#version-004)
* [Verison 005](#version-005)
* [Version 006](#version-006)
* [Version 007](#version-007)
* [Version 008](#version-008)
* [Version 009](#version-009)
* [Version 010](#version-010)
* [Version 011](#version-011)
* [Version 012](#version-012)
* [Version 013](#version-013)
* [Version 014](#version-014)
* [Version 015](#version-015)

# Version 002 #

## Hand ##
Class Hand **Deleted**

Reason: For now, there is no reason to have one class responsabile for one single Array of **Card**, i am creating this Array on class **Player**.

## Player ##
As said in class **Hand** , received one Array of **Card**

## Board ##
Created two methods
* buy_from_deck(Player): void
* buy_from_discard(Player): void

# Version 003 #

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

# Version 004 #

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

# Version 005 #

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

# Version 006 #

Update on associoation lines.    
Enumerations are nothing more than associations.    
It's better call "player_id" than just "player" so many variables and methods have been update to be clear that what you should be putting there is an ID.

## Play ##
Every **Play** connect to one **Card**.    
**Play**s are created by **HistoryEntry**, so there is no reason to connect to **Player**.    

# Version 007 #

Nothing to see here

# Version 008 #

Started to write code in java.

## Player_connection / Connection ##
The socket responsable for talking with the client, i will putting this together with __Player__.  
There is no reason to go through __Board__ until __Player__ to call a method in __Player__.  

## Game / ConnectionReceiver ##
This object will receive all __Conection__ and give it to the __Board__, after the job is done.  
Changing the name to represent what really is.  

## Board ##
player_turn renamed to turnFromPlayer.  
player_turn_attacks renamed to attacksThisTurn.  
use_card renamed to discardCard. 

### discardCard ###
Now the __Player__ will be in charge to remove from hand/equip, this method shouldn't be interacting with variables of __Player__ directly.  

## New Things ##
numberOfPlayers => Still don't know if i need this or i should get the size of the ArrayList with players.  
nextTurn() => Pass turn  
resetAttacksThisTurn() => attacksThisTurn go to 0, so you can attack again.  
increaseAttacksThisTurn() => attacksThisTurn +1, so you know how many times the turn player attacked.  
decideShiftOrder() => Should be called one time only, when the game start to decide the order.  
pickFromDeck() => Pick a card from the deck.  
pickFromDiscard() => Pick a card from the discard.  

# Version 009 #

## Player ##
Variables:
* Removed variable 'id'. For now i don't know what to do with it.  

Methods:
* All methods renamed from something like abc_defg to abcDefg.
* buy_from_deck() removed could make any player buy, there is no reason for each player have one method like that.
* pickFromDeck() make this player buy from deck, if you want another player buying from deck call this function from the other player.
* discardCard() also could make any player discard, now can only make himself discard and instead of using a string to find the card, use the address of the object.
* receiveCard() is just adding a card to the hand.
* equipCard() is just adding a card to the hand. And can only target the own player.

## Card ##
Interface doesn't create variables.

## Equipment ##
Received name, description from old Card.  
Received Function that will the card action when called.  

## Function ##
An interface so you could pass anonymous methods, still testing.

# Version 010 #

This may be big...  
PS: I am commenting everything that i can in the code, may be a bad idea...

## Package Server ##
Before was

* client
* other
* server

Now is

* client
* other
* server
* server.card
* server.player

I think you are smart enough to know that i did this to be more organized.

## Connection/ConnectionToClient ##
* Changed the name so you don't confuse with the connection created on player.
* It will receive a mensagem and split into arguments.

## Connection/ConnectionToServer ##
* Changed the name so you don't confuse with the connection created on server.
* Extends thread so can always be listen to changes on server.

## Board ##
* Changed some System.out.println to System.out.format (concatenation takes too much work/time)
* turnFromPlayer will count player 0 as a player. For programmers it easy to understand this way.
* startGame() start the game, one time that this function is called you will be listen one player until he pass the turn.

## FunctionEquipment ##
As soon you use a card equipment, it will call this interface that knows that you card is an equipment so it will be able to call functions from equipment.

## AllCards ##
One object to rule them all... One object to create all cards.

# Version 011 #

## FunctionEvent ##
Created, same reason from FunctionEquipment.

## FunctionWeapon ##
Created, same reason from FunctionEquipment.

## Weapon ##
* Removed attacks attribute. Maybe in the future.

## AllCards ##
* equipment(), weapon() and event() are functions that will create X equals cards.

# Version 012 #

## Board ##
* shuffleDiscardOnDeck() created, as the name say put all discard cards on deck and suffle.

## Player ##
* pickFromDeck() removed.
  * Before you had to do player.pickFromDeck(X), now you have to do player.receiveCards(board.pickFromDeck(X))
  * What is the difference now? Before you couldn't take directly to the board, now you can. So i don't need a function that do what two functions could do together easly.
* receiveCard() renamed to receiveCards(), now you send one array of cards and don't have to make a loop to give X cards anymore.

# Version 013 #

## Board ##
* distanceBetween() created, check the distance between two players (doesn't count dead player).

# Version 014 #
So many mistakes or things that i forgot.

## Board ##
* increaseAttacksThisTurn() removed
* resetAttacksThisTurn() removed
* setAttacksThisTurn() add
  * Instead of have functions to increase or to reset, let the server decide the exactly number.
  * Notice that this a setXXX so will not appear on diagram class.
* endGame() created.

## Player ##
* attackPlayer() was here all the time but never on diagram... so here is it.
  * The truth is, it did exist but it wasn't complete. It still not complete but this is no reason for not being here.
* blockPlayer() created.
* command() add to the diagram.
  * Created on version 013/012/011 and forgot to put on diagram.
* distanceBetween() removed from diagram.
  * For 1 second was on __Player__ but i moved to __Board__ and forgot to edit the diagram.
  
# Version 015 #

## HistoryEntry ##
Created, so finally have attributes and methods.
* append() add an action to the end.
* read() return a string telling every action in order.

## Action ##
Better name than Play.
* As i am using the objects as reference i don't need player_doing_id and player_target_id. The same goes for cards, i am using cards reference...
* time, no need for this.
* read(), read the action trying to discovery what happened.

## State ##

* ATTACKING and BLOCKING removed. (no need for them now at least)