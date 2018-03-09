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

	public static DiskCache getCache(String key) {
		
		if(!cacheMap.containsKey(key)) {
			synchronized(DiskCache.class) {
				if(!cacheMap.containsKey(key))
					cacheMap.put(key, new DiskCache(key));
			}
		}
		return cacheMap.get(key);
	}
	
	private File cache;
	
	private DiskCache(String key) {
		File f = FileUtil.userHome();
		cache = new File(f.getAbsolutePath()+"\\Documents\\DesignPatern\\"+key);
		cache.mkdirs();
		System.out.println(cache.getAbsolutePath());
	}
	
	@Override
	public long size() {
		return FileUtil.dirLength(cache);
	}

	@Override
	public boolean add(String key, byte[] buf) {
		File f = new File(cache.getAbsolutePath()+"\\"+key);
		f.mkdirs();
		while(!FileUtil.copy(new ByteArrayInputStream(buf),f));
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
