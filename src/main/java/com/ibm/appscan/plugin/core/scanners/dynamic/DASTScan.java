package com.ibm.appscan.plugin.core.scanners.dynamic;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.ibm.appscan.plugin.core.error.InvalidTargetException;
import com.ibm.appscan.plugin.core.error.ScannerException;
import com.ibm.appscan.plugin.core.logging.DefaultProgress;
import com.ibm.appscan.plugin.core.logging.IProgress;
import com.ibm.appscan.plugin.core.scan.IScanServiceProvider;
import com.ibm.appscan.plugin.core.scanners.ASoCScan;
import com.ibm.appscan.plugin.core.scanners.Messages;

public class DASTScan extends ASoCScan implements DASTConstants {

	private static final String REPORT_FORMAT = "pdf"; //$NON-NLS-1$
	
	public DASTScan(Map<String, String> properties, IScanServiceProvider provider) {
		super(properties, new DefaultProgress(), provider);
	}
	
	public DASTScan(Map<String, String> properties, IProgress progress, IScanServiceProvider provider) {
		super(properties, progress, provider);
	}

	@Override
	public void run() throws ScannerException, InvalidTargetException {
		String type = DYNAMIC_ANALYZER;
		String target = getTarget();
		
		if(target == null)
			throw new InvalidTargetException(Messages.getMessage(TARGET_INVALID, target));

		Map<String, String> params = getProperties();
		params.put(STARTING_URL, target);
		
		String scanFile = params.remove(SCAN_FILE);
		if(scanFile != null && new File(scanFile).isFile()) {
			type = DYNAMIC_ANALYZER_WITH_FILE;
			File file = new File(params.remove(SCAN_FILE));
			
			try {
				String fileId = getServiceProvider().submitFile(file);
				if(fileId == null)
					throw new ScannerException(Messages.getMessage(ERROR_FILE_UPLOAD, file.getName()));
				params.put(SCAN_FILE_ID, fileId);
			} catch (IOException e) {
				throw new ScannerException(Messages.getMessage(SCAN_FAILED, e.getLocalizedMessage()));
			}
		}
			
		setScanId(getServiceProvider().createAndExecuteScan(type, params));
		
		if(getScanId() == null)
			throw new ScannerException(Messages.getMessage(ERROR_CREATING_SCAN));
	}

	@Override
	public String getType() {
		return DAST;
	}

	@Override
	protected String getReportFormat() {
		return REPORT_FORMAT;
	}
}
