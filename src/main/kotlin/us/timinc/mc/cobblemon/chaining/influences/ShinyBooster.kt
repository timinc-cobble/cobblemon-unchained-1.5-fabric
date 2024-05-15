package us.timinc.mc.cobblemon.chaining.influences

import com.cobblemon.mod.common.Cobblemon
import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnAction
import net.minecraft.server.network.ServerPlayerEntity
import us.timinc.mc.cobblemon.chaining.config.ShinyBoostConfig
import kotlin.random.Random.Default.nextInt

class ShinyBooster(override val player: ServerPlayerEntity, override val config: ShinyBoostConfig) :
    Booster(player, config) {
    override fun boost(action: PokemonSpawnAction, species: String, points: Int) {
        if (action.props.shiny != null) return

        val shinyChances = points + 1
        val shinyRate: Int = Cobblemon.config.shinyRate.toInt()

        if (shinyChances <= 1) {
            info("conclusion: player didn't get any shiny boost, deferring")
            return
        }

        val shinyRoll = nextInt(shinyRate)
        val successfulRoll = shinyRoll < shinyChances

        info("${player.name.string} has $points points, $shinyChances shiny chances out of $shinyRate total chances, rolls a $shinyRoll, ${if (successfulRoll) "wins" else "loses"}")
        action.props.shiny = successfulRoll
        info("conclusion: set shiny to $successfulRoll")
    }
}