package us.timinc.mc.cobblemon.chaining.config

class HiddenBoostConfig : BoostConfig() {
    override val koStreakPoints = 100
    override val koCountPoints = 1
    override val captureStreakPoints = 0
    override val captureCountPoints = 0
    override val thresholds: Map<Int, Int> = mutableMapOf(99 to 1)
    val marbles = 5
}