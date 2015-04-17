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

import java.util.Objects;
import java.util.UUID;

/**
 * Basic implementation of Link
 */
public class DefaultLink implements Link {

    private final UUID id = UUID.randomUUID();
    private final SameTypePair<Vertex> anchors;

    public DefaultLink(Vertex firstVertex, Vertex secondVertex) {
        this(firstVertex, secondVertex, false);
    }

    public DefaultLink(Vertex firstVertex, Vertex secondVertex, boolean canBeRecursive) {
        this(new SameTypePair<>(firstVertex, secondVertex), canBeRecursive);
    }

    public DefaultLink(SameTypePair<Vertex> anchors) {
        this(anchors, false);
    }

    public DefaultLink(SameTypePair<Vertex> anchors, boolean canBeRecursive) {

        if (!canBeRecursive) {
            if (Objects.equals(anchors.getLeft(), anchors.getRight())) {
                throw new GraphConsistencyException("Recursive links are not allowed");
            }
        }

        this.anchors = anchors;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public SameTypePair<Vertex> getAnchors() {
        return anchors;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Link)) {
            return false;
        }

        Link other = (Link) obj;

        return Objects.equals(id, other.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
