package fr.ensim.dp.chainOfResponsability;

public abstract class AbstractChainCache implements IFilterCache{
	
	private AbstractChainCache prev=null;
	
	private AbstractChainCache next=null;
	
	protected AbstractChainCache setPrev(AbstractChainCache acc) {
		prev = acc;
		return this;
	}
	
	protected AbstractChainCache setNext(AbstractChainCache acc) {
		next = acc;
		acc.setPrev(this);
		return this;
	}
	
	public byte[] add(String key,byte[] buff) {
		buff = doAdd(key,buff);
		if(next!=null) {
			buff = next.add(key,buff);
		}
		return buff;
	}
	
	public byte[] retrieve(String key, byte[] buff) {
		buff = doRetrieve(key,buff);
		if(prev!=null) {
			buff = prev.retrieve(key,buff);
		}
		return buff;
	}
}
