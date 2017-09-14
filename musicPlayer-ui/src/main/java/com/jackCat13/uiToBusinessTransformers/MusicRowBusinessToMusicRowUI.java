package com.jackCat13.uiToBusinessTransformers;

import java.util.ArrayList;
import java.util.List;

import com.jackCat13.UIBeans.MusicRow;
import com.jackCat13.entities.MusicEntry;

public class MusicRowBusinessToMusicRowUI {

	public static MusicRow transformObject(MusicEntry musicRowBusiness) {
		MusicRow musicRowUI = new MusicRow(musicRowBusiness.getMusicName(), musicRowBusiness.getMusicPath(),
				musicRowBusiness.getFadeOutDuration() + "");
		return musicRowUI;
	}
	
	public static List<MusicRow> tranformObjectList(List<MusicEntry> musicRowBusinessList){
		List<MusicRow> musicRowUIList = new ArrayList<MusicRow>();
		for (MusicEntry musicEntry : musicRowBusinessList) {
			musicRowUIList.add(transformObject(musicEntry));
		}
		return musicRowUIList;
	}
}
