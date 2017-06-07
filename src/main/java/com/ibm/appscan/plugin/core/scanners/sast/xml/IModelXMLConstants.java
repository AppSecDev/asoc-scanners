/*
 * (c) Copyright IBM Corp. 2016. 
 * (c) Copyright HCL Technologies Ltd. 2017. 
*/

package com.ibm.appscan.plugin.core.scanners.sast.xml;

public interface IModelXMLConstants {

	String E_CONFIGURATION			= "Configuration";			//$NON-NLS-1$
	String E_TARGETS				= "Targets";				//$NON-NLS-1$
	String E_TARGET					= "Target";					//$NON-NLS-1$
	String E_TARGET_SETTINGS		= "TargetSettings";			//$NON-NLS-1$
	String E_CUSTOM_BUILD_INFO		= "CustomBuildInfo";		//$NON-NLS-1$
	String E_INCLUDE				= "Include";				//$NON-NLS-1$
	String E_EXCLUDE				= "Exclude";				//$NON-NLS-1$

	//Java
	String A_PATH					= "path";					//$NON-NLS-1$
	String A_SRC_PATH				= "src_path";				//$NON-NLS-1$
	String A_SRC_ROOT				= "src_root";				//$NON-NLS-1$
	String A_JDK_PATH				= "jdk_path";				//$NON-NLS-1$
	String A_JSP_COMPILER			= "jsp_compiler";			//$NON-NLS-1$
	String A_ADDITIONAL_CLASSPATH	= "additional_classpath";	//$NON-NLS-1$
	String A_OUTPUTS_ONLY			= "outputs-only";			//$NON-NLS-1$
	
	//C++
	String A_COMPILER_OPTS			= "compiler_opts";
	String A_MACROS					= "macros";
	String A_INCLUDE_PATHS			= "include_paths";
	String A_BUILD_CONFIG			= "build_configuration";
	
	String APPSCAN_CONFIG			= "appscan-config";			//$NON-NLS-1$
	String DOT_XML					= ".xml";					//$NON-NLS-1$
	
	//.NET
	String A_REFERENCES 			= "references";				//$NON-NLS-1$
	String A_FRAMEWORK 				= "framework_version";		//$NON-NLS-1$
}
