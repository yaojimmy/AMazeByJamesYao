package edu.wm.cs.cs301.jamesyao.gui;

import edu.wm.cs.cs301.jamesyao.generation.Maze;

public class MazeHolder {
    private static Maze maze;
    public static Maze getMaze() {return maze;}
    public static void setMaze(Maze m) {MazeHolder.maze = m;}
}
