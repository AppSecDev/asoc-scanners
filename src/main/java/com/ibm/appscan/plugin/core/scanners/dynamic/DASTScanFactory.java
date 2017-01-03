package com.ibm.appscan.plugin.core.scanners.dynamic;

import java.util.Map;

import com.ibm.appscan.plugin.core.auth.IAuthenticationProvider;
import com.ibm.appscan.plugin.core.logging.IProgress;
import com.ibm.appscan.plugin.core.scan.CloudScanServiceProvider;
import com.ibm.appscan.plugin.core.scan.IScan;
import com.ibm.appscan.plugin.core.scan.IScanFactory;
import com.ibm.appscan.plugin.core.scan.IScanServiceProvider;

public class DASTScanFactory implements IScanFactory, DASTConstants {

	@Override
	public IScan create(Map<String, String> properties, IProgress progress, IAuthenticationProvider authProvider) {
		IScanServiceProvider serviceProvider = new CloudScanServiceProvider(progress, authProvider);
		return new DASTScan(properties, progress, serviceProvider);
	}

	@Override
	public String getType() {
		return DAST;
	}
}
