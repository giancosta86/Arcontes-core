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
import info.gianlucacosta.helios.reflection.Locator;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

/**
 * Delegates every method to a graph obtained via a Locator,
 * <strong>except</strong> getId(), equals() and hashcode()
 */
public class GraphProxy implements Graph {

    private static final EqualGraphsPredicate EQUAL_GRAPHS_PREDICATE = new EqualGraphsPredicate();
    private final Locator<Graph> graphLocator;
    private final UUID id;

    public GraphProxy(Locator<Graph> graphLocator) {
        this.graphLocator = graphLocator;

        id = UUID.randomUUID();
    }

    private Graph getGraph() {
        return graphLocator.locate();
    }

    @Override
    public void addLink(Link link) {
        getGraph().addLink(link);
    }

    @Override
    public void addLinks(Collection<? extends Link> links) {
        getGraph().addLinks(links);
    }

    @Override
    public void addLinkListener(LinkListener listener) {
        getGraph().addLinkListener(listener);
    }

    @Override
    public void addVertex(Vertex vertex) {
        getGraph().addVertex(vertex);
    }

    @Override
    public void addVertexes(Collection<? extends Vertex> vertexes) {
        getGraph().addVertexes(vertexes);
    }

    @Override
    public void addVertexListener(VertexListener listener) {
        getGraph().addVertexListener(listener);
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public Set<Link> getLinks() {
        return getGraph().getLinks();
    }

    @Override
    public Set<Vertex> getVertexes() {
        return getGraph().getVertexes();
    }

    @Override
    public void removeLink(Link link) {
        getGraph().removeLink(link);
    }

    @Override
    public void removeLinkListener(LinkListener listener) {
        getGraph().removeLinkListener(listener);
    }

    @Override
    public void removeVertex(Vertex vertex) {
        getGraph().removeVertex(vertex);
    }

    @Override
    public void removeVertexListener(VertexListener listener) {
        getGraph().removeVertexListener(listener);
    }

    @Override
    public Collection<VertexListener> getVertexListeners() {
        return getGraph().getVertexListeners();
    }

    @Override
    public Collection<LinkListener> getLinkListeners() {
        return getGraph().getLinkListeners();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Graph)) {
            return false;
        }

        Graph other = (Graph) obj;
        return EQUAL_GRAPHS_PREDICATE.evaluate(new SameTypePair<>(this, other));
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
