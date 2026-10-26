package xpmultiplier;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerXpEvent;

@Mod(XpMultiplier.MODID)
public class XpMultiplier {
    public static final String MODID = "xpmultiplier";

    /** Во сколько раз увеличивается весь получаемый опыт. */
    public static final int MULTIPLIER = 7;

    public XpMultiplier(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.addListener(XpMultiplier::onXpChange);
    }

    private static void onXpChange(PlayerXpEvent.XpChange event) {
        int amount = event.getAmount();
        if (amount > 0) { // отрицательные значения (списание опыта) не трогаем
            long boosted = (long) amount * MULTIPLIER;
            event.setAmount((int) Math.min(boosted, Integer.MAX_VALUE));
        }
    }
}
