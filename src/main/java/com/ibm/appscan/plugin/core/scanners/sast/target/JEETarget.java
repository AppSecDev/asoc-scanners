package com.ibm.appscan.plugin.core.scanners.sast.target;

import java.util.Map;

import com.ibm.appscan.plugin.core.scanners.sast.xml.IModelXMLConstants;

public abstract class JEETarget extends JavaTarget implements IJEETarget {

	@Override
	public Map<String, String> getProperties() {
		Map<String, String> buildInfos = super.getProperties();
		buildInfos.put(IModelXMLConstants.A_JSP_COMPILER, getJSPCompiler());
		return buildInfos;
	}
}
