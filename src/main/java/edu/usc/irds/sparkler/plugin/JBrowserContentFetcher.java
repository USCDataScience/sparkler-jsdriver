/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.usc.irds.sparkler.plugin;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.commons.io.FileUtils;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;


public class JBrowserContentFetcher {

	public JBrowserDriver createBrowserInstance() {
		return new JBrowserDriver(Settings.builder()
				.timezone(Timezone.AMERICA_NEWYORK)
				.ajaxResourceTimeout(2000)
				.ajaxWait(2000)
				.socketTimeout(2000)
				.connectTimeout(2000)
				.build());	
	}
	
	public void quitBrowserInstance(JBrowserDriver driver) {
		driver.quit();
	}
	
	public int saveHtml(String webUrl) {
		long start = System.currentTimeMillis();
		
		JBrowserDriver driver = createBrowserInstance();

		System.out.println("Time taken to create driver- " + (System.currentTimeMillis() - start));
		
		// This will block for the page load and any
		// associated AJAX requests
		driver.get(webUrl);
		
		int status = driver.getStatusCode();
		
		// Returns the page source in its current state, including
		// any DOM updates that occurred after page load
		saveHtmlInFile(webUrl, driver.getPageSource());
		
		System.out.println("Time taken - " + (System.currentTimeMillis() - start));
		quitBrowserInstance(driver);
		return status;
	}
	/**
	 * @param webUrl - Takes a http url
	 * @return - encode file name save on File system
	 */
	public String getEncodedFileName(String webUrl){
		String filename = "DEFAULT.HTML";
		try {
			filename = URLEncoder.encode(webUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return filename;
	}
	
	public void saveHtmlInFile(String webUrl, String source) {
		
		File file = new File(getEncodedFileName(webUrl));
		try {
			FileUtils.writeStringToFile(file, source);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		JBrowserContentFetcher jbrowserExample = new JBrowserContentFetcher();
		jbrowserExample.saveHtml(args[0]);
	}
}
