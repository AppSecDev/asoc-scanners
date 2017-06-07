/*
 * (c) Copyright IBM Corp. 2016. 
 * (c) Copyright HCL Technologies Ltd. 2017. 
*/

package com.ibm.appscan.plugin.core.scanners.sast.target;

public interface IJEETarget extends IJavaTarget {

	/**
	 * Gets the jsp compiler that should be used for this target.
	 * @return
	 */
	String getJSPCompiler();
	
}
