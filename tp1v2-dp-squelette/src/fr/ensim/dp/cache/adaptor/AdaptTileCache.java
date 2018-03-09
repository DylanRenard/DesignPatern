package fr.ensim.dp.cache.adaptor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.imageio.ImageIO;

import org.jdesktop.swingx.mapviewer.TileCache;

import fr.ensim.dp.cache.MemoryCache;

public class AdaptTileCache extends TileCache {
	
	public static AdaptTileCache INSTANCE = new AdaptTileCache();
	
	private AdaptTileCache() {
		super();
	}
	
	@Override
	public BufferedImage get(URI arg0) throws IOException {
		InputStream in = new ByteArrayInputStream(MemoryCache.INSTANCE.retrieve(arg0.getPath()));
		
		return ImageIO.read(in);
	}

	@Override
	public void needMoreMemory() {
		MemoryCache.INSTANCE.clear();
	}

	@Override
	public void put(URI arg0, byte[] arg1, BufferedImage arg2) {
		MemoryCache.INSTANCE.add(arg0.getPath(), arg1);
	}
	
}
