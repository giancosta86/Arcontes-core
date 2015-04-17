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

import info.gianlucacosta.arcontes.graphs.Graph;
import info.gianlucacosta.arcontes.graphs.Link;
import info.gianlucacosta.arcontes.graphs.Vertex;
import info.gianlucacosta.helios.application.io.CommonQuestionOutcome;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Never interacts with the user - whenever one of its data-providing methods is
 * called, the data are read from internal lists.
 * <p>
 * Very useful to perform unit testing of algorithms
 */
public abstract class ScriptedAlgorithmInput implements AlgorithmInput {

    private Queue<Vertex> inputVertexes;
    private Queue<Link> inputLinks;
    private Queue<String> inputStrings;
    private Queue<Integer> inputIntegers;
    private Queue<Double> inputDoubles;
    private Queue<CommonQuestionOutcome> inputQuestionOutcomes;
    private Queue<Object> inputGenericItems;

    protected Queue<Vertex> initInputVertexes() {
        return new LinkedList<>();
    }

    protected Queue<Link> initInputLinks() {
        return new LinkedList<>();
    }

    protected Queue<Double> initInputDoubles() {
        return new LinkedList<>();
    }

    protected Queue<Integer> initInputIntegers() {
        return new LinkedList<>();
    }

    protected Queue<String> initInputStrings() {
        return new LinkedList<>();
    }

    protected Queue<CommonQuestionOutcome> initInputQuestionOutcomes() {
        return new LinkedList<>();
    }

    protected Queue<Object> initInputGenericItems() {
        return new LinkedList<>();
    }

    private Queue<Vertex> getInputVertexes() {
        if (inputVertexes == null) {
            inputVertexes = initInputVertexes();
        }

        return inputVertexes;
    }

    private Queue<Link> getInputLinks() {
        if (inputLinks == null) {
            inputLinks = initInputLinks();
        }

        return inputLinks;
    }

    private Queue<Integer> getInputIntegers() {
        if (inputIntegers == null) {
            inputIntegers = initInputIntegers();
        }

        return inputIntegers;
    }

    private Queue<Double> getInputDoubles() {
        if (inputDoubles == null) {
            inputDoubles = initInputDoubles();
        }

        return inputDoubles;
    }

    private Queue<String> getInputStrings() {
        if (inputStrings == null) {
            inputStrings = initInputStrings();
        }

        return inputStrings;
    }

    private Queue<CommonQuestionOutcome> getInputQuestionOutcomes() {
        if (inputQuestionOutcomes == null) {
            inputQuestionOutcomes = initInputQuestionOutcomes();
        }

        return inputQuestionOutcomes;
    }

    private Queue<Object> getInputGenericItems() {
        if (inputGenericItems == null) {
            inputGenericItems = initInputGenericItems();
        }

        return inputGenericItems;
    }

    @Override
    public <T> T askForItem(String prompt, Collection<T> sourceItems) {
        return (T) getInputGenericItems().remove();
    }

    @Override
    public <T> T askForItem(String prompt, Collection<T> sourceItems, T initialValue) {
        return (T) getInputGenericItems().remove();
    }

    @Override
    public Vertex askForVertex(String prompt, Collection<Vertex> sourceVertexes) {
        return getInputVertexes().remove();
    }

    @Override
    public Vertex askForVertex(String prompt, Collection<Vertex> sourceVertexes, Vertex initialVertex) {
        return getInputVertexes().remove();
    }

    @Override
    public final Vertex askForVertex(Graph graph, String prompt) {
        return getInputVertexes().remove();
    }

    @Override
    public final Vertex askForVertex(Graph graph, String prompt, Vertex initialVertex) {
        return getInputVertexes().remove();
    }

    @Override
    public Link askForLink(String prompt, Collection<Link> sourceLinks) {
        return getInputLinks().remove();
    }

    @Override
    public Link askForLink(String prompt, Collection<Link> sourceLinks, Link initialLink) {
        return getInputLinks().remove();
    }

    @Override
    public final Link askForLink(Graph graph, String prompt) {
        return getInputLinks().remove();
    }

    @Override
    public final Link askForLink(Graph graph, String prompt, Link initialLink) {
        return getInputLinks().remove();
    }

    @Override
    public final Double askForDouble(String prompt, Double initialValue) {
        return getInputDoubles().remove();
    }

    @Override
    public final Integer askForInteger(String prompt, Integer initialValue) {
        return getInputIntegers().remove();
    }

    @Override
    public final String askForString(String prompt, String initialValue) {
        return getInputStrings().remove();
    }

    @Override
    public CommonQuestionOutcome askYesNoQuestion(String prompt) {
        return getInputQuestionOutcomes().remove();
    }

    @Override
    public CommonQuestionOutcome askYesNoCancelQuestion(String prompt) {
        return getInputQuestionOutcomes().remove();
    }
}
