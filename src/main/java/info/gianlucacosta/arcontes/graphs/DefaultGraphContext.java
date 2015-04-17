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

package info.gianlucacosta.arcontes.graphs;

import info.gianlucacosta.helios.collections.general.SameTypePair;
import info.gianlucacosta.helios.metainfo.MetaInfoRepository;

import java.util.UUID;

/**
 * Basic implementation of GraphContext
 */
public class DefaultGraphContext implements GraphContext {

    private static final EqualGraphContextsPredicate EQUAL_GRAPH_CONTEXTS_PREDICATE = new EqualGraphContextsPredicate();
    private final Graph graph;
    private final MetaInfoRepository metaInfoRepository;
    private final UUID id;

    public DefaultGraphContext(Graph graph, MetaInfoRepository metaInfoRepository) {
        this.graph = graph;
        this.metaInfoRepository = metaInfoRepository;

        id = UUID.randomUUID();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public Graph getGraph() {
        return graph;
    }

    @Override
    public MetaInfoRepository getMetaInfoRepository() {
        return metaInfoRepository;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GraphContext)) {
            return false;
        }

        GraphContext other = (GraphContext) obj;

        return EQUAL_GRAPH_CONTEXTS_PREDICATE.evaluate(new SameTypePair<>(this, other));
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
