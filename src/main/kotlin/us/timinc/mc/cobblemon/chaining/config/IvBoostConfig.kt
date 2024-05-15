package us.timinc.mc.cobblemon.chaining.config

class IvBoostConfig : BoostConfig() {
    override val koStreakPoints = 0
    override val koCountPoints = 0
    override val captureStreakPoints = 1
    override val captureCountPoints = 0
    override val thresholds: Map<Int, Int> = mutableMapOf(Pair(5, 1), Pair(10, 2), Pair(20, 3), Pair(30, 4))
}