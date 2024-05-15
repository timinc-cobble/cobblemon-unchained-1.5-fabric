package us.timinc.mc.cobblemon.chaining.config

import net.minecraft.entity.player.PlayerEntity
import us.timinc.mc.cobblemon.chaining.util.Util

abstract class BoostConfig {
    val debug: Boolean = false
    val blacklist = mutableSetOf<String>()
    val whitelist = mutableSetOf<String>()
    abstract val koStreakPoints: Int
    abstract val koCountPoints: Int
    abstract val captureStreakPoints: Int
    abstract val captureCountPoints: Int
    abstract val thresholds: Map<Int, Int>

    fun getPointsFromThreshold(player: PlayerEntity, species: String): Int {
        val points = Util.getPlayerScore(player, species, this)
        return thresholds.maxOfOrNull { if (it.key <= points) it.value else 0 } ?: 0
    }
}