package io.github.superbderrick.scoreboard.match

/** Which player is on the left / right of the scoreboard. */
enum class Side {
    LEFT, RIGHT;

    fun other(): Side = if (this == LEFT) RIGHT else LEFT
}

/** Outcome of a single ball. UNKNOWN means the scorer did not see who won it. */
enum class Rally(val code: Char) {
    LEFT('L'), RIGHT('R'), UNKNOWN('?');

    companion object {
        fun fromCode(c: Char): Rally =
                values().firstOrNull { it.code == c } ?: throw IllegalArgumentException("bad rally code: $c")

        fun of(side: Side): Rally = if (side == Side.LEFT) LEFT else RIGHT
    }
}
