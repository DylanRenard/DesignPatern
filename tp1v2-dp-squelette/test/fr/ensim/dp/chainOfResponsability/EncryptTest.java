package fr.ensim.dp.chainOfResponsability;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.ensim.dp.cache.MemoryCache;

public class EncryptTest {

	public static IFilterCache encrypt = new EncryptFilterCache();
	final String key = "test";
	final byte[] buff = "buff".getBytes();
	
	@Test
	public void testDoAdd() {
		byte[] buffEncrypt = encrypt.doAdd(key,buff);
		assertNotEquals(buff,buffEncrypt);
		buffEncrypt = encrypt.doRetrieve(key, buffEncrypt);
		assertArrayEquals(buff,buffEncrypt);
	}
}
