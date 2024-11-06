package Core;

public class StaticUtilties
{
    public static final int [][] defaultShipShape =
           {     //X,Y
                {  15,  0 }, //Point 1
                { -10,  10}, // Point 2
                { -10, -10}, // Point 3
           };

    public static final int [][] defaultProjectileShape =
    {     //X,Y
            { 2,  2 }, //Point 1
            { -2,  2}, // Point 2
            { -2, -2}, // Point 3
            { 2, -2}, // Point 4
    };

    public static int[][][] defaultAsteroidShape =
            {
                    // Asteroid 1 - 5 vertices
                    {
                            {15, 3},
                            {5, 17},
                            {-12, 5},
                            {-8, -8},
                            {10, -17}
                    },
                    // Asteroid 2 - 6 vertices
                    {
                            {18, 7},
                            {10, 16},
                            {2, 20},
                            {-12, 10},
                            {-15, -5},
                            {5, -18}
                    },
                    // Asteroid 3 - 7 vertices
                    {
                            {12, 15},
                            {0, 20},
                            {-10, 12},
                            {-15, 0},
                            {-5, -15},
                            {8, -10},
                            {15, 0}
                    },
                    // Asteroid 4 - 8 vertices
                    {
                            {20, 5},
                            {12, 12},
                            {4, 18},
                            {-6, 18},
                            {-15, 10},
                            {-20, -5},
                            {-10, -15},
                            {8, -10}
                    },
                    // Asteroid 5 - 9 vertices
                    {
                            {25, 10},
                            {15, 18},
                            {5, 22},
                            {-5, 20},
                            {-15, 12},
                            {-25, 5},
                            {-20, -10},
                            {-10, -20},
                            {8, -15}
                    },
            };
}
