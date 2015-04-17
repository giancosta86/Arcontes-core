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

import info.gianlucacosta.arcontes.graphs.Graph;
import info.gianlucacosta.arcontes.graphs.GraphContext;
import info.gianlucacosta.arcontes.graphs.Vertex;
import info.gianlucacosta.arcontes.graphs.metainfo.DefaultNameInfo;
import info.gianlucacosta.arcontes.graphs.metainfo.GraphContextBasedMetaInfoAgent;
import info.gianlucacosta.arcontes.graphs.metainfo.NameInfo;
import info.gianlucacosta.helios.metainfo.MetaInfoException;
import info.gianlucacosta.helios.metainfo.MetaInfoRepository;

import java.util.Objects;

/**
 * Associates a unique NameInfo metainfo to any link passed to its act() method
 * <p>
 * The name is split into two parts:
 * <ol>
 * <li>a name root, passed into the agent's constructor</li>
 * <li>an integer, ensuring that the name is unique</li>
 * </ol>
 */
public class ProgressiveVertexNameInfoAgent extends GraphContextBasedMetaInfoAgent<Vertex> {

    private final String nameRoot;

    public ProgressiveVertexNameInfoAgent(GraphContext graphContext) {
        this(graphContext, "V");
    }

    public ProgressiveVertexNameInfoAgent(GraphContext graphContext, String nameRoot) {
        super(false, graphContext);
        this.nameRoot = nameRoot;
    }

    @Override
    protected boolean doAct(MetaInfoRepository metaInfoRepository, Vertex vertex) {
        Graph graph = getGraphContext().getGraph();

        for (int vertexNumber = graph.getVertexes().size() + 1; ; vertexNumber++) {
            String vertexName = String.format("%s%d", nameRoot, vertexNumber);

            boolean nameAlreadyExisting = false;

            for (Vertex currentVertex : graph.getVertexes()) {
                String currentVertexName;

                try {
                    currentVertexName = metaInfoRepository.getMetaInfo(currentVertex, NameInfo.class).getName();
                } catch (MetaInfoException ex) {
                    continue;
                }

                if (Objects.equals(currentVertexName, vertexName)) {
                    nameAlreadyExisting = true;
                    break;
                }
            }

            if (!nameAlreadyExisting) {
                metaInfoRepository.putMetaInfo(vertex, new DefaultNameInfo(vertexName));
                break;
            }
        }

        return true;
    }

}
