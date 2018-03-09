package fr.ensim.dp.cache;

import java.util.HashMap;

import fr.ensim.dp.chainOfResponsability.AbstractChainCache;
import fr.ensim.dp.chainOfResponsability.IFilterCache;

public class MemoryCache implements ICache{

	public static MemoryCache INSTANCE = new MemoryCache();
	private MemoryCache() {
		cache = new HashMap<String,byte[]>();
	}
	
	private HashMap<String,byte[]> cache;
	private long size = 0;
	
	@Override
	public long size() {
		return size;
	}

	@Override
	public boolean add(String key, byte[] buf) {
		cache.put(key, buf);
		size += buf.length;
		return true;
	}

	@Override
	public byte[] retrieve(String key) {
		return (byte[]) cache.get(key);
	}

	@Override
	public void clear() {
		cache.clear();
		size = 0;
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
