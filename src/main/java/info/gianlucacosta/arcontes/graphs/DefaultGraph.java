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

import java.util.*;

/**
 * Basic implementation of Graph
 */
public class DefaultGraph implements Graph {

    private static final EqualGraphsPredicate EQUAL_GRAPHS_PREDICATE = new EqualGraphsPredicate();
    private final UUID id;
    private final Set<Vertex> vertexes = new HashSet<>();
    private final Set<Link> links = new HashSet<>();
    private transient List<VertexListener> vertexListeners = new ArrayList<>();
    private transient List<LinkListener> linkListeners = new ArrayList<>();

    public DefaultGraph() {
        id = UUID.randomUUID();
    }

    public DefaultGraph(Graph source) {
        id = source.getId();
        vertexes.addAll(source.getVertexes());
        links.addAll(source.getLinks());
        getInternalVertexListeners().addAll(source.getVertexListeners());
        getInternalLinkListeners().addAll(source.getLinkListeners());
    }

    private DefaultGraph(UUID id) {
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public Collection<VertexListener> getVertexListeners() {
        return Collections.unmodifiableCollection(getInternalVertexListeners());
    }

    private Collection<VertexListener> getInternalVertexListeners() {
        if (vertexListeners == null) {
            vertexListeners = new ArrayList<>();
        }

        return vertexListeners;
    }

    @Override
    public Collection<LinkListener> getLinkListeners() {
        return Collections.unmodifiableCollection(getInternalLinkListeners());
    }

    private Collection<LinkListener> getInternalLinkListeners() {
        if (linkListeners == null) {
            linkListeners = new ArrayList<>();
        }
        return linkListeners;
    }

    @Override
    public Set<Vertex> getVertexes() {
        return Collections.unmodifiableSet(vertexes);
    }

    @Override
    public Set<Link> getLinks() {
        return Collections.unmodifiableSet(links);
    }

    @Override
    public void addVertex(Vertex vertex) {
        if (vertexes.contains(vertex)) {
            throw new GraphConsistencyException("Cannot add the same vertex twice");
        }

        for (VertexListener vertexListener : getInternalVertexListeners()) {
            if (!vertexListener.onVertexAdding(this, vertex)) {
                return;
            }
        }

        vertexes.add(vertex);

        for (VertexListener vertexListener : getInternalVertexListeners()) {
            vertexListener.onVertexAdded(this, vertex);
        }
    }

    @Override
    public void addVertexes(Collection<? extends Vertex> vertexes) {
        for (Vertex vertex : vertexes) {
            addVertex(vertex);
        }
    }

    @Override
    public void removeVertex(Vertex vertex) {
        if (!vertexes.contains(vertex)) {
            throw new GraphConsistencyException("Cannot remove a vertex not belonging to the graph");
        }

        for (VertexListener vertexListener : getInternalVertexListeners()) {
            if (!vertexListener.onVertexRemoving(this, vertex)) {
                return;
            }
        }

        Set<Link> originalLinks = new HashSet<>(links);
        for (Link link : originalLinks) {
            if (link.getAnchors().contains(vertex)) {
                removeLink(link, false);
            }
        }

        vertexes.remove(vertex);

        for (VertexListener vertexListener : getInternalVertexListeners()) {
            vertexListener.onVertexRemoved(this, vertex);
        }
    }

    @Override
    public void addLink(Link link) {
        if (links.contains(link)) {
            throw new GraphConsistencyException("Cannot add the same link twice");
        }

        for (Vertex anchor : link.getAnchors()) {
            if (!vertexes.contains(anchor)) {
                throw new GraphConsistencyException("The added link anchors must belong to the graph");
            }
        }

        for (LinkListener linkListener : getInternalLinkListeners()) {
            if (!linkListener.onLinkAdding(this, link)) {
                return;
            }
        }

        links.add(link);

        for (LinkListener linkListener : getInternalLinkListeners()) {
            linkListener.onLinkAdded(this, link);
        }
    }

    @Override
    public void addLinks(Collection<? extends Link> links) {
        for (Link link : links) {
            addLink(link);
        }
    }

    @Override
    public void removeLink(Link link) {
        removeLink(link, true);
    }

    protected void removeLink(Link link, boolean canBePrevented) {
        if (!links.contains(link)) {
            throw new GraphConsistencyException("Cannot remove a link not belonging to the graph");
        }

        for (LinkListener linkListener : getInternalLinkListeners()) {
            boolean linkRemovingResult = linkListener.onLinkRemoving(this, link);

            if (canBePrevented && !linkRemovingResult) {
                return;
            }
        }

        links.remove(link);

        for (LinkListener linkListener : getInternalLinkListeners()) {
            linkListener.onLinkRemoved(this, link);
        }
    }

    @Override
    public void addVertexListener(VertexListener listener) {
        getInternalVertexListeners().add(listener);

    }

    @Override
    public void removeVertexListener(VertexListener listener) {
        getInternalVertexListeners().remove(listener);

    }

    @Override
    public void addLinkListener(LinkListener listener) {
        getInternalLinkListeners().add(listener);
    }

    @Override
    public void removeLinkListener(LinkListener listener) {
        getInternalLinkListeners().remove(listener);
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
