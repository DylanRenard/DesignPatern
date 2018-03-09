package fr.ensim.dp.cache;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiskCacheTest {

	DiskCache cache;
	byte[] buf;
	String key;
	
	@Before
	public void setup() {
	cache = DiskCache.getCache("test");
	buf = "test".getBytes();
	key = "test";
	}
	
	@After
	public void clean() {
		cache.clear();
	}
	
	@Test
	public void testSize() {
		cache.add(key, buf);
		assertEquals(buf.length,cache.size());
	}

	@Test
	public void testAdd() {
		assertEquals(true,cache.add(key, buf));
	}

	@Test
	public void testRetreive() {
		cache.add(key, buf);
		assertArrayEquals(buf,cache.retrieve(key));
	}

	@Test
	public void testClear() {
		cache.clear();
		cache.size();
	}

}
