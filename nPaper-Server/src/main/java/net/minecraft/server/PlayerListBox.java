package net.minecraft.server;

import javax.swing.JList;
import java.util.ArrayList;
import java.util.List;

public class PlayerListBox extends JList<String> implements IUpdatePlayerListBox {
    private static final byte UPDATE_INTERVAL = 20; // Rinny - use byte instead of int
    private final MinecraftServer minecraftServer;
    private int tickCount;

    public PlayerListBox(MinecraftServer minecraftServer) {
        this.minecraftServer = minecraftServer;
        minecraftServer.a(this);
    }

    public void resize() {
        if (tickCount++ % UPDATE_INTERVAL == 0) {
            List<String> playerNames = new ArrayList<>();
            for (EntityPlayer player : minecraftServer.getPlayerList().players) {
                playerNames.add(player.getName());
            }
            setListData(playerNames.toArray(new String[0]));
        }
    }
}
