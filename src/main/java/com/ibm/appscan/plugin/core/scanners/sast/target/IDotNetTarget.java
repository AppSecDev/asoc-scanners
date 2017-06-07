/*
 * (c) Copyright IBM Corp. 2016. 
 * (c) Copyright HCL Technologies Ltd. 2017. 
*/

package com.ibm.appscan.plugin.core.scanners.sast.target;

public interface IDotNetTarget extends ISASTTarget {

	/**
	 * Gets the dependencies of this target as a string.
	 * @return
	 */
	String getReferences();

	/**
	 * Gets the targeted framework version of this target.
	 * @return
	 */
	String getFrameworkVersion();
}
