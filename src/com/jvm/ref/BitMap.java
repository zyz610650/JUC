package com.jvm.ref;

import java.util.BitSet;

/**
 * @author: @zyz
 */
public class BitMap {
	public static void main (String[] args) {
		BitSet bitSet=new BitSet ();
		BitSet bitSet1=new BitSet ();
		bitSet.set (0);
		bitSet.set (4);
		bitSet1.set (9);
		bitSet1.set (0);
		bitSet.set (56);
		System.out.println (bitSet.get (0,2));
		bitSet.or (bitSet1);
		System.out.println (bitSet);
		System.out.println (bitSet.get (56));
	}
}
