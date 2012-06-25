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

public class PositiveIntegerValue extends Value implements INumericValue {

	private static final long serialVersionUID = -8824495369506076325L;

	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		// also save invalid values, they are checked later with isEvaluated()
		this.value = value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}

	public double value() {
		return value;
	}

	@Override
	public void parse(String text) {
		setValue(Integer.parseInt(text));
	}

}
