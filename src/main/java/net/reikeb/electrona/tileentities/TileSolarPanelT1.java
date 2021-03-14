package net.reikeb.electrona.tileentities;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tags.BlockTags;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.Locale;

import static net.reikeb.electrona.setup.RegistryHandler.TILE_SOLAR_PANEL_T_1;

public class TileSolarPanelT1 extends TileEntity implements ITickableTileEntity {

    private int ElectronicPower;
    private int MaxStorage;

    public TileSolarPanelT1() {
        super(TILE_SOLAR_PANEL_T_1.get());
    }

    @Override
    public void tick() {
        // We get the variables
        World world = this.level;
        int x = this.worldPosition.getX();
        int y = this.worldPosition.getY();
        int z = this.worldPosition.getZ();
        BlockPos blockPos = this.getBlockPos();

        // We get the NBT Tags
        this.getTileData().putDouble("MaxStorage", 1000);
        double electronicPower = this.getTileData().getDouble("ElectronicPower");

        // We generate the energy (this part is uncommon for all generators)
        if ((world.canSeeSkyFromBelowWater(new BlockPos(x, y + 1, z)))
                && (world.isDay())) {
            if (electronicPower < 996) {
                if ((world.getLevelData().isRaining() || world.getLevelData().isThundering())) {
                    this.getTileData().putDouble("ElectronicPower", (electronicPower + 0.1));
                } else {
                    this.getTileData().putDouble("ElectronicPower", (electronicPower + 0.2));
                }
            } else if (electronicPower >= 996 && electronicPower < 1000) {
                this.getTileData().putDouble("ElectronicPower", (electronicPower + 0.05));
            }
        } else {
            if (electronicPower > 4) {
                this.getTileData().putDouble("ElectronicPower", (electronicPower - 0.2));
            } else if (electronicPower > 0 && electronicPower <= 4) {
                this.getTileData().putDouble("ElectronicPower", (electronicPower - 0.05));
            }
        }

        // We pass energy to blocks around (this part is common to all generators)
        for (Direction dir : Direction.values()) {
            TileEntity tileEntity = world.getBlockEntity(blockPos.relative(dir));
            Block offsetBlock = world.getBlockState(blockPos.relative(dir)).getBlock();
            double offsetElectronicPower = tileEntity.getTileData().getDouble("ElectronicPower");
            if ((BlockTags.getAllTags().getTagOrEmpty(new ResourceLocation(("forge:electrona/machines_all").toLowerCase(Locale.ENGLISH)))
                    .contains(offsetBlock)) || ((BlockTags.getAllTags().getTagOrEmpty(new ResourceLocation(("forge:electrona/cable")
                    .toLowerCase(Locale.ENGLISH))).contains(offsetBlock)))) {
                if ((tileEntity.getTileData().getDouble("ElectronicPower") < (tileEntity.getTileData()
                .getDouble("ElectronicPower") - 4)) && (electronicPower > 4)) {
                    this.getTileData().putDouble("ElectronicPower", (electronicPower - 4));
                    tileEntity.getTileData().putDouble("ElectronicPower", (offsetElectronicPower + 4));
                }
            }
        }
    }

    @Override
    public void load(BlockState blockState, CompoundNBT compound) {
        super.load(blockState, compound);
        ElectronicPower = compound.getInt("ElectronicPower");
        MaxStorage = compound.getInt("MaxStorage");
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound = super.save(compound);
        compound.putInt("ElectronicPower", ElectronicPower);
        compound.putInt("MaxStorage", MaxStorage);
        return compound;
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 0, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        this.load(this.getBlockState(), pkt.getTag());
    }
}
