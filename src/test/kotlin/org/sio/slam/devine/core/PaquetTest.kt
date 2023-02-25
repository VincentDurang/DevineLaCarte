package org.sio.slam.devine.core

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.fail
import org.sio.slam.devine.core.Carte
import org.sio.slam.devine.core.Paquet
import org.sio.slam.devine.enum.CouleurCarte
import org.sio.slam.devine.enum.NomCarte
import org.sio.slam.devine.fabrique.createJeu32Cartes
import org.sio.slam.devine.fabrique.createJeu52Cartes
import java.util.Collections.shuffle

internal class PaquetTest {

    @Test
    fun cardinal2Cartes() {
        val paquet2Cartes = Paquet(listOf(
            Carte(NomCarte.VALET, CouleurCarte.COEUR),
            Carte(NomCarte.DIX, CouleurCarte.TREFLE),
        ))
        assertEquals(2, paquet2Cartes.cardinal())
    }

    @Test
    fun testToString2Cartes() {
        val paquet2Cartes = Paquet(listOf(
            Carte(NomCarte.VALET, CouleurCarte.COEUR),
            Carte(NomCarte.DIX, CouleurCarte.TREFLE),
        ))
        assertEquals("Paquet de 2 cartes", paquet2Cartes.toString() )
    }

    @Test
    fun testGetCartes() {
        val paquet2Cartes = Paquet(listOf(
                Carte(NomCarte.VALET, CouleurCarte.COEUR),
                Carte(NomCarte.DIX, CouleurCarte.TREFLE),
        ))
        var desCartes: List<Carte> = paquet2Cartes.cartes
        assertEquals(2, desCartes.size)
        assertEquals(NomCarte.VALET, desCartes[0].nom)
        assertEquals(NomCarte.DIX, desCartes[1].nom)
        assertEquals(CouleurCarte.COEUR, desCartes[0].couleur)
        assertEquals(CouleurCarte.TREFLE,desCartes[1].couleur)
    }

    @Test
    fun fabriqueDe32Cartes() {
        val paquet32 :List<Carte> =createJeu32Cartes()
        assertEquals(32, paquet32.size)
    }

    @Test
    fun fabriqueDe52Cartes() {
        val paquet52 :List<Carte> =createJeu52Cartes()
        assertEquals(52, paquet52.size)
    }
    @Test
    fun testMelangeCarte() {
        val paquet2Cartes = Paquet(listOf(
            Carte(NomCarte.VALET, CouleurCarte.COEUR),
            Carte(NomCarte.DIX, CouleurCarte.TREFLE),
            Carte(NomCarte.DAME, CouleurCarte.PIQUE),
            Carte(NomCarte.NEUF, CouleurCarte.TREFLE),
        ))
        var desCartes: List<Carte> = paquet2Cartes.cartes
        shuffle(desCartes)

        assertNotEquals(NomCarte.DIX,desCartes[1].nom)
    }
}
    @Test
    fun strat(){
        val paquet52 :List<Carte> =createJeu52Cartes()

    }
