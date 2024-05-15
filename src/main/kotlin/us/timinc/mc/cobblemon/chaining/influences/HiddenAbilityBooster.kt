package us.timinc.mc.cobblemon.chaining.influences

import com.cobblemon.mod.common.api.Priority
import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnAction
import net.minecraft.server.network.ServerPlayerEntity
import us.timinc.mc.cobblemon.chaining.config.HiddenBoostConfig
import kotlin.random.Random.Default.nextInt

class HiddenAbilityBooster(override val player: ServerPlayerEntity, override val config: HiddenBoostConfig) :
    Booster(player, config) {
    override fun boost(action: PokemonSpawnAction, species: String, points: Int) {
        val totalMarbles = config.marbles
        val speciesData = action.props.species?.let { PokemonSpecies.getByName(it) } ?: return
        val ability = speciesData.abilities.mapping[Priority.LOW]?.first()?.template?.name
        println("$species : $ability")

        if (ability == null) {
            info("conclusion: species doesn't have hidden ability")
            return
        }

        if (points == 0) {
            info("conclusion: winning player didn't get any hidden ability chance")
            return
        }

        val hiddenAbilityRoll = nextInt(0, totalMarbles)
        val successfulRoll = hiddenAbilityRoll < points

        info(
            "${player.name.string} has $points points, has a $points out of ${totalMarbles}, rolls a $hiddenAbilityRoll, ${if (successfulRoll) "wins" else "loses"}"
        )

        if (!successfulRoll) {
            info("conclusion: did not give $species its hidden ability")
            return
        }

        action.props.ability = ability
        info("conclusion: gave $species its hidden ability")
    }
}