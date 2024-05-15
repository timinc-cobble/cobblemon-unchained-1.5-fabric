package us.timinc.mc.cobblemon.chaining.influences

import com.cobblemon.mod.common.api.spawning.detail.PokemonSpawnAction
import com.cobblemon.mod.common.pokemon.IVs
import net.minecraft.server.network.ServerPlayerEntity
import us.timinc.mc.cobblemon.chaining.config.IvBoostConfig

class IvBooster(player: ServerPlayerEntity, config: IvBoostConfig) : Booster(player, config) {
    override fun boost(action: PokemonSpawnAction, species: String, points: Int) {
        info("${player.name.string} wins with $points points, $points perfect IVs")
        if (points <= 0) {
            info("conclusion: player didn't get any perfect IVs")
            return
        }
        if (action.props.ivs == null) action.props.ivs = IVs.createRandomIVs(points)
        info("conclusion: set $points IVs to perfect")
    }
}