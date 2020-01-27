androidClimbing
pour la page faceboook anthetification avec firebase
https://climbingandroid.firebaseapp.com/__/auth/handler
pour firebase
reussite de l'instalation de google analytics et du depot de project sur la page de la console de firebase


##Travail par ordre de chronologie et avancement de mon projet 
0 travail sur les différents écrans 
1 recherche des model
2 ajout de gps avec openstreet map 
3 ajout de room en interner pour binder l'api au code
4 test de firebase et de sa base realtime databse.
5 regard sur authentification
6 page de login de type facebook, google, etc.
7 genration de l apk
8 envoie et test sur firebase analytics.
9 creation de puces dans la map 
10 creation de plusieurs activity (meme en passant des données via put extra, et en passant des objet serialiser aussi)
11 # activityfriends recupere la liste de ses amis
    ativity api qui recupere des images de api climbingroutes
    ativity card qui affiche une carte
    ativity maps cree la carte des sites autour de soi ou autres
    firebase utilisant le reatime database pour faire des push ou des information en mode broadcsat
    user profile pour editer ou modifier son profile 
    create a new card pour enregistrer un nouvelle carte et la mettre en methode post sur spring.auxietre.com
        plus les 3 sites internets spring.auxietre.com pour l'administrations roles des internevants (administrateur, videotubeurs ..)
        react.auxietre.com pour le site en react
        vuejs.auxietre.com pour le site en vuejs
        angular1 et angular2 deux version du site en angular.
a peu près entre Gui et mmm plus de 300h depuis le début de cette année de travail sur les sites car je suis parti de 0 en Web 
et en appli cette année
présentation sur l'appli en pièces jointe via le slide et des screenshot pris dans le Dossier MP4 tout au long de mon developpement.
Je suis très content de l'avancement de ce projet, mais c'est long et chaque dépendances à ses propres variances qui peuvent vraiment tout planté !! enfer sur terre
.
12 Creation du mode tablette pour une seule vue: La visualisation de la carte et de la listeView.
13 remise en forme des liens des boutons pour une cohérence entre les pages



##CREATION DE LOG PERSO POUR DEBUGGAGE ET POUR UTILISER APRES EN PRODUCTION (tout avec Log.i Log.w ...)
a chaque Log je met l'activity qui est en jeu et en general _ suivi du nom de la methode qui appel le Log
j'ai utilisé le Log.i et log.w seulement pour l'instant
ex: 
Log.w("DATABASE", "erreur pendant la recuperation des images ");
Log.i("DEBUGGAGE", ""+ climber.getFile_name());

##Deux versions de mon Apk ont été soumises à Firebase 

la première étant un echec suite à une methode mal lancée en asyncTask (requete Http vers le serveur spring.auxietre.com)
la deuxième est une réussite avec semble t-il que des erreurs minimes 
le test à été fait sur un telephone samsung galaxy 8 de apk2 du dossier Mp4
dans ce dossier vous trouverez toutes les captures d'écrans réalisées tous au long de mon projet 
et les google docs, pdf, et google slide de mon projet total.
Je n'ai pas encore tout fait loin de la, mais ce projet me tiens énormément à coeur depuis plus de 10 ans, 
et commencer à avoir les compétences informatique pour tout réaliser de A à Z était un rêve qui
se concretise de jours en jour 

##GRACE A TOUS VOS COURS, merci à tous les professeurs, doctorans, maître de conférence
##qui m'ont motivé et suivi pendant ces trois années merveilleuses A l'Istic. François.

