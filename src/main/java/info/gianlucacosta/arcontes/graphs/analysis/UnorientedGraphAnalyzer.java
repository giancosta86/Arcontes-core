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

import info.gianlucacosta.arcontes.graphs.Link;
import info.gianlucacosta.arcontes.graphs.Vertex;
import info.gianlucacosta.helios.collections.general.SameTypePair;

import java.util.Collection;

/**
 * Provides analysis methods dedicated to unoriented graphs
 */
public interface UnorientedGraphAnalyzer extends GraphAnalyzer {

    Collection<Link> getAnchoredEdges(Vertex vertex);

    Collection<Link> getEdgesBetween(Vertex oneVertex, Vertex anotherVertex);

    Collection<Link> getEdgesBetween(SameTypePair<Vertex> vertexes);

    double getMinWeightBetween(Vertex oneVertex, Vertex anotherVertex);

    double getMinWeightBetween(SameTypePair<Vertex> vertexes);

    Collection<Link> getEdgesWithMinWeightBetween(Vertex oneVertex, Vertex anotherVertex);

    Collection<Link> getEdgesWithMinWeightBetween(SameTypePair<Vertex> vertexes);

}
