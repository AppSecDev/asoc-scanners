/**
 * © Copyright IBM Corporation 2016.
 * LICENSE: Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 */

package com.ibm.appscan.plugin.core.scanners;

import java.util.Map;

import com.ibm.appscan.plugin.core.CoreConstants;
import com.ibm.appscan.plugin.core.logging.DefaultProgress;
import com.ibm.appscan.plugin.core.logging.IProgress;
import com.ibm.appscan.plugin.core.results.CloudResultsProvider;
import com.ibm.appscan.plugin.core.results.IResultsProvider;
import com.ibm.appscan.plugin.core.scan.IScanServiceProvider;
import com.ibm.appscan.plugin.core.scan.IScan;

public abstract class ASoCScan implements IScan, ScanConstants{
	
	private String m_target;
	private String m_appId;
	private String m_name;
	private String m_scanId;
	private IProgress m_progress;
	private IScanServiceProvider m_serviceProvider;
	
	public ASoCScan(Map<String, String> properties, IScanServiceProvider provider) {
		this(properties, new DefaultProgress(), provider);
	}
	
	public ASoCScan(Map<String, String> properties, IProgress progress, IScanServiceProvider provider) {
		m_target = properties.get(CoreConstants.TARGET);
		m_appId = properties.get(CoreConstants.ID) == null ? DEFAULT_APP_ID : properties.get(CoreConstants.ID);
		m_name = properties.get(CoreConstants.NAME) == null ? getType() : properties.get(CoreConstants.NAME);
		m_progress = progress;
		m_serviceProvider = provider;
	}

	@Override
	public String getScanId() {
		return m_scanId;
	}

	@Override
	public String getName() {
		return m_name;
	}
	
	@Override
	public IResultsProvider getResultsProvider() {
		CloudResultsProvider provider = new CloudResultsProvider(m_scanId, getType(), m_serviceProvider, m_progress);
		provider.setReportFormat(getReportFormat());
		return provider;
	}

	protected void setScanId(String id) {
		m_scanId = id;
	}
	
	protected String getAppId() {
		return m_appId;
	}
	
	protected String getTarget() {
		return m_target;
	}
	
	protected IProgress getProgress() {
		return m_progress;
	}
	
	protected IScanServiceProvider getServiceProvider() {
		return m_serviceProvider;
	}
	
	protected abstract String getReportFormat();
}
