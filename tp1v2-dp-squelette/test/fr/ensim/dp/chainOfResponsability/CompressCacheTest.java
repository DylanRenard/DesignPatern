package fr.ensim.dp.chainOfResponsability;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.ensim.dp.cache.MemoryCache;

public class CompressCacheTest {

	public static IFilterCache compress = new CompressFilterCache();
	final String key = "test";
	final byte[] buff = "buff".getBytes();
	
	@Test
	public void testDoAdd() {
		byte[] buffEncrypt = compress.doAdd(key,buff);
		assertNotEquals(buff,buffEncrypt);
		buffEncrypt = compress.doRetrieve(key, buffEncrypt);
		assertArrayEquals(buff,buffEncrypt);
	}
}
