# Index #
* [Version 002](https://github.com/thiagola92/Reset/blob/master/digrams/activity%20diagram/Patch_History.md#version-002)

# Version 002 #

## Player ##
Do jeito que estava escrito "Esperar turno" podia ir para "Turno" a qualquer momento. Foi mudado para ter uma condição, É "Seu turno?" [Sim] e [Não].    
Estar sendo atacado depende de outro jogador, então a decisão foi transferida para __Another Player__.    
"Reação" dava a opção de usar carta ou não, mas "Verificando" não fazia nada demais e no final levava ao mesmo local ("Atualizar jogo") então foi retirado a questão de [Sim] ou [Não], já que não faz diferença no final.    
"Atualizar jogo" leva a pergunta de novo de "Seu turno?" E o processo se repete.

"Usar carta" e "Passar turno" não são estados, são opções que o jogador tem.    
"Esperando reação" foi retirado e virou "Reação" dentro de __Another Player__.    

## Another Player ##
Tudo que envolve a resposta de um segundo jogador, ficará dentro daquela área.
