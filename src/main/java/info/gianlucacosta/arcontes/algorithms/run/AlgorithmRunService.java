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

import info.gianlucacosta.arcontes.algorithms.Algorithm;
import info.gianlucacosta.arcontes.algorithms.AlgorithmException;
import info.gianlucacosta.helios.beans.events.ValueChangedListener;

/**
 * Provides methods to easily control the execution of a given algorithm.
 * <p>
 * After calling the start() method, the service will always target the passed
 * algorithm: to target a new algorithm, you'll have to call stop(), then
 * start(), passing the new algorithm
 */
public interface AlgorithmRunService {

    /**
     * Starts the execution of the provided algorithm, and calls its
     * initialization method
     *
     * @param targetAlgorithm The algorithm to run, which becomes the "target
     *                        algorithm"
     * @return the boolean value returned by the algorithm's initialization
     * method
     * @throws AlgorithmException whenever thrown by the algorithm's
     *                            initialization method
     */
    boolean start(Algorithm targetAlgorithm) throws AlgorithmException;

    /**
     * Runs all the steps of the target algorithm until its completion
     *
     * @throws AlgorithmException whenever thrown by the target algorithm
     */
    void runToEnd() throws AlgorithmException;

    /**
     * Runs a single step of the target algorithm
     *
     * @throws AlgorithmException whenever thrown by the target algorithm
     */
    void runStep() throws AlgorithmException;

    /**
     * Stops the execution of the current target algorithm, leaving this
     * instance ready to start another algorithm
     */
    void stop();

    /**
     * @return <p>
     * The detailed execution state of the target algorithm.
     * <p>
     * The possible state values are explained in the documentation of the
     * return type
     */
    AlgorithmRunState getRunState();

    void addRunStateChangedListener(ValueChangedListener<AlgorithmRunState> listener);

    void removeRunStateChangedListener(ValueChangedListener<AlgorithmRunState> listener);

}
