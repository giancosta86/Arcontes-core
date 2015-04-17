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

import info.gianlucacosta.arcontes.algorithms.metainfo.DefaultCurrentStepInfo;
import info.gianlucacosta.arcontes.graphs.GraphContext;
import info.gianlucacosta.helios.metainfo.MetaInfoRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Basic implementation of Algorithm.
 * <p>
 * It provides some useful features, such as a finalization phase and setting
 * the current step index (0-based, taking into account the initialization
 * phase) as a metainfo (by associating CurrentStepInfo to the
 * <i>GraphContext</i> object)
 */
public abstract class AbstractAlgorithm implements Algorithm {

    private transient List<AlgorithmListener> algorithmListeners = new ArrayList<>();
    private int currentStep;
    private final GraphContext graphContext;
    private final AlgorithmSettings algorithmSettings;
    private final AlgorithmInput algorithmInput;
    private final AlgorithmOutput algorithmOutput;

    public AbstractAlgorithm(GraphContext graphContext, AlgorithmSettings algorithmSettings, AlgorithmInput algorithmInput, AlgorithmOutput algorithmOutput) {
        this.graphContext = graphContext;
        this.algorithmSettings = algorithmSettings;
        this.algorithmInput = algorithmInput;
        this.algorithmOutput = algorithmOutput;
    }

    @Override
    public int getCurrentStep() {
        return currentStep;
    }

    @Override
    public boolean init() throws AlgorithmException {
        MetaInfoRepository metaInfoRepository = graphContext.getMetaInfoRepository();

        metaInfoRepository.putMetaInfo(graphContext, new DefaultCurrentStepInfo(0));

        boolean result = doInit();

        if (!result) {
            return false;
        }

        for (AlgorithmListener algorithmListener : algorithmListeners) {
            algorithmListener.onInited();
        }

        return true;
    }

    protected abstract boolean doInit() throws AlgorithmException;

    @Override
    public AlgorithmStepOutcome runStep() throws AlgorithmException {
        currentStep++;

        MetaInfoRepository metaInfoRepository = graphContext.getMetaInfoRepository();
        metaInfoRepository.putMetaInfo(graphContext, new DefaultCurrentStepInfo(currentStep));

        for (AlgorithmListener algorithmListener : getAlgorithmListeners()) {
            algorithmListener.onStepStarted(currentStep);
        }

        AlgorithmStepOutcome stepOutcome = doRunStep();

        for (AlgorithmListener algorithmListener : getAlgorithmListeners()) {

            algorithmListener.onStepCompleted(currentStep, stepOutcome);
        }

        switch (stepOutcome) {
            case INTERRUPT: {
                for (AlgorithmListener algorithmListener : getAlgorithmListeners()) {
                    algorithmListener.onInterrupted();
                }
            }
            break;

            case FINISH: {
                doFinish();

                for (AlgorithmListener algorithmListener : getAlgorithmListeners()) {
                    algorithmListener.onFinished();
                }
            }
            break;
        }

        return stepOutcome;
    }

    /**
     * Runs a generic algorithm step
     *
     * @return the outcome of the step; all the possible outcomes are explained
     * in the documentation of the return type
     * @throws AlgorithmException if some fatal error occurred
     */
    protected abstract AlgorithmStepOutcome doRunStep() throws AlgorithmException;

    /**
     * Runs the finalization phase, usually collecting the algorithm results
     *
     * @throws AlgorithmException if some error occurred
     */
    protected abstract void doFinish() throws AlgorithmException;

    private Collection<AlgorithmListener> getAlgorithmListeners() {
        if (algorithmListeners == null) {
            algorithmListeners = new ArrayList<>();
        }

        return algorithmListeners;
    }

    @Override
    public void addAlgorithmListener(AlgorithmListener listener) {
        getAlgorithmListeners().add(listener);
    }

    @Override
    public void removeAlgorithmListener(AlgorithmListener listener) {
        getAlgorithmListeners().remove(listener);
    }

    protected GraphContext getGraphContext() {
        return graphContext;
    }

    protected AlgorithmSettings getAlgorithmSettings() {
        return algorithmSettings;
    }

    protected AlgorithmInput getAlgorithmInput() {
        return algorithmInput;
    }

    protected AlgorithmOutput getAlgorithmOutput() {
        return algorithmOutput;
    }

}
