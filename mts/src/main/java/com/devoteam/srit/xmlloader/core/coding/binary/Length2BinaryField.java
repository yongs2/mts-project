/* 
 * Copyright 2012 Devoteam http://www.devoteam.com
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 
 * 
 * This file is part of Multi-Protocol Test Suite (MTS).
 * 
 * Multi-Protocol Test Suite (MTS) is free software: you can redistribute
 * it and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, either version 3 of the
 * License.
 * 
 * Multi-Protocol Test Suite (MTS) is distributed in the hope that it will
 * be useful, but WITHOUT ANY WARRANTY; without even the implied warranty 
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Multi-Protocol Test Suite (MTS).
 * If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package com.devoteam.srit.xmlloader.core.coding.binary;

import com.devoteam.srit.xmlloader.core.exception.ExecutionException;

import gp.utils.arrays.Array;
import gp.utils.arrays.Integer16Array;
import gp.utils.arrays.SupArray;

import org.dom4j.Element;


/**
 *
 * @author indiaye
 */
public class Length2BinaryField extends FieldAbstract
{
	
	public Length2BinaryField()
    {
    }
	
    public Length2BinaryField(Element rootXML) throws Exception 
    {
        super(rootXML);
        if (this.length % 8 != 0) {
            throw new ExecutionException("Wrong length for binary field : \"" + this.name + "\"");
        }
    }

    @Override
    public void setValue(String value, int offset, SupArray array) 
    {
    	this.offset = offset;
		array.addLast(new Integer16Array(value.length() / 2));	// FF00 는 4 bytes 이지만 0xFF 0x00 로 바뀌게 되면 절반으로 변경
        Array valueArray = Array.fromHexString(value);	
        super.setValueFromArray( valueArray, offset, array);
    }

    @Override
    public String getValue(Array array) 
    {
		int length = array.getBits(this.offset, 16);
    	Array arrayValue = array.subArray(this.offset / 8 + 2, length);
    	return Array.toHexString(arrayValue);
    }
    
    @Override
    public FieldAbstract clone()
    {
    	Length2BinaryField newField = new Length2BinaryField(); 
    	newField.copyToClone(this);
    	return newField;
    }
}
