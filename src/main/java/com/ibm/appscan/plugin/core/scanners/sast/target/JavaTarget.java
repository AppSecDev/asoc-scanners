package com.ibm.appscan.plugin.core.scanners.sast.target;

import java.util.HashMap;
import java.util.Map;

import com.ibm.appscan.plugin.core.scanners.sast.xml.IModelXMLConstants;

public abstract class JavaTarget extends DefaultTarget implements IJavaTarget {

	@Override
	public Map<String, String> getProperties() {
		HashMap<String, String> buildInfos = new HashMap<String, String>();
		buildInfos.put(IModelXMLConstants.A_ADDITIONAL_CLASSPATH, getClasspath());
		buildInfos.put(IModelXMLConstants.A_JDK_PATH, getJava());
		return buildInfos;
	}
}
