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
package com.iamcontent.device.servo;

import static com.iamcontent.device.servo.ServoSources.calibratedServoSource;
import static com.iamcontent.device.servo.ServoSources.rawServoSource;
import static com.iamcontent.device.servo.ServoSources.reChanneledServoSource;

import com.google.common.base.Function;
import com.iamcontent.device.servo.calibrate.ServoSourceCalibrator;
import com.iamcontent.device.servo.raw.ServoController;

/**
 * A 'standard' configuration of {@link Servo}s, where each raw {@link Servo},
 * indexed by an integer, is calibrated to a normal range (0..1), then
 * re-channeled and then calibrated once more (tuned).
 * 
 * @author Greg Elderfield
 */
public class StandardServoConfiguration<C> {

	private final ServoController<Integer> servoController;
	private final ServoSource<C> tunedServos;

	public StandardServoConfiguration(ServoController<Integer> servoController, ServoSourceCalibrator<Integer> normalizingServoCalibrator,
			Function<C, Integer> channelTranslation, ServoSourceCalibrator<C> tuningCalibrator) {

		this.servoController = servoController;
		this.tunedServos = 
				calibratedServoSource(
						reChanneledServoSource(
								calibratedServoSource(
										rawServoSource(servoController), 
										normalizingServoCalibrator), 
								channelTranslation),
						tuningCalibrator);
	}

	public ServoController<Integer> getServoController() {
		return servoController;
	}

	public ServoSource<C> getTunedServos() {
		return tunedServos;
	}
}
