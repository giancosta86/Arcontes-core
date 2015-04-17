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

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class DefaultGraphTest {

    private DefaultGraph graph;

    protected DefaultGraph createGraph() {
        return new DefaultGraph();
    }

    protected DefaultGraph getGraph() {
        return graph;
    }

    protected Vertex createVertex() {
        return new DefaultVertex();
    }

    protected Link createLink(DefaultGraph g) {
        Vertex v1 = createVertex();
        Vertex v2 = createVertex();

        if (!g.getVertexes().contains(v1)) {
            g.addVertex(v1);
        }

        if (!g.getVertexes().contains(v2)) {
            g.addVertex(v2);
        }

        return new DefaultLink(v1, v2);
    }

    @Before
    public void setUp() {
        graph = createGraph();
    }

    @Test
    public void constructor_shouldAssignId() {
        assertThat(graph.getId(), notNullValue());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getVertexes_shouldReturnAndUnmodifiableSet() {
        graph.getVertexes().add(createVertex());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getLinks_shouldReturnAndUnmodifiableSet() {
        graph.getLinks().add(createLink(graph));
    }

    @Test
    public void addVertex_shouldAddANewVertex() {
        Vertex v = createVertex();

        graph.addVertex(v);

        assertThat(graph.getVertexes(), hasItem(v));
    }

    @Test
    public void addVertex_shouldWorkWhenAnEmptyVertexAdapterIsAttached() {
        graph.addVertexListener(new VertexAdapter());

        graph.addVertex(createVertex());
        assertThat(graph.getVertexes(), hasSize(1));
    }

    @Test(expected = GraphConsistencyException.class)
    public void addVertex_shouldFailWhenAddingTheSameVertexTwice() {
        Vertex v = createVertex();

        graph.addVertex(v);
        graph.addVertex(v);
    }

    @Test
    public void addVertex_shouldDoNothingWhenAnAttachedVertexListenerPreventsIt() {
        graph.addVertexListener(new VertexAdapter() {
            @Override
            public boolean onVertexAdding(Graph graph, Vertex vertex) {
                return true;
            }

        });

        graph.addVertexListener(new VertexAdapter() {
            @Override
            public boolean onVertexAdding(Graph graph, Vertex vertex) {
                return false;
            }

        });

        graph.addVertex(createVertex());

        assertThat(graph.getVertexes(), hasSize(0));
    }

    @Test
    public void addVertex_shouldTriggerVertexListenersAfterAddingTheVertex() {
        final List<Integer> listenerResults = new ArrayList<>();

        graph.addVertexListener(new VertexAdapter() {
            @Override
            public void onVertexAdded(Graph graph, Vertex vertex) {
                listenerResults.add(90);
            }

        });

        graph.addVertexListener(new VertexAdapter() {
            @Override
            public void onVertexAdded(Graph graph, Vertex vertex) {
                listenerResults.add(85);
            }

        });

        graph.addVertex(createVertex());

        assertThat(listenerResults, hasItem(90));
        assertThat(listenerResults, hasItem(85));
    }

    @Test
    public void removeVertex_shouldRemoveTheVertex() {
        Vertex v = createVertex();

        graph.addVertex(v);
        graph.removeVertex(v);

        assertThat(graph.getVertexes(), hasSize(0));
    }

    @Test
    public void removeVertex_shouldWorkWhenAnEmptyVertexAdapterIsAttached() {
        graph.addVertexListener(new VertexAdapter());

        Vertex v = createVertex();
        graph.addVertex(v);
        graph.removeVertex(v);

        assertThat(graph.getVertexes(), hasSize(0));
    }

    @Test(expected = GraphConsistencyException.class)
    public void removeVertex_shouldFailIfTheVertexDoesNotBelongToTheGraph() {
        Vertex v = createVertex();

        graph.removeVertex(v);
    }

    @Test
    public void removeVertex_shouldRemoveAllTheLinksAnchoredToThatVertex() {
        Vertex v1 = createVertex();
        Vertex v2 = createVertex();
        Vertex v3 = createVertex();

        Link e1 = new DefaultLink(v1, v2);
        Link e2 = new DefaultLink(v1, v3);
        Link e3 = new DefaultLink(v2, v3);

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        graph.addLink(e1);
        graph.addLink(e2);
        graph.addLink(e3);

        graph.removeVertex(v1);

        assertThat(graph.getLinks(), not(hasItem(e1)));
        assertThat(graph.getLinks(), not(hasItem(e2)));
        assertThat(graph.getLinks(), hasItem(e3));
    }

    @Test
    public void removeVertex_shouldTriggerTheRemoveHandlerForAllTheAnchoredLinks() {
        final List<Link> removedLinks = new ArrayList<>();

        graph.addLinkListener(new LinkAdapter() {
            @Override
            public void onLinkRemoved(Graph graph, Link link) {
                removedLinks.add(link);
            }

        });

        Vertex v1 = createVertex();
        Vertex v2 = createVertex();
        Vertex v3 = createVertex();

        Link e1 = new DefaultLink(v1, v2);
        Link e2 = new DefaultLink(v1, v3);
        Link e3 = new DefaultLink(v2, v3);

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        graph.addLink(e1);
        graph.addLink(e2);
        graph.addLink(e3);

        graph.removeVertex(v1);

        assertThat(removedLinks, hasItem(e1));
        assertThat(removedLinks, hasItem(e2));
        assertThat(removedLinks, not(hasItem(e3)));
    }

    @Test
    public void removeVertex_shouldRemoveTheAttachedLinksAndThatCannotBePreventedByLinkListeners() {

        graph.addLinkListener(new LinkAdapter() {
            @Override
            public boolean onLinkRemoving(Graph graph, Link link) {
                return false;
            }

        });

        Vertex v1 = createVertex();
        Vertex v2 = createVertex();
        Vertex v3 = createVertex();

        Link e1 = new DefaultLink(v1, v2);
        Link e2 = new DefaultLink(v1, v3);
        Link e3 = new DefaultLink(v2, v3);

        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addVertex(v3);

        graph.addLink(e1);
        graph.addLink(e2);
        graph.addLink(e3);

        graph.removeVertex(v1);

        assertThat(graph.getLinks(), hasSize(1));
    }

    @Test
    public void removeVertex_shouldDoNothingWhenAnAttachedVertexListenerPreventsIt() {
        graph.addVertexListener(new VertexAdapter() {
            @Override
            public boolean onVertexRemoving(Graph graph, Vertex vertex) {
                return true;
            }

        });

        graph.addVertexListener(new VertexAdapter() {
            @Override
            public boolean onVertexRemoving(Graph graph, Vertex vertex) {
                return false;
            }

        });

        Vertex v = createVertex();
        graph.addVertex(v);

        graph.removeVertex(v);

        assertThat(graph.getVertexes(), hasSize(1));
    }

    @Test
    public void removeVertex_shouldTriggerVertexListenersAfterRemovingTheVertex() {
        final List<Integer> listenerResults = new ArrayList<>();

        graph.addVertexListener(new VertexAdapter() {
            @Override
            public void onVertexRemoved(Graph graph, Vertex vertex) {
                listenerResults.add(879);
            }

        });

        graph.addVertexListener(new VertexAdapter() {
            @Override
            public void onVertexRemoved(Graph graph, Vertex vertex) {
                listenerResults.add(912);
            }

        });

        Vertex v = createVertex();

        graph.addVertex(v);
        graph.removeVertex(v);

        assertThat(listenerResults, hasItem(879));
        assertThat(listenerResults, hasItem(912));
    }

    @Test
    public void addLink_shouldAddANewLink() {
        Link l = createLink(graph);

        graph.addLink(l);

        assertThat(graph.getLinks(), hasItem(l));
    }

    @Test
    public void addLink_shouldWorkWhenAnEmptyLinkAdapterIsAttached() {
        graph.addLinkListener(new LinkAdapter());

        graph.addLink(createLink(graph));
        assertThat(graph.getLinks(), hasSize(1));
    }

    @Test(expected = GraphConsistencyException.class)
    public void addLink_shouldFailWhenAddingTheSameLinkTwice() {
        Link l = createLink(graph);

        graph.addLink(l);
        graph.addLink(l);
    }

    @Test(expected = GraphConsistencyException.class)
    public void addLink_shouldFailWhenAddingALinkWithAnAnchorNotBelongingToTheGraph() {
        Vertex vAlpha = createVertex();
        Vertex vBeta = createVertex();

        graph.addVertex(vAlpha);

        Link wrongLink = new DefaultLink(vAlpha, vBeta);
        graph.addLink(wrongLink);
    }

    @Test
    public void addLink_shouldDoNothingWhenAnAttachedLinkListenerPreventsIt() {
        graph.addLinkListener(new LinkAdapter() {
            @Override
            public boolean onLinkAdding(Graph graph, Link link) {
                return true;
            }

        });

        graph.addLinkListener(new LinkAdapter() {
            @Override
            public boolean onLinkAdding(Graph graph, Link link) {
                return false;
            }

        });

        graph.addLink(createLink(graph));

        assertThat(graph.getLinks(), hasSize(0));
    }

    @Test
    public void addLink_shouldTriggerLinkListenersAfterAddingTheLink() {
        final List<Integer> listenerResults = new ArrayList<>();

        graph.addLinkListener(new LinkAdapter() {
            @Override
            public void onLinkAdded(Graph graph, Link link) {
                listenerResults.add(500);
            }

        });

        graph.addLinkListener(new LinkAdapter() {
            @Override
            public void onLinkAdded(Graph graph, Link link) {
                listenerResults.add(430);
            }

        });

        graph.addLink(createLink(graph));

        assertThat(listenerResults, hasItem(500));
        assertThat(listenerResults, hasItem(430));
    }

    @Test
    public void removeLink_shouldWork() {
        Link link = createLink(graph);

        graph.addLink(link);

        graph.removeLink(link);

        assertThat(graph.getLinks(), hasSize(0));
    }

    @Test
    public void removeLink_shouldWorkWhenAnEmptyLinkAdapterIsAttached() {
        graph.addLinkListener(new LinkAdapter());
        Link l = createLink(graph);

        graph.addLink(l);
        graph.removeLink(l);

        assertThat(graph.getLinks(), hasSize(0));
    }

    @Test
    public void removeLink_shouldDoNothingWhenAnAttachedLinkListenerPreventsIt() {
        graph.addLinkListener(new LinkAdapter() {
            @Override
            public boolean onLinkRemoving(Graph graph, Link link) {
                return true;
            }

        });

        graph.addLinkListener(new LinkAdapter() {
            @Override
            public boolean onLinkRemoving(Graph graph, Link link) {
                return false;
            }

        });

        Link l = createLink(graph);
        graph.addLink(l);

        graph.removeLink(l);

        assertThat(graph.getLinks(), hasSize(1));
    }

    @Test
    public void removeLink_shouldTriggerLinkListenersAfterRemovingTheLink() {
        final List<Integer> listenerResults = new ArrayList<>();

        graph.addLinkListener(new LinkAdapter() {
            @Override
            public void onLinkRemoved(Graph graph, Link link) {
                listenerResults.add(123);
            }

        });

        graph.addLinkListener(new LinkAdapter() {
            @Override
            public void onLinkRemoved(Graph graph, Link link) {
                listenerResults.add(700);
            }

        });

        Link l = createLink(graph);

        graph.addLink(l);
        graph.removeLink(l);

        assertThat(listenerResults, hasItem(123));
        assertThat(listenerResults, hasItem(700));
    }

}
