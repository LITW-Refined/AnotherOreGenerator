package de.pilz.anotheroregenerator.events;

import net.minecraftforge.event.terraingen.OreGenEvent;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import de.pilz.anotheroregenerator.configuration.DefaultOreGenConfig;

public class OreGenEventHandler {

    @SubscribeEvent
    public void onOreGenMinable(OreGenEvent.GenerateMinable event) {
        boolean cancel = false;

        switch (event.type) {
            case CUSTOM:
                if (DefaultOreGenConfig.disableCustomOreGen) cancel = true;
                break;
            case COAL:
                if (DefaultOreGenConfig.disableVanillaOreGenCoal) cancel = true;
                break;
            case DIAMOND:
                if (DefaultOreGenConfig.disableVanillaOreGenDiamond) cancel = true;
                break;
            case GOLD:
                if (DefaultOreGenConfig.disableVanillaOreGenGold) cancel = true;
                break;
            case IRON:
                if (DefaultOreGenConfig.disableVanillaOreGenIron) cancel = true;
                break;
            case LAPIS:
                if (DefaultOreGenConfig.disableVanillaOreGenLapis) cancel = true;
                break;
            case REDSTONE:
                if (DefaultOreGenConfig.disableVanillaOreGenRedstone) cancel = true;
                break;
            case DIRT:
                if (DefaultOreGenConfig.disableVanillaOreGenDirt) cancel = true;
                break;
            case GRAVEL:
                if (DefaultOreGenConfig.disableVanillaOreGenGravel) cancel = true;
                break;
            case QUARTZ:
                if (DefaultOreGenConfig.disableVanillaOreGenQuartz) cancel = true;
                break;
        }

        if (cancel) event.setResult(Result.DENY);
    }
}
