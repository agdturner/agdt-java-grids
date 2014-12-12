/**
 * Version 1.0 is to handle single variable 2DSquareCelled raster data.
 * Copyright (C) 2005 Andy Turner, CCG, University of Leeds, UK.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA.
 */
package uk.ac.leeds.ccg.andyt.grids.core;
import uk.ac.leeds.ccg.andyt.grids.core.AbstractGrid2DSquareCell.ChunkID;
/**
 * A factory for constructing Grid2DSquareCellIntChunkMap instances.
 */
public class Grid2DSquareCellIntChunkMapFactory
        extends AbstractGrid2DSquareCellIntChunkFactory {
    
    /**
     * Creates a new Grid2DSquareCellIntChunkMapFactory.
     */
    public Grid2DSquareCellIntChunkMapFactory(){}
    
    /**
     * Create new _Grid2DSquareCellIntChunk.
     * @return 
     */
    @Override
    public AbstractGrid2DSquareCellIntChunk createGrid2DSquareCellIntChunk() {
        return new Grid2DSquareCellIntChunkMap();
    }
    
    /**
     * Create new _Grid2DSquareCellIntChunk.
     * @param _Grid2DSquareCellInt
     * @return 
     */
    @Override
    public AbstractGrid2DSquareCellIntChunk createGrid2DSquareCellIntChunk(
            Grid2DSquareCellInt _Grid2DSquareCellInt,
            ChunkID chunkID) {
        return new Grid2DSquareCellIntChunkMap(
                _Grid2DSquareCellInt,
                chunkID );
    }
    
    /**
     * Create new _Grid2DSquareCellIntChunk.
     * @param _Grid2DSquareCellIntChunk
     * @return 
     */
    @Override
    public AbstractGrid2DSquareCellIntChunk createGrid2DSquareCellIntChunk(
            AbstractGrid2DSquareCellIntChunk _Grid2DSquareCellIntChunk,
            ChunkID chunkID) {
        return new Grid2DSquareCellIntChunkMap(
                _Grid2DSquareCellIntChunk,
                chunkID );
    }
    
}
