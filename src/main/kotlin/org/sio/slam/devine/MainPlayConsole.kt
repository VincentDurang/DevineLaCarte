package org.sio.slam.devine

import org.sio.slam.devine.core.Carte
import org.sio.slam.devine.core.Jeu
import org.sio.slam.devine.core.Paquet
import org.sio.slam.devine.enum.CouleurCarte
import org.sio.slam.devine.enum.NomCarte
import org.sio.slam.devine.enum.getCouleurCarteFromString
import org.sio.slam.devine.enum.getNomCarteFromString
import org.sio.slam.devine.fabrique.createJeu32Cartes
import org.sio.slam.devine.fabrique.createJeu52Cartes

fun main(args: Array<String>) {

    var paquetDeCartes = Paquet(createJeu52Cartes())
    var carteDuJoueur: Carte
    var nomCarteUser: NomCarte?
    var couleurCarteUser: CouleurCarte?

    var aide = false

    do {
        println("Voulez-option de l'aide. (oui ou non)")
        var reponse = readln().trim().uppercase()
        if (reponse == "OUI") {
            aide = true
        } else if (reponse == "NON") {
            aide = false
        } else {
            print("erreur\n")
        }
    } while (reponse != "OUI" && reponse != "NON")

    do {
        println("Choix du paquet : Paquet de 32 (entrez 32) ou Paquet de 52 (entrez 52)")
        val paquet = readln().toInt()
        if (paquet === 32) {
            println("Création d'un paquet de 32 cartes")
            paquetDeCartes = Paquet(createJeu32Cartes())
        } else if (paquet === 52) {
            println("Création d'un paquet de 52 cartes")
        } else {
            print("erreur\n")
        }
    } while (paquet != 32 && paquet != 52)

    println(" ==== Instanciation du jeu, début de la partie. ====")
    val jeu = Jeu(aide, paquetDeCartes)

    var carteADeviner = jeu.carteADeviner
    var couleurCarteUserStr: String
    var nomCarteUserStr: String
    var abandonner: Boolean = false
    var compteur = 0
    do {
        var recommencer = 0
        compteur++
        do {
            println("Entrez un nom de carte dans le jeu (exemples : Roi, sept, six, As...) :")
            nomCarteUserStr = readLine() + ""
            nomCarteUser = getNomCarteFromString(nomCarteUserStr.trim().uppercase())
            if (getNomCarteFromString(nomCarteUserStr.trim().uppercase()) == null) {
                print("error\n")
            }
        } while (getNomCarteFromString(nomCarteUserStr.trim().uppercase()) == null)

        do {
            println("Entrez un nom de \"couleur\" de carte parmi : Pique, Trefle, Coeur, Carreau : ")
            couleurCarteUserStr = readLine() + ""
            couleurCarteUser = getCouleurCarteFromString(couleurCarteUserStr.trim().uppercase())
            if (getCouleurCarteFromString(couleurCarteUserStr.trim().uppercase()) == null) {
                print("error\n")
            }
        } while (getCouleurCarteFromString(couleurCarteUserStr.trim().uppercase()) == null)

        if (nomCarteUser != null && couleurCarteUser != null) {
            // prise en compte de la carte du joueur
            carteDuJoueur = Carte(nomCarteUser, couleurCarteUser)


            if (jeu.isMatch(carteDuJoueur)) {
                println("Bravo, vous avez trouvé la bonne carte !")
                recommencer++

            } else {
                println("Ce n'est pas la bonne carte !")
                println("votre proposition  : $carteDuJoueur")
                if (aide == true) {
                    carteADeviner.compareTo(carteDuJoueur)
                    if (carteDuJoueur < carteADeviner)
                        println("La carte a deviner est plus grande")

                    if (carteDuJoueur > carteADeviner)
                        println("La carte a deviner est plus petite")

                    // TODO: (A) si l'aide est activée, alors dire si la carte proposée est
                    //  plus petite ou plus grande que la carte à deviner

                }
                println("Voulez-continuer?(oui ou non)")
                val restart = readln().trim().uppercase()
                if (restart == "OUI") {
                    abandonner = true
                } else if (restart == "NON") {
                    recommencer++
                    abandonner = false
                    println(" ==== Fin prématurée de la partie ====")
                } else {
                    println("erreur")
                }
            }
            // } else {
            // utilisateur a mal renseigné sa carte
            //val nomCarte = if (nomCarteUserStr == "") "?" else nomCarteUserStr
            // couleurCarte = if (couleurCarteUserStr == "") "?" else couleurCarteUserStr

            //println("Désolé, mauvaise définition de carte ! (${nomCarte} de ${couleurCarte})")
            //}
        }
    } while (recommencer == 0)


// TODO (A) permettre au joueur de retenter une autre carte (sans relancer le jeu) ou d'abandonner la partie
// TODO (A) Présenter à la fin la carte à deviner
    println("La carte a deviner etais la ${carteADeviner}")


// TODO (challenge-4) la stratégie de jeu est à implémenter... à faire lorsque les autres TODOs auront été réalisés
    println("${jeu.strategiePartie(abandonner, compteur)}")
    //println(compteur)
    println(" ==== Fin de la partie. ====")
}

/*
fun choixValeur(): NomCarte? {

    var nomCarteUserStr: String

    println("Entrez un nom de carte dans le jeu (exemples : Roi, sept, six, As...) :")

    // Réalisation d'une boucle pour la valeur entrez

    do {
        nomCarteUserStr = readLine() + ""
        nomCarteUser = getNomCarteFromString(nomCarteUserStr.trim().uppercase())

        if (getNomCarteFromString(nomCarteUserStr.trim().uppercase()) == null) {
            print("error\n")
        }
    } while (getNomCarteFromString(nomCarteUserStr.trim().uppercase()) == null)

    return nomCarteUser
}

fun choixCouleur(): CouleurCarte? {
    var couleurCarteUserStr: String

    do {
        println("Entrez un nom de \"couleur\" de carte parmi : Pique, Trefle, Coeur, Carreau : ")
        couleurCarteUserStr = readLine() + ""
        couleurCarteUser = getCouleurCarteFromString(couleurCarteUserStr.trim().uppercase())

        if (getCouleurCarteFromString(couleurCarteUserStr.trim().uppercase()) == null) {
            print("error\n")
        }

    } while (getCouleurCarteFromString(couleurCarteUserStr.trim().uppercase()) == null)

    return couleurCarteUser
}
*/