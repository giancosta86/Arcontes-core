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

import info.gianlucacosta.arcontes.graphs.Link;

import java.util.Objects;

/**
 * Wraps a link, and can be used by its subclasses to deal with the link in a
 * more user-friendly context (for example, a subclassing wrapper can introduce
 * a toString() method returning the link's label, obtained from the graph
 * context).
 * <p>
 * Two LinkWrapper's are equal if and only if their underlying links are equal.
 */
public abstract class LinkWrapper implements Comparable<LinkWrapper> {

    private final Link link;

    public LinkWrapper(Link link) {
        Objects.requireNonNull(link);

        this.link = link;
    }

    public Link getLink() {
        return link;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof LinkWrapper)) {
            return false;
        }

        LinkWrapper other = (LinkWrapper) obj;
        return Objects.equals(link, other.link);
    }

    @Override
    public int hashCode() {
        return link.hashCode();
    }

}
