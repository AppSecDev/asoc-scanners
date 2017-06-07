/*
 * (c) Copyright HCL Technologies Ltd. 2017. 
*/

package com.ibm.appscan.plugin.core.scanners.sast.target;

public interface ICppTarget extends ISASTTarget {

	/**
	 * Gets the compiler options used to build the target.
	 * @return
	 */
	String getCompilerOptions();
	
	/**
	 * Gets the macros defined for the target.
	 * @return
	 */
	String getMacros();
	
	/**
	 * Gets the include directories for the target.
	 * @return
	 */
	String getIncludeDirs();
}
