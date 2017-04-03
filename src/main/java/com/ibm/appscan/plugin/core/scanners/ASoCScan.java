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
import com.ibm.appscan.plugin.core.scan.IScan;
import com.ibm.appscan.plugin.core.scan.IScanServiceProvider;
import com.ibm.appscan.plugin.core.utils.SystemUtil;

public abstract class ASoCScan implements IScan, ScanConstants{
	
	private String m_target;
	private String m_scanId;
	private IProgress m_progress;
	private IScanServiceProvider m_serviceProvider;
	private Map<String, String> m_properties;
	
	public ASoCScan(Map<String, String> properties, IScanServiceProvider provider) {
		this(properties, new DefaultProgress(), provider);
	}
	
	public ASoCScan(Map<String, String> properties, IProgress progress, IScanServiceProvider provider) {
		m_target = properties.remove(CoreConstants.TARGET);
		m_properties = properties;
		m_progress = progress;
		m_serviceProvider = provider;
        m_runAnalysis = properties.get(CoreConstants.RUN_ANALYSIS);
	}

	@Override
	public String getScanId() {
		return m_scanId;
	}

	@Override
	public String getName() {
		return m_properties.get(CoreConstants.SCAN_NAME);
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
		return m_properties.get(CoreConstants.APP_ID);
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
	
	protected Map<String, String> getProperties() {
		if(!m_properties.containsKey(CoreConstants.LOCALE))
			m_properties.put(CoreConstants.LOCALE, SystemUtil.getLocale());
		if(!m_properties.containsKey(CoreConstants.EMAIL_NOTIFICATION))
			m_properties.put(CoreConstants.EMAIL_NOTIFICATION, Boolean.toString(false));
		return m_properties;
	}
    
    protected String getRunAnalysis() {
        return m_runAnalysis;
    }
    
	
	protected abstract String getReportFormat();
}

