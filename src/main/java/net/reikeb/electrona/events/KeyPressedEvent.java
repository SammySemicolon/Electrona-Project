package net.reikeb.electrona.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.world.IWorld;

import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.reikeb.electrona.Electrona;
import net.reikeb.electrona.init.ItemInit;

@Mod.EventBusSubscriber(modid = Electrona.MODID)
public class KeyPressedEvent {

    @SubscribeEvent
    public static void onPlayerPressesKey(InputEvent.KeyInputEvent event) {
        if ((Minecraft.getInstance().screen != null) && (Minecraft.getInstance().screen instanceof ChatScreen)) return;
        if (event.getKey() == Minecraft.getInstance().options.keyJump.getKey().getValue()) {
            Entity entity = Minecraft.getInstance().player;
            IWorld world = Minecraft.getInstance().level;
            double x = entity.getX();
            double y = entity.getY();
            double z = entity.getZ();
            Direction direction = entity.getDirection();
            ItemStack itemstack = ((LivingEntity) entity).getItemBySlot(EquipmentSlotType.byTypeAndIndex(EquipmentSlotType.Group.ARMOR, 2));
            if ((itemstack.getItem() == ItemInit.MECHANIC_WINGS.get().asItem()) && (itemstack.getOrCreateTag().getDouble("ElectronicPower") >= 0.3)) {
                entity.setDeltaMovement((entity.getDeltaMovement().x()), 0.3, (entity.getDeltaMovement().z()));
                for (int i = 0; i < 9; i++) {
                    world.addParticle(ParticleTypes.CLOUD, x, y, z, 0, -0.1, 0);
                }
                itemstack.getOrCreateTag().putDouble("ElectronicPower", (itemstack.getOrCreateTag().getDouble("ElectronicPower")) - 0.3);
            }
        }
    }
}
