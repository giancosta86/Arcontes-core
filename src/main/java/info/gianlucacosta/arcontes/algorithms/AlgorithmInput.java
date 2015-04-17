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

/**
 * Provides methods to ask the user for several kinds of data that might be
 * required by the algorithm
 */
public interface AlgorithmInput {

    String askForString(String prompt, String initialValue);

    Integer askForInteger(String prompt, Integer initialValue);

    Double askForDouble(String prompt, Double initialValue);

    <T> T askForItem(String prompt, Collection<T> sourceItems);

    <T> T askForItem(String prompt, Collection<T> sourceItems, T initialValue);

    Vertex askForVertex(String prompt, Collection<Vertex> sourceVertexes);

    Vertex askForVertex(String prompt, Collection<Vertex> sourceVertexes, Vertex initialVertex);

    Vertex askForVertex(Graph graph, String prompt);

    Vertex askForVertex(Graph graph, String prompt, Vertex initialVertex);

    Link askForLink(String prompt, Collection<Link> sourceLinks);

    Link askForLink(String prompt, Collection<Link> sourceLinks, Link initialLink);

    Link askForLink(Graph graph, String prompt);

    Link askForLink(Graph graph, String prompt, Link initialLink);

    CommonQuestionOutcome askYesNoQuestion(String prompt);

    CommonQuestionOutcome askYesNoCancelQuestion(String prompt);
}
