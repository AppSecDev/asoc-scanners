/*
 * (c) Copyright IBM Corp. 2016. 
 * (c) Copyright HCL Technologies Ltd. 2017. 
*/

package com.ibm.appscan.plugin.core.scanners.sast.target;


import java.io.File;
import java.util.Map;
import java.util.Set;

import  com.ibm.appscan.plugin.core.scan.ITarget;

public interface ISASTTarget extends ITarget{
	
	/**
	 * Gets the target file.
	 * @return The target file.
	 */
	File getTargetFile();
	
	/**
	 * Gets a map of properties associated with the target.
	 * @return The Map of properties.
	 */
	Map<String, String> getProperties();
	
	/**
	 * Gets a list of exclusion patterns for this target.
	 * @return A list of patterns to exclude.
	 */
	Set<String> getExclusionPatterns();
	
	/**
	 * Gets a list of inclusion patterns for this target.
	 * @return A list of patterns to include.
	 */
	Set<String> getInclusionPatterns();
	
	/**
	 * Whether this target is only associated with build output files (e.g. .jar, .dll, etc.)
	 * @return false if this target includes non-build outputs.
	 */
	boolean outputsOnly();
}
