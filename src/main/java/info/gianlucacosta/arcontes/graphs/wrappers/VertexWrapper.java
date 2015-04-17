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

package info.gianlucacosta.arcontes.graphs.wrappers;

import info.gianlucacosta.arcontes.graphs.Vertex;

import java.util.Objects;

/**
 * Wraps a vertex, and can be used by its subclasses to deal with the vertex in
 * a more user-friendly context (for example, a subclassing wrapper can
 * introduce a toString() method returning the vertex's name, obtained from the
 * graph context).
 * <p>
 * Two VertexWrapper's are equal if and only if their underlying vertexes are
 * equal.
 */
public abstract class VertexWrapper implements Comparable<VertexWrapper> {

    private final Vertex vertex;

    public VertexWrapper(Vertex vertex) {
        Objects.requireNonNull(vertex);

        this.vertex = vertex;
    }

    public Vertex getVertex() {
        return vertex;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof VertexWrapper)) {
            return false;
        }

        VertexWrapper other = (VertexWrapper) obj;
        return Objects.equals(vertex, other.vertex);
    }

    @Override
    public int hashCode() {
        return vertex.hashCode();
    }

}
