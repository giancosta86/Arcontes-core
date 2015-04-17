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

package info.gianlucacosta.arcontes.graphs.metainfo;

import info.gianlucacosta.arcontes.graphs.GraphContext;
import info.gianlucacosta.helios.metainfo.AbstractMetaInfoAgent;
import info.gianlucacosta.helios.metainfo.MetaInfoRepository;

/**
 * A metainfo agent associated with a graph context and which can operate only
 * on the metainfo repository of that graph context
 */
public abstract class GraphContextBasedMetaInfoAgent<THandle> extends AbstractMetaInfoAgent<THandle> {

    private final GraphContext graphContext;

    public GraphContextBasedMetaInfoAgent(boolean transactional, GraphContext graphContext) {
        super(transactional);
        this.graphContext = graphContext;
    }

    protected GraphContext getGraphContext() {
        return graphContext;
    }

    @Override
    public boolean act(MetaInfoRepository metaInfoRepository, THandle handle) {
        if (!metaInfoRepository.equals(graphContext.getMetaInfoRepository())) {
            throw new IllegalArgumentException();
        }

        return super.act(metaInfoRepository, handle);
    }

    @Override
    protected abstract boolean doAct(MetaInfoRepository metaInfoRepository, THandle handle);

}
