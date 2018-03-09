package fr.ensim.dp.chainOfResponsability;

import org.apache.log4j.Logger;

import fr.ensim.dp.util.LoggerUtil;

public class LogFilterCache extends AbstractChainCache {

	final static Logger logger = LoggerUtil.getLogger();

	@Override
	public byte[] doAdd(String key, byte[] buff) {
		logger.debug("On a envoyé le buff : " + buff.toString());
		return buff;
	}

	@Override
	public byte[] doRetrieve(String key,byte[] buff) {
		logger.debug("On a récupéré le buff : " + buff);
		return buff;
	}
}
