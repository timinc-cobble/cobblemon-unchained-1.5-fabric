package us.timinc.mc.cobblemon.chaining.config

class ShinyBoostConfig : BoostConfig() {
    override val koStreakPoints = 1
    override val koCountPoints = 0
    override val captureStreakPoints = 0
    override val captureCountPoints = 0
    override val thresholds: Map<Int, Int> = mutableMapOf(Pair(100, 1), Pair(300, 2), Pair(500, 3))
}