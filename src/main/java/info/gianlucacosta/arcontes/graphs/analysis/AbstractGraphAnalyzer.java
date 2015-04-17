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

package info.gianlucacosta.arcontes.graphs.analysis;

import info.gianlucacosta.arcontes.graphs.Graph;
import info.gianlucacosta.arcontes.graphs.GraphContext;
import info.gianlucacosta.arcontes.graphs.Link;
import info.gianlucacosta.arcontes.graphs.Vertex;
import info.gianlucacosta.helios.collections.general.SameTypePair;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Basic implementation of GraphAnalyzer
 */
public abstract class AbstractGraphAnalyzer implements GraphAnalyzer {

    private final GraphContext graphContext;

    public AbstractGraphAnalyzer(GraphContext graphContext) {
        this.graphContext = graphContext;
    }

    protected GraphContext getGraphContext() {
        return graphContext;
    }

    @Override
    public Collection<Vertex> getUnconnectedVertexes() {
        Set<Vertex> connectedVertexes = new HashSet<>();

        Graph graph = getGraphContext().getGraph();

        for (Link link : graph.getLinks()) {
            SameTypePair<Vertex> linkAnchors = link.getAnchors();
            connectedVertexes.addAll(linkAnchors);
        }

        Set<Vertex> result = new HashSet<>(graph.getVertexes());
        result.removeAll(connectedVertexes);

        return result;
    }

}
