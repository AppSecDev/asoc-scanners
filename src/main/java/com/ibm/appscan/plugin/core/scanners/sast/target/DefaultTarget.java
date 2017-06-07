package com.ibm.appscan.plugin.core.scanners.sast.target;

import java.util.HashSet;
import java.util.Set;

public abstract class DefaultTarget implements ISASTTarget {

	Set<String> m_exclusionPatterns;
	Set<String> m_inclusionPatterns;
	
	public DefaultTarget() {
		m_exclusionPatterns = new HashSet<String>();
		m_inclusionPatterns = new HashSet<String>();
	}
	
	@Override
	public String getTarget() {
		return getTargetFile().getAbsolutePath();
	}
	
	@Override
	public Set<String> getExclusionPatterns() {
		return m_exclusionPatterns;
	}

	@Override
	public Set<String> getInclusionPatterns() {
		return m_inclusionPatterns;
	}

	@Override
	public boolean outputsOnly() {
		return false;
	}
}
