/**
 * Copyright (c) 2015, adar.w (adar.w@outlook.com) 
 * 
 * http://www.adar-w.me
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pers.adar.hsn.adaptor.impl;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import pers.adar.hsn.component.ChannelSession.ChannelContext;

public class FileChannelAdaptor extends StandardChannelAdaptor {

	private static final String DIR = "/opt/files/";
	
	@Override
	public void onMessage(ChannelContext channelContext) {
		try (FileChannel fileChannel = FileChannel.open(Paths.get(DIR + now()), StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE)) {
			fileChannel.write(channelContext.read());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		channelContext.close();
	}
	
	private String now() {
		return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
	}
}