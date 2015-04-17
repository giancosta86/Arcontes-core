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

package info.gianlucacosta.arcontes.graphs.metainfo.agents;

import info.gianlucacosta.arcontes.graphs.metainfo.DefaultBoundedWeightInfo;
import info.gianlucacosta.helios.metainfo.AbstractMetaInfoAgent;
import info.gianlucacosta.helios.metainfo.MetaInfoRepository;

/**
 * Associates handles with the same DefaultBoundedWeightInfo metainfo, passed to
 * the agent's constructor
 */
public class DefaultBoundedWeightInfoAgent<THandle, TWeight extends Comparable<TWeight>> extends AbstractMetaInfoAgent<THandle> {

    private final DefaultBoundedWeightInfo<TWeight> boundedWeightInfo;

    public DefaultBoundedWeightInfoAgent(TWeight defaultValue) {
        this(defaultValue, defaultValue);
    }

    public DefaultBoundedWeightInfoAgent(TWeight defaultWeightValue, TWeight defaultCapacityValue) {
        super(true);

        this.boundedWeightInfo = new DefaultBoundedWeightInfo<>(defaultWeightValue, defaultCapacityValue);
    }

    @Override
    protected boolean doAct(MetaInfoRepository metaInfoRepository, THandle handle) {
        metaInfoRepository.putMetaInfo(handle, boundedWeightInfo);
        return true;
    }

}
