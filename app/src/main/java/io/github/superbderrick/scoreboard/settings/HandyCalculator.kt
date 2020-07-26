package io.github.superbderrick.scoreboard.settings

/**
 * Created by derrick on 25/03/2018.
 */
object HandyCalculator {
    fun getHandy(point: Int): Handy {
        var point = point
        var direction = 0
        if (point < 7) {
            direction = 0
        } else {
            direction = 1
            point = point - 6
        }
        val handy = Handy()
        handy.handyPoint = point
        handy.direction = direction
        return handy
    }
}