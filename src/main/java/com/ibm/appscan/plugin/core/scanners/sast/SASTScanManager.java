/*
 * (c) Copyright IBM Corp. 2016. 
 * (c) Copyright HCL Technologies Ltd. 2017. 
*/

package com.ibm.appscan.plugin.core.scanners.sast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.transform.TransformerException;

import com.ibm.appscan.plugin.core.CoreConstants;
import com.ibm.appscan.plugin.core.error.AppScanException;
import com.ibm.appscan.plugin.core.error.InvalidTargetException;
import com.ibm.appscan.plugin.core.error.ScannerException;
import com.ibm.appscan.plugin.core.logging.IProgress;
import com.ibm.appscan.plugin.core.scan.IScanManager;
import com.ibm.appscan.plugin.core.scan.IScanServiceProvider;
import com.ibm.appscan.plugin.core.scan.ITarget;
import com.ibm.appscan.plugin.core.scanners.sast.SASTConstants;
import com.ibm.appscan.plugin.core.scanners.sast.SASTScan;
import com.ibm.appscan.plugin.core.scanners.sast.target.ISASTTarget;
import com.ibm.appscan.plugin.core.scanners.sast.xml.ModelWriter;
import com.ibm.appscan.plugin.core.scanners.sast.xml.XmlWriter;

public class SASTScanManager implements IScanManager{
	
	private List<ISASTTarget> m_targets;
	private SASTScan m_scan;
	private String m_workingDirectory;

	public SASTScanManager(String workingDir) {
		m_workingDirectory = workingDir;
		m_targets = new ArrayList<ISASTTarget>();
	}

	@Override
	public void prepare(IProgress progress, Map<String, String> properties) throws AppScanException {
		createConfig();
		properties.put(CoreConstants.TARGET, m_workingDirectory);
		properties.put(SASTConstants.PREPARE_ONLY, Boolean.toString(true));
		run(progress, properties, null);
	}

	@Override
	public void analyze(IProgress progress, Map<String, String> properties, IScanServiceProvider provider) throws AppScanException {
		if(m_scan == null || m_scan.getIrx() == null) {
			createConfig();
			properties.put(CoreConstants.TARGET, m_workingDirectory);
		}
		else
			properties.put(CoreConstants.TARGET, m_scan.getIrx().getAbsolutePath());
		
		run(progress, properties, provider);
	}
	
	@Override
	public void addScanTarget(ITarget target) {
		if(target instanceof ISASTTarget)
			m_targets.add((ISASTTarget)target);
	}

	private  void run(IProgress progress,Map<String, String> properties, IScanServiceProvider provider) throws AppScanException {
		try {
			m_scan = new SASTScan(properties, progress, provider);
			m_scan.run();
		} catch (InvalidTargetException | ScannerException e) {
			throw new AppScanException(e.getLocalizedMessage());
		}
	}

	private String createConfig() throws AppScanException  {
		try {
			ModelWriter writer = new XmlWriter();
			writer.initWriters(new File(m_workingDirectory));		
			writer.visit(m_targets);
			writer.write();
			return writer.getOutputLocation();
		} catch (IOException | TransformerException  e) {
			throw new AppScanException(e.getLocalizedMessage());
		}
	}
}
