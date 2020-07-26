package io.github.superbderrick.scoreboard.helper

/**
 * Created by derrick on 27/01/2018.
 */
class ScoreManager {
    interface OnScoreChangeListener {
        fun onFirstScoreChanged(score: Int)
        fun onSecondScoreChanged(score: Int)
        fun onScoreInitialized(score: Int)
    }

    enum class Operation {
        Increase, Decrease
    }

    enum class UserType {
        First, Second
    }

    var firstScore = 0
        private set
    var secondScore = 0
        private set
    var scoreMaxRange = 0
    var listener: OnScoreChangeListener? = null

    fun changeScore(operation: Operation, type: UserType) {
        if (type == UserType.First) {
            regulateFirstScore(operation)
        } else {
            regulateSecondScore(operation)
        }
    }

    fun changeScore(operation: Operation, type: UserType, addtionalPoint: Int) {
        if (type == UserType.First) {
            regulateFirstScore(operation, addtionalPoint)
        } else {
            regulateSecondScore(operation, addtionalPoint)
        }
    }

    private fun regulateFirstScore(operation: Operation) {
        if (operation == Operation.Increase) {
            if (firstScore < scoreMaxRange && firstScore >= 0) {
                firstScore++
            }
        } else {
            if (firstScore > 0 && firstScore < scoreMaxRange + 1) {
                firstScore--
            }
        }
        listener!!.onFirstScoreChanged(firstScore)
    }

    private fun regulateFirstScore(operation: Operation, addtionalPoint: Int) {
        if (operation == Operation.Increase) {
            if (firstScore < scoreMaxRange && firstScore >= 0) {
                firstScore = firstScore + addtionalPoint
            }
        } else {
            if (firstScore > 0 && firstScore < scoreMaxRange + 1) {
                firstScore--
            }
        }
        listener!!.onFirstScoreChanged(firstScore)
    }

    private fun regulateSecondScore(operation: Operation) {
        if (operation == Operation.Increase) {
            if (secondScore < scoreMaxRange && secondScore >= 0) {
                secondScore++
            }
        } else {
            if (secondScore > 0 && secondScore < scoreMaxRange + 1) {
                secondScore--
            }
        }
        listener!!.onSecondScoreChanged(secondScore)
    }

    private fun regulateSecondScore(operation: Operation, addtionalPoint: Int) {
        if (operation == Operation.Increase) {
            if (secondScore < scoreMaxRange && secondScore >= 0) {
                secondScore = secondScore + addtionalPoint
            }
        } else {
            if (secondScore > 0 && secondScore < scoreMaxRange + 1) {
                secondScore--
            }
        }
        listener!!.onSecondScoreChanged(secondScore)
    }

    fun resetScore() {
        firstScore = 0
        secondScore = 0
        listener!!.onScoreInitialized(0)
    }

    private fun initValues() {
        firstScore = 0
        secondScore = 0
    }



    companion object {
        const val DEFAULT_MAXIMUM_SCORE = 30
    }

    init {
        initValues()
    }
}