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
 * Basic implementation of AlgorithmOutput
 */
public abstract class AbstractAlgorithmOutput implements AlgorithmOutput {

    @Override
    public void println(Object value) {
        print(value);
        println();
    }

    @Override
    public void println(String valueDescription, Object value) {
        println(String.format("%s = %s", valueDescription, value));
    }

    @Override
    public void printHeader(Object header) {
        println("------------");
        println(header);
        println("------------");
    }

}
