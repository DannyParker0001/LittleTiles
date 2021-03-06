package com.creativemd.littletiles.common.structure.type.premade.signal;

import java.util.ArrayList;
import java.util.List;

import com.creativemd.creativecore.client.rendering.RenderBox;
import com.creativemd.littletiles.LittleTiles;
import com.creativemd.littletiles.common.structure.LittleStructure;
import com.creativemd.littletiles.common.structure.registry.LittleStructureType;
import com.creativemd.littletiles.common.structure.signal.component.SignalComponentType;
import com.creativemd.littletiles.common.structure.signal.network.ISignalStructureTransmitter;
import com.creativemd.littletiles.common.tile.parent.IStructureTileList;
import com.creativemd.littletiles.common.tile.preview.LittlePreviews;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LittleSignalCable extends LittleSignalCableBase implements ISignalStructureTransmitter {
	
	public LittleSignalCable(LittleStructureType type, IStructureTileList mainBlock) {
		super(type, mainBlock);
	}
	
	@Override
	public boolean canConnect(EnumFacing facing) {
		return true;
	}
	
	@Override
	public int getIndex(EnumFacing facing) {
		return facing.ordinal();
	}
	
	@Override
	public EnumFacing getFacing(int index) {
		return EnumFacing.VALUES[index];
	}
	
	public static class LittleStructureTypeCable extends LittleStructureTypeNetwork {
		
		@SideOnly(Side.CLIENT)
		public List<RenderBox> cubes;
		
		public LittleStructureTypeCable(String id, String category, Class<? extends LittleStructure> structureClass, int attribute, String modid, int bandwidth) {
			super(id, category, structureClass, attribute, modid, bandwidth, 6);
		}
		
		@Override
		@SideOnly(Side.CLIENT)
		public List<RenderBox> getRenderingCubes(LittlePreviews previews) {
			if (cubes == null) {
				float size = (float) ((Math.sqrt(bandwidth) * 1F / 32F) * 1.4);
				cubes = new ArrayList<>();
				cubes.add(new RenderBox(0, 0.5F - size, 0.5F - size, size * 2, 0.5F + size, 0.5F + size, LittleTiles.coloredBlock).setColor(-13619152));
				cubes.add(new RenderBox(0 + size * 2, 0.5F - size * 0.8F, 0.5F - size * 0.8F, 1 - size * 2, 0.5F + size * 0.8F, 0.5F + size * 0.8F, LittleTiles.singleCable).setColor(-13619152).setKeepUV(true));
				cubes.add(new RenderBox(1 - size * 2, 0.5F - size, 0.5F - size, 1, 0.5F + size, 0.5F + size, LittleTiles.coloredBlock).setColor(-13619152));
			}
			return cubes;
		}
		
		@Override
		public int getBandwidth() {
			return bandwidth;
		}
		
		@Override
		public void changed() {
			
		}
		
		@Override
		public boolean[] getState() {
			return null;
		}
		
		@Override
		public SignalComponentType getType() {
			return SignalComponentType.TRANSMITTER;
		}
		
	}
	
}
