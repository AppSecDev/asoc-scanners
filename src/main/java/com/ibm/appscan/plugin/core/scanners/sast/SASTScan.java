/**
 * © Copyright IBM Corporation 2016.
 * LICENSE: Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 */

package com.ibm.appscan.plugin.core.scanners.sast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.ibm.appscan.plugin.core.error.InvalidTargetException;
import com.ibm.appscan.plugin.core.error.ScannerException;
import com.ibm.appscan.plugin.core.logging.DefaultProgress;
import com.ibm.appscan.plugin.core.logging.IProgress;
import com.ibm.appscan.plugin.core.scan.IScanServiceProvider;
import com.ibm.appscan.plugin.core.scanners.ASoCScan;
import com.ibm.appscan.plugin.core.scanners.Messages;
import com.ibm.appscan.plugin.core.CoreConstants;


public class SASTScan extends ASoCScan implements SASTConstants {

	private static final String REPORT_FORMAT = "html"; //$NON-NLS-1$
	
	private File m_irx;
	
	public SASTScan(Map<String, String> properties, IScanServiceProvider provider) {
		super(properties, new DefaultProgress(), provider);
	}
	
	public SASTScan(Map<String, String> properties, IProgress progress, IScanServiceProvider provider) {
		super(properties, progress, provider);
	}
	
	@Override
	public void run() throws ScannerException, InvalidTargetException {
		String target = getTarget();
		
		if(target == null || !(new File(target).exists()))
			throw new InvalidTargetException(Messages.getMessage(TARGET_INVALID, target));

		try {
			generateIR();
            if(isRunAnalysis())
            {
                analyzeIR();
            }
		} catch(IOException e) {
			throw new ScannerException(Messages.getMessage(SCAN_FAILED, e.getLocalizedMessage()));
		}
		
		if(isRunAnalysis() && getScanId() == null)
			throw new ScannerException(Messages.getMessage(ERROR_RUNNING_SCAN));
	}

	@Override
	public String getType() {
		return SAST;
	}
	
	@Override
	protected String getReportFormat() {
		return REPORT_FORMAT;
	}
	
    private boolean isRunAnalysis() {
        if(getRunAnalysis()!= null && getRunAnalysis().equalsIgnoreCase("FALSE"))
            return false;
        return true;
    }
    
	private void generateIR() throws IOException, ScannerException {
		File targetFile = new File(getTarget());

		//If we were given an irx file, don't generate a new one
		if(targetFile.getName().endsWith(IRX_EXTENSION) && targetFile.isFile()) {
			m_irx = targetFile;
			return;
		}

		//Get the target directory
		String targetDir = targetFile.isDirectory() ? targetFile.getAbsolutePath() : targetFile.getParent();

		//Set the args
		ArrayList<String> args = new ArrayList<String>();
		args.add(PREPARE);
		args.add(OPT_NAME);
		args.add(getName());
		
		//Create and run the process
		new SAClient(getProgress()).run(targetDir, args);
		m_irx = new File(targetDir, getName() + IRX_EXTENSION);
		if(!m_irx.isFile())
			throw new ScannerException(Messages.getMessage(ERROR_GENERATING_IRX, getScanLogs().getAbsolutePath()));
	}
	
	private void analyzeIR() throws IOException, ScannerException {
		String fileId = getServiceProvider().submitFile(m_irx);
		if(fileId == null)
			throw new ScannerException(Messages.getMessage(ERROR_FILE_UPLOAD, m_irx.getName()));		
				
		Map<String, String> params = getProperties();
		params.put(ARSA_FILE_ID, fileId);
		
		setScanId(getServiceProvider().createAndExecuteScan(STATIC_ANALYZER, params));
		if(getScanId() == null)
			throw new ScannerException(Messages.getMessage(ERROR_SUBMITTING_IRX));
	}
	
	private File getScanLogs() {
		if(m_irx == null) {
			return new File("logs"); //$NON-NLS-1$
		}
		String logsFile = m_irx.getName();
		logsFile = logsFile.substring(0, logsFile.lastIndexOf(".") - 1); //$NON-NLS-1$
		logsFile += "_logs.zip"; //$NON-NLS-1$
		return new File(m_irx.getParentFile(), logsFile);
	}
}
