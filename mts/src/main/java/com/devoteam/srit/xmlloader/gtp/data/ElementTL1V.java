package com.devoteam.srit.xmlloader.gtp.data;

import gp.utils.arrays.Array;
import gp.utils.arrays.Integer08Array;
import gp.utils.arrays.SupArray;

import com.devoteam.srit.xmlloader.core.coding.binary.Dictionary;
import com.devoteam.srit.xmlloader.core.coding.binary.ElementAbstract;

/**
 * 
 * @author jdufour
 * This class has been created to handle particular 141 IE as its length field is encoded in one byte.
 */

public class ElementTL1V extends ElementAbstract {

	public ElementTL1V()
	{
		
	}
	
	@Override
    public int decodeFromArray(Array array, Dictionary dictionary) throws Exception
    {
    	this.tag = new Integer08Array(array.subArray(0, 1)).getValue();
    	int length = new Integer08Array(array.subArray(1, 1)).getValue();
    	this.fieldsArray = new SupArray();
        this.fieldsArray.addFirst(array.subArray(2, length));
        return length + 2;
    }

	@Override
    public SupArray encodeToArray() 
	{
        SupArray sup = new SupArray();
        Integer08Array idArray = new Integer08Array(this.tag);
        sup.addLast(idArray);
       	Integer08Array lengthArray = new Integer08Array(this.fieldsArray.length);
       	sup.addLast(lengthArray);

	    sup.addLast(this.fieldsArray);
	    
        return sup;
    }

}
