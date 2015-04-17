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

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

/**
 * A graph
 */
public interface Graph {

    UUID getId();

    Set<Vertex> getVertexes();

    void addVertex(Vertex vertex);

    void addVertexes(Collection<? extends Vertex> vertexes);

    void removeVertex(Vertex vertex);

    Collection<VertexListener> getVertexListeners();

    void addVertexListener(VertexListener listener);

    void removeVertexListener(VertexListener listener);

    Set<Link> getLinks();

    void addLinks(Collection<? extends Link> links);

    void addLink(Link link);

    void removeLink(Link link);

    Collection<LinkListener> getLinkListeners();

    void addLinkListener(LinkListener listener);

    void removeLinkListener(LinkListener listener);
}
