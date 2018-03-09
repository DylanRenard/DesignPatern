package fr.ensim.dp.chainOfResponsability;

public interface IFilterCache {
	
	public byte[] doAdd(String key, byte[] buff);
	
	public byte[] doRetrieve(String key, byte[] buff);
}
