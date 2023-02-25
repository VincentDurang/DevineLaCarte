package org.sio.slam.devine.core
import kotlin.math.log2

class Jeu(val avecAide: Boolean, val paquet: Paquet, paramCarteADeviner: Carte? = null) {
    val carteADeviner: Carte
        // le getter par défaut, inutile de le redéclarer (juste pour la démonstration)
        // field est ici synonyme de carteADeviner (implicite backing memory de la propriété)
        // REM : faire référence à carteADeviner au lieu de field entrainerait une récursion incontrôlée
        get() = field
    // set(value) { field = value } <== impossible car la propriété est en lecture seule (val)

    init {
        // si le paramètre paramCarteADeviner du constructeur a comme valeur null
        // alors on demande au paquet de nous fournir une carte à deviner
        // sinon on retient la valeur de carte transmise
        this.carteADeviner = paramCarteADeviner ?: this.paquet.getCarteADeviner()
    }

    /**
     * Permettre de savoir si la proposition de carte EST bien la carte à deviner, ou non
     */
    fun isMatch(carteProposee: Carte): Boolean {
        return this.carteADeviner == carteProposee
    }

    /**
     * Analyse la partie du joueur, a-t-il abandonné la partie,
     *  a-t-il trouvé la carte en un nombre de fois "convenable" ou "inconvenable",
     *  a-t-il eu de la chance ?
     */
    fun strategiePartie(abandonner : Boolean , compteur: Int): String? {

        var reponse: String
        var paquet2 = paquet.cardinal().toDouble()
        val pChance = (compteur.toDouble() / paquet.cartes.size.toDouble())*100
        //var chance: String

    if (abandonner == false) {
        return("Vous avez abandonné (nb d'essai(s) $compteur)")
    } else {
        if (compteur > log2(paquet2)) {
            reponse = "Vous avez trouvé la carte avec un nb d'essais inconvenable (nb d'essai(s) $compteur)"
            if (pChance < 30.0){
                println("Vous avez de la chance, vous n'aviez que $pChance% de chance de trouver la carte")

            }else{
                println ("Vous n'avez pas beaucoup de chance vous aviez $pChance% de chance de trouver la bonne carte")

            }
            return reponse
        } else if (compteur < log2(paquet2)) {
            reponse = "Vous avez trouvé la carte avec un nb d'essais convenable (nb d'essai(s) $compteur)"
            if (pChance < 30.0){
                println("Vous avez de la chance, vous n'aviez que $pChance% de chance de trouver la carte")
            }else{
                println ("Vous n'avez pas beaucoup de chance vous aviez $pChance% de chance de trouver la bonne carte")

            }
            return reponse
        }

}
        return null
    }
}
