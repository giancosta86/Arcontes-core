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

package info.gianlucacosta.arcontes.graphs.conversions.vertexconverters;

import info.gianlucacosta.arcontes.graphs.Vertex;
import info.gianlucacosta.arcontes.graphs.wrappers.vertexwrappers.NameBasedVertexWrapper;
import info.gianlucacosta.helios.conversions.CollectionToSortedCollectionConverter;
import info.gianlucacosta.helios.metainfo.MetaInfoRepository;

/**
 * Converts a collection of vertexes to a collection of NameBasedVertexWrapper's
 */
public class VertexesToNameBasedVertexWrappersConverter extends CollectionToSortedCollectionConverter<Vertex, NameBasedVertexWrapper> {

    private final MetaInfoRepository metaInfoRepository;

    public VertexesToNameBasedVertexWrappersConverter(MetaInfoRepository metaInfoRepository) {
        this.metaInfoRepository = metaInfoRepository;
    }

    @Override
    protected NameBasedVertexWrapper convertItem(Vertex vertex) {
        return new NameBasedVertexWrapper(vertex, metaInfoRepository);
    }

}
