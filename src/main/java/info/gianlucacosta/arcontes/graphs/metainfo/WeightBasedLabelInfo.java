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

import info.gianlucacosta.helios.metainfo.MetaInfoRepository;

/**
 * Metainfo whose "label" value is always synchronized with the "weight"
 * metainfo of a specific handle, and converted to string using
 * CommonDecimalFormat.
 */
public class WeightBasedLabelInfo<TWeight> implements LabelInfo {

    private final MetaInfoRepository metaInfoRepository;
    private final Object weightHandle;

    public WeightBasedLabelInfo(MetaInfoRepository metaInfoRepository, Object weightHandle) {
        this.metaInfoRepository = metaInfoRepository;
        this.weightHandle = weightHandle;
    }

    @Override
    public String getLabel() {
        WeightInfo<TWeight> weightInfo = metaInfoRepository.getMetaInfo(weightHandle, WeightInfo.class);
        TWeight weight = weightInfo.getWeight();

        return weight.toString();
    }

}
