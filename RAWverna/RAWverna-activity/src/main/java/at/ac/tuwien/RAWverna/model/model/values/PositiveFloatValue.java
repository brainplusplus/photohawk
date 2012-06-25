/*******************************************************************************
 * Copyright 2006 - 2012 Vienna University of Technology,
 * Department of Software Technology and Interactive Systems, IFS
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
package at.ac.tuwien.RAWverna.model.model.values;

import at.ac.tuwien.RAWverna.model.model.util.FloatFormatter;

public class PositiveFloatValue extends Value implements INumericValue {

	private static final long serialVersionUID = -1170922225142475324L;

	private FloatFormatter formatter;

	private double value;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		// also save invalid values, they are checked later with isChanged()
		this.value = value;
	}

	public double value() {
		return value;
	}

	@Override
	public String toString() {
		if (formatter == null) {
			formatter = new FloatFormatter();
		}
		return formatter.formatFloatPrecisly(value);
	}

	@Override
	public String getFormattedValue() {
		if (formatter == null) {
			formatter = new FloatFormatter();
		}
		return formatter.formatFloat(value);
	}

	@Override
	public void parse(String text) {
		setValue(Double.parseDouble(text));
	}

}
