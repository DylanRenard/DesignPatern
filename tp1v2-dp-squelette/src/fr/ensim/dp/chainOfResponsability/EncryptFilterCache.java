package fr.ensim.dp.chainOfResponsability;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import fr.ensim.dp.cache.MemoryCache;
import fr.ensim.dp.util.LoggerUtil;

public class EncryptFilterCache extends AbstractChainCache {
	
	final static Logger logger = LoggerUtil.getLogger();

	private static final String CRYPT_ALGO = "AES";
	private static final String KEY_ALGO = "AES";
	private static final byte[] secret = "1gl8er09lDN2f35A".getBytes();
	
	@Override
	public byte[] doAdd(String key, byte[] buff) {
		logger.debug("doAdd encrypt buff ="+buff);
		try {
			Cipher cipher = Cipher.getInstance(CRYPT_ALGO);
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(secret,KEY_ALGO));
			buff = cipher.doFinal(buff);
			logger.debug("doAdd encrypt buff ="+buff);
			return buff;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public byte[] doRetrieve(String key, byte[] buff) {
		logger.debug("doRetrieve encrypt buff="+buff);
		try {
			Cipher cipher = Cipher.getInstance(CRYPT_ALGO);
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(secret,KEY_ALGO));
			buff = cipher.doFinal(buff);
			logger.debug("doRetrieve encrypt buff ="+buff);
			return buff;
		}	 catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
