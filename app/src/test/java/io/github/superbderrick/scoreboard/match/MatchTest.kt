package io.github.superbderrick.scoreboard.match

import org.junit.Assert.*
import org.junit.Test

class MatchTest {
    private fun win(m: Match, side: Side) {
        repeat(11) { assertTrue(m.addRally(Rally.of(side))) }
    }

    @Test
    fun firstServerAlternatesEachGame() {
        val m = Match(2, Side.LEFT)
        win(m, Side.LEFT)
        assertTrue(m.startNextGame())
        assertEquals(Side.RIGHT, m.currentGame.server)
        win(m, Side.RIGHT)
        assertTrue(m.startNextGame())
        assertEquals(Side.LEFT, m.currentGame.server)
    }

    @Test
    fun matchEndsWhenSomeoneHasGamesToWin() {
        val m = Match(2)
        win(m, Side.LEFT)
        m.startNextGame()
        win(m, Side.LEFT)
        assertTrue(m.isOver)
        assertEquals(Side.LEFT, m.winner)
        assertFalse(m.startNextGame())
        assertFalse(m.addRally(Rally.LEFT))
    }

    @Test
    fun cannotStartNextGameWhileUnknownBallsUnresolved() {
        val m = Match(2)
        repeat(10) { m.addRally(Rally.LEFT) }
        m.addRally(Rally.UNKNOWN)
        assertFalse(m.canStartNextGame)
        assertTrue(m.currentGame.resolve(10, Side.LEFT))
        assertTrue(m.canStartNextGame)
        assertEquals(1, m.leftGames)
    }

    @Test
    fun undoStepsBackIntoPreviousGame() {
        val m = Match(3)
        win(m, Side.LEFT)
        m.startNextGame()
        assertTrue(m.undo())
        assertEquals(1, m.games.size)
        assertEquals(10, m.currentGame.leftScore)
        assertEquals(0, m.leftGames)
    }

    @Test
    fun encodeDecodeRoundTrip() {
        val m = Match(2, Side.RIGHT)
        win(m, Side.LEFT)
        m.startNextGame()
        m.addRally(Rally.RIGHT)
        m.addRally(Rally.UNKNOWN)
        val copy = Match.decode(m.encode())!!
        assertEquals(m.encode(), copy.encode())
        assertEquals(m.currentGame.server, copy.currentGame.server)
        assertEquals(1, copy.leftGames)
    }

    @Test
    fun decodeGarbageReturnsNull() {
        assertNull(Match.decode("nonsense"))
    }
}
