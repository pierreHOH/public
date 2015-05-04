/**
  IAmContent Public Libraries.
  Copyright (C) 2015 Greg Elderfield
  @author Greg Elderfield, iamcontent@jarchitect.co.uk
 
  This program is free software; you can redistribute it and/or modify it under the terms of the
  GNU General Public License as published by the Free Software Foundation; either version 2 of 
  the License, or (at your option) any later version.

  This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
  without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
  See the GNU General Public License for more details.

  You should have received a copy of the GNU General Public License along with this program;
  if not, write to the Free Software Foundation, Inc., 
  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.iamcontent.device.controller.pololu;

import com.iamcontent.io.cli.CommandLineDriver;

/**
 * An example driver for the {@link PololuMaestroUsbServoController}. Useful for manual testing.
 * @author Greg Elderfield
 */
public class PololuCommandLineDriver extends CommandLineDriver implements Runnable {

	private final PololuMaestroUsbServoController device = new PololuMaestroUsbServoController();
	
	public static void main(String[] args) {
		final PololuCommandLineDriver driver = new PololuCommandLineDriver();
		driver.run();
	}

	@Override
	public void run() {
		for (String c : commandStrings())
			execute(c);
	}
	
	private void execute(String c) {
		device.getServo(0).setPosition(parse(c));
	}

	private static double parse(String s) {
		try {
			return Double.parseDouble(s);
		} catch(NumberFormatException e) {
			System.err.println("Ooops");
			return 0;
		}
	}
}
