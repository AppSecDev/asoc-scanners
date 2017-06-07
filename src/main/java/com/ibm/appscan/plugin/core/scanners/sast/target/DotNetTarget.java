/*
 * (c) Copyright HCL Technologies Ltd. 2017. 
*/

package com.ibm.appscan.plugin.core.scanners.sast.target;

import java.util.HashMap;
import java.util.Map;

import com.ibm.appscan.plugin.core.scanners.sast.xml.IModelXMLConstants;

public abstract class DotNetTarget extends DefaultTarget implements IDotNetTarget {

	@Override
	public Map<String, String> getProperties() {
		HashMap<String, String> buildInfos = new HashMap<String, String>();
		buildInfos.put(IModelXMLConstants.A_REFERENCES, getReferences());
		buildInfos.put(IModelXMLConstants.A_FRAMEWORK, getFrameworkVersion());
		return buildInfos;
	}
}
