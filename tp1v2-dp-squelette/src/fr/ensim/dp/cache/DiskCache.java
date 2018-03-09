package fr.ensim.dp.cache;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import fr.ensim.dp.chainOfResponsability.AbstractChainCache;
import fr.ensim.dp.chainOfResponsability.IFilterCache;
import fr.ensim.dp.util.FileUtil;

public class DiskCache implements ICache{
	
	private static HashMap<String,DiskCache> cacheMap = new HashMap<String, DiskCache>();
	
	private File cache;

	public static DiskCache getCache(String key) {
		
		if(!cacheMap.containsKey(key)) {
			synchronized(DiskCache.class) {
				if(!cacheMap.containsKey(key))
					cacheMap.put(key, new DiskCache(key));
			}
		}
		return cacheMap.get(key);
	}
	
	private DiskCache(String key) {
		cache = new File("C:\\Users\\Renard\\Documents\\Cours\\DesignPatern\\Cache\\" + key);
		cache.mkdirs();
	}
	
	@Override
	public long size() {
		return FileUtil.dirLength(cache);
	}

	@Override
	public boolean add(String key, byte[] buf) {
		FileUtil.copy(new ByteArrayInputStream(buf),new File(cache.getAbsolutePath()+"\\"+key));
		return true;
	}

	@Override
	public byte[] retrieve(String key) {
		try {
			return FileUtil.readFile(new File(cache.getAbsolutePath()+"\\"+key));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void clear() {
		FileUtil.deleteDirectory(cache);
		cache.mkdirs();
	}

	@Override
	public boolean add(String key, byte[] buff, AbstractChainCache action) {
		buff = action.add(key, buff);
		return add(key, buff);
	}

	@Override
	public byte[] retrieve(String key, AbstractChainCache action) {
		return action.retrieve(key, retrieve(key));
	}

}
