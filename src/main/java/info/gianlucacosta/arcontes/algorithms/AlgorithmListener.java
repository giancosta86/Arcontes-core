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
 * Listener whose methods are called during the whole execution of an algorithm
 */
public interface AlgorithmListener {

    /**
     * Called at the end of the init() method, if it is going to return true
     * (that is, if the algorithm is going to continue)
     */
    void onInited();

    /**
     * Called at the beginning of an algorithm step, just after the step index
     * has been increased and the related metainfo has been stored
     *
     * @param step the current step index
     */
    void onStepStarted(int step);

    /**
     * Called immediately after a step has been performed, before processing its
     * result for flow control
     *
     * @param step        the current step index
     * @param stepOutcome the outcome of the step
     */
    void onStepCompleted(int step, AlgorithmStepOutcome stepOutcome);

    /**
     * The last step before the end of an algorithm manually interrupted by the
     * user.
     */
    void onInterrupted();

    /**
     * The last step before the end of an algorithm that completed successfully.
     * It is called after the finalization phase.
     */
    void onFinished();

}
