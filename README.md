# Projet bataille navale - IN205

Ceci est le repository du TP bataille navale pour le cours IN205.
## Questions normales:
J'ai globalement suivi le déroulement du TP (cf commits). En revanche, dans les commits intermédiaires du début, il y avait beaucoup de bugs non réglés, qui ont été résolus au fil du TP. Donc les versions finales permettent une meilleure appréciation du travail.

### Coups déjà tirés:
Pour gérer les coups déjà tirés sur un navire, j'ai rajouté un élément dans l'énum Hit (ALREADY_SHOT), qui se comporte comme un MISS à ceci près qu'il permet d'éviter de compter plusieurs fois un tir sur un même navire, et ainsi de le couler plusieurs fois, ce qui est problématique. Pour les autres cases, on considère que l'on renvoie juste un "MISS", peu importe le nombre de tirs déjà effectués dessus. Tant pis pour le joueur! A partir de ALREADY_SHOT, on peut donc régler les problèmes de comptage des navires coulés, etc...

## Questions bonus:
### Multijoueur :
Le multijoueur a été implémenté. Il a fallu rajouter un Booléen singlePlayer dans la classe Game pour pouvoir choisir si on joue seul contre l'IA ou à deux. Il a donc fallu modifier la boucle de jeu avec des conditionnelles sur singlePlayer pour afficher ou non la grille du joueur 2 lorsqu'il s'agit de son tour.

La fonction init(boolean) prend désormais un booléen en argument, qui décide si la partie est multijoueur ou un seul joueur, lorsque celle-ci est créée

### Sauvegardes :

-Parfois, il y a cependant une erreur (attrapée, qui fait simplement recommencer une nouvelle partie) n'arrivant pas à lire le joueur sérialisé.
-Aussi, il a donc fallu que les classes à sérialiser implémentent la classe Serializable.
-Enfin, une partie 1 joueur reste 1 joueur, de même pour une partie 2-joueurs; après chargement de celles-ci. Cela pourrait être intéressant éventuellement de voir pour continuer en solo une partie multijoueur. En revanche, étant donné que Player et PlayerAI sont deux classes différentes, la sérialisation se fait différemment et il faudrait donc pouvoir convertir l'une en l'autre facilement, ce que je n'ai pas fait 