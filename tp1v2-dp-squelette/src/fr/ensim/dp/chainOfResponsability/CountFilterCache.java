package fr.ensim.dp.chainOfResponsability;

import org.apache.log4j.Logger;

import fr.ensim.dp.util.LoggerUtil;

public class CountFilterCache extends AbstractChainCache {
	
	final static Logger logger = LoggerUtil.getLogger();

	private static int numberAdd = 0;
	private static int numberRetrieve = 0;
	
	@Override
	public byte[] doAdd(String key, byte[] buff) {
		logger.debug("doAdd count buff ="+buff);
		numberAdd++;
		return buff;
	}

	@Override
	public byte[] doRetrieve(String key,byte[] buff) {
		logger.debug("doRetrieve count");
		numberRetrieve++;
		return buff;
	}
}
