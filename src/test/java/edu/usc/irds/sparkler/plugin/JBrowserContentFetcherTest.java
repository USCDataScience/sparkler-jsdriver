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

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class JBrowserContentFetcherTest {

	@Test
	public void test() throws IOException {
		List<String> seeds = IOUtils.readLines(
			      this.getClass().getResourceAsStream("/seed.txt"),
			      "UTF-8");
		
		for (String seed : seeds){
			JBrowserContentFetcher fetcher = new JBrowserContentFetcher();
			fetcher.saveHtml(seed);
			File file = new File(fetcher.getEncodedFileName(seed));
			assertTrue("Can't find file for " + seed, file.exists());
		}
		
		System.out.println("All seeds successfully fetched");
		
	}

}
