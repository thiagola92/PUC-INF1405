# Changes #

Update on associoation lines.    
Enumerations are nothing more than associations.    
It's better call "player_id" than just "player" so many variables and methods have been update to be clear that what you should be putting there is an ID.

## Play ##
Every **Play** connect to one **Card**.    
**Play**s are created by **HistoryEntry**, so there is no reason to connect to **Player**.    