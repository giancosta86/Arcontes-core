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
 * An algorithm
 */
public interface Algorithm {

    /**
     * @return the current step, starting from 1. It returns 0 only during the
     * initialization phase
     */
    int getCurrentStep();

    /**
     * Performs the initialization phase for the algorithm
     *
     * @return true if the algorithm can correctly start, or false if the user
     * somehow decided to cancel the algorithm
     * @throws AlgorithmException if some fatal error occurred
     */
    boolean init() throws AlgorithmException;

    /**
     * Runs a generic algorithm step
     *
     * @return <p>
     * the outcome of the step
     * <p>
     * The possible outcomes are explained in the documentation of the return
     * type
     * @throws AlgorithmException if some fatal error occurred
     */
    AlgorithmStepOutcome runStep() throws AlgorithmException;

    void addAlgorithmListener(AlgorithmListener listener);

    void removeAlgorithmListener(AlgorithmListener listener);

}
