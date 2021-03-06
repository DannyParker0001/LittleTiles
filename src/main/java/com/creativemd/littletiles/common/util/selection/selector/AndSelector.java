package com.creativemd.littletiles.common.util.selection.selector;

import com.creativemd.littletiles.common.tile.LittleTile;
import com.creativemd.littletiles.common.tile.parent.IParentTileList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class AndSelector extends TileSelector {
	
	public TileSelector[] selectors;
	
	public AndSelector(TileSelector... selectors) {
		this.selectors = selectors;
	}
	
	public AndSelector() {
		
	}
	
	@Override
	protected void saveNBT(NBTTagCompound nbt) {
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < selectors.length; i++) {
			list.appendTag(selectors[i].writeNBT(new NBTTagCompound()));
		}
		nbt.setTag("list", list);
	}
	
	@Override
	protected void loadNBT(NBTTagCompound nbt) {
		NBTTagList list = nbt.getTagList("list", 10);
		selectors = new TileSelector[list.tagCount()];
		for (int i = 0; i < selectors.length; i++) {
			selectors[i] = TileSelector.loadSelector(list.getCompoundTagAt(i));
		}
	}
	
	@Override
	public boolean is(IParentTileList parent, LittleTile tile) {
		for (int i = 0; i < selectors.length; i++) {
			if (!selectors[i].is(parent, tile))
				return false;
		}
		return true;
	}
	
}
