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
import info.gianlucacosta.helios.beans.events.ValueChangedEvent;
import info.gianlucacosta.helios.beans.events.ValueChangedListener;

/**
 * Basic implementation of AlgorithmRunService
 */
public class DefaultAlgorithmRunService implements AlgorithmRunService {

    private final ValueChangedEvent<AlgorithmRunState> runStateChangedEvent = new ValueChangedEvent<>();
    private Algorithm targetAlgorithm;
    private AlgorithmRunState runState = AlgorithmRunState.NOT_STARTED;

    @Override
    public boolean start(Algorithm targetAlgorithm) throws AlgorithmException {
        if (runState != AlgorithmRunState.NOT_STARTED) {
            throw new IllegalStateException();
        }

        this.targetAlgorithm = targetAlgorithm;

        if (!targetAlgorithm.init()) {
            return false;
        }

        setRunState(AlgorithmRunState.STARTED);

        return true;
    }

    @Override
    public void runToEnd() throws AlgorithmException {
        if (runState != AlgorithmRunState.STARTED) {
            throw new IllegalStateException();
        }

        setRunState(AlgorithmRunState.RUNNING);

        try {
            while (runState == AlgorithmRunState.RUNNING) {
                switch (targetAlgorithm.runStep()) {
                    case INTERRUPT:
                        setRunState(AlgorithmRunState.INTERRUPTED);
                        break;

                    case FINISH:
                        setRunState(AlgorithmRunState.FINISHED);
                        break;
                }
            }
        } catch (AlgorithmException ex) {
            setRunState(AlgorithmRunState.CRASHED);
            throw ex;
        }
    }

    @Override
    public void runStep() throws AlgorithmException {
        if (runState != AlgorithmRunState.STARTED) {
            throw new IllegalStateException();
        }

        setRunState(AlgorithmRunState.RUNNING);

        try {
            switch (targetAlgorithm.runStep()) {
                case CONTINUE:
                    setRunState(AlgorithmRunState.STARTED);
                    break;

                case INTERRUPT:
                    setRunState(AlgorithmRunState.INTERRUPTED);
                    break;

                case FINISH:
                    setRunState(AlgorithmRunState.FINISHED);
                    break;
            }

        } catch (AlgorithmException ex) {
            setRunState(AlgorithmRunState.CRASHED);
            throw ex;
        }
    }

    @Override
    public void stop() {
        if (runState == AlgorithmRunState.NOT_STARTED) {
            throw new IllegalArgumentException();
        }

        setRunState(AlgorithmRunState.NOT_STARTED);
        targetAlgorithm = null;
    }

    @Override
    public AlgorithmRunState getRunState() {
        return runState;
    }

    private void setRunState(AlgorithmRunState runState) {
        AlgorithmRunState oldRunState = this.runState;
        this.runState = runState;

        if (runState != oldRunState) {
            runStateChangedEvent.fire(oldRunState, runState);
        }
    }

    @Override
    public void addRunStateChangedListener(ValueChangedListener<AlgorithmRunState> listener) {
        runStateChangedEvent.addListener(listener);
    }

    @Override
    public void removeRunStateChangedListener(ValueChangedListener<AlgorithmRunState> listener) {
        runStateChangedEvent.removeListener(listener);
    }

}
