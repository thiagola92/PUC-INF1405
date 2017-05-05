# Index #
* [Version 002](#version-002)
* [Version 003](#version-003)
* [Version 004](#version-004)
* [Version 005](#version-005)

# Version 002 #

## Player ##
(1)From one elipse you should go to another elipse or diamond, not both.  
Diamonds represent options, so if you can go one way or other, you should use diamond to show when you will pick one or other.  
Elipse are close to action/state so you shouldn't treat like a loop.

"Esperar turno" could go to "Turno" or to one diamond, this didn't make sense so because you couldn't know when to take which path.  
"Esperar turno" was removed because there is other way to make a loop and show the condition to get out.
I tried to show this by making a diamond that could take you to your turn if was your turn or take you to "Esperar" that could make you go back to the diamond.  
Everything that depends from server do should be on __Server__, everything that depends from the player of the turn to should be on __Player__, everything that depends from other player should be on... __Another Player__.  
You could remove "Verificando" and put together with "Atualizar jogo", meaning that the diamond also didn't make sense.  
"Atualizar jogo" is now connecting to the diamond that check if is the player turn.  

"Usar carta" and "Passar turno", reading the diagram you coudn't guess where you would go from "Turno", that's because it should have a diamond (1).  

## Server ##
"Esperando reação" is not an action from __Server__, moving to __Another Player__ as "Reação".  

## Another Player ##
New.  

# Version 003 #

## Player / Turn Player ##
Renamed from __Player__ to __Turn Player__.  
We don't need one loop to check if is the player turn, the __Server__ should warn you, so i am removing the loop diamond.  
You don't need to treat when is your turn and interacting with someone AND when is not your turn and you are being interacted. The __Turn Player__ and __Another Player Turn__ exist so you can see the action and reaction at the same time and not repeat everything.  

## Server ##
New state "Definir jogador do turno".
E criou uma atividade "Definir jogador do turno" e dela você vai para o jogador do turno, não precisa mais de uma condição de ficar esperando ser a vez.  

# Version 004 #

## Server ##
I didn't get but professor recomended putting another loop on "Espera t segundos".  
"Pega primeira carta" and "Entregar para o jogador do turno" should be implicit on "Compra carta" so i am removing.  

## Player Turn ##
A loop to check if you have card on hand, you shouldn't be able to play without cards.  

## Another Player Turn ##
Every effect from cards on another player should be here(you know that server will manage this effects but it's not his action).   

# Version 005 #

## Another Player Turn ##
After being attacked if he is dead, the game end.  
