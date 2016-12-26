/*******************************************************************************
 * Copyright 2016 See MINI2DX_AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.mini2Dx.natives;

/**
 * Provides information about the OS that the JVM is running on
 */
public class OsInformation {
	private static final String DESKTOP_OS = System.getProperty("os.name") != null
			? System.getProperty("os.name").toLowerCase() : null;
	private static final String JAVA_RUNTIME = System.getProperty("java.runtime.name") != null
			? System.getProperty("java.runtime.name").toLowerCase() : "";
	private static final boolean IS_ARM = System.getProperty("os.arch").startsWith("arm");
	private static final boolean IS_64_BIT = System.getProperty("os.arch").equals("amd64")
			|| System.getProperty("os.arch").equals("x86_64");
	private static final String ABI = (System.getProperty("sun.arch.abi") != null ? System.getProperty("sun.arch.abi") : "");

	private static Os os;

	public static Os getOs() {
		if (os == null) {
			os = determineOs();
		}
		return os;
	}

	private static Os determineOs() {
		if (JAVA_RUNTIME.contains("android")) {
			return Os.ANDROID;
		}
		if (DESKTOP_OS != null) {
			if (DESKTOP_OS.indexOf("win") >= 0) {
				return Os.WINDOWS;
			}
			if (DESKTOP_OS.indexOf("mac") >= 0) {
				return Os.MAC;
			}
			if (DESKTOP_OS.indexOf("nix") >= 0) {
				return Os.UNIX;
			}
			if (DESKTOP_OS.indexOf("nux") >= 0) {
				return Os.UNIX;
			}
			if (DESKTOP_OS.indexOf("aix") >= 0) {
				return Os.UNIX;
			}
		}
		if (!JAVA_RUNTIME.contains("android")) {
			return Os.IOS;
		}
		return Os.UNKNOWN;
	}

	public static boolean isWindows() {
		return getOs() == Os.WINDOWS;
	}

	public static boolean isMac() {
		return getOs() == Os.MAC;
	}

	public static boolean isUnix() {
		return getOs() == Os.UNIX;
	}

	public static boolean isAndroid() {
		return getOs() == Os.ANDROID;
	}

	public static boolean isIOS() {
		return getOs() == Os.IOS;
	}

	public static boolean is64Bit() {
		return IS_64_BIT;
	}

	public static boolean isArm() {
		return IS_ARM;
	}
	
	public static String getAbi() {
		return ABI;
	}
}
