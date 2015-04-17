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
 * The possible <i>expected</i> outcomes of an algorithm step - whereas an error
 * in the algorithm must be notified by throwing an <i>AlgorithmException</i>
 */
public enum AlgorithmStepOutcome {

    /**
     * The algorithm is ready to perform another step
     */
    CONTINUE,
    /**
     * There are no more steps, so the algorithm should proceed to the
     * finalizing operations
     */
    FINISH,
    /**
     * The algorithm cannot go on because it was interrupted by the user (who,
     * for example, refused to provide required parameters)
     */
    INTERRUPT
}
