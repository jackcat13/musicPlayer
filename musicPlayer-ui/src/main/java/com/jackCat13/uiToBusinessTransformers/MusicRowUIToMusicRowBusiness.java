package com.jackCat13.uiToBusinessTransformers;

import com.jackCat13.UIBeans.MusicRow;
import com.jackCat13.entities.MusicEntry;

public class MusicRowUIToMusicRowBusiness {

	public static MusicEntry transformObject(MusicRow musicRowUI) {
		MusicEntry musicEntryBusiness = new MusicEntry();
		musicEntryBusiness.setMusicName(musicRowUI.getMusicName());
		musicEntryBusiness.setMusicPath(musicRowUI.getMusicPath());
		musicEntryBusiness.setFadeOutDuration(Integer.parseInt(musicRowUI.getMusicFadeOutDuration()));
		return musicEntryBusiness;
	}
}
