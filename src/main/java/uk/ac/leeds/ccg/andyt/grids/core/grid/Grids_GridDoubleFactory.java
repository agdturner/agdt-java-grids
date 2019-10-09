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
package uk.ac.leeds.ccg.andyt.grids.core.grid;

import uk.ac.leeds.ccg.andyt.grids.core.grid.chunk.Grids_AbstractGridChunkDoubleFactory;
import java.io.File;
import java.io.ObjectInputStream;
import uk.ac.leeds.ccg.andyt.grids.core.Grids_Dimensions;
import uk.ac.leeds.ccg.andyt.grids.core.Grids_Environment;
import uk.ac.leeds.ccg.andyt.grids.core.grid.chunk.Grids_GridChunkDoubleFactory;
import uk.ac.leeds.ccg.andyt.grids.core.grid.stats.Grids_GridDoubleStats;
import uk.ac.leeds.ccg.andyt.grids.core.grid.stats.Grids_GridDoubleStatsNotUpdated;

/**
 * A factory for constructing Grids_GridDouble instances.
 */
public class Grids_GridDoubleFactory
        extends Grids_AbstractGridFactory {

    /**
     * The NoDataValue for creating chunks.
     */
    protected double NoDataValue;

    public Grids_GridChunkDoubleFactory GridChunkDoubleFactory;
//    public Grids_GridChunkDoubleMapFactory ChunkDoubleMapFactory;
//    public Grids_GridChunkDoubleArrayFactory ChunkDoubleArrayFactory;
    public Grids_AbstractGridChunkDoubleFactory DefaultGridChunkDoubleFactory;

    public Grids_GridDoubleStats Stats;

    protected Grids_GridDoubleFactory() {
    }

    /**
     * Creates a new Grids_GridDoubleFactory.
     *
     * @param ge
     * @param gridChunkDoubleFactory
     * @param defaultGridChunkDoubleFactory
     * @param chunkNRows The number of rows chunks have by default.
     * @param chunkNCols The number of columns chunks have by default.
     */
    public Grids_GridDoubleFactory(Grids_Environment ge,
            Grids_GridChunkDoubleFactory gridChunkDoubleFactory,
            Grids_AbstractGridChunkDoubleFactory defaultGridChunkDoubleFactory,
            int chunkNRows, int chunkNCols) {
        super(ge, chunkNRows, chunkNCols, null);
        GridChunkDoubleFactory = gridChunkDoubleFactory;
        DefaultGridChunkDoubleFactory = defaultGridChunkDoubleFactory;
        Stats = new Grids_GridDoubleStatsNotUpdated(ge);
        NoDataValue = -Double.MAX_VALUE;
    }

    /**
     * Creates a new Grids_GridDoubleFactory.
     *
     * @param ge
     * @param gridChunkDoubleFactory
     * @param defaultGridChunkDoubleFactory
     * @param noDataValue
     * @param chunkNRows The number of rows chunks have by default.
     * @param chunkNCols The number of columns chunks have by default.
     * @param dimensions
     * @param stats
     */
    public Grids_GridDoubleFactory(Grids_Environment ge,
            Grids_GridChunkDoubleFactory gridChunkDoubleFactory,
            Grids_AbstractGridChunkDoubleFactory defaultGridChunkDoubleFactory,
            double noDataValue, int chunkNRows, int chunkNCols,
            Grids_Dimensions dimensions, Grids_GridDoubleStats stats) {
        super(ge, chunkNRows, chunkNCols, dimensions);
        GridChunkDoubleFactory = gridChunkDoubleFactory;
        DefaultGridChunkDoubleFactory = defaultGridChunkDoubleFactory;
        Stats = stats;
        NoDataValue = noDataValue;
    }

    /**
     * Set DefaultGridChunkDoubleFactory to cf.
     *
     * @param cf
     */
    public void setDefaultChunkFactory(
            Grids_AbstractGridChunkDoubleFactory cf) {
        DefaultGridChunkDoubleFactory = cf;
    }

    /**
     * Returns NoDataValue.
     *
     * @return
     */
    public double getNoDataValue() {
        return NoDataValue;
    }

    /**
     * Sets NoDataValue to noDataValue.
     *
     * @param noDataValue
     */
    public void setNoDataValue(double noDataValue) {
        NoDataValue = noDataValue;
    }

    /////////////////////////
    // Create from scratch //
    /////////////////////////
    /**
     * Returns A new Grids_GridDouble with all values as NoDataValues.
     *
     * @param dir The Directory to be used for storing grid.
     * @param nRows The number of rows in the grid.
     * @param nCols The number of columns in the grid.
     * @param dimensions The xmin, ymin, xmax, ymax, cellsize.
     * @return
     */
    @Override
    public Grids_GridDouble create(File dir, long nRows, long nCols,
            Grids_Dimensions dimensions) {
        return create(new Grids_GridDoubleStatsNotUpdated(env), dir,
                GridChunkDoubleFactory, nRows, nCols, dimensions);
    }

    /**
     * @param stats The type of Grids_GridDoubleStats to accompany the returned
     * grid.
     * @param dir The Directory to be used for storing grid.
     * @param cf The preferred Grids_AbstractGridChunkDoubleFactory for creating
     * chunks that the constructed Grid is to be made of.
     * @param nRows The number of rows in the grid.
     * @param nCols The number of columns in the grid.
     * @param dimensions The xmin, ymin, xmax, ymax, cellsize.
     * @return A new Grids_GridDouble grid with all values as NoDataValues.
     */
    public Grids_GridDouble create(Grids_GridDoubleStats stats, File dir,
            Grids_AbstractGridChunkDoubleFactory cf, long nRows, long nCols,
            Grids_Dimensions dimensions) {
        Grids_GridDouble result;
        result = new Grids_GridDouble(getStats(stats), dir, cf, ChunkNRows,
                ChunkNCols, nRows, nCols, dimensions, NoDataValue, env);
        return result;
    }

    //////////////////////////////////////////////////////
    // Create from an existing Grids_AbstractGridNumber //
    //////////////////////////////////////////////////////
    /**
     * @param dir The Directory to be used for storing the grid.
     * @param g The Grids_AbstractGridNumber from which values are used.
     * @param startRow The topmost row index of g.
     * @param startCol The leftmost column index of g.
     * @param endRow The bottom row index of g.
     * @param endCol The rightmost column index of g.
     * @return A new Grids_GridDouble with all values taken from g.
     */
    @Override
    public Grids_GridDouble create(File dir, Grids_AbstractGrid g,
            long startRow, long startCol, long endRow, long endCol) {
        return create(new Grids_GridDoubleStatsNotUpdated(env), dir, g,
                DefaultGridChunkDoubleFactory, startRow, startCol, endRow,
                endCol);
    }

    /**
     * @param stats The type of Grids_GridDoubleStats to accompany the returned
     * grid.
     * @param dir The directory to be used for storing the grid.
     * @param cf The preferred Grids_AbstractGridChunkDoubleFactory for creating
     * chunks that the constructed Grid is to be made of.
     * @param g The Grids_AbstractGridNumber from which grid values are used.
     * @param startRow The topmost row index of g.
     * @param startCol The leftmost column index of g.
     * @param endRow The bottom row index of g.
     * @param endCol The rightmost column index of g.
     * @return A new Grids_GridDouble with all values taken from g.
     */
    public Grids_GridDouble create(Grids_GridDoubleStats stats, File dir,
            Grids_AbstractGrid g, Grids_AbstractGridChunkDoubleFactory cf,
            long startRow, long startCol, long endRow, long endCol) {
        return new Grids_GridDouble(getStats(stats), dir, g, cf, ChunkNRows,
                ChunkNCols, startRow, startCol, endRow, endCol, NoDataValue);
    }

    ////////////////////////
    // Create from a File //
    ////////////////////////
    /**
     * @param dir The Directory to be used for storing the grid.
     * @param gridFile Either a directory, or a formatted File with a specific
     * extension containing the data and information about the grid to be
     * constructed.
     * @param startRow The topmost row index of the grid stored as gridFile.
     * @param startCol The leftmost column index of the grid stored as gridFile.
     * @param endRow The bottom row index of the grid stored as gridFile.
     * @param endCol The rightmost column index of the grid stored as gridFile.
     * @return A new Grids_GridDouble with values obtained from gridFile.
     */
    @Override
    public Grids_GridDouble create(File dir, File gridFile, long startRow,
            long startCol, long endRow, long endCol) {
        return create(new Grids_GridDoubleStatsNotUpdated(env), dir,
                gridFile, DefaultGridChunkDoubleFactory, startRow, startCol,
                endRow, endCol);
    }

    /**
     * @param stats The type of Grids_GridDoubleStats to accompany the returned
     * grid.
     * @param dir The directory to be used for storing the grid.
     * @param gridFile Either a directory, or a formatted File with a specific
     * extension containing the data and information about the grid to be
     * constructed.
     * @param cf The preferred factory for creating chunks that the constructed
     * Grid is to be made of.
     * @param startRow The topmost row index of the grid stored as gridFile.
     * @param startCol The leftmost column index of the grid stored as gridFile.
     * @param endRow The bottom row index of the grid stored as gridFile.
     * @param endCol The rightmost column index of the grid stored as gridFile.
     * @return A new Grids_GridDouble with values obtained from gridFile.
     */
    public Grids_GridDouble create(Grids_GridDoubleStats stats, File dir,
            File gridFile, Grids_AbstractGridChunkDoubleFactory cf,
            long startRow, long startCol, long endRow, long endCol) {
        return new Grids_GridDouble(getStats(stats), dir, gridFile, cf,
                ChunkNRows, ChunkNCols, startRow, startCol, endRow, endCol,
                NoDataValue, env);
    }

    /**
     * @param dir The directory to be used for storing the grid.
     * @param gridFile Either a directory, or a formatted File with a specific
     * extension containing the data and information about the grid to be
     * returned.
     * @return A new Grids_GridDouble with values obtained from gridFile.
     */
    @Override
    public Grids_GridDouble create(File dir, File gridFile) {
        return new Grids_GridDouble(env, dir, gridFile);
    }

    /////////////////////////
    // Create from a cache //
    /////////////////////////
    /**
     * @param dir The Directory to be used for storing the grid.
     * @param gridFile A file containing the data to be used in construction.
     * @param ois The ObjectInputStream to construct from.
     * @return A new Grids_GridDouble with values obtained from gridFile.
     */
    public @Override
    Grids_GridDouble create(File dir, File gridFile, ObjectInputStream ois) {
        return new Grids_GridDouble(dir, gridFile, ois, env);
    }

    /**
     * @param stats
     * @return A new Grids_GridDoubleStats of the same type for use.
     */
    private Grids_GridDoubleStats getStats(Grids_GridDoubleStats stats) {
        if (stats instanceof Grids_GridDoubleStatsNotUpdated) {
            return new Grids_GridDoubleStatsNotUpdated(env);
        } else {
            return new Grids_GridDoubleStats(env);
        }
    }
}
