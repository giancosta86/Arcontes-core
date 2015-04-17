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

package info.gianlucacosta.arcontes.graphs.wrappers.linkwrappers;

import info.gianlucacosta.arcontes.graphs.Link;
import info.gianlucacosta.arcontes.graphs.Vertex;
import info.gianlucacosta.arcontes.graphs.metainfo.NameInfo;
import info.gianlucacosta.arcontes.graphs.wrappers.LinkWrapper;
import info.gianlucacosta.helios.collections.general.SameTypePair;
import info.gianlucacosta.helios.metainfo.MetaInfoRepository;

/**
 * LinkWrapper whose toString() method returns a representation suitable for
 * representing an edge.
 * <p>
 * The compareTo() method just compares toString().
 */
public class NameBasedEdgeWrapper extends LinkWrapper {

    private static final String EDGE_FORMAT_STRING = "{%s, %s}";
    private final MetaInfoRepository metaInfoRepository;

    public NameBasedEdgeWrapper(Link link, MetaInfoRepository metaInfoRepository) {
        super(link);

        this.metaInfoRepository = metaInfoRepository;
    }

    @Override
    public String toString() {
        Link link = getLink();
        SameTypePair<Vertex> anchors = link.getAnchors();

        String firstName = metaInfoRepository.getMetaInfo(anchors.getLeft(), NameInfo.class).getName();
        String secondName = metaInfoRepository.getMetaInfo(anchors.getRight(), NameInfo.class).getName();

        if (firstName.compareTo(secondName) <= 0) {
            return String.format(EDGE_FORMAT_STRING, firstName, secondName);
        } else {
            return String.format(EDGE_FORMAT_STRING, secondName, firstName);
        }
    }

    @Override
    public int compareTo(LinkWrapper other) {
        return toString().compareTo(other.toString());
    }

}
