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

import java.util.Objects;

/**
 * Basic implementation of WeightInfo
 */
public class DefaultWeightInfo<TWeight> implements WeightInfo<TWeight> {

    private final TWeight weight;

    public DefaultWeightInfo(TWeight weight) {
        this.weight = weight;
    }

    @Override
    public TWeight getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof WeightInfo)) {
            return false;
        }

        WeightInfo<TWeight> other = (WeightInfo<TWeight>) obj;
        return Objects.equals(weight, other.getWeight());
    }

    @Override
    public int hashCode() {
        return weight.hashCode();
    }

    @Override
    public String toString() {
        return weight.toString();
    }

}
