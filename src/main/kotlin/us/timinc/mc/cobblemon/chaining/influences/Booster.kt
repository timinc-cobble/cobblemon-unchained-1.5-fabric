package us.timinc.mc.cobblemon.chaining.influences

import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnAction
import com.cobblemon.mod.common.api.spawning.detail.SpawnAction
import com.cobblemon.mod.common.api.spawning.influence.SpawningInfluence
import net.minecraft.server.network.ServerPlayerEntity
import us.timinc.mc.cobblemon.chaining.config.BoostConfig
import us.timinc.mc.cobblemon.chaining.util.Util

abstract class Booster(open val player: ServerPlayerEntity, open val config: BoostConfig) : SpawningInfluence {
    override fun affectAction(action: SpawnAction<*>) {
        if (action !is PokemonSpawnAction) return
        val species = action.detail.pokemon.species?: return
        info("$species spawning at ${action.ctx.position.toShortString()}")

        if (!Util.matchesList(action.detail.pokemon, config.whitelist, config.blacklist)) {
            info("$species is blocked by the blacklist/whitelist")
            return
        }

        val points = config.getPointsFromThreshold(player, species)

        boost(action, species, points)
    }

    abstract fun boost(action: PokemonSpawnAction, species: String, points: Int)

    fun info(msg: String) {
        if (config.debug) {
            println(msg)
        }
    }
}