package org.sio.slam.devine.core

import org.sio.slam.devine.fabrique.createJeu32Cartes
import java.util.Collections.shuffle
import kotlin.random.Random
class Paquet constructor(var cartes: List<Carte> = ArrayList<Carte>()) {
    init {
        if (this.cartes.isEmpty()) {
            this.cartes = createJeu32Cartes()
        }
    }

    /**
     * Donne le nombre de cartes dans le paquet
     */
    fun cardinal(): Int = cartes.size

    /**
     * Représentation textuelle de l'état du paquet
     */
    override fun toString(): String {
        return "Paquet de ${cardinal()} cartes"
    }

    /**
     * C'est le paquet qui décide quelle sera la carte à deviner
     * @see [org.sio.slam.Jeu]
     */
    fun getCarteADeviner(): Carte {
        // TODO implémenter une solution moins prédictive !!
        fun rand(start: Int, end: Int): Int {
            require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
            return Random(System.nanoTime()).nextInt(end - start + 1) + start
        }
        var start = 0
        var end =0

        if ( this.cartes == createJeu32Cartes()){
            start = 0
            end = 31
            return this.cartes[rand(start, end)]
        } else {
            start = 0
            end = 51
            return this.cartes[rand(start, end)]
        }
    }

    fun rebattreCarte() {
        return shuffle(this.cartes)
    }
}
