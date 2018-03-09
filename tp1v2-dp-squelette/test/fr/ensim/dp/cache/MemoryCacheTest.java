package fr.ensim.dp.cache;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MemoryCacheTest {

	MemoryCache cache = MemoryCache.INSTANCE;
	byte[] buf = "test".getBytes();
	String key = "test";
	
	@Before
	public void setup() {
		cache.clear();
	}
	
	@Test
	public void testSize() {
		cache.add(key,buf);
		assertEquals(buf.length,cache.size());
	}

	@Test
	public void testAdd() {
		assertEquals(true,cache.add(key,buf));
	}

	@Test
	public void testRetreive() {
		cache.add(key,buf);
		assertEquals(buf,cache.retrieve(key));
	}

	@Test
	public void testClear() {
		cache.add(key,buf);
		cache.clear();
		assertEquals(0,cache.size());
	}

}
