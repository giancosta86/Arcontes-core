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
import info.gianlucacosta.arcontes.graphs.metainfo.WeightInfo;
import info.gianlucacosta.helios.collections.general.CollectionItems;
import info.gianlucacosta.helios.collections.general.SameTypePair;
import info.gianlucacosta.helios.metainfo.MetaInfoRepository;

import java.util.*;

/**
 * Basic implementation of UnorientedGraphAnalyzer
 */
public class DefaultUnorientedGraphAnalyzer extends AbstractGraphAnalyzer implements UnorientedGraphAnalyzer {

    public DefaultUnorientedGraphAnalyzer(GraphContext graphContext) {
        super(graphContext);
    }

    @Override
    public Collection<Link> getEdgesBetween(Vertex oneVertex, Vertex anotherVertex) {
        Collection<Link> result = new HashSet<>();

        GraphContext graphContext = getGraphContext();

        for (Link link : graphContext.getGraph().getLinks()) {
            SameTypePair<Vertex> edgeAnchors = link.getAnchors();

            if (edgeAnchors.contains(oneVertex) && edgeAnchors.contains(anotherVertex)) {
                result.add(link);
            }
        }

        return result;
    }

    @Override
    public Collection<Link> getEdgesBetween(SameTypePair<Vertex> vertexes) {
        return getEdgesBetween(vertexes.getLeft(), vertexes.getRight());
    }

    @Override
    public Collection<Link> getAnchoredEdges(Vertex vertex) {
        List<Link> result = new ArrayList<>();

        Graph graph = getGraphContext().getGraph();

        for (Link link : graph.getLinks()) {
            if (link.getAnchors().contains(vertex)) {
                result.add(link);
            }
        }

        return result;
    }

    @Override
    public Collection<Link> getEdgesWithMinWeightBetween(Vertex oneVertex, Vertex anotherVertex) {
        Set<Link> result = new HashSet<>();

        GraphContext graphContext = getGraphContext();
        MetaInfoRepository metaInfoRepository = graphContext.getMetaInfoRepository();

        double minWeight = Double.POSITIVE_INFINITY;

        for (Link edge : getEdgesBetween(oneVertex, anotherVertex)) {
            WeightInfo<Double> weightInfo = metaInfoRepository.getMetaInfo(edge, WeightInfo.class);
            if (weightInfo.getWeight() < minWeight) {
                result.clear();
                result.add(edge);
            } else if (weightInfo.getWeight() == minWeight) {
                result.add(edge);
            }
        }

        return result;
    }

    @Override
    public Collection<Link> getEdgesWithMinWeightBetween(SameTypePair<Vertex> vertexes) {
        return getEdgesWithMinWeightBetween(vertexes.getLeft(), vertexes.getRight());
    }

    @Override
    public double getMinWeightBetween(Vertex oneVertex, Vertex anotherVertex) {
        Collection<Link> lightestEdges = getEdgesWithMinWeightBetween(oneVertex, anotherVertex);

        if (lightestEdges.isEmpty()) {
            return Double.POSITIVE_INFINITY;
        } else {
            MetaInfoRepository metaInfoRepository = getGraphContext().getMetaInfoRepository();

            WeightInfo<Double> weightInfo = metaInfoRepository.getMetaInfo(
                    CollectionItems.getFirst(lightestEdges),
                    WeightInfo.class);

            return weightInfo.getWeight();
        }
    }

    @Override
    public double getMinWeightBetween(SameTypePair<Vertex> vertexes) {
        return getMinWeightBetween(vertexes.getLeft(), vertexes.getRight());
    }

    @Override
    public boolean isGraphComplete() {
        Graph graph = getGraphContext().getGraph();

        for (Vertex sourceVertex : graph.getVertexes()) {
            for (Vertex targetVertex : graph.getVertexes()) {
                if (targetVertex.equals(sourceVertex)) {
                    continue;
                }

                if (getEdgesBetween(sourceVertex, targetVertex).isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

}
