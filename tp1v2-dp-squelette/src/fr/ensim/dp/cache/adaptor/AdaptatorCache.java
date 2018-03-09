package fr.ensim.dp.cache.adaptor;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import javax.imageio.ImageIO;
import org.jdesktop.swingx.mapviewer.TileCache;
import fr.ensim.dp.cache.ICache;


public class AdaptatorCache extends TileCache {

	ICache cache;

	public AdaptatorCache(ICache cache) {
		this.cache = cache;
	}

	@Override
	public BufferedImage get(URI arg0) throws IOException {
		byte[] buf = cache.retrieve(arg0.getPath());
		if (buf != null) {
			InputStream in = new ByteArrayInputStream(cache.retrieve(arg0.getPath()));
			return ImageIO.read(in);
		}
		return null;
		
	}

	@Override
	public void needMoreMemory() {
		cache.clear();
	}

	@Override
	public void put(URI arg0, byte[] arg1, BufferedImage arg2) {
		cache.add(arg0.getPath(),arg1);
	}

}
