/**
 * © Copyright IBM Corporation 2016.
 * LICENSE: Apache License, Version 2.0 https://www.apache.org/licenses/LICENSE-2.0
 */

package com.ibm.appscan.plugin.core.scanners.sast;

import java.util.Map;

import com.ibm.appscan.plugin.core.auth.IAuthenticationProvider;
import com.ibm.appscan.plugin.core.logging.IProgress;
import com.ibm.appscan.plugin.core.scan.CloudScanServiceProvider;
import com.ibm.appscan.plugin.core.scan.IScanServiceProvider;
import com.ibm.appscan.plugin.core.scan.IScan;
import com.ibm.appscan.plugin.core.scan.IScanFactory;

public class SASTScanFactory implements IScanFactory, SASTConstants {

	@Override
	public IScan create(Map<String, String> properties, IProgress progress, IAuthenticationProvider authProvider) {
		IScanServiceProvider serviceProvider = new CloudScanServiceProvider(progress, authProvider);
		return new SASTScan(properties, progress, serviceProvider);
	}

	@Override
	public String getType() {
		return SAST;
	}
}
