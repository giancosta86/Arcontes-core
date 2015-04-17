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
import info.gianlucacosta.helios.metainfo.MetaInfoRepositoryProxy;
import info.gianlucacosta.helios.reflection.Locator;

import java.util.UUID;

/**
 * Delegates every method to a graph context whose components are obtained via
 * related instances of Locator, <strong>except</strong> getId(), equals() and
 * hashcode()
 */
public class GraphContextProxy implements GraphContext {

    private static final EqualGraphContextsPredicate EQUAL_GRAPH_CONTEXTS_PREDICATE = new EqualGraphContextsPredicate();
    private final GraphProxy graphProxy;
    private final MetaInfoRepositoryProxy metaInfoRepositoryProxy;
    private final UUID id;

    public GraphContextProxy(Locator<Graph> graphLocator, Locator<MetaInfoRepository> metaInfoRepositoryLocator) {
        this.graphProxy = new GraphProxy(graphLocator);
        this.metaInfoRepositoryProxy = new MetaInfoRepositoryProxy(metaInfoRepositoryLocator);

        id = UUID.randomUUID();
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public Graph getGraph() {
        return graphProxy;
    }

    @Override
    public MetaInfoRepository getMetaInfoRepository() {
        return metaInfoRepositoryProxy;
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
