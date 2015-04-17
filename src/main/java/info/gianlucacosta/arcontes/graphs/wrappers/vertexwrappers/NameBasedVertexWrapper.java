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

package info.gianlucacosta.arcontes.graphs.wrappers.vertexwrappers;

import info.gianlucacosta.arcontes.graphs.Vertex;
import info.gianlucacosta.arcontes.graphs.metainfo.NameInfo;
import info.gianlucacosta.arcontes.graphs.wrappers.VertexWrapper;
import info.gianlucacosta.helios.metainfo.MetaInfoRepository;

/**
 * VertexWrapper whose toString() method returns the vertex name.
 * <p>
 * The compareTo() method just compares toString().
 */
public class NameBasedVertexWrapper extends VertexWrapper {

    private final MetaInfoRepository metaInfoRepository;

    public NameBasedVertexWrapper(Vertex vertex, MetaInfoRepository metaInfoRepository) {
        super(vertex);
        this.metaInfoRepository = metaInfoRepository;
    }

    @Override
    public String toString() {
        Vertex vertex = getVertex();
        return metaInfoRepository.getMetaInfo(vertex, NameInfo.class).getName();
    }

    @Override
    public int compareTo(VertexWrapper other) {
        return toString().compareTo(other.toString());
    }

}
