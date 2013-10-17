package net.shiroumi.central.Command.ArgsParser;

import java.util.Map.Entry;

public class ArgsEntry<K,V> implements Entry<K,V> {
	private K key;
	private V val;

	public ArgsEntry(K par1Key, V par2Value) {
		key = par1Key;
		val = par2Value;
	}

	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return val;
	}

	@Override
	public V setValue(V value) {
		return val = value;
	}

	@Override
	public String toString() {
		return "ArgsEntry@" + this.hashCode() + ": key = " + key + ", value = " + val;
	}
}