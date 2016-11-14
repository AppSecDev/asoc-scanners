/**
 * © Copyright IBM Corporation 2016.
 * LICENSE: Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 */

package com.ibm.appscan.plugin.core.scanners.mobile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ibm.appscan.plugin.core.CoreConstants;
import com.ibm.appscan.plugin.core.error.InvalidTargetException;
import com.ibm.appscan.plugin.core.error.ScannerException;
import com.ibm.appscan.plugin.core.logging.DefaultProgress;
import com.ibm.appscan.plugin.core.logging.IProgress;
import com.ibm.appscan.plugin.core.scan.IScanServiceProvider;
import com.ibm.appscan.plugin.core.scanners.ASoCScan;
import com.ibm.appscan.plugin.core.scanners.Messages;

public class MAScan extends ASoCScan implements MAConstants {
	
	private static final String REPORT_FORMAT = "pdf"; //$NON-NLS-1$
	
	public MAScan(Map<String, String> properties, IScanServiceProvider provider) {
		super(properties, new DefaultProgress(), provider);
	}
	
	public MAScan(Map<String, String> properties, IProgress progress, IScanServiceProvider provider) {
		super (properties, progress, provider);
	}
	
	@Override
	public void run() throws ScannerException, InvalidTargetException {
		String target = getTarget();
		
		if(target == null || !(new File(target).isFile()))
			throw new InvalidTargetException(Messages.getMessage(TARGET_INVALID, target));

		File targetFile = new File(target);
		try {
			String fileId = getServiceProvider().submitFile(targetFile);
			if(fileId == null)
				throw new ScannerException(Messages.getMessage(ERROR_FILE_UPLOAD, targetFile.getName()));

			Map<String, String> params = new HashMap<String, String>();
			params.put(APPLICATION_FILE_ID, fileId);
			params.put(CoreConstants.APP_ID, getAppId());
			params.put(CoreConstants.SCAN_NAME, getName());
			
			setScanId(getServiceProvider().createAndExecuteScan(MOBILE_ANALYZER, params));
			if(getScanId() == null)
				throw new ScannerException(Messages.getMessage(ERROR_SUBMITTING_FILE, targetFile.getName()));
		} catch (IOException e) {
			throw new ScannerException(Messages.getMessage(SCAN_FAILED, e.getLocalizedMessage()));
		}
	}
	
	@Override
	public String getType() {
		return MA;
	}

	@Override
	protected String getReportFormat() {
		return REPORT_FORMAT;
	}
}
