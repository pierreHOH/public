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
package com.iamcontent.io.gson;

import static com.iamcontent.io.gson.GsonUtils.customGson;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.iamcontent.core.math.DoubleRange;

public class DoubleRangeDeserializerTest {

	private Gson gson;

	@Before
	public void setUp() throws Exception {
		gson = customGson();
	}

	@Test
	public void testDeserialize() {
		final DoubleRange actual = gson.fromJson("{'limit1':1.0,'limit2':10.0}", DoubleRange.class);
		assertExactlyEquals(1.0, actual.getLimit1());
		assertExactlyEquals(10.0, actual.getLimit2());
	}

	private void assertExactlyEquals(double expected, double actual) {
		assertEquals(expected, actual, 0.0);
	}
}
