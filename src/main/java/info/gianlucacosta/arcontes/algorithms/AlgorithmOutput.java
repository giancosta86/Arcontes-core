/*§
  ===========================================================================
  Arcontes - Core
  ===========================================================================
  Copyright (C) 2013-2015 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

package info.gianlucacosta.arcontes.algorithms;

/**
 * Used by algorithms to provide output for the user.
 * <p>
 * The concept of "line" strictly depends on the underlying output method of the
 * implementing classes
 */
public interface AlgorithmOutput {

    /**
     * Prints an object
     *
     * @param value the value to print
     */
    void print(Object value);

    /**
     * Prints an empty line
     */
    void println();

    /**
     * Prints an object, then moves to the following line
     *
     * @param value the value to print
     */
    void println(Object value);

    /**
     * Prints the string "<i>valueDescription</i> = <i>value</i>", then moves to
     * the following line
     *
     * @param valueDescription a description of the value
     * @param value            the value to print
     */
    void println(String valueDescription, Object value);

    /**
     * Prints the passed header, then goes to the following line.
     * <p>
     * The exact format for the header depends on the specific implementation
     *
     * @param header the header text
     */
    void printHeader(Object header);
}
