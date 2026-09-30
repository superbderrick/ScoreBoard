package io.github.superbderrick.scoreboard.match

/** A best-of-N match. The first server alternates every game. */
class Match(val gamesToWin: Int, private val firstServerOfFirstGame: Side = Side.LEFT) {
    private val gameList = ArrayList<Game>()

    init {
        gameList.add(Game(firstServerOfFirstGame))
    }

    val games: List<Game> get() = gameList
    val currentGame: Game get() = gameList[gameList.size - 1]

    val leftGames: Int get() = gameList.count { it.winner == Side.LEFT }
    val rightGames: Int get() = gameList.count { it.winner == Side.RIGHT }

    val winner: Side?
        get() = when {
            leftGames >= gamesToWin -> Side.LEFT
            rightGames >= gamesToWin -> Side.RIGHT
            else -> null
        }

    val isOver: Boolean get() = winner != null

    val canStartNextGame: Boolean
        get() = currentGame.status == Game.Status.OVER && !isOver

    fun addRally(rally: Rally): Boolean = !isOver && currentGame.add(rally)

    fun startNextGame(): Boolean {
        if (!canStartNextGame) return false
        gameList.add(Game(firstServerFor(gameList.size)))
        return true
    }

    /** Undoes the last ball; from an empty new game it steps back into the previous game. */
    fun undo(): Boolean {
        if (currentGame.ballsPlayed == 0 && gameList.size > 1) {
            gameList.removeAt(gameList.size - 1)
        }
        return currentGame.undo() != null
    }

    private fun firstServerFor(gameIndex: Int): Side =
            if (gameIndex % 2 == 0) firstServerOfFirstGame else firstServerOfFirstGame.other()

    fun encode(): String =
            "$gamesToWin|${firstServerOfFirstGame.name}|" + gameList.joinToString(";") { it.encode() }

    companion object {
        fun decode(s: String): Match? {
            try {
                val parts = s.split("|", limit = 3)
                if (parts.size != 3) return null
                val match = Match(parts[0].toInt(), Side.valueOf(parts[1]))
                match.gameList.clear()
                parts[2].split(";").forEachIndexed { i, g ->
                    match.gameList.add(Game.decode(match.firstServerFor(i), g))
                }
                return match
            } catch (e: Exception) {
                return null
            }
        }
    }
}
