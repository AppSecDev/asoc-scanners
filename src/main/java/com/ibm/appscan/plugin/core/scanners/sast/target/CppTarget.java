/*
 * (c) Copyright HCL Technologies Ltd. 2017. 
*/

package com.ibm.appscan.plugin.core.scanners.sast.target;

import java.util.HashMap;
import java.util.Map;

import com.ibm.appscan.plugin.core.scanners.sast.xml.IModelXMLConstants;

public abstract class CppTarget extends DefaultTarget implements ICppTarget {

	@Override
	public Map<String, String> getProperties() {
		HashMap<String, String> buildInfos = new HashMap<String, String>();
		buildInfos.put(IModelXMLConstants.A_ADDITIONAL_CLASSPATH, getCompilerOptions());
		buildInfos.put(IModelXMLConstants.A_MACROS, getMacros());
		buildInfos.put(IModelXMLConstants.A_INCLUDE_PATHS, getIncludeDirs());
		return buildInfos;
	}
}
