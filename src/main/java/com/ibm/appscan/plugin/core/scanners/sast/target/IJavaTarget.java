/*
 * (c) Copyright IBM Corp. 2016. 
 * (c) Copyright HCL Technologies Ltd. 2017. 
*/

package com.ibm.appscan.plugin.core.scanners.sast.target;

public interface IJavaTarget extends ISASTTarget {

	/**
	 * Gets the classpath for this target as a String.
	 * @return
	 */
	String getClasspath();
	
	/**
	 * Gets the jre/jdk associated with this target.
	 * @return
	 */
	String getJava();
}
