package com.creativemd.littletiles.common.util.ingredient.rules;

import com.creativemd.creativecore.common.utils.sorting.BlockSelector;
import com.creativemd.creativecore.common.utils.sorting.BlockSelector.BlockSelectorAnd;
import com.creativemd.creativecore.common.utils.sorting.BlockSelector.BlockSelectorBlock;
import com.creativemd.creativecore.common.utils.sorting.BlockSelector.BlockSelectorClass;
import com.creativemd.creativecore.common.utils.sorting.BlockSelector.BlockSelectorProperty;
import com.creativemd.creativecore.common.utils.type.PairList;
import com.creativemd.littletiles.LittleTiles;
import com.creativemd.littletiles.common.block.BlockLTColored;
import com.creativemd.littletiles.common.block.BlockLTTransparentColored;
import com.creativemd.littletiles.common.util.ingredient.rules.BlockIngredientRule.BlockIngredientRuleFixedBlock;
import com.creativemd.littletiles.common.util.ingredient.rules.BlockIngredientRule.BlockIngredientRuleFixedMeta;
import com.creativemd.littletiles.common.util.ingredient.rules.BlockIngredientRule.BlockIngredientRuleMappedState;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class IngredientRules {
	
	private static PairList<BlockSelector, BlockIngredientRule> blockRules = new PairList<>();
	
	public static void registerBlockRule(BlockSelector selector, BlockIngredientRule rule) {
		blockRules.add(selector, rule);
	}
	
	public static PairList<BlockSelector, BlockIngredientRule> getBlockRules() {
		return blockRules;
	}
	
	public static void loadRules() {
		registerBlockRule(new BlockSelectorBlock(LittleTiles.flowingWater), new BlockIngredientRuleFixedBlock(LittleTiles.transparentColoredBlock, BlockLTTransparentColored.EnumType.water.ordinal()));
		registerBlockRule(new BlockSelectorBlock(LittleTiles.whiteFlowingWater), new BlockIngredientRuleFixedBlock(LittleTiles.transparentColoredBlock, BlockLTTransparentColored.EnumType.white_water.ordinal()));
		registerBlockRule(new BlockSelectorBlock(LittleTiles.flowingLava), new BlockIngredientRuleFixedBlock(LittleTiles.coloredBlock, BlockLTColored.EnumType.lava.ordinal()));
		registerBlockRule(new BlockSelectorBlock(LittleTiles.whiteFlowingLava), new BlockIngredientRuleFixedBlock(LittleTiles.coloredBlock, BlockLTColored.EnumType.white_lava.ordinal()));
		
		registerBlockRule(new BlockSelectorBlock(Blocks.LOG), new BlockIngredientRuleMappedState() {
			
			@Override
			public int getMeta(IBlockState state, double value) {
				return state.getValue(BlockOldLog.VARIANT).ordinal();
			}
		});
		registerBlockRule(new BlockSelectorBlock(Blocks.LOG2), new BlockIngredientRuleMappedState() {
			
			@Override
			public int getMeta(IBlockState state, double value) {
				return state.getValue(BlockNewLog.VARIANT).ordinal();
			}
		});
		registerBlockRule(new BlockSelectorAnd(new BlockSelectorClass(BlockHorizontal.class, BlockRotatedPillar.class), new BlockSelectorProperty(BlockHorizontal.FACING, BlockRotatedPillar.AXIS)), new BlockIngredientRuleFixedMeta(0));
	}
}
