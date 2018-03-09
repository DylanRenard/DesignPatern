package fr.ensim.dp.chainOfResponsability;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import fr.ensim.dp.cache.DiskCache;
import fr.ensim.dp.cache.MemoryCache;

public class ChainTest {

	@Test
	public void test() {
		LogFilterCache log = new LogFilterCache();
		CountFilterCache count = new CountFilterCache();
		CompressFilterCache compress = new CompressFilterCache();
		EncryptFilterCache encrypt = new EncryptFilterCache();

		log.setNext(count);
		count.setNext(compress);
		compress.setNext(encrypt);

		final String key = "test";
		final byte[] buff = "buff".getBytes();

		MemoryCache cache = MemoryCache.INSTANCE;

		cache.add(key, buff, log);

		byte[] buffRep = cache.retrieve(key, encrypt);

		assertArrayEquals(buff, buffRep);
	}
}
