package io.github.superbderrick.scoreboard.match

/**
 * One game (11 points, win by 2). The game is stored as the list of balls played, and score,
 * server and status are all derived from it.
 *
 * A ball whose winner was not seen is stored as [Rally.UNKNOWN]. It still counts as a ball played,
 * so serve rotation stays correct, and it can be assigned to a player later with [resolve].
 */
class Game(val firstServer: Side = Side.LEFT) {
    enum class Status {
        IN_PROGRESS,
        /** Unknown balls could decide the game: the scorer should resolve them. */
        MAYBE_OVER,
        OVER
    }

    private val balls = ArrayList<Rally>()

    val rallies: List<Rally> get() = balls
    val ballsPlayed: Int get() = balls.size
    val leftScore: Int get() = balls.count { it == Rally.LEFT }
    val rightScore: Int get() = balls.count { it == Rally.RIGHT }
    val unknownCount: Int get() = balls.count { it == Rally.UNKNOWN }

    /** Serve changes every 2 balls, and every ball once 20 balls have been played (10:10). */
    val server: Side
        get() {
            val n = balls.size
            val firstServes = if (n >= DEUCE_BALLS) n % 2 == 0 else (n / 2) % 2 == 0
            return if (firstServes) firstServer else firstServer.other()
        }

    val status: Status
        get() {
            val l = leftScore
            val r = rightScore
            val u = unknownCount
            if (u == 0) return if (isWin(l, r)) Status.OVER else Status.IN_PROGRESS
            for (k in 0..u) {
                if (isWin(l + k, r + u - k)) return Status.MAYBE_OVER
            }
            return Status.IN_PROGRESS
        }

    val winner: Side?
        get() {
            if (status != Status.OVER) return null
            return if (leftScore > rightScore) Side.LEFT else Side.RIGHT
        }

    /** Returns false if the game is already over. */
    fun add(rally: Rally): Boolean {
        if (status == Status.OVER) return false
        balls.add(rally)
        return true
    }

    fun undo(): Rally? = if (balls.isEmpty()) null else balls.removeAt(balls.size - 1)

    /**
     * Assigns the winner of an unknown ball. Returns false (and changes nothing) if [index] is not
     * an unknown ball, or if the result would mean the game had already ended before its last ball.
     */
    fun resolve(index: Int, winner: Side): Boolean {
        if (index !in balls.indices || balls[index] != Rally.UNKNOWN) return false
        val candidate = ArrayList(balls)
        candidate[index] = Rally.of(winner)
        if (endsEarly(candidate)) return false
        balls[index] = candidate[index]
        return true
    }

    /** True if some fully known prefix of [seq] (shorter than [seq]) already ends the game. */
    private fun endsEarly(seq: List<Rally>): Boolean {
        var l = 0
        var r = 0
        for (i in 0 until seq.size - 1) {
            when (seq[i]) {
                Rally.LEFT -> l++
                Rally.RIGHT -> r++
                Rally.UNKNOWN -> return false
            }
            if (isWin(l, r)) return true
        }
        return false
    }

    fun encode(): String = String(CharArray(balls.size) { balls[it].code })

    companion object {
        const val WIN_SCORE = 11
        private const val DEUCE_BALLS = 20

        fun isWin(a: Int, b: Int): Boolean = maxOf(a, b) >= WIN_SCORE && Math.abs(a - b) >= 2

        fun decode(firstServer: Side, s: String): Game {
            val game = Game(firstServer)
            s.forEach { game.balls.add(Rally.fromCode(it)) }
            return game
        }
    }
}
