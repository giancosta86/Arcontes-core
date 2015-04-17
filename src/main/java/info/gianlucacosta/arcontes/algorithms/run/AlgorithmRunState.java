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

package info.gianlucacosta.arcontes.algorithms.run;

/**
 * The execution state of an algorithm
 */
public enum AlgorithmRunState {

    /**
     * The algorithm hasn't been started yet
     */
    NOT_STARTED,
    /**
     * The algorithm has been started, but it is not performing any step
     */
    STARTED,
    /**
     * The algorithm is performing a step or an indivisible sequence of steps
     */
    RUNNING,
    /**
     * The algorithm has been interrupted by the user (for example, the user
     * refused to provide required parameters)
     */
    INTERRUPTED,
    /**
     * The algorithm has completed successfully
     */
    FINISHED,
    /**
     * The algorithm execution failed because of an exception
     */
    CRASHED
}
