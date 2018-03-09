package fr.ensim.dp.chainOfResponsability;

import java.io.IOException;

import org.apache.log4j.Logger;

import fr.ensim.dp.cache.MemoryCache;
import fr.ensim.dp.util.GzipUtil;
import fr.ensim.dp.util.LoggerUtil;

public class CompressFilterCache extends AbstractChainCache {
	
	final static Logger logger = LoggerUtil.getLogger();

	@Override
	public byte[] doAdd(String key, byte[] buff) {
		logger.debug("doAdd compress buff ="+buff);
		try {
			return GzipUtil.compress(buff);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public byte[] doRetrieve(String key,byte[] buff) {
		logger.debug("doRetrieve compress");
		try {
			return GzipUtil.uncompress(buff);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
