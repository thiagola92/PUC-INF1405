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

# Version 003 #

Eram basicamente 3 partes  
* Conectar com o server.
* Quando não era a vez do jogador.
* Quando era a vez do jogador.

Primeira parte estava okay.  
A segunda parte você tinha que mostrar tudo que podia fazer fora do seu turno e tudo que o OUTRO jogador podia fazer fora do turno dele.  
A terceira acabava sendo o inverso apenas, mostrar tudo que você pode fazer dentro do seu turno e tudo que o outr jogador pode fazer fora do turno dele.  

O professor simplificou fazendo 
* Conectar com o server.
* Quando é a vez do jogador.

E criou uma atividade "Definir jogador do turno" e dela você vai para o jogador do turno, não precisa mais de uma condição de ficar esperando ser a vez.  

# Version 004 #

Eu não entendi direito, mas aparentemente era bom eu botar uma condição que verifica se o __t__ segundos passou, embora achasse que isso já estaria incluso no "Espera t segundos".  
Professor falou que poderia eliminar "Turno" pos não é um estado do jogador, em outras palavras, um estado onde o jogador não faz nada, não é um estado necessário.  
Consequentemente deletei "Pega primeira carta do deck" e "Entrega para jogador do turno" pois essas ações estão implicitas em "Compra carta".  
Próxima modificação é que varias ações que eram consideradas do outro jogador estavam no servidor, quem está comprando carta ou descartando carta é o jogador, não o server.  
Por último, acrescentamos uma verificação para saber se o jogador tem carta na mão.  

# Version 005 #

Acrescentada um caminho que finaliza o jogo.