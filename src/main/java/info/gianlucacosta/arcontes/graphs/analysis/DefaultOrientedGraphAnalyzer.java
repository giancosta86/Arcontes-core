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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Basic implementation of OrientedGraphAnalyzer
 */
public class DefaultOrientedGraphAnalyzer extends AbstractGraphAnalyzer implements OrientedGraphAnalyzer {

    public DefaultOrientedGraphAnalyzer(GraphContext graphContext) {
        super(graphContext);
    }

    @Override
    public Vertex getHead(SameTypePair<Vertex> vertexes) {
        return vertexes.getLeft();
    }

    @Override
    public Vertex getHead(Link link) {
        return getHead(link.getAnchors());
    }

    @Override
    public Vertex getTail(SameTypePair<Vertex> vertexes) {
        return vertexes.getRight();
    }

    @Override
    public Vertex getTail(Link link) {
        return getTail(link.getAnchors());
    }

    @Override
    public Collection<Link> getEnteringArcs(Vertex vertex) {
        Set<Link> result = new HashSet<>();

        Graph graph = getGraphContext().getGraph();

        for (Link link : graph.getLinks()) {
            if (getTail(link).equals(vertex)) {
                result.add(link);
            }
        }

        return result;
    }

    @Override
    public Collection<Link> getExitingArcs(Vertex vertex) {
        Set<Link> result = new HashSet<>();

        Graph graph = getGraphContext().getGraph();

        for (Link link : graph.getLinks()) {
            if (getHead(link).equals(vertex)) {
                result.add(link);
            }
        }

        return result;
    }

    @Override
    public Collection<Link> getArcsBetween(Vertex head, Vertex tail) {
        Set<Link> result = new HashSet<>();

        Graph graph = getGraphContext().getGraph();

        for (Link link : graph.getLinks()) {
            if (getHead(link).equals(head)
                    && getTail(link).equals(tail)) {
                result.add(link);
            }
        }

        return result;
    }

    @Override
    public Collection<Link> getArcsBetween(SameTypePair<Vertex> vertexes) {
        return getArcsBetween(getHead(vertexes), getTail(vertexes));
    }

    @Override
    public boolean isGraphComplete() {
        Graph graph = getGraphContext().getGraph();

        for (Vertex sourceVertex : graph.getVertexes()) {
            for (Vertex targetVertex : graph.getVertexes()) {
                if (targetVertex.equals(sourceVertex)) {
                    continue;
                }

                if (getArcsBetween(sourceVertex, targetVertex).isEmpty()) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public Collection<Link> getArcsWithMinWeightBetween(Vertex head, Vertex tail) {
        Set<Link> result = new HashSet<>();

        GraphContext graphContext = getGraphContext();
        MetaInfoRepository metaInfoRepository = graphContext.getMetaInfoRepository();

        double minWeight = Double.POSITIVE_INFINITY;

        for (Link link : getArcsBetween(head, tail)) {
            WeightInfo<Double> weightInfo = metaInfoRepository.getMetaInfo(link, WeightInfo.class);
            if (weightInfo.getWeight() < minWeight) {
                result.clear();
                result.add(link);
            } else if (weightInfo.getWeight() == minWeight) {
                result.add(link);
            }
        }

        return result;
    }

    @Override
    public Collection<Link> getArcsWithMinWeightBetween(SameTypePair<Vertex> vertexes) {
        return getArcsWithMinWeightBetween(getHead(vertexes), getTail(vertexes));
    }

    @Override
    public double getMinWeightBetween(Vertex head, Vertex tail) {
        Collection<Link> lightestArcs = getArcsWithMinWeightBetween(head, tail);

        if (lightestArcs.isEmpty()) {
            return Double.POSITIVE_INFINITY;
        } else {
            MetaInfoRepository metaInfoRepository = getGraphContext().getMetaInfoRepository();

            WeightInfo<Double> weightInfo = metaInfoRepository.getMetaInfo(
                    CollectionItems.getFirst(lightestArcs),
                    WeightInfo.class);

            return weightInfo.getWeight();
        }
    }

    @Override
    public double getMinWeightBetween(SameTypePair<Vertex> vertexes) {
        return getMinWeightBetween(getHead(vertexes), getTail(vertexes));
    }

}
